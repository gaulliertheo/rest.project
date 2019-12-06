package rest.project.model;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Dvd {

	private int id;
	private int userID;
	private String name;
	private String kind;
	private String director;
	private Date publishingDate;
	private String listActor;
	private String summary;
	private float globalRate;
	private boolean borrowed;

	@XmlElementWrapper
	@XmlElement(name = "comment")
	private List<Comment> comments;

	public Dvd(int userID, String name, String kind, String director, Date publishingDate, String listActor,
			String summary) {

		this.userID = userID;
		this.name = name;
		this.kind = kind;
		this.director = director;
		this.publishingDate = publishingDate;
		this.listActor = listActor;
		this.summary = summary;
		this.comments = new ArrayList<Comment>();
	}

	public Dvd(int id, int userID, String name, String kind, String director, Date publishingDate, String listActor,
			String summary, float globalRate, boolean borrowed) {

		this.id = id;
		this.userID = userID;
		this.name = name;
		this.kind = kind;
		this.director = director;
		this.publishingDate = publishingDate;
		this.listActor = listActor;
		this.summary = summary;
		this.globalRate = globalRate;
		this.borrowed = borrowed;
		this.comments = new ArrayList<Comment>();
	}

	public Integer getId() {
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

	public Dvd() {
	}

	public Dvd(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Date getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}

	public String getListActor() {
		return listActor;
	}

	public void setListActor(String listActor) {
		this.listActor = listActor;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public float getGlobalRate() {
		return globalRate;
	}

	public void setGlobalRate(float globalRate) {
		this.globalRate = globalRate;
	}

	public boolean isBorrowed() {
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
