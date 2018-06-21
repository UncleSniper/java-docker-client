package org.unclesniper.dockerclient;

import java.io.IOException;
import org.unclesniper.dockerclient.request.ContainerListParameterRequest;

public class DaemonDockerService implements DockerService {

	private DockerDaemon daemon;

	public DaemonDockerService(DockerDaemon daemon) {
		this.daemon = daemon;
	}

	public DockerDaemon getDaemon() {
		return daemon;
	}

	public void setDaemon(DockerDaemon daemon) {
		this.daemon = daemon;
	}

	public void listContainers(ContainerListRequest request, ContainerListSink response) throws IOException {
		daemon.performRequest("containers/json", new ContainerListParameterRequest(request), null,
				null);//TODO
	}

}
