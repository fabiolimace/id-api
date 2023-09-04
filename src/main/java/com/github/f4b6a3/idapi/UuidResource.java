package com.github.f4b6a3.idapi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

import com.github.f4b6a3.uuid.UuidCreator;
import com.github.f4b6a3.uuid.enums.UuidNamespace;
import com.github.f4b6a3.uuid.util.UuidValidator;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("uuid")
public class UuidResource {

	static final int HARD_LIMIT = 1000;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response auto(@QueryParam("limit") int limit) {
		return v4(limit);
	}

	@GET
	@Path("v1")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response v1(@QueryParam("limit") int limit) {
		return response(UuidCreator::getTimeBased, limit);
	}

	@GET
	@Path("v3")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response v3(@QueryParam("namespace") String namespace, @QueryParam("name") String name) {
		return response(() -> UuidCreator.getNameBasedMd5(namespace(namespace), name), 1);
	}

	@GET
	@Path("v4")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response v4(@QueryParam("limit") int limit) {
		return response(UuidCreator::getRandomBased, limit);
	}

	@GET
	@Path("v5")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response v5(@QueryParam("namespace") String namespace, @QueryParam("name") String name) {
		return response(() -> UuidCreator.getNameBasedSha1(namespace(namespace), name), 1);
	}

	@GET
	@Path("v6")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response v6(@QueryParam("limit") int limit) {
		return response(UuidCreator::getTimeOrdered, limit);
	}

	@GET
	@Path("v7")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response v7(@QueryParam("limit") int limit) {
		return response(UuidCreator::getTimeOrderedEpoch, limit);
	}

	static Response response(Supplier<Object> supplier, int limit) {
		try {
			final int max = Math.min(Math.max(1, limit), HARD_LIMIT);
			List<String> list = new ArrayList<>();
			for (int i = 0; i < max; i++) {
				list.add(supplier.get().toString());
			}
			return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(list).build();
		} catch (RuntimeException e) {
			if (e instanceof IllegalArgumentException) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}
		}
	}

	static UUID namespace(String namespace) {

		if (namespace == null) {
			return null;
		}

		if (List.of("dns", "url", "oid", "x500").contains(namespace)) {
			String key = String.format("NAMESPACE_%S", namespace);
			return Enum.valueOf(UuidNamespace.class, key).getValue();
		} else if (UuidValidator.isValid(namespace)) {
			return UUID.fromString(namespace);
		}

		return null;
	}
}
