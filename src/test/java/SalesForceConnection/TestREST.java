package SalesForceConnection;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.sforce.async.SObject;

public class TestREST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String USERNAME = "dgururaj@hadronorg.com";
		final String PASSWORD = "Delta2907GcEZ2eFywzceXMJ9ZDSM7SFG";
		final String LOGINURL = "https://login.salesforce.com";
		final String GRANTSERVICE = "/services/oauth2/token?grant_type=password";

		// YOUR OAUTH CONSUMER KEY
		String CLIENTID = "3MVG9n_HvETGhr3D4vijgV5yl0kiTO..2po_spRD.GpO9rkmM5fix4GV1uAFYXBHexbIBiXx33HVGXg4K3EnT";
		// YOUR OAUTH CONSUMER SECRET
		String CLIENTSECRET = "783FFB9264B165A4FFD12FEE05AB3314DD9639024868A1C3B9854E7D63AD61DF";
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String loginURL = LOGINURL + GRANTSERVICE + "&client_id=" + CLIENTID + "&client_secret=" + CLIENTSECRET
				+ "&username=" + USERNAME + "&password=" + PASSWORD;
		System.out.println(loginURL);
		// Login requests must be POSTs
		HttpPost httpPost = new HttpPost(loginURL);
		HttpResponse response = null;
		try {
			// Execute the login POST request
			response = httpclient.execute(httpPost);
		} catch (ClientProtocolException cpException) {
			// Handle protocol exception
		} catch (IOException ioException) {
			// Handle system IO exception
		}

		// verify response is HTTP OK
		final int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		
		if (statusCode != HttpStatus.SC_OK) {
			System.out.println("Error authenticating to Force.com:" + statusCode);
			// Error is in EntityUtils.toString(response.getEntity())
			return;
		}
		String getResult = null;
		try {
			getResult = EntityUtils.toString(response.getEntity());
			System.out.println("getResult::"+getResult);
		} catch (IOException ioException) {
			// Handle system IO exception
		}
		JSONObject jsonObject = null;
		String loginAccessToken = null;
		String loginInstanceUrl = null;
		String id = null;
		try {
			jsonObject = (JSONObject) new JSONTokener(getResult).nextValue();
			loginAccessToken = jsonObject.getString("access_token");
			loginInstanceUrl = jsonObject.getString("instance_url");
			id=jsonObject.getString("id");
		} catch (JSONException jsonException) {
			// Handle JSON exception
		}
		System.out.println(response.getStatusLine());
		System.out.println("Successful login");

		System.out.println(" instance URL: " + loginInstanceUrl);
		System.out.println(" access token/session ID:" + loginAccessToken);
		System.out.println("ID:" + id);
		// release connection
		
		
		
		//httpPost.releaseConnection();
		
	
		
		
		
		
		
		

	}

}
