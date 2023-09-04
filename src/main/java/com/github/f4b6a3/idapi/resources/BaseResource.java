package com.github.f4b6a3.idapi.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import com.github.f4b6a3.uuid.exception.InvalidUuidException;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public abstract class BaseResource {

	static final int LIMIT = 1000;

	static Response response(Supplier<Object> supplier, Integer limit, String accept) {
		try {

			Object entity = null;
			MediaType type = null;
			List<String> list = new ArrayList<>();

			int max = Math.min(Objects.requireNonNullElse(limit, 1), LIMIT);
			IntStream.range(0, max).forEach(x -> list.add(supplier.get().toString()));

			// always use JSON unless the user requests TEXT
			if (accept != null && accept.contains(MediaType.TEXT_PLAIN)) {
				// make a newline-separated list
				entity = String.join("\n", list);
				type = MediaType.TEXT_PLAIN_TYPE;
			} else {
				entity = list; // use the list as-is
				type = MediaType.APPLICATION_JSON_TYPE;
			}

			return Response.status(Response.Status.OK).type(type).entity(entity).build();

		} catch (IllegalArgumentException | InvalidUuidException e) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		} catch (RuntimeException e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
}
