package com.example.idapi.resources;

import com.github.f4b6a3.tsid.TsidCreator;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("tsid")
public class TsidResource extends BaseResource {

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Response tsid(@QueryParam("n") Integer n, @HeaderParam("Accept") String accept) {
		return response(TsidCreator::getTsid, n, accept);
	}

	@GET
	@Path("int64")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Response int64(@QueryParam("n") Integer n, @HeaderParam("Accept") String accept) {
		return response(() -> TsidCreator.getTsid().toLong(), n, accept);
	}

	@GET
	@Path("uint64")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Response uint64(@QueryParam("n") Integer n, @HeaderParam("Accept") String accept) {
		return response(() -> Long.toUnsignedString(TsidCreator.getTsid().toLong()), n, accept);
	}
}
