package me.anilkc.alexa;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.SpeechletV2;

public class NepaliCalendarSpeechlet implements SpeechletV2 {


  private static final String ASK_NEPALI_DATE_INTENT = "AskNepaliDateIntent";
  private static final String CONVERT_NEPALI_TO_ENGLISH_DATE_INTENT = "ConvertNepaliToEnglishDateIntent";
  private static final String CONVERT_ENGLISH_TO_NEPALI_DATE_INTENT = "ConvertEnglishToNepaliDateIntent";
  private static final Logger LOG = LoggerFactory.getLogger(NepaliCalendarSpeechlet.class);


  public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
    LOG.info("onSessionStarted requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(),
        requestEnvelope.getSession().getSessionId());
  }

  public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
    LOG.info("onLaunch requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(),
        requestEnvelope.getSession().getSessionId());
    return getWelcomeResponse();
  }

  public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
    IntentRequest intentRequest = requestEnvelope.getRequest();
    Session session = requestEnvelope.getSession();
    LOG.info("onIntent requestId={}, sessionId={}, intent={}, slots={}", intentRequest.getRequestId(), session.getSessionId(),
        intentRequest.getIntent().getName(), intentRequest.getIntent().getSlots());

    Intent intent = intentRequest.getIntent();
    Slot dateTobeConvertedSlot = intent.getSlot("dateTobeConverted");
    StringBuilder speechText = new StringBuilder();
    SpeechletResponse speechletResponse = new SpeechletResponse();

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date dateTobeConverted;
    try {
      LOG.info("dateTobeConverted = {}", dateTobeConvertedSlot.getValue());
      dateTobeConverted = dateFormat.parse(dateTobeConvertedSlot.getValue());
      if (validateDate(dateTobeConverted, speechText, intent.getName()).length() > 0) {
        return AlexaUtil.getSpeechletResponse("Can't handle dates", speechText.toString(), true);
      }
    } catch (ParseException e) {
      speechText.append("I don't understand. ");
      speechletResponse = AlexaUtil.getSpeechletResponse("Sorry!!!", speechText.toString(), true);
      return speechletResponse;
    }


    if (CONVERT_ENGLISH_TO_NEPALI_DATE_INTENT.equals(intent.getName())) {

      speechText.append(AlexaDateUtil.getFormattedDateInEnglish(dateTobeConverted));
      speechText.append(" is ");
      speechText.append(AlexaDateUtil.getFormattedDateInNepali(NepaliDateConverter.convertEnglishToNepaliDate(dateTobeConverted)));
      speechText.append(" in Bikram Sumbut.");
      speechletResponse = AlexaUtil.getSpeechletResponse("English to Nepali Date", speechText.toString(), true);

    } else if (CONVERT_NEPALI_TO_ENGLISH_DATE_INTENT.equals(intent.getName())) {

      // Get Nepali Date
      NepaliDate nepaliDate = NepaliDateConverter.convertEnglishToNepaliDate(dateTobeConverted);
      speechText.append(AlexaDateUtil.getFormattedDateInNepali(nepaliDate));
      speechText.append(" is ");
      // Convert same nepali date to English date
      Calendar cal = Calendar.getInstance();
      cal.set(nepaliDate.getYear(), nepaliDate.getMonth(), nepaliDate.getDayOfMonth());
      speechText.append(AlexaDateUtil.getFormattedDateInEnglish(NepaliDateConverter.convertNepaliToEnglishDate(cal.getTime())));
      speechText.append(" in Anno Domini.");
      speechletResponse = AlexaUtil.getSpeechletResponse("Nepali to English Date", speechText.toString(), true);

    } else if (ASK_NEPALI_DATE_INTENT.equals(intent.getName())) {

      speechText.append("It is ");
      speechText.append(AlexaDateUtil.getFormattedDateInNepali(NepaliDateConverter.convertEnglishToNepaliDate(dateTobeConverted)));
      speechText.append(" in Bikram Sumbut.");
      speechletResponse = AlexaUtil.getSpeechletResponse("Nepali Date", speechText.toString(), true);

    } else {
      speechText.append("Something unexpected happened. Try back later.");
    }

    return speechletResponse;
  }

  private StringBuilder validateDate(Date dateTobeConverted, StringBuilder speechText, String intent) {

    LocalDate date = AlexaDateUtil.getLocalDateFromDate(dateTobeConverted);
    LocalDate baseDate;
    LocalDate maxDate;

    if (StringUtils.equals(CONVERT_ENGLISH_TO_NEPALI_DATE_INTENT, intent) || StringUtils.equals(ASK_NEPALI_DATE_INTENT, intent)) {

      baseDate = NepaliDateConverter.getBaseEnglishDate();
      maxDate = NepaliDateConverter.getMaxEnglishDate();
      if (date.isBefore(baseDate) || date.isAfter(maxDate)) {
        baseDate = NepaliDateConverter.getBaseEnglishDate();
        maxDate = NepaliDateConverter.getMaxEnglishDate();
        speechText.append("Sorry. I can only handle dates between ");
        speechText.append(AlexaDateUtil.getFormattedDateInEnglish(AlexaDateUtil.getDateFromLocalDate(baseDate)));
        speechText.append(" and ");
        speechText.append(AlexaDateUtil.getFormattedDateInEnglish(AlexaDateUtil.getDateFromLocalDate(maxDate)));
      }

    } else if (StringUtils.equals(CONVERT_NEPALI_TO_ENGLISH_DATE_INTENT, intent)) {

      baseDate = NepaliDateConverter.getBaseNepaliDate();
      maxDate = NepaliDateConverter.getMaxNepaliDate();
      if (date.isBefore(baseDate) || date.isAfter(maxDate)) {
        speechText.append("Sorry. I can only handle dates between ");
        speechText.append(AlexaDateUtil.getFormattedDateInNepali(AlexaDateUtil.getNepaliDateFromLocalDate(baseDate)));
        speechText.append(" and ");
        speechText.append(AlexaDateUtil.getFormattedDateInNepali(AlexaDateUtil.getNepaliDateFromLocalDate(maxDate)));
      }

    }
    return speechText;
  }

  @Override
  public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
    LOG.info("onSessionEnd requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(),
        requestEnvelope.getSession().getSessionId());
  }

  private SpeechletResponse getWelcomeResponse() {
    String speechText = "You can ask for Nepali date or convert english date to nepali date or vice versa.";
    return AlexaUtil.getSpeechletResponse("Welcome", speechText, false);
  }
}
