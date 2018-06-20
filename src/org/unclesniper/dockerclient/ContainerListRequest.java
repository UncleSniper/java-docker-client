package org.unclesniper.dockerclient;

import java.io.IOException;

public interface ContainerListRequest {

	boolean isAll();

	int getLimit();

	boolean isSize();

	void filters(ContainerListFiltersSink sink) throws IOException;

}
