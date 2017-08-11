package me.anilkc.alexa;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

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

import me.anilkc.intent.handler.AlexaIntentHandler;
import me.anilkc.intent.handler.AskNepaliDateIntent;
import me.anilkc.intent.handler.CancelIntent;
import me.anilkc.intent.handler.ConvertEnglishToNepaliDateIntent;
import me.anilkc.intent.handler.ConvertNepaliToEnglishDateIntent;
import me.anilkc.intent.handler.HelpIntent;
import me.anilkc.intent.handler.StopIntent;

public class NepaliCalendarSpeechlet implements SpeechletV2 {

  private static final Logger LOG = LoggerFactory.getLogger(NepaliCalendarSpeechlet.class);

  public static final Map<String, AlexaIntentHandler> ALEXA_INTENT_HANDLER_DISPATCHER = new HashMap<>();
  public static final Map<String, AlexaIntentHandler> ALEXA_AMAZON_BUILT_IN_INTENT = new HashMap<>();

  static {
    ALEXA_AMAZON_BUILT_IN_INTENT.put("AMAZON.CancelIntent", new CancelIntent());
    ALEXA_AMAZON_BUILT_IN_INTENT.put("AMAZON.StopIntent", new StopIntent());
    ALEXA_AMAZON_BUILT_IN_INTENT.put("AMAZON.HelpIntent", new HelpIntent());

    ALEXA_INTENT_HANDLER_DISPATCHER.put("ConvertNepaliToEnglishDateIntent", new ConvertNepaliToEnglishDateIntent());
    ALEXA_INTENT_HANDLER_DISPATCHER.put("ConvertEnglishToNepaliDateIntent", new ConvertEnglishToNepaliDateIntent());
    ALEXA_INTENT_HANDLER_DISPATCHER.put("AskNepaliDateIntent", new AskNepaliDateIntent());
  }


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

    try {

      if (ALEXA_AMAZON_BUILT_IN_INTENT.containsKey(intent.getName())) {
        return ALEXA_AMAZON_BUILT_IN_INTENT.get(intent.getName()).handle(requestEnvelope);
      }

      // Need date beyond this point
      Slot dateTobeConvertedSlot = intent.getSlot("dateTobeConverted");
      if (dateTobeConvertedSlot == null || StringUtils.isEmpty(dateTobeConvertedSlot.getValue())) {
        return AlexaUtil.getSpeechletResponse("Sorry!!!", "I don't understand.", true);
      }

      LOG.info("dateTobeConverted = {}", dateTobeConvertedSlot.getValue());
      return ALEXA_INTENT_HANDLER_DISPATCHER.get(intent.getName()).handle(requestEnvelope);

    } catch (ParseException e) {
      LOG.error("Exception while parsing date: ", e);
      return AlexaUtil.getSpeechletResponse("Sorry!!!", "I don't understand.", true);
    }
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
