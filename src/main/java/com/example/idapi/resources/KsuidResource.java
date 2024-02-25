package com.example.idapi.resources;

import com.github.f4b6a3.ksuid.KsuidCreator;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("ksuid")
public class KsuidResource extends BaseResource {

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Response ksuid(@QueryParam("n") Integer n, @HeaderParam("Accept") String accept) {
		return response(KsuidCreator::getKsuid, n, accept);
	}

	@GET
	@Path("monotonic")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Response monotonic(@QueryParam("n") Integer n, @HeaderParam("Accept") String accept) {
		return response(KsuidCreator::getMonotonicKsuid, n, accept);
	}

	@GET
	@Path("subsecond") // precise on JDK 17+
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Response subsecond(@QueryParam("n") Integer n, @HeaderParam("Accept") String accept) {
		return response(KsuidCreator::getSubsecondKsuid, n, accept);
	}
}
