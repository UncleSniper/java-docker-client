package org.unclesniper.dockerclient.request;

import java.io.IOException;
import org.unclesniper.dockerclient.ParameterSink;
import org.unclesniper.dockerclient.ParameterRequest;
import org.unclesniper.dockerclient.ContainerListRequest;
import org.unclesniper.dockerclient.ContainerListFiltersSink;

public class ContainerListParameterRequest implements ParameterRequest {

	private static final class FilterSink implements ContainerListFiltersSink {

		private final ParameterSink parameters;

		public FilterSink(ParameterSink parameters) {
			this.parameters = parameters;
		}

	}

	private ContainerListRequest request;

	public ContainerListParameterRequest(ContainerListRequest request) {
		this.request = request;
	}

	public ContainerListRequest getRequest() {
		return request;
	}

	public void setRequest(ContainerListRequest request) {
		this.request = request;
	}

	public void setParameters(ParameterSink sink) throws IOException {
		if(request.isAll())
			sink.addParameter("all", "true");
		int limit = request.getLimit();
		if(limit > 0)
			sink.addParameter("limit", String.valueOf(limit));
		if(request.isSize())
			sink.addParameter("size", "true");
		request.filters(new FilterSink(sink));
	}

}
