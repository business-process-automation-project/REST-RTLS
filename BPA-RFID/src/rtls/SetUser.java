package rtls;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import org.json.*;





public class SetUser {

	
	public static String Set (String payload) throws KeyManagementException, NoSuchAlgorithmException, JSONException, IOException {
		JSONObject obj = new JSONObject(OAuth.retrieveToken(rtls.OAuthConfig.getAuthUrl(), rtls.OAuthConfig.getClientId(), rtls.OAuthConfig.getUsername(), rtls.OAuthConfig.getPassword()));
		System.out.println(obj);
		String Token = obj.getString("access_token");
						
		String Test = rtls.getposition.Feld17(null);
		 String Test1 = Test.substring(1);
		System.out.println(Test);
		JSONObject obj2 = new JSONObject(Test1);
		String ID = obj2.getString("address");
		String BadgeID = ID;
						//String Adresse = Badgeleer.getString("address");
			 
			 
			 
			 String surl = ("https://192.168.130.2:8083/rest/devices/" + BadgeID + "/display/resources/?refresh=true&access_token=" + Token);			 
			 rtls.puturl.putURl(surl, payload);
				  return(BadgeID);
			 
			  
		
	
	}

}