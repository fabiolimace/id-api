package com.example.idapi.resources;

import java.util.List;

import com.github.f4b6a3.uuid.UuidCreator;
import com.github.f4b6a3.uuid.util.UuidValidator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UuidResourceTest extends TestCase {

	public UuidResourceTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(UuidResourceTest.class);
	}

	public void testNamespace() {

		for (String namespace : List.of("dns", "url", "oid", "x500")) {
			assertTrue(UuidValidator.isValid(UuidResource.namespace(namespace)));
		}

		assertFalse(UuidValidator.isValid(UuidResource.namespace("")));
		assertFalse(UuidValidator.isValid(UuidResource.namespace(null)));
		assertFalse(UuidValidator.isValid(UuidResource.namespace("INVALID")));

		assertTrue(UuidValidator.isValid(UuidResource.namespace(UuidCreator.getNil().toString())));
		assertTrue(UuidValidator.isValid(UuidResource.namespace(UuidCreator.getMax().toString())));
		assertTrue(UuidValidator.isValid(UuidResource.namespace("28cdef1e-90ba-43a5-8e9a-92f4a14d8604")));
	}
}
