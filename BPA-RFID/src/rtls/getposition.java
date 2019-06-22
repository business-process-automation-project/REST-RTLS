package rtls;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import org.json.*;





public class getposition {
	
	
	public static String Feld14 (String[] args) throws KeyManagementException, NoSuchAlgorithmException, JSONException, IOException {

		JSONObject obj = new JSONObject(OAuth.retrieveToken(rtls.OAuthConfig.getAuthUrl(), rtls.OAuthConfig.getClientId(), rtls.OAuthConfig.getUsername(), rtls.OAuthConfig.getPassword()));
		String Token = obj.getString("access_token");
		String surl = ("https://192.168.130.2:8083/rest/geofencing/areas/14/devices?access_token=" + Token);			 
			
		  return(geturl.getURl(surl));


}


	
	public static String Feld15 (String[] args) throws KeyManagementException, NoSuchAlgorithmException, JSONException, IOException {
				JSONObject obj = new JSONObject(OAuth.retrieveToken(rtls.OAuthConfig.getAuthUrl(), rtls.OAuthConfig.getClientId(), rtls.OAuthConfig.getUsername(), rtls.OAuthConfig.getPassword()));
				String Token = obj.getString("access_token");
				String surl = ("https://192.168.130.2:8083/rest/geofencing/areas/15/devices?access_token=" + Token);			 
	return(geturl.getURl(surl));
}

	
	public static String Feld16 (String[] args) throws KeyManagementException, NoSuchAlgorithmException, JSONException, IOException {

		
		
		JSONObject obj = new JSONObject(OAuth.retrieveToken(rtls.OAuthConfig.getAuthUrl(), rtls.OAuthConfig.getClientId(), rtls.OAuthConfig.getUsername(), rtls.OAuthConfig.getPassword()));
		String Token = obj.getString("access_token");
		String surl = ("https://192.168.130.2:8083/rest/geofencing/areas/16/devices?access_token=" + Token);			 
		 
		  return(geturl.getURl(surl));


}
	

}