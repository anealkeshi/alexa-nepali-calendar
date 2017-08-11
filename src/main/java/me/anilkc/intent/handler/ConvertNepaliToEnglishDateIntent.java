package me.anilkc.intent.handler;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;

import me.anilkc.alexa.AlexaDateUtil;
import me.anilkc.alexa.AlexaUtil;
import me.anilkc.alexa.NepaliDate;
import me.anilkc.alexa.NepaliDateConverter;


public class ConvertNepaliToEnglishDateIntent implements AlexaIntentHandler {

  private static final Logger LOG = LoggerFactory.getLogger(ConvertNepaliToEnglishDateIntent.class);

  @Override
  public SpeechletResponse handle(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) throws ParseException {

    StringBuilder speechText = new StringBuilder();
    Date dateTobeConverted = getDateFromRequest(requestEnvelope.getRequest().getIntent());

    LocalDate date = AlexaDateUtil.getLocalDateFromDate(dateTobeConverted);
    LocalDate baseDate = NepaliDateConverter.getBaseEnglishDate();
    LocalDate maxDate = NepaliDateConverter.getMaxEnglishDate();

    if (date.isBefore(baseDate) || date.isAfter(maxDate)) {
      speechText.append("Sorry. I can only handle dates between ");
      speechText.append(AlexaDateUtil.getFormattedDateInNepali(AlexaDateUtil.getNepaliDateFromLocalDate(baseDate)));
      speechText.append(" and ");
      speechText.append(AlexaDateUtil.getFormattedDateInNepali(AlexaDateUtil.getNepaliDateFromLocalDate(maxDate)));
    } else {
      // Get Nepali Date
      NepaliDate nepaliDate = NepaliDateConverter.convertEnglishToNepaliDate(dateTobeConverted);
      speechText.append(AlexaDateUtil.getFormattedDateInNepali(nepaliDate));
      speechText.append(" is ");
      // Convert same nepali date to English date
      Calendar cal = Calendar.getInstance();
      cal.set(nepaliDate.getYear(), nepaliDate.getMonth(), nepaliDate.getDayOfMonth());
      speechText.append(AlexaDateUtil.getFormattedDateInEnglish(NepaliDateConverter.convertNepaliToEnglishDate(cal.getTime())));
      speechText.append(" in Anno Domini.");
    }


    return AlexaUtil.getSpeechletResponse("Nepali to English Date", speechText.toString(), true);
  }
}
