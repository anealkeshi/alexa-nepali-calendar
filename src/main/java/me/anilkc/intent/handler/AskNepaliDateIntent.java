package me.anilkc.intent.handler;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;

import me.anilkc.alexa.AlexaDateUtil;
import me.anilkc.alexa.AlexaUtil;
import me.anilkc.alexa.NepaliDateConverter;


public class AskNepaliDateIntent implements AlexaIntentHandler {

  private static final Logger LOG = LoggerFactory.getLogger(AskNepaliDateIntent.class);

  @Override
  public SpeechletResponse handle(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) throws ParseException {

    StringBuilder speechText = new StringBuilder();
    Date dateTobeConverted = getDateFromRequest(requestEnvelope.getRequest().getIntent());
    LocalDate date = AlexaDateUtil.getLocalDateFromDate(dateTobeConverted);
    LocalDate baseDate = NepaliDateConverter.getBaseEnglishDate();
    LocalDate maxDate = NepaliDateConverter.getMaxEnglishDate();

    if (date.isBefore(baseDate) || date.isAfter(maxDate)) {
      baseDate = NepaliDateConverter.getBaseEnglishDate();
      maxDate = NepaliDateConverter.getMaxEnglishDate();
      speechText.append("Sorry. I can only handle dates between ");
      speechText.append(AlexaDateUtil.getFormattedDateInEnglish(AlexaDateUtil.getDateFromLocalDate(baseDate)));
      speechText.append(" and ");
      speechText.append(AlexaDateUtil.getFormattedDateInEnglish(AlexaDateUtil.getDateFromLocalDate(maxDate)));
    } else {
      speechText.append("It is ");
      speechText.append(AlexaDateUtil.getFormattedDateInNepali(NepaliDateConverter.convertEnglishToNepaliDate(dateTobeConverted)));
      speechText.append(" in Bikram Sumbut.");
    }

    return AlexaUtil.getSpeechletResponse("Nepali Date", speechText.toString(), true);
  }
}
