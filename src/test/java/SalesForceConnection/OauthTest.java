package SalesForceConnection;

import io.restassured.RestAssured;
import io.restassured.authentication.OAuthSignature;
import io.restassured.http.Header;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.json.JSONTokener;

public class OauthTest {

	public static void main(String[] args) {

		final String USERNAME = "dgururaj@hadronorg.com";
		final String PASSWORD = "Delta2907GcEZ2eFywzceXMJ9ZDSM7SFG";
		final String LOGINURL = "https://login.salesforce.com";
		final String GRANTSERVICE = "/services/oauth2/token?grant_type=password";
		// YOUR OAUTH CONSUMER KEY
		String CLIENTID = "3MVG9n_HvETGhr3D4vijgV5yl0kiTO..2po_spRD.GpO9rkmM5fix4GV1uAFYXBHexbIBiXx33HVGXg4K3EnT";
		// YOUR OAUTH CONSUMER SECRET
		String CLIENTSECRET = "783FFB9264B165A4FFD12FEE05AB3314DD9639024868A1C3B9854E7D63AD61DF";
		String URL = "https://ap16.salesforce.com/services/data/v48.0/query?q=select Name,Phone from Account";
		String AccessToken;
		String loginURL = LOGINURL + GRANTSERVICE + "&client_id=" + CLIENTID + "&client_secret=" + CLIENTSECRET
				+ "&username=" + USERNAME + "&password=" + PASSWORD;

		Response response = given().post(loginURL);
		System.out.println(response.asString());
		JSONObject jsonObject = null;
		jsonObject = (JSONObject) new JSONTokener(response.asString()).nextValue();
		AccessToken = jsonObject.getString("access_token");
		Header someHeader = new Header("Authorization", "Bearer " + AccessToken);
		System.out.println(given().header(someHeader).when().get(URL).statusCode());

		response = given().header(someHeader).when().get(URL);
		System.out.println(response.asString());

	}

}
