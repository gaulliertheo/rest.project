package rest.project.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rest.project.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public enum DBdao {
	instance;

	private Connection dbConn;
	private Statement stmt;
	private ResultSet rs;
	private String dbUrl;
	private String user;
	private String pwd;

	private DBdao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("DRIVER OK ! ");

			dbUrl = "jdbc:mysql://localhost:3306/restproject";
			user = "root";
			pwd = "";

			dbConn = DriverManager.getConnection(dbUrl, user, pwd);
			System.out.println("Connection effective !");
			stmt = dbConn.createStatement();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/* ----------------- MEDIA ----------------- */

	public int addMedia(int userID) throws SQLException {
		String query = "INSERT INTO media (userID, globalRate, borrowed) VALUE(";
		query += userID + ", '0.0', 0);";
		stmt.executeUpdate(query);

		rs = stmt.executeQuery("SELECT id FROM media WHERE userID = " + userID + " ORDER BY 1 DESC LIMIT 1;");
		rs.next();
		int id = rs.getInt("id");
		
		return id;
	}

	public void deleteMedia(int id, int userID) throws SQLException {
		stmt.executeUpdate("DELETE FROM media WHERE id = " + id + " AND userID = " + userID + ";");
	}
	
	public void borrowMedia(int id) throws SQLException{
		stmt.executeUpdate("UPDATE media SET borrowed = 1 WHERE id = " + id + ";");
	}
	
	public void returnMedia(int id) throws SQLException{
	stmt.executeUpdate("UPDATE media SET borrowed = 0 WHERE id = " + id + ";");
	}


	/* ----------------- DVD ----------------- */

	public Map<String, Dvd> getDvds() {
		Map<String, Dvd> contentProvider = new HashMap<>();

		List<Comment> comments = new ArrayList<Comment>();
		comments.addAll(getComments().values());

		try {
			rs = stmt.executeQuery("SELECT * FROM media m JOIN dvd d ON m.id = d.id;");
			while (rs.next()) {
				int id = rs.getInt("id");
				int userID = rs.getInt("userID");
				String name = rs.getString("name");
				String kind = rs.getString("kind");
				String director = rs.getString("director");
				Date publishingDate = rs.getDate("publishingDate");
				String listActor = rs.getString("listActor");
				String summary = rs.getString("summary");
				float globalRate = rs.getFloat("globalRate");
				boolean borrowed = rs.getBoolean("borrowed");

				Dvd dvd = new Dvd(id, userID, name, kind, director, publishingDate, listActor, summary, globalRate,
						borrowed);

					for (Comment comment : comments) {
						if (id == comment.getMediaID()) {
							dvd.addComment(comment);
						}
					}

				contentProvider.put(Integer.toString(id), dvd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contentProvider;
	}

	public void addDvd(Dvd dvd) throws SQLException {
		int userID = dvd.getUserID();
		String name = dvd.getName();
		String kind = dvd.getKind();
		String director = dvd.getDirector();
		Date publishingDate = dvd.getPublishingDate();
		String listActor = dvd.getListActor();
		String summary = dvd.getSummary();

		int id = addMedia(userID);
		
		String date = DatetoString(publishingDate);

		String query = "INSERT INTO dvd (id, name, kind, director, publishingDate, listActor, summary) VALUE (";
		query += id;
		query += ",'" + name + "'";
		query += ",'" + kind + "'";
		query += ",'" + director + "'";
		query += ",'" + date + "'";
		query += ",'" + listActor + "'";
		query += ",'" + summary + "');";
		
		stmt.executeUpdate(query);
	}

	public void deleteDvd(int id, int userID) throws SQLException {
		stmt.executeUpdate("DELETE FROM dvd WHERE id = " + id + ";");
		deleteMedia(id, userID);
	}

	/* ----------------- BOOK ----------------- */

	public Map<String, Book> getBooks() {
		Map<String, Book> contentProvider = new HashMap<>();

		List<Comment> comments = new ArrayList<Comment>();
		comments.addAll(getComments().values());

		try {
			rs = stmt.executeQuery("SELECT * FROM media m JOIN book b ON m.id = b.id;");
			while (rs.next()) {
				int id = rs.getInt("id");
				int userID = rs.getInt("userID");
				String title = rs.getString("title");
				String kind = rs.getString("kind");
				String author = rs.getString("author");
				Date publishingDate = rs.getDate("publishingDate");
				String pushingHouse = rs.getString("pushingHouse");
				String summary = rs.getString("summary");
				float globalRate = rs.getFloat("globalRate");
				boolean borrowed = rs.getBoolean("borrowed");

				Book book = new Book(id, userID, title, author, summary, pushingHouse, publishingDate, kind,
						globalRate, borrowed);

				contentProvider.put(Integer.toString(id), book);

				for (Comment comment : comments) {
					if (id == comment.getMediaID()) {
						book.addComment(comment);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contentProvider;
	}

	public void addBook(Book book) throws SQLException {
		int userID = book.getUserID();
		String title = book.getTitle();
		String kind = book.getKind();
		String author = book.getAuthor();
		Date publishingDate = book.getPublishingDate();
		String pushingHouse = book.getPushingHouse();
		String summary = book.getSummary();

		int id = addMedia(userID);
		
		String date = DatetoString(publishingDate);

		String query = "INSERT INTO book (id, title, author, summary, pushingHouse, publishingDate, kind) VALUE(";
		query += id;
		query += ",'" + title + "'";
		query += ",'" + author + "'";
		query += ",'" + summary + "'";
		query += ",'" + pushingHouse + "'";
		query += ",'" + date + "'";
		query += ",'" + kind + "');";

		stmt.executeUpdate(query);
	}

	public void deleteBook(int id, int userID) throws SQLException {
		stmt.executeUpdate("DELETE FROM book WHERE id = " + id + ";");
		deleteMedia(id, userID);
	}

	/* ----------------- VIDEO GAME ----------------- */

	public Map<String, VideoGame> getVideoGames() {
		Map<String, VideoGame> contentProvider = new HashMap<>();

		List<Comment> comments = new ArrayList<Comment>();
		comments.addAll(getComments().values());

		try {
			rs = stmt.executeQuery("SELECT * FROM media m JOIN videoGame v ON m.id = v.id;");
			while (rs.next()) {
				int id = rs.getInt("id");
				int userID = rs.getInt("userID");
				String name = rs.getString("name");
				String kind = rs.getString("kind");
				String developer = rs.getString("developer");
				Date publishingDate = rs.getDate("publishingDate");
				String support = rs.getString("support");
				String description = rs.getString("description");
				float globalRate = rs.getFloat("globalRate");
				boolean borrowed = rs.getBoolean("borrowed");

				VideoGame videoGame = new VideoGame(id, userID, name, kind, developer, publishingDate, support,
						description, globalRate, borrowed);

				contentProvider.put(Integer.toString(id), videoGame);

				for (Comment comment : comments) {
					if (id == comment.getMediaID()) {
						videoGame.addComment(comment);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contentProvider;
	}

	public void addVideoGame(VideoGame videoGame) throws SQLException {
		int userID = videoGame.getUserID();
		String name = videoGame.getName();
		String kind = videoGame.getKind();
		String developer = videoGame.getDeveloper();
		Date publishingDate = videoGame.getPublishingDate();
		String support = videoGame.getSupport();
		String description = videoGame.getDescription();

		int id = addMedia(userID);
		
		String date = DatetoString(publishingDate);

		String query = "INSERT INTO videogame (id, name, kind, developer, publishingDate, support, description) VALUE(";
		query += id;
		query += ",'" + name + "'";
		query += ",'" + kind + "'";
		query += ",'" + developer + "'";
		query += ",'" + date + "'";
		query += ",'" + support + "'";
		query += ",'" + description + "');";

		stmt.executeUpdate(query);
	}

	public void deleteVideoGame(int id, int userID) throws SQLException {
		stmt.executeUpdate("DELETE FROM videoGame WHERE id = " + id + ";");
		deleteMedia(id, userID);
	}

	/* ----------------- COMMENT ----------------- */

	public Map<String, Comment> getComments() {
		Map<String, Comment> contentProvider = new HashMap<>();

		try {
			rs = stmt.executeQuery("SELECT * FROM comment;");
			while (rs.next()) {
				int mediaID = rs.getInt("mediaID");
				int userID = rs.getInt("userID");
				String description = rs.getString("description");
				float rate = rs.getFloat("rate");

				Comment comment = new Comment(mediaID, userID, description, rate);
				String id = Integer.toString(mediaID) + "-" + Integer.toString(userID);

				contentProvider.put(id, comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contentProvider;
	}

	/* ----------------- USER ----------------- */

	public Map<String, User> getUsers() {
		Map<String, User> contentProvider = new HashMap<>();

		List<Dvd> dvds = new ArrayList<Dvd>();
		dvds.addAll(getDvds().values());
		List<Book> books = new ArrayList<Book>();
		books.addAll(getBooks().values());
		List<VideoGame> videoGames = new ArrayList<VideoGame>();
		videoGames.addAll(getVideoGames().values());

		try {
			rs = stmt.executeQuery("SELECT * FROM user;");
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String mail = rs.getString("mail");
				String name = rs.getString("name");
				String city = rs.getString("city");
				String address = rs.getString("address");

				User user = new User(id, mail, name, city, address);

				for (Dvd dvd : dvds) {
					if (id == dvd.getUserID()) {
						user.addDvd(dvd);
					}
				}

				for (Book book : books) {
					if (id == book.getUserID()) {
						user.addBook(book);
					}
				}

				for (VideoGame videoGame : videoGames) {
					if (id == videoGame.getUserID()) {
						user.addVideoGame(videoGame);
					}
				}

				contentProvider.put(Integer.toString(id), user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contentProvider;
	}
	
	public User getAUser(int userID) {
		try {
			rs = stmt.executeQuery("SELECT * FROM user;");
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String mail = rs.getString("mail");
				String name = rs.getString("name");
				String city = rs.getString("city");
				String address = rs.getString("address");

				if(userID == id){
					User user = new User(id, mail, name, city, address);
					return user;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/* ----------------- OTHER ----------------- */
	
	private String DatetoString(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("y-M-d");
		return formatter.format(date);
	}

}