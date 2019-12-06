package rest.project.resources;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import rest.project.model.VideoGame;

@Path("/videogames")
public class VideoGamesResource {

	// Connection

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Return the list of VideoGames for applications
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<VideoGame> getVideoGames() {
		List<VideoGame> VideoGames = new ArrayList<VideoGame>();
		VideoGames.addAll(DBdao.instance.getVideoGames().values());
		return VideoGames;
	}

	// returns the number of VideoGames
	// Use http://localhost:8080/com.vogella.jersey.VideoGame/rest/VideoGames/count
	// to get the total number of records
	@GET
	@Path("count")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCount() {
		int count = DBdao.instance.getVideoGames().size();
		return String.valueOf(count);
	}

	@POST
	@Path("addvideogame")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String newVideoGame(@FormParam("userID") int userID, @FormParam("name") String name, @FormParam("kind") String kind,
			@FormParam("developer") String developer, @FormParam("publishingDate") String date,
			@FormParam("support") String support, @FormParam("description") String description,
			@Context HttpServletResponse servletResponse) throws IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("y-M-d");
		try {
			Date publishingDate = formatter.parse(date);
			VideoGame VideoGame = new VideoGame(userID, name, kind, developer,publishingDate,
					support, description);

			DBdao.instance.addVideoGame(VideoGame);
			return "Add: videogame added !";
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	// Defines that the next path parameter after VideoGames is
	// treated as a parameter and passed to the VideoGameResources
	// Allows to type http://localhost:8080/rest.VideoGame/rest/VideoGames/1
	// 1 will be treaded as parameter VideoGame and passed to VideoGameResource
	@Path("{videoGame}")
	public VideoGameResource getVideoGame(@PathParam("videoGame") String id) {
		return new VideoGameResource(uriInfo, request, id);
	}

}