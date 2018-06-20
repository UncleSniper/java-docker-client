package org.unclesniper.dockerclient;

import java.io.IOException;
import org.unclesniper.json.JSONSink;
import org.unclesniper.json.MalformedJSONException;

public interface DockerDaemon {

	void performRequest(String endpoint, JSONRequest request, JSONSink response)
			throws IOException, MalformedJSONException;

}
