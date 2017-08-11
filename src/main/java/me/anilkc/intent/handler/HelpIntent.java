package me.anilkc.intent.handler;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;

import me.anilkc.alexa.AlexaUtil;

public class HelpIntent implements AlexaIntentHandler {

  @Override
  public SpeechletResponse handle(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {

    StringBuilder speechText = new StringBuilder();
    speechText.append("Nepali Calendar can responds back Nepali Date. ");
    speechText.append("You can ask to convert English (Anno Domini) Date to Nepali Date (Bikram Sambat) and vice versa.");

    return AlexaUtil.getSpeechletResponse("Help", speechText.toString(), false);
  }

}
