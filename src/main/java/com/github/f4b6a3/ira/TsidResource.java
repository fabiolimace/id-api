package com.github.f4b6a3.ira;

import com.github.f4b6a3.tsid.TsidCreator;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/tsid")
public class TsidResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getTsid() {
		return TsidCreator.getTsid256().toString();
	}
}
