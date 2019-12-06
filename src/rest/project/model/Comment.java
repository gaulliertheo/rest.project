package rest.project.model;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Comment {

	@XmlTransient
	private Integer mediaID;
	
	private Integer userID;
	private String description;
	private float rate;

	public Comment() {

	}

	public Comment(Integer mediaID, Integer userID, String description, float rate) {
		super();
		this.mediaID = mediaID;
		this.userID = userID;
		this.description = description;
		this.rate = rate;
	}

	public Integer getMediaID() {
		return mediaID;
	}

	public void setMediaID(Integer mediaID) {
		this.mediaID = mediaID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

}
