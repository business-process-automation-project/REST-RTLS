package rtls;

public class OAuthConfig
{
  private static String GrantType = "password";
  private static String AuthUrl = "https://192.168.130.2:8083/rest/oauth/authorization/unauth-token";
  private static String ClientId ="3039379a-119f-467c-8f94-6ea02b764b99";
  private static String Username ="iiotlab";
  private static String Password ="gbijhg3q4aro";
  private static String refreshToken = null;
  
  
  

  public OAuthConfig(String granttype,String url, String id, String username, String pass, String token)
  {
	OAuthConfig.GrantType = granttype;
    OAuthConfig.AuthUrl = url;
    OAuthConfig.ClientId = id;
    OAuthConfig.Username = username;
    OAuthConfig.Password = pass;
    OAuthConfig.refreshToken = token;
   
  }
  public String getGrantType() {return GrantType;}
  public String getrefreshToken() {
	  return refreshToken;
  }
  public static String getAuthUrl()
  {
    return AuthUrl;
  }

  public static String getClientId()
  {
    return ClientId;
  }

  public static String getUsername()
  {
    return Username;
  }

  public static String getPassword()
  {
    return Password;
  }

 

  
  
}
