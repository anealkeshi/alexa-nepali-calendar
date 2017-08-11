package me.anilkc.intent.handler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;

public interface AlexaIntentHandler {

  SpeechletResponse handle(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) throws ParseException;

  default Date getDateFromRequest(Intent intent) throws ParseException {
    Slot dateTobeConvertedSlot = intent.getSlot("dateTobeConverted");
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return dateFormat.parse(dateTobeConvertedSlot.getValue());
  }
}
