package edu.suu.codecamp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import org.eclipse.egit.github.core.client.GitHubClient;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		//GitHubClient client = new GitHubClient();

		try
		{
			URL url = new URL("https://api.github.com/repos/ktrinanm/2450/pulls/2");
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Authorization", "token d5b2873239e6431778b886eaa6a6e7f4c10f35ef");
			
			BufferedReader input = new BufferedReader(new 
					InputStreamReader(httpConnection.getInputStream()));
			String inLine;
			StringBuffer response = new StringBuffer();

			while((inLine = input.readLine()) != null)
			{
				response.append(inLine);
			}

			input.close();

			System.out.println( response.toString());
		}
		catch(Exception e)
		{
			System.out.println("Uh oh! Something went wrong!!");
			System.out.println(e.getMessage());
		}
    }
}
