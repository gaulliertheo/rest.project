package rest.project.resources;

import java.util.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import rest.project.dao.DBdao;
import rest.project.model.*;

/// Will map the resource to the URL users
@Path("/users")
public class UsersResource {

	// Connection

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Return the list of users for applications
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		users.addAll(DBdao.instance.getUsers().values());
		return users;
	}

	// returns the number of users
	// Use http://localhost:8080/com.vogella.jersey.dvd/rest/users/count
	// to get the total number of records
	@GET
	@Path("count")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCount() {
		int count = DBdao.instance.getUsers().size();
		return String.valueOf(count);
	}

	@Path("{user}")
	public UserResource getUser(@PathParam("user") String id) {
		return new UserResource(uriInfo, request, id);
	}

}
