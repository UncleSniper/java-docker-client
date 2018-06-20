package org.unclesniper.dockerclient;

import java.io.IOException;

public interface DockerService {

	void listContainers(ContainerListRequest request, ContainerListSink response) throws IOException;

}
