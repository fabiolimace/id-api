package com.github.f4b6a3.ira;

import com.github.f4b6a3.ulid.UlidCreator;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/ulid")
public class UlidResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String tsid() {
		return UlidCreator.getUlid().toString();
	}

	@GET
	@Path("monotonic")
	@Produces(MediaType.TEXT_PLAIN)
	public String monotonic() {
		return UlidCreator.getMonotonicUlid().toString();
	}
}
