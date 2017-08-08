package me.anilkc.alexa;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public final class NepaliCalendarSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {

  private static final Set<String> supportedApplicationIds = new HashSet<String>();

  static {
    supportedApplicationIds.add(System.getenv("NEPALI_CALENDAR_APP_ID"));
  }

  public NepaliCalendarSpeechletRequestStreamHandler() {
    super(new NepaliCalendarSpeechlet(), supportedApplicationIds);
  }
}
