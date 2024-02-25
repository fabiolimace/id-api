package com.example.idapi.filters;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class AppRequestFilter implements ContainerRequestFilter {

	private final static Logger LOGGER = Logger.getLogger(AppRequestFilter.class.getName());

	@Override
	public void filter(ContainerRequestContext ctx) throws IOException {

		LOGGER.info("Method: " + ctx.getMethod());
		LOGGER.info("Path: " + ctx.getUriInfo().getPath());
		LOGGER.info("Query Parameters: " + ctx.getUriInfo().getQueryParameters());

		if (Objects.isNull(ctx.getHeaderString("User-Agent"))) {
			ctx.abortWith(
					Response.status(Response.Status.FORBIDDEN).entity("\"Cannot access without User-Agent.\"").build());
		} else {
			LOGGER.info("User-Agent: " + ctx.getHeaderString("User-Agent"));
		}
	}
}
