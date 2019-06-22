package rtls;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OAuth {

	private static final Logger logger = LoggerFactory.getLogger(OAuth.class);

	
	public static String retrieveToken(OAuthConfig config) throws IOException, KeyManagementException, NoSuchAlgorithmException {
		return retrieveToken(config.getAuthUrl(), config.getClientId(), config.getUsername(), config.getPassword());
	}
	
	 public static String refresh(OAuthConfig config) throws IOException, KeyManagementException, NoSuchAlgorithmException {
		return refreshToken(config.getAuthUrl(), config.getClientId(), config.getUsername(), config.getrefreshToken());
	}

	/**
	 * @param grantType
	 *            either "password" or "refresh_token"
	 * @param clientId
	 *            The client needs to be registered beforehand to the system. As
	 *            the Client registration feature is not implemented yet, one
	 *            can use the RTLS GUI client id
	 *            (3039379a-119f-467c-8f94-6ea02b764b99) for the time being.
	 * @param username
	 *            name of the user
	 * @param pwdOrToken
	 *            password or refresh token depending on grant type
	 * @return json
	 *         {"access_token":"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx","refresh_token":"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx","expires_in":xxxx}.
	 *         Types are String, String, int
	 * @throws IOException
	 *             if response code is bad. The entered credentials might be
	 *             wrong.
	 * @throws KeyManagementException
	 *             if SSLContext wasn't initialized.
	 * @throws NoSuchAlgorithmException
	 *             if no Provider supports a SSLContextSpi implementation for
	 *             the specified protocol.
	 */
	public static String retrieveToken(String url, String clientId, String username,
			String password) throws IOException, KeyManagementException, NoSuchAlgorithmException {
		
		String urlParameters = "grant_type=" + "password" + "&client_id=" + clientId + "&username=" + username
				+ "&password=" + password;
		return httpPostWithParams(url, urlParameters);

	}

	public static String refreshToken(String url, String clientId, String username, String refreshToken)
			throws KeyManagementException, NoSuchAlgorithmException, IOException {
		String urlParameters = "grant_type=" + "refresh_token" + "&client_id=" + clientId + "&username=" + username
				+ "&refresh_token=" + refreshToken;
		return httpPostWithParams(url, urlParameters);

	}

	public static String httpPostWithParams(String surl, String urlParameters)
			throws IOException, NoSuchAlgorithmException, KeyManagementException {

		URL url = new URL(surl);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

		SSLContext context;
		context = NaiveSSLContext.getInstance("TLS");
		SSLContext.setDefault(context);

		con.setSSLSocketFactory(context.getSocketFactory());

		con.setHostnameVerifier(new HostnameVerifier() {

			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		});

		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		// Send
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int rspCode = con.getResponseCode();
		logger.debug("httpPostWithParams: reponse code " + rspCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}
}

