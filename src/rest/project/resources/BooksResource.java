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

@Path("/books")
public class BooksResource {

	// Connection

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Return the list of Books for applications
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> getBooks() {
		List<Book> Books = new ArrayList<Book>();
		Books.addAll(DBdao.instance.getBooks().values());
		return Books;
	}

	// returns the number of Books
	// Use http://localhost:8080/com.vogella.jersey.Book/rest/Books/count
	// to get the total number of records
	@GET
	@Path("count")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCount() {
		int count = DBdao.instance.getBooks().size();
		return String.valueOf(count);
	}

	@POST
	@Path("addBook")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String newBook(@FormParam("userID") int userID, @FormParam("title") String title,
			@FormParam("author") String author, @FormParam("summary") String summary,
			@FormParam("publishingDate") String date, @FormParam("pushingHouse") String pushingHouse,
			@FormParam("kind") String kind, @Context HttpServletResponse servletResponse) throws IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("y-M-d");
		try {
			Date publishingDate = formatter.parse(date);
			Book Book = new Book(userID, title, author, summary, pushingHouse, publishingDate, kind);

			DBdao.instance.addBook(Book);
			return "Add: Book added !";

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	// Defines that the next path parameter after Books is
	// treated as a parameter and passed to the BookResources
	// Allows to type http://localhost:8080/rest.Book/rest/books/1
	// 1 will be treaded as parameter Book and passed to BookResource
	@Path("{book}")
	public BookResource getBook(@PathParam("book") String id) {
		return new BookResource(uriInfo, request, id);
	}

}
