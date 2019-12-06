package rest.project.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import rest.project.dao.DBdao;
import rest.project.model.*;

public class UserResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;

	public UserResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	// Application integration
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser() {
		User User = DBdao.instance.getUsers().get(id);
		if (User == null)
			throw new RuntimeException("Get: User with id " + id + " not found");
		return User;
	}
}