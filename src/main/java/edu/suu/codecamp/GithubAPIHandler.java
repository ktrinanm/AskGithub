package edu.suu.codecamp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.json.*;
//import org.eclipse.egit.github.core.client.GitHubClient;

/**
 * Hello world!
 *
 */
public class GithubAPIHandler
{
    static String oAuthToken = new String("sample_access_token");
    String userName;
    String repoName;
    String pullReqName;
    
    public GithubAPIHandler( String repName,String pullReq)
    {
        repoName = repName;
        pullReqName = pullReq;
        
        JsonObject json = (JsonObject)HttpRequestProcessor("https://api.github.com/user", oAuthToken, "GET");
        
        userName = json.getString("login");
        
    }
    
    
    public String pullReqStatus()
    {
        JsonArray output = (JsonArray)HttpRequestProcessor("https://api.github.com/repos/"+ userName + "/"+ repoName +"/pulls?state=all", oAuthToken,"GET");
        
        String [] pullreqStr = pullReqName.split(" ");
        String tempUrl = "";
        String response = "There are";
        for(int i = 0; i < output.size(); i++)
        {
            JsonObject object = output.getJsonObject(i);
            for(String str: pullreqStr)
            {
                if(object.getString("title").contains(str))
                {
                    tempUrl = object.getString("review_comments_url");
                    tempUrl = tempUrl.replace("/comments", "/reviews");
                   
                    
                    JsonArray reviews = (JsonArray)HttpRequestProcessor(tempUrl, oAuthToken, "GET");
                    response += " " +reviews.size() + " reviews total.";
                    int numApproved = 0, numPending = 0, numChangesReq = 0, numCommented = 0;
                    for(int j =0; j< reviews.size(); j++)
                    {
                        JsonObject tempJson = reviews.getJsonObject(j);
                        switch(tempJson.getString("state"))
                        {
                            case "APPROVED":
                                numApproved++;
                                break;
                            case "PENDING":
                                numPending++;
                                break;
                            case "COMMENTED":
                                numCommented++;
                                break;
                            case "CHANGES_REQUESTED":
                                numChangesReq++;
                                break;     
                                
                        }
                        
                    }
                    response += " " + numApproved + " were Approved. " + numPending + " were still Pending. "
                        + numChangesReq + " had changes requested. " + numCommented + " were commented on.";

                    return response;    
                }
                    
        }
		}
        return "No reviews were able to be found.";
    
	}
    
    
    public static JsonStructure HttpRequestProcessor(String sUrl, String oAuth, String method)
    {
		try
		{
			URL url = new URL(sUrl);
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod(method);
			httpConnection.setRequestProperty("Authorization", "token "+ oAuth);
			
			BufferedReader input = new BufferedReader(new 
					InputStreamReader(httpConnection.getInputStream()));
            
            JsonReader jsonReader = Json.createReader(input);
            JsonStructure jstruct = jsonReader.read();
            jsonReader.close();
			/*String inLine;
			StringBuffer response = new StringBuffer();

			while((inLine = input.readLine()) != null)
			{
				response.append(inLine);
			}

			input.close();

			System.out.println( response.toString());*/
            
            return jstruct;
            
		}
		catch(Exception e)
		{
			System.out.println("Uh oh! Something went wrong!!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
        return null;
    }
}
