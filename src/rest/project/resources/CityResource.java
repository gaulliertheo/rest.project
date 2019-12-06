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

/// Will map the resource to the URL dvds
@Path("city")
public class CityResource {

	// Connection

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	/// Return the list of dvds for applications
	@GET
	@Path("{city}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object> getDvdsBrowser(@PathParam("city") String city) {
		List<Object> lesMedias = new ArrayList<>();

		for (Dvd dvd : DBdao.instance.getDvds().values()) {
			User user = DBdao.instance.getAUser(dvd.getUserID());
			if (city.equals(user.getCity())) {
				lesMedias.add(dvd);
			}
		}

		for (Book book : DBdao.instance.getBooks().values()) {
			User user = DBdao.instance.getAUser(book.getUserID());
			if (city.equals(user.getCity())) {
				lesMedias.add(book);
			}
		}

		for (VideoGame videoGame : DBdao.instance.getVideoGames().values()) {
			User user = DBdao.instance.getAUser(videoGame.getUserID());
			if (city.equals(user.getCity())) {
				lesMedias.add(videoGame);
			}
		}

		return lesMedias;
	}
}