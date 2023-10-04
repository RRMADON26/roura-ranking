package com.rrmadon;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Message;

import java.util.HashMap;
import java.util.Map;

@Path("/hello")
public class ExampleResource {


	@Inject
	ExampleService service;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public void hello() {
		service.consume(new Message<Map<String, Object>>() {
			@Override
			public Map<String, Object> getPayload() {
				return null;
			}
		});
	}
}
