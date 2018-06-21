package org.unclesniper.dockerclient;

import java.io.IOException;
import org.unclesniper.json.JSONSink;

public interface DockerDaemon {

	void performRequest(String endpoint, ParameterRequest paramRequest, JSONRequest jsonRequest,
			JSONSink response) throws IOException;

}
