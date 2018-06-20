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

	public void performRequest(String endpoint, JSONRequest request, JSONSink response)
			throws IOException, MalformedJSONException {
		URL url = new URL(baseURL + endpoint);
		URLConnection conn = url.openConnection();
		if(request != null)
			conn.setDoOutput(true);
		conn.connect();
		if(request != null) {
			try(OutputStream os = conn.getOutputStream()) {
				request.sendRequest(new JSONPrinter(os));
			}
		}
		try(InputStream is = conn.getInputStream()) {
			new JSONParser(response).pullSerial(is);
		}
	}

}
