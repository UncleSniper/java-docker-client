package org.unclesniper.dockerclient;

import java.io.IOException;
import org.unclesniper.json.JSONSink;

public interface JSONRequest {

	void sendRequest(JSONSink sink) throws IOException;

}
