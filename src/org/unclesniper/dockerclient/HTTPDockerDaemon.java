package org.unclesniper.dockerclient;

import java.net.URL;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import org.unclesniper.json.JSONSink;
import org.unclesniper.json.JSONParser;
import org.unclesniper.json.JSONPrinter;
import org.unclesniper.json.MalformedJSONException;

public class HTTPDockerDaemon implements DockerDaemon {

	private String baseURL;

	public HTTPDockerDaemon(String baseURL) {
		this.baseURL = baseURL;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public void performRequest(String endpoint, ParameterRequest paramRequest, JSONRequest jsonRequest,
			JSONSink response) throws IOException {
		URLBuilder url = new URLBuilder(baseURL);
		url.add(endpoint);
		if(paramRequest != null)
			paramRequest.setParameters(url);
		URLConnection conn = url.toURL().openConnection();
		if(jsonRequest != null)
			conn.setDoOutput(true);
		conn.connect();
		if(jsonRequest != null) {
			try(OutputStream os = conn.getOutputStream()) {
				jsonRequest.sendRequest(new JSONPrinter(os));
			}
		}
		try(InputStream is = conn.getInputStream()) {
			new JSONParser(response).pullSerial(is);
		}
		catch(MalformedJSONException mje) {
			throw new IOException("Bad JSON response from docker daemon '"
					+ baseURL + "': " + mje.getMessage(), mje);
		}
	}

}
