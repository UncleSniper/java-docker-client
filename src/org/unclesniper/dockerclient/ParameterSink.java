package org.unclesniper.dockerclient;

public interface ParameterSink {

	void addParameter(String key, String value);

	void continueParameter(String moreValue);

}
