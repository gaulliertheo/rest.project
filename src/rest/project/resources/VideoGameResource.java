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
import rest.project.model.VideoGame;

public class VideoGameResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;

	public VideoGameResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	// Application integration
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public VideoGame getVideoGame() {
		VideoGame VideoGame = DBdao.instance.getVideoGames().get(id);
		if (VideoGame == null)
			throw new RuntimeException("Get: VideoGame with id " + id + " not found");
		return VideoGame;
	}
	
	@PUT
	@Path("borrow")
	@Produces(MediaType.APPLICATION_JSON)
	public VideoGame borrowVideoGame() throws NumberFormatException, SQLException{
		DBdao.instance.borrowMedia(Integer.parseInt(id));
		VideoGame videoGame = DBdao.instance.getVideoGames().get(id);
		return videoGame;
	}
	
	@PUT
	@Path("return")
	@Produces(MediaType.APPLICATION_JSON)
	public VideoGame returnVideoGame() throws NumberFormatException, SQLException{
		DBdao.instance.returnMedia(Integer.parseInt(id));
		VideoGame videoGame = DBdao.instance.getVideoGames().get(id);
		return videoGame;
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deleteVideoGame(@FormParam("userID") int userID, @Context HttpServletResponse servletResponse) {
		VideoGame VideoGame = DBdao.instance.getVideoGames().get(id);
		if (VideoGame == null) {
			throw new RuntimeException("Delete: VideoGame with id " + id + " not found");
		} else {
			try {
				DBdao.instance.deleteVideoGame(VideoGame.getId(), userID);
				return "Delete: VideoGame with id " + id + " deleted !";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}