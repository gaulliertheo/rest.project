package rest.project.resources;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import rest.project.dao.DBdao;
import rest.project.model.Dvd;

public class DvdResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;

	public DvdResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	// Application integration
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Dvd getDvd() {
		Dvd dvd = DBdao.instance.getDvds().get(id);
		if (dvd == null)
			throw new RuntimeException("Get: Dvd with id " + id + " not found");
		return dvd;
	}
	
	@PUT
	@Path("borrow")
	@Produces(MediaType.APPLICATION_JSON)
	public Dvd borrowDvd() throws NumberFormatException, SQLException{
		DBdao.instance.borrowMedia(Integer.parseInt(id));
		Dvd dvd = DBdao.instance.getDvds().get(id);
		return dvd;
	}
	
	@PUT
	@Path("return")
	@Produces(MediaType.APPLICATION_JSON)
	public Dvd returnDvd() throws NumberFormatException, SQLException{
		DBdao.instance.returnMedia(Integer.parseInt(id));
		Dvd dvd = DBdao.instance.getDvds().get(id);
		return dvd;
	}
	

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deleteDvd(@FormParam("userID") int userID, @Context HttpServletResponse servletResponse) {
		Dvd dvd = DBdao.instance.getDvds().get(id);
		if (dvd == null) {
			throw new RuntimeException("Delete: Dvd with id " + id + " not found");
		} else {
			try {
				DBdao.instance.deleteDvd(dvd.getId(), userID);
				return "Delete: Dvd with id " + id + " deleted !";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}