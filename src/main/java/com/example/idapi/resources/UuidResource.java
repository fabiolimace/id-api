package com.example.idapi.resources;

import java.util.List;

import com.github.f4b6a3.uuid.UuidCreator;
import com.github.f4b6a3.uuid.enums.UuidNamespace;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("uuid")
public class UuidResource extends BaseResource {

	@GET
	@Path("v1")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Response v1(@QueryParam("n") Integer n, @HeaderParam("Accept") String accept) {
		return response(UuidCreator::getTimeBased, n, accept);
	}

	@GET
	@Path("v3")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Response v3(@QueryParam("namespace") String namespace, @QueryParam("name") String name,
			@HeaderParam("Accept") String accept) {
		return response(() -> UuidCreator.getNameBasedMd5(namespace(namespace), name), 1, accept);
	}

	@GET
	@Path("v4")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Response v4(@QueryParam("n") Integer n, @HeaderParam("Accept") String accept) {
		return response(UuidCreator::getRandomBased, n, accept);
	}

	@GET
	@Path("v5")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Response v5(@QueryParam("namespace") String namespace, @QueryParam("name") String name,
			@HeaderParam("Accept") String accept) {
		return response(() -> UuidCreator.getNameBasedSha1(namespace(namespace), name), 1, accept);
	}

	@GET
	@Path("v6")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Response v6(@QueryParam("n") Integer n, @HeaderParam("Accept") String accept) {
		return response(UuidCreator::getTimeOrdered, n, accept);
	}

	@GET
	@Path("v7")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Response v7(@QueryParam("n") Integer n, @HeaderParam("Accept") String accept) {
		return response(UuidCreator::getTimeOrderedEpoch, n, accept);
	}

	static String namespace(String namespace) {

		if (namespace != null && List.of("dns", "url", "oid", "x500").contains(namespace)) {
			String key = String.format("NAMESPACE_%S", namespace);
			return Enum.valueOf(UuidNamespace.class, key).getValue().toString();
		}

		return namespace;
	}
}
