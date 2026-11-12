package peertubeminer.peertube.model.videominer;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VMVideo {
	private String id;
	private String name;
	private String description;
	private String releaseTime;
	private List<VMCaption> captions = new ArrayList<>();
	private List<VMComment> comments = new ArrayList<>();
	private VMUser user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public List<VMCaption> getCaptions() {
		return captions;
	}

	public void setCaptions(List<VMCaption> captions) {
		this.captions = captions;
	}

	public List<VMComment> getComments() {
		return comments;
	}

	public void setComments(List<VMComment> comments) {
		this.comments = comments;
	}

	public VMUser getUser() {
		return user;
	}

	public void setUser(VMUser user) {
		this.user = user;
	}
}
