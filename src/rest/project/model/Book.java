package rest.project.model;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

	private int id;
	private int userID;
	private String title;
	private String summary;
	private String author;
	private String pushingHouse;
	private Date publishingDate;
	private String kind;
	private float globalRate;
	private boolean borrowed;

	@XmlElementWrapper
	@XmlElement(name="comment")
	private List<Comment> comments;

	public Book() {
	}

	public Book(int id) {
		this.id = id;
	}

	public Book(int id, int userID, String title, String author, String summary, String pushingHouse,
			Date publishingDate, String kind, float globalRate, boolean borrowed) {

		this.id = id;
		this.userID = userID;
		this.title = title;
		this.author = author;
		this.summary = summary;
		this.pushingHouse = pushingHouse;
		this.publishingDate = publishingDate;
		this.kind = kind;
		this.globalRate = globalRate;
		this.borrowed = borrowed;
		this.comments = new ArrayList<Comment>();

	}
	
	public Book(int userID, String title, String author, String summary, String pushingHouse,
			Date publishingDate, String kind) {

		
		this.userID = userID;
		this.title = title;
		this.author = author;
		this.summary = summary;
		this.pushingHouse = pushingHouse;
		this.publishingDate = publishingDate;
		this.kind = kind;
		this.comments = new ArrayList<Comment>();

	}


	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPushingHouse() {
		return pushingHouse;
	}

	public void setPushingHouse(String pushingHouse) {
		this.pushingHouse = pushingHouse;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Date getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(Date pushingDate) {
		this.publishingDate = pushingDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public float getGlobalRate() {
		return globalRate;
	}

	public void setGlobalRate(float globalRate) {
		this.globalRate = globalRate;
	}

	public boolean getBorrowed() {
		return borrowed;
	}

	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public Comment getAComment(String id) {
		for (Comment comment : comments) {
			if (id.equals(comment.getMediaID())) {
				return comment;
			}
		}
		return null;
	}

	public void addComment(Comment comment) {
		comments.add(comment);
	}

}