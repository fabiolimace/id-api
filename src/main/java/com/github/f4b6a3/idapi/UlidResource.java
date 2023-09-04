package com.github.f4b6a3.idapi;

import static com.github.f4b6a3.idapi.UuidResource.response;

import com.github.f4b6a3.ulid.UlidCreator;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("ulid")
public class UlidResource {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response auto(@QueryParam("limit") int limit) {
		return ulid(limit);
	}

	@GET
	@Path("monotonic")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response ulid(@QueryParam("limit") int limit) {
		return response(UlidCreator::getUlid, limit);
	}

	@GET
	@Path("monotonic")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response monotonic(@QueryParam("limit") int limit) {
		return response(UlidCreator::getMonotonicUlid, limit);
	}
}
