package edu.suu.codecamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.amazon.speech.ui.OutputSpeech;

/**
 * Speechlet for handling requests from the AskGithub Skill
 */

public class AskGithubSpeechlet implement SpeechletV2
{
	private static final Logger log = LoggerFactory.getLogger (AskGithubSpeechlet.class);

	@Override
	public SpeechletResponse onLaunch (SpeechletRequestEnvelope<LaunchRequest>
			requestEnvelope)
	{
		log.info("On Skill Launch:\n\trequestId={}, sessionId={}",
				requestEnvelope.getRequest().getRequestId(),
				requestEnvelope.getSession().getSessionId());

		return ; //Create a conversation to get info? TODO .. .Something....
	}

	@Override
	public SpeechletResponse onIntent (SpeechletRequestEnvelope<IntentREquest>
			requestEnvelope)
	{
		IntentRequest intentRequest = requestEnvelope.getRequest();

		log.info("OnIntent Call:\n\trequestId={}, sessionId={}",
				requestEnvelope.getRequest().getRequestId(),
				requestEnvelope.getSession().getSessionId());

		Intent intent = request.getIntent();
		String intentName = (intent != null) ? intent.getName() : null;

		// TODO: Finish this switch once know what Intents

		switch (intentName)
		{
			default:
				return ; //default response method. Cards?
		}
	}

	@Override
	public void onSessionEnded (SpeechletRequestEnvelope<SessionEndedRequest>
			requestEnvelope)
	{
			log.info("On Session Ended Call:\n\trequestId={}, sessionId={}",
				requestEnvelope.getRequest().getRequestId(),
				requestEnvelope.getSession().getSessionId());
	}

	// Start of private methods for handling specific requests
}
