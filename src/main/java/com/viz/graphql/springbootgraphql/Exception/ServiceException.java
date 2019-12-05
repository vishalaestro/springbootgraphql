package com.viz.graphql.springbootgraphql.Exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;


	public ServiceException(String message, Throwable t) {
		super(message, t);
	}

}
