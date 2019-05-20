package com.farmerswife.cirkus.codetest.server.restlet.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import com.farmerswife.cirkus.codetest.server.utils.JsonParser;

/**
 * THIS IS AN EXAMPLE OF A SERVER RESOURCE THAT HANDLES ALL COMMON TYPES OF REQUESTS.
 * 
 * Entry point for /api/v1/example URL requests (see @com.farmerswife.cirkus.codetest.server.restlet.RestletApplication.java).
 * Available annotations are: @GET, @POST, @PUT, @DELETE
 * 
 * Note that @POST and @PUT methods always have an single argument - @org.restlet.representation.Representation - 
 * which hold the request data entity.
 * 
 * Hint: Use the supplied JsonParser helper class to convert JSON data into a java.util.Map.
 */
public class MyExampleResource extends ServerResource {
	/* If you want to access a DAO directly from here, you can simply inject it as the example below. */
	// @com.google.inject.Inject UserDAO myDAO;
	
	// POST methods
	@Post("json")
	public Representation myPostMethod(Representation entity) {
		
		// Parse request
		try {
			Map<String, Object> requestBodyAsMap = JsonParser.jsonToMap(entity.getText());
		} catch (Exception exc) {
		}

		// Create response as a JSON object
		Map<String, Object> responseAsMap = new HashMap<String, Object>();
		// ...
		return new JsonRepresentation(responseAsMap);
		
	}

	// PUT methods
	@Put("json")
	public Representation myPutMethod(Representation entity) {
		
		// Parse request
		try {
			Map<String, Object> requestBodyAsMap = JsonParser.jsonToMap(entity.getText());
		} catch (Exception exc) {
		}

		// Create response as a JSON object
		Map<String, Object> responseAsMap = new HashMap<String, Object>();
		// ...
		return new JsonRepresentation(responseAsMap);
	} 

	// GET methods
	@Get
	public Representation myGetMethod() {
		
		// Create response as a JSON array
		List<Map<String, Object>> responseArray = new ArrayList<>();
		// ...
		return new JsonRepresentation(new org.json.JSONArray(responseArray));
	}

	// DELETE methods
	@Delete
	public Representation myDeleteMethod() {
		
		/* No method argument because DELETE methods are not allowed to have a request body, according to the HTTP specification. */
		
		// Create an empty response
		return new EmptyRepresentation();
	
	}

}
