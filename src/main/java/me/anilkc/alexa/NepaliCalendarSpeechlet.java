package me.anilkc.alexa;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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


    // TODO validate date range


    Intent intent = intentRequest.getIntent();
    Slot dateTobeConvertedSlot = intent.getSlot("dateTobeConverted");
    StringBuilder speechText = new StringBuilder();
    SpeechletResponse speechletResponse = new SpeechletResponse();

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date dateTobeConverted;
    try {
      LOG.info("dateTobeConverted = {}", dateTobeConvertedSlot.getValue());
      dateTobeConverted = dateFormat.parse(dateTobeConvertedSlot.getValue());
    } catch (ParseException e) {
      speechText.append("I don't understand. ");
      speechletResponse = AlexaUtil.getSpeechletResponse("Sorry!!!", speechText.toString(), true);
      return speechletResponse;
    }


    if ("ConvertEnglishToNepaliDateIntent".equals(intent.getName())) {

      speechText.append(AlexaDateUtil.getFormattedDateInEnglish(dateTobeConverted));
      speechText.append(" is ");
      speechText.append(AlexaDateUtil.getFormattedDateInNepali(NepaliDateConverter.convertEnglishToNepalDate(dateTobeConverted)));
      speechText.append(" in Bikram Sumbut.");
      speechletResponse = AlexaUtil.getSpeechletResponse("English to Nepali Date", speechText.toString(), true);

    } else if ("ConvertNepaliToEnglishDateIntent".equals(intent.getName())) {

      speechText.append(AlexaDateUtil.getFormattedDateInNepali(dateTobeConverted));
      speechText.append(" is ");
      speechText.append(AlexaDateUtil.getFormattedDateInEnglish(NepaliDateConverter.convertNepaliToEnglishDate(dateTobeConverted)));
      speechText.append(" in Anno Domini.");
      speechletResponse = AlexaUtil.getSpeechletResponse("Nepali to English Date", speechText.toString(), true);

    } else if ("AskNepaliDateIntent".equals(intent.getName())) {

      speechText.append("It is ");
      speechText.append(AlexaDateUtil.getFormattedDateInNepali(NepaliDateConverter.convertEnglishToNepalDate(dateTobeConverted)));
      speechText.append(" in Bikram Sumbut.");
      speechletResponse = AlexaUtil.getSpeechletResponse("Nepali Date", speechText.toString(), true);

    } else {
      speechText.append("Something unexpected happened. Try back later.");
    }

    return speechletResponse;
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
