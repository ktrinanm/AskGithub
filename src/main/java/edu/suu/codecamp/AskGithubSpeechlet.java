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

public class AskGithubSpeechlet implements SpeechletV2
{
	private static final Logger log = LoggerFactory.getLogger (AskGithubSpeechlet.class);

	public static void main(String[] args)
	{
		
	}

	@Override
	public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> 
			requestEnvelope)
	{
		log.info("On Skill Launch:\n\trequestId={}, sessionId={}",
				requestEnvelope.getRequest().getRequestId(),
				requestEnvelope.getSession().getSessionId());
	}

	@Override
	public SpeechletResponse onLaunch (SpeechletRequestEnvelope<LaunchRequest>
			requestEnvelope)
	{
		log.info("On Skill Launch:\n\trequestId={}, sessionId={}",
				requestEnvelope.getRequest().getRequestId(),
				requestEnvelope.getSession().getSessionId());

		return getWelcomeResponse();
	}

	@Override
	public SpeechletResponse onIntent (SpeechletRequestEnvelope<IntentRequest>
			requestEnvelope)
	{
		IntentRequest intentRequest = requestEnvelope.getRequest();

		log.info("OnIntent Call:\n\trequestId={}, sessionId={}",
				requestEnvelope.getRequest().getRequestId(),
				requestEnvelope.getSession().getSessionId());
		//String accessToken = requestEnvelope.getSession().getUser().getAccessToken();

		Intent intent = intentRequest.getIntent();
		String intentName = (intent != null) ? intent.getName() : null;

		switch (intentName)
		{
			case "AMAZON.CancelIntent":
			{
				return SpeechletResponse.newTellResponse(
						getPlainTextOutputSpeech("Thank you."));
			}
			case "AMAZON.HelpIntent":
			{
				return getWelcomeResponse();
			}
			case "AMAZON.StopIntent":
			{
				return SpeechletResponse.newTellResponse(
						getPlainTextOutputSpeech("Thank you."));
			}
			case "listpullrequests":
			{
				GithubAPIHandler ghah = new GithubAPIHandler(
						intent.getSlot("repo_name").getValue(), 
						intent.getSlot("pull_name").getValue());
				String response = ghah.pullReqStatus();// Call List Method
				return SpeechletResponse.newTellResponse(
						getPlainTextOutputSpeech(response));
			}
			case "pullrequeststatus":
			{
				GithubAPIHandler ghah = new GithubAPIHandler(
						intent.getSlot("repo_name").getValue(), 
						intent.getSlot("pull_name").getValue());
				String response = ghah.pullReqStatus();// Call List Method
				return SpeechletResponse.newTellResponse(
						getPlainTextOutputSpeech(response));
			}
			default:
				return getWelcomeResponse(); //default response method. Cards?
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
	
	private SpeechletResponse getWelcomeResponse()
	{
		String speechText = "You can ask me for information about your git repositories.";
		return getAskResponse(speechText);
	}

	private Reprompt getReprompt(OutputSpeech outputSpeech)
	{
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(outputSpeech);

		return reprompt;
	}

	private PlainTextOutputSpeech getPlainTextOutputSpeech(String speechText)
	{
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		return speech;
	}

	private SpeechletResponse getAskResponse(String speechText)
	{
		PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);
		Reprompt reprompt = getReprompt(speech);

		return SpeechletResponse.newAskResponse(speech, reprompt);
	}
}
