package com.aisstwitchminer.twitchminer.model.videominer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VMUser {
	private String id;
	private String name;

	@JsonProperty("user_link")
	private String userLink;

	@JsonProperty("picture_link")
	private String pictureLink;

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

	public String getUserLink() {
		return userLink;
	}

	public void setUserLink(String userLink) {
		this.userLink = userLink;
	}

	public String getPictureLink() {
		return pictureLink;
	}

	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
	}
}
