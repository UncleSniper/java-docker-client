package org.unclesniper.dockerclient;

import java.net.URL;
import java.net.URLEncoder;
import java.net.MalformedURLException;
import java.io.UnsupportedEncodingException;

public final class URLBuilder implements ParameterSink {

	private final StringBuilder builder = new StringBuilder();

	private boolean hasQuestion;

	public URLBuilder() {}

	public URLBuilder(String start) {
		builder.append(start);
		hasQuestion = start.indexOf('?') >= 0;
	}

	public URLBuilder add(String piece) {
		builder.append(piece);
		if(!hasQuestion && piece.indexOf('?') >= 0)
			hasQuestion = true;
		return this;
	}

	public void addParameter(String key, String value) {
		if(hasQuestion)
			builder.append('&');
		else {
			builder.append('?');
			hasQuestion = true;
		}
		builder.append(key);
		builder.append('=');
		if(value != null)
			paramValue(value);
	}

	public void continueParameter(String moreValue) {
		paramValue(moreValue);
	}

	private void paramValue(String value) {
		try {
			builder.append(URLEncoder.encode(value, "UTF-8"));
		}
		catch(UnsupportedEncodingException uee) {
			throw new Error("Your JVM doesn't support UTF-8. Say whaaaaaaaat!?");
		}
	}

	public URLBuilder param(String key, String value) {
		addParameter(key, value);
		return this;
	}

	public String toString() {
		return builder.toString();
	}

	public URL toURL() throws MalformedURLException {
		return new URL(builder.toString());
	}

}
