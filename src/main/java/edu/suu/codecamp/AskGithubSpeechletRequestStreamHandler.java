package edu.suu.codecamp;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

/*
 *  The handler for the AskGithub skill.
 *
 *  extends SpeechletRequestStreamHandler
 *
 */

public final class AskGithubSpeechletRequestStreamHandler extends
	SpeechletRequestStreamHandler 
{
	private static final Set<String> supportedApplicationIds = new HashSet<String>();
	static 
	{
		supportedApplicationIds.add("amzn1.ask.skill.b13b6a1c-b650-4949-9b9f-a79222f35191");
	}

	public AskGithubSpeechletRequestStreamHandler() 
	{
		super(new AskGithubSpeechlet(), supportedApplicationIds);
	}
}
