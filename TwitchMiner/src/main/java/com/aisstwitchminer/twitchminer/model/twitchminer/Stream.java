package com.aisstwitchminer.twitchminer.model.twitchminer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"user_id",
"user_login",
"user_name",
"game_id",
"game_name",
"type",
"title",
"description",
"created_at",
"published_at",
"url",
"viewer_count",
"started_at",
"language",
"thumbnail_url",
"tag_ids",
"tags",
"is_mature"
})
@Generated("jsonschema2pojo")
public class Stream {

@JsonProperty("id")
private String id;
@JsonProperty("user_id")
private String userId;
@JsonProperty("user_login")
private String userLogin;
@JsonProperty("user_name")
private String userName;
@JsonProperty("game_id")
private String gameId;
@JsonProperty("game_name")
private String gameName;
@JsonProperty("type")
private String type;
@JsonProperty("title")
private String title;
@JsonProperty("description")
private String description;
@JsonProperty("created_at")
private String createdAt;
@JsonProperty("published_at")
private String publishedAt;
@JsonProperty("url")
private String url;
@JsonProperty("viewer_count")
private Integer viewerCount;
@JsonProperty("started_at")
private String startedAt;
@JsonProperty("language")
private String language;
@JsonProperty("thumbnail_url")
private String thumbnailUrl;
@JsonProperty("tag_ids")
private List<Object> tagIds;
@JsonProperty("tags")
private List<String> tags;
@JsonProperty("is_mature")
private Boolean isMature;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("id")
public String getId() {
return id;
}

@JsonProperty("id")
public void setId(String id) {
this.id = id;
}

@JsonProperty("user_id")
public String getUserId() {
return userId;
}

@JsonProperty("user_id")
public void setUserId(String userId) {
this.userId = userId;
}

@JsonProperty("user_login")
public String getUserLogin() {
return userLogin;
}

@JsonProperty("user_login")
public void setUserLogin(String userLogin) {
this.userLogin = userLogin;
}

@JsonProperty("user_name")
public String getUserName() {
return userName;
}

@JsonProperty("user_name")
public void setUserName(String userName) {
this.userName = userName;
}

@JsonProperty("game_id")
public String getGameId() {
return gameId;
}

@JsonProperty("game_id")
public void setGameId(String gameId) {
this.gameId = gameId;
}

@JsonProperty("game_name")
public String getGameName() {
return gameName;
}

@JsonProperty("game_name")
public void setGameName(String gameName) {
this.gameName = gameName;
}

@JsonProperty("type")
public String getType() {
return type;
}

@JsonProperty("type")
public void setType(String type) {
this.type = type;
}

@JsonProperty("title")
public String getTitle() {
return title;
}

@JsonProperty("title")
public void setTitle(String title) {
this.title = title;
}

@JsonProperty("viewer_count")
public Integer getViewerCount() {
return viewerCount;
}

@JsonProperty("viewer_count")
public void setViewerCount(Integer viewerCount) {
this.viewerCount = viewerCount;
}

@JsonProperty("started_at")
public String getStartedAt() {
return startedAt;
}

@JsonProperty("started_at")
public void setStartedAt(String startedAt) {
this.startedAt = startedAt;
}

@JsonProperty("language")
public String getLanguage() {
return language;
}

@JsonProperty("language")
public void setLanguage(String language) {
this.language = language;
}

@JsonProperty("thumbnail_url")
public String getThumbnailUrl() {
return thumbnailUrl;
}

@JsonProperty("thumbnail_url")
public void setThumbnailUrl(String thumbnailUrl) {
this.thumbnailUrl = thumbnailUrl;
}

@JsonProperty("tag_ids")
public List<Object> getTagIds() {
return tagIds;
}

@JsonProperty("tag_ids")
public void setTagIds(List<Object> tagIds) {
this.tagIds = tagIds;
}

@JsonProperty("tags")
public List<String> getTags() {
return tags;
}

@JsonProperty("tags")
public void setTags(List<String> tags) {
this.tags = tags;
}

@JsonProperty("is_mature")
public Boolean getIsMature() {
return isMature;
}

@JsonProperty("is_mature")
public void setIsMature(Boolean isMature) {
this.isMature = isMature;
}

@JsonProperty("description")
public String getDescription() {
return description;
}

@JsonProperty("description")
public void setDescription(String description) {
this.description = description;
}

@JsonProperty("created_at")
public String getCreatedAt() {
return createdAt;
}

@JsonProperty("created_at")
public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

@JsonProperty("published_at")
public String getPublishedAt() {
return publishedAt;
}

@JsonProperty("published_at")
public void setPublishedAt(String publishedAt) {
this.publishedAt = publishedAt;
}

@JsonProperty("url")
public String getUrl() {
return url;
}

@JsonProperty("url")
public void setUrl(String url) {
this.url = url;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(Stream.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
sb.append("id");
sb.append('=');
sb.append(((this.id == null)?"<null>":this.id));
sb.append(',');
sb.append("userId");
sb.append('=');
sb.append(((this.userId == null)?"<null>":this.userId));
sb.append(',');
sb.append("userLogin");
sb.append('=');
sb.append(((this.userLogin == null)?"<null>":this.userLogin));
sb.append(',');
sb.append("userName");
sb.append('=');
sb.append(((this.userName == null)?"<null>":this.userName));
sb.append(',');
sb.append("gameId");
sb.append('=');
sb.append(((this.gameId == null)?"<null>":this.gameId));
sb.append(',');
sb.append("gameName");
sb.append('=');
sb.append(((this.gameName == null)?"<null>":this.gameName));
sb.append(',');
sb.append("type");
sb.append('=');
sb.append(((this.type == null)?"<null>":this.type));
sb.append(',');
sb.append("title");
sb.append('=');
sb.append(((this.title == null)?"<null>":this.title));
sb.append(',');
sb.append("viewerCount");
sb.append('=');
sb.append(((this.viewerCount == null)?"<null>":this.viewerCount));
sb.append(',');
sb.append("startedAt");
sb.append('=');
sb.append(((this.startedAt == null)?"<null>":this.startedAt));
sb.append(',');
sb.append("language");
sb.append('=');
sb.append(((this.language == null)?"<null>":this.language));
sb.append(',');
sb.append("thumbnailUrl");
sb.append('=');
sb.append(((this.thumbnailUrl == null)?"<null>":this.thumbnailUrl));
sb.append(',');
sb.append("tagIds");
sb.append('=');
sb.append(((this.tagIds == null)?"<null>":this.tagIds));
sb.append(',');
sb.append("tags");
sb.append('=');
sb.append(((this.tags == null)?"<null>":this.tags));
sb.append(',');
sb.append("isMature");
sb.append('=');
sb.append(((this.isMature == null)?"<null>":this.isMature));
sb.append(',');
sb.append("additionalProperties");
sb.append('=');
sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
sb.append(',');
if (sb.charAt((sb.length()- 1)) == ',') {
sb.setCharAt((sb.length()- 1), ']');
} else {
sb.append(']');
}
return sb.toString();
}

}


