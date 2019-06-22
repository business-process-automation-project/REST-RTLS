package rtls;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import org.json.*;





public class SetUser {

	
	public static String Test (String payload) throws KeyManagementException, NoSuchAlgorithmException, JSONException, IOException {
		JSONObject obj = new JSONObject(OAuth.retrieveToken(rtls.OAuthConfig.getAuthUrl(), rtls.OAuthConfig.getClientId(), rtls.OAuthConfig.getUsername(), rtls.OAuthConfig.getPassword()));
		String Token = obj.getString("access_token");
			 
				  
				// GET leere BAdge
			  
			  String BadgeID = "8121069331292357663";
				  
			  String surl = ("https://192.168.130.2:8083/rest/devices/8121069331292357663/display/resources/?refresh=true&access_token=" + Token);			 
			  rtls.puturl.putURl(surl, payload);
				  return(BadgeID);
			 
			  
		
	
	}

}