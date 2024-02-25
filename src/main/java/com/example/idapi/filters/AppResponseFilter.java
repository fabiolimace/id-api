package com.example.idapi.filters;

import java.io.IOException;
import java.util.logging.Logger;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class AppResponseFilter implements ContainerResponseFilter {

	private final static Logger LOGGER = Logger.getLogger(AppResponseFilter.class.getName());

	@Override
	public void filter(ContainerRequestContext requestCtx, ContainerResponseContext responseCtx) throws IOException {
		LOGGER.info("Status: " + responseCtx.getStatus());
		LOGGER.info("Content-Type: " + responseCtx.getHeaderString("Content-Type"));
	}
}
