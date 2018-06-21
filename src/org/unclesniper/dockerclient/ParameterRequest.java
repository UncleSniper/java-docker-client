package org.unclesniper.dockerclient;

import java.io.IOException;

public interface ParameterRequest {

	void setParameters(ParameterSink sink) throws IOException;

}
