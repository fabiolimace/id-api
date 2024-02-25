package com.example.idapi.resources;

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

	// Maximum number of IDs per request
	static final int LIMIT = getLimitFromEnvironment();

	static final int getLimitFromEnvironment() {

		final int limit = 10;

		final String key = "ID_API_LIMIT";
		String value = System.getenv(key);

		return value != null ? Integer.parseInt(value) : limit;
	}

	static Response response(Supplier<Object> supplier, Integer n, String accept) {
		try {

			Object entity = null;
			MediaType type = null;
			List<String> list = new ArrayList<>();

			int max = Math.min(Objects.requireNonNullElse(n, 1), LIMIT);
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
