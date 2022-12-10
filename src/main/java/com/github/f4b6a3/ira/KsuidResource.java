package com.github.f4b6a3.ira;

import com.github.f4b6a3.ksuid.KsuidCreator;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/ksuid")
public class KsuidResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String ksuid() {
		return KsuidCreator.getKsuid().toString();
	}

	@GET
	@Path("monotonic")
	@Produces(MediaType.TEXT_PLAIN)
	public String monotonic() {
		return KsuidCreator.getMonotonicKsuid().toString();
	}

	@GET
	@Path("subsecond")
	@Produces(MediaType.TEXT_PLAIN)
	public String subsecond() {
		return KsuidCreator.getSubsecondKsuid().toString();
	}
}
