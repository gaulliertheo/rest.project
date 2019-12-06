package rest.project.resources;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("/dvds")
public class DvdsResource {

	// Connection

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Return the list of dvds for applications
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dvd> getDvds() {
		List<Dvd> dvds = new ArrayList<Dvd>();
		dvds.addAll(DBdao.instance.getDvds().values());
		return dvds;
	}

	// returns the number of dvds
	// Use http://localhost:8080/com.vogella.jersey.dvd/rest/dvds/count
	// to get the total number of records
	@GET
	@Path("count")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCount() {
		int count = DBdao.instance.getDvds().size();
		return String.valueOf(count);
	}

	@POST
	@Path("addDvd")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String newDvd(@FormParam("userID") int userID, @FormParam("name") String name,
			@FormParam("kind") String kind, @FormParam("director") String director,
			@FormParam("publishingDate") String date, @FormParam("listActor") String listActor,
			@FormParam("summary") String summary, @Context HttpServletResponse servletResponse) throws IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("y-M-d");
		try {
			Date publishingDate = formatter.parse(date);
			Dvd dvd = new Dvd(userID, name, kind, director, publishingDate, listActor, summary);

			DBdao.instance.addDvd(dvd);
			return "Add: Dvd added !";

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	// Defines that the next path parameter after dvds is
	// treated as a parameter and passed to the DvdResources
	// Allows to type http://localhost:8080/rest.dvd/rest/dvds/1
	// 1 will be treaded as parameter dvd and passed to DvdResource
	@Path("{dvd}")
	public DvdResource getDvd(@PathParam("dvd") String id) {
		return new DvdResource(uriInfo, request, id);
	}

}