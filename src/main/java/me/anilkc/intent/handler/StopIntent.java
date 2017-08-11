package me.anilkc.intent.handler;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;

import me.anilkc.alexa.AlexaUtil;


public class StopIntent implements AlexaIntentHandler {

  @Override
  public SpeechletResponse handle(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
    return AlexaUtil.getSpeechletResponse("Bye Nepali Calendar", "Thank you for using Nepali Calendar", true);
  }
}
