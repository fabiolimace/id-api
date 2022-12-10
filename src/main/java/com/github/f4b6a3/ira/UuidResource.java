package com.github.f4b6a3.ira;

import com.github.f4b6a3.uuid.UuidCreator;
import com.github.f4b6a3.uuid.enums.UuidNamespace;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/uuid")
public class UuidResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String uuid() {
		return UuidCreator.getRandomBased().toString();
	}

	@GET
	@Path("/v1")
	@Produces(MediaType.TEXT_PLAIN)
	public String v1() {
		return UuidCreator.getTimeBased().toString();
	}

	@GET
	@Path("/v3/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String v3(@PathParam("name") String name) {
		return UuidCreator.getNameBasedMd5(name).toString();
	}

	@GET
	@Path("/v3/url/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String v3url(@PathParam("name") String name) {
		return UuidCreator.getNameBasedMd5(UuidNamespace.NAMESPACE_URL, name).toString();
	}

	@GET
	@Path("/v4")
	@Produces(MediaType.TEXT_PLAIN)
	public String v4() {
		return UuidCreator.getRandomBased().toString();
	}

	@GET
	@Path("/v5/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String v5(@PathParam("name") String name) {
		return UuidCreator.getNameBasedSha1(name).toString();
	}

	@GET
	@Path("/v5/url/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String v5url(@PathParam("name") String name) {
		return UuidCreator.getNameBasedSha1(UuidNamespace.NAMESPACE_URL, name).toString();
	}

	@GET
	@Path("/v6")
	@Produces(MediaType.TEXT_PLAIN)
	public String v6() {
		return UuidCreator.getTimeOrdered().toString();
	}

	@GET
	@Path("/v7")
	@Produces(MediaType.TEXT_PLAIN)
	public String v7() {
		return UuidCreator.getTimeOrderedEpoch().toString();
	}
}
