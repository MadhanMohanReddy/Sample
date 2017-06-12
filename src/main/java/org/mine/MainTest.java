package org.mine;

import com.google.gson.JsonArray;
import com.rallydev.rest.request.Request;
import org.apache.log4j.Logger;

import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.GetRequest;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.request.UpdateRequest;
import com.rallydev.rest.response.GetResponse;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.response.UpdateResponse;
import com.rallydev.rest.util.Fetch;
import com.rallydev.rest.util.QueryFilter;
import com.rallydev.rest.util.Ref;
import com.rallydev.rest.client.HttpClient;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.scheme.Scheme;

public class MainTest {
	final static Logger logger = Logger.getLogger(MainTest.class);
	public static void main(String[] args) {
		logger.info("Main called...");
		try{
			//System.out.println("testing.." + TestA.testA());
			testRally();
			System.out.println("testing..Rally.." + testRally());
		} catch(Exception e) {
			System.out.println("Error occured");
			logger.error(e.getMessage(), e);
		}
	}

	public static String testRally() {
		String rallyURL = "https://rally1.rallydev.com";
		String applicationName = "RallyRestApi";
		//String apiKey = "_OsgoO1OVRTeAnjm5oaMhC2VkwQXkno7GY7TEdqOw";
		String apiKey = "abcd";
		String userName = "madhan.reddy@tavant.com";
		String userPassword = "Welcome123";

		StringBuilder html = new StringBuilder("");
		RallyRestApi restApi = null;
		try {
			restApi = new RallyRestApi(new URI(rallyURL),apiKey);
			restApi.setApplicationName(applicationName);
			restApi.setProxy(new URI("http://rally1.rallydev.com"), userName, userPassword);
			HttpClient client = restApi.getClient();

			SSLSocketFactory sf = new SSLSocketFactory(new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] certificate, String authType)
					throws CertificateException {
					//trust all certs
					return true;
				}
			}, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			client.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, sf));

			QueryRequest defects = new QueryRequest("defect");

			defects.setFetch(new Fetch("FormattedID", "Name", "State", "Priority"));
			defects.setQueryFilter(new QueryFilter("State", "<", "Fixed"));
			defects.setOrder("Priority ASC,FormattedID ASC");

			//Return up to 5, 1 per page
			defects.setPageSize(1);
			defects.setLimit(6);

			QueryResponse queryResponse = restApi.query(defects);

			if (queryResponse.wasSuccessful()) {
				System.out.println(String.format("\nTotal results: %d", queryResponse.getTotalResultCount()));
				html.append("<h1>Total results:</h1>"+queryResponse.getTotalResultCount());
				System.out.println("Top 6:");
				html.append("<h1>Top 6:</h1>");
				html.append("<table><tr><th>FormattedID</th><th>Name</th><th>Priority</th><th>State</th></tr></table>");
				for (JsonElement result : queryResponse.getResults()) {
					JsonObject defect = result.getAsJsonObject();
					System.out.println(String.format("\tFormattedID=%s, Name=%s: Priority=%s, State=%s",
						defect.get("FormattedID").getAsString(),
						defect.get("Name").getAsString(),
						defect.get("Priority").getAsString(),
						defect.get("State").getAsString()));
					html.append("<tr>" + "<td>" + defect.get("FormattedID").getAsString() + "</td>"
															+ "<td>" + defect.get("Name").getAsString() + "</td>"
															+ "<td>" + defect.get("Priority").getAsString() + "</td>"
															+ "<td>" + defect.get("State").getAsString() + "</td>"
											+ "</tr>");
				}
			} else {
				System.err.println("The following errors occurred: ");
				html.append("<h1>The following errors occurred: </h1>");
				for (String err : queryResponse.getErrors()) {
					System.err.println("\t" + err);
					html.append(err);
				}

			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
			html.append(e);
		} catch (IOException e) {
			e.printStackTrace();
			html.append(e);
		} catch (Exception e) {
			e.printStackTrace();
			html.append(e);
		}
		return html.toString();
	}

}
