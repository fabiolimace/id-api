package com.github.f4b6a3.idapi;

import static com.github.f4b6a3.idapi.UuidResource.response;

import com.github.f4b6a3.ksuid.KsuidCreator;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("ksuid")
public class KsuidResource {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response auto(@QueryParam("limit") int limit) {
		return ksuid(limit);
	}

	@GET
	@Path("ksuid")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response ksuid(@QueryParam("limit") int limit) {
		return response(KsuidCreator::getKsuid, limit);
	}

	@GET
	@Path("monotonic")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response monotonic(@QueryParam("limit") int limit) {
		return response(KsuidCreator::getMonotonicKsuid, limit);
	}

	@GET
	@Path("subsecond") // precise on JDK 17+
	@Produces({ MediaType.APPLICATION_JSON })
	public Response subsecond(@QueryParam("limit") int limit) {
		return response(KsuidCreator::getSubsecondKsuid, limit);
	}
}
