package com.github.f4b6a3.idapi.resources;

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
	@Produces({ MediaType.APPLICATION_JSON })
	public Response tsid(@QueryParam("limit") Integer limit, @HeaderParam("Accept") String accept) {
		return response(TsidCreator::getTsid, limit, accept);
	}

	@GET
	@Path("int64")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response int64(@QueryParam("limit") Integer limit, @HeaderParam("Accept") String accept) {
		return response(() -> TsidCreator.getTsid().toLong(), limit, accept);
	}

	@GET
	@Path("uint64")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response uint64(@QueryParam("limit") Integer limit, @HeaderParam("Accept") String accept) {
		return response(() -> Long.toUnsignedString(TsidCreator.getTsid().toLong()), limit, accept);
	}
}