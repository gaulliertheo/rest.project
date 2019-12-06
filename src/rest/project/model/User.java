package rest.project.model;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

	private Integer id;
	private String mail;
	private String name;
	private String city;
	private String address;
	
	@XmlElementWrapper
	@XmlElement(name="dvd")
	private List<Dvd> dvds;
	
	@XmlElementWrapper
	@XmlElement(name="book")
	private List<Book> books;
	
	@XmlElementWrapper
	@XmlElement(name="videoGame")
	private List<VideoGame> videoGames;

	public User() {
	}

	public User(Integer id) {
		this.id = id;
	}

	public User(Integer id, String mail, String name, String city, String address) {
		this.id = id;
		this.mail = mail;
		this.name = name;
		this.city = city;
		this.address = address;
		this.dvds = new ArrayList<Dvd>();
		this.books = new ArrayList<Book>();
		this.videoGames = new ArrayList<VideoGame>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Dvd> getDvds() {
		return dvds;
	}

	public Dvd getADvd(int id) {
		for (Dvd dvd : dvds) {
			if (id == dvd.getId()) {
				return dvd;
			}
		}
		return null;
	}

	public void addDvd(Dvd dvd) {
		dvds.add(dvd);
	}

	public List<Book> getBooks() {
		return books;
	}

	public Book getABook(int id) {
		for (Book book : books) {
			if (id == book.getId()) {
				return book;
			}
		}
		return null;
	}

	public void addBook(Book book) {
		books.add(book);
	}

	public List<VideoGame> getVideoGames() {
		return videoGames;
	}

	public VideoGame getAVideoGame(int id) {
		for (VideoGame videoGame : videoGames) {
			if (id == videoGame.getId()) {
				return videoGame;
			}
		}
		return null;
	}

	public void addVideoGame(VideoGame videoGame) {
		videoGames.add(videoGame);
	}

}
