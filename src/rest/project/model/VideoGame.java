package rest.project.model;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class VideoGame {

	private int id;
	private int userID;
	private String name;
	private String kind;
	private String developer;
	private Date publishingDate;
	private String support;
	private String description;
	private float globalRate;
	private boolean borrowed;

	@XmlElementWrapper
	@XmlElement(name="comment")
	private List<Comment> comments;

	public VideoGame() {

	}

	
	public VideoGame(int userID, String name, String kind, String developer, Date publishingDate,
			String support, String description) {
		super();
		this.name = name;
		this.kind = kind;
		this.developer = developer;
		this.publishingDate = publishingDate;
		this.support = support;
		this.description = description;
		this.userID = userID;
		this.comments = new ArrayList<Comment>();
	}
	
	public VideoGame(int id, int userID, String name, String kind, String developer, Date publishingDate,
			String support, String description, float globalRate, boolean borrowed) {
		super();
		this.name = name;
		this.kind = kind;
		this.developer = developer;
		this.publishingDate = publishingDate;
		this.support = support;
		this.description = description;
		this.userID = userID;
		this.id = id;
		this.globalRate = globalRate;
		this.borrowed = borrowed;
		this.comments = new ArrayList<Comment>();
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

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public Date getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}

	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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