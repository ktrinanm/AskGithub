package edu.suu.codecamp17

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
	private static final Set<String> supportApplicationIds = new HashSet<String>();
	static 
	{
		supportedApplicationIds.add("[]");
	}

	public AskGithubSpeechletRequestStreamHandler() 
	{
		super(new AskGithubSpeechlet(), supportedApplicationIds);
	}
}
