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
import rest.project.model.Book;

public class BookResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;

	public BookResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	// Application integration
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Book getBook() {
		Book Book = DBdao.instance.getBooks().get(id);
		if (Book == null)
			throw new RuntimeException("Get: Book with id " + id + " not found");
		return Book;
	}
	
	@PUT
	@Path("borrow")
	@Produces(MediaType.APPLICATION_JSON)
	public Book borrowBook() throws NumberFormatException, SQLException{
		DBdao.instance.borrowMedia(Integer.parseInt(id));
		Book book = DBdao.instance.getBooks().get(id);
		return book;
	}
	
	@PUT
	@Path("return")
	@Produces(MediaType.APPLICATION_JSON)
	public Book returnBook() throws NumberFormatException, SQLException{
		DBdao.instance.returnMedia(Integer.parseInt(id));
		Book book = DBdao.instance.getBooks().get(id);
		return book;
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deleteBook(@FormParam("userID") int userID, @Context HttpServletResponse servletResponse) {
		Book Book = DBdao.instance.getBooks().get(id);
		if (Book == null) {
			throw new RuntimeException("Delete: Book with id " + id + " not found");
		} else {
			try {
				DBdao.instance.deleteBook(Book.getId(), userID);
				return "Delete: Book with id " + id + " deleted !";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
