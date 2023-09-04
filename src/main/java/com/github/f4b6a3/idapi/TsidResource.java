package com.github.f4b6a3.idapi;

import static com.github.f4b6a3.idapi.UuidResource.response;

import com.github.f4b6a3.tsid.TsidCreator;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("tsid")
public class TsidResource {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response tsid(@QueryParam("limit") int limit) {
		return response(TsidCreator::getTsid, limit);
	}
}
