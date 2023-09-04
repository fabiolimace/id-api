package com.github.f4b6a3.idapi;

import java.util.List;

import com.github.f4b6a3.uuid.UuidCreator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	public void testUuidNamespace() {

		for (String namespace : List.of("dns", "url", "oid", "x500")) {
			assertNotNull(UuidResource.namespace(namespace));
		}

		assertNull(UuidResource.namespace(""));
		assertNull(UuidResource.namespace(null));
		assertNull(UuidResource.namespace("INVALID"));

		assertNotNull(UuidResource.namespace(UuidCreator.getNil().toString()));
		assertNotNull(UuidResource.namespace(UuidCreator.getMax().toString()));
		assertNotNull(UuidResource.namespace("28cdef1e-90ba-43a5-8e9a-92f4a14d8604"));
	}
}
