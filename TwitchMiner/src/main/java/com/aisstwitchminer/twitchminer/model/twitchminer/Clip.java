package com.aisstwitchminer.twitchminer.model.twitchminer;

import java.util.LinkedHashMap;
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
"url",
"embed_url",
"broadcaster_id",
"broadcaster_name",
"creator_id",
"creator_name",
"video_id",
"game_id",
"language",
"title",
"view_count",
"created_at",
"thumbnail_url",
"duration",
"vod_offset",
"is_featured"
})
@Generated("jsonschema2pojo")
public class Clip {

@JsonProperty("id")
private String id;
@JsonProperty("url")
private String url;
@JsonProperty("embed_url")
private String embedUrl;
@JsonProperty("broadcaster_id")
private String broadcasterId;
@JsonProperty("broadcaster_name")
private String broadcasterName;
@JsonProperty("creator_id")
private String creatorId;
@JsonProperty("creator_name")
private String creatorName;
@JsonProperty("video_id")
private String videoId;
@JsonProperty("game_id")
private String gameId;
@JsonProperty("language")
private String language;
@JsonProperty("title")
private String title;
@JsonProperty("view_count")
private Integer viewCount;
@JsonProperty("created_at")
private String createdAt;
@JsonProperty("thumbnail_url")
private String thumbnailUrl;
@JsonProperty("duration")
private Double duration;
@JsonProperty("vod_offset")
private Object vodOffset;
@JsonProperty("is_featured")
private Boolean isFeatured;
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

@JsonProperty("url")
public String getUrl() {
return url;
}

@JsonProperty("url")
public void setUrl(String url) {
this.url = url;
}

@JsonProperty("embed_url")
public String getEmbedUrl() {
return embedUrl;
}

@JsonProperty("embed_url")
public void setEmbedUrl(String embedUrl) {
this.embedUrl = embedUrl;
}

@JsonProperty("broadcaster_id")
public String getBroadcasterId() {
return broadcasterId;
}

@JsonProperty("broadcaster_id")
public void setBroadcasterId(String broadcasterId) {
this.broadcasterId = broadcasterId;
}

@JsonProperty("broadcaster_name")
public String getBroadcasterName() {
return broadcasterName;
}

@JsonProperty("broadcaster_name")
public void setBroadcasterName(String broadcasterName) {
this.broadcasterName = broadcasterName;
}

@JsonProperty("creator_id")
public String getCreatorId() {
return creatorId;
}

@JsonProperty("creator_id")
public void setCreatorId(String creatorId) {
this.creatorId = creatorId;
}

@JsonProperty("creator_name")
public String getCreatorName() {
return creatorName;
}

@JsonProperty("creator_name")
public void setCreatorName(String creatorName) {
this.creatorName = creatorName;
}

@JsonProperty("video_id")
public String getVideoId() {
return videoId;
}

@JsonProperty("video_id")
public void setVideoId(String videoId) {
this.videoId = videoId;
}

@JsonProperty("game_id")
public String getGameId() {
return gameId;
}

@JsonProperty("game_id")
public void setGameId(String gameId) {
this.gameId = gameId;
}

@JsonProperty("language")
public String getLanguage() {
return language;
}

@JsonProperty("language")
public void setLanguage(String language) {
this.language = language;
}

@JsonProperty("title")
public String getTitle() {
return title;
}

@JsonProperty("title")
public void setTitle(String title) {
this.title = title;
}

@JsonProperty("view_count")
public Integer getViewCount() {
return viewCount;
}

@JsonProperty("view_count")
public void setViewCount(Integer viewCount) {
this.viewCount = viewCount;
}

@JsonProperty("created_at")
public String getCreatedAt() {
return createdAt;
}

@JsonProperty("created_at")
public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

@JsonProperty("thumbnail_url")
public String getThumbnailUrl() {
return thumbnailUrl;
}

@JsonProperty("thumbnail_url")
public void setThumbnailUrl(String thumbnailUrl) {
this.thumbnailUrl = thumbnailUrl;
}

@JsonProperty("duration")
public Double getDuration() {
return duration;
}

@JsonProperty("duration")
public void setDuration(Double duration) {
this.duration = duration;
}

@JsonProperty("vod_offset")
public Object getVodOffset() {
return vodOffset;
}

@JsonProperty("vod_offset")
public void setVodOffset(Object vodOffset) {
this.vodOffset = vodOffset;
}

@JsonProperty("is_featured")
public Boolean getIsFeatured() {
return isFeatured;
}

@JsonProperty("is_featured")
public void setIsFeatured(Boolean isFeatured) {
this.isFeatured = isFeatured;
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
sb.append(Clip.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
sb.append("id");
sb.append('=');
sb.append(((this.id == null)?"<null>":this.id));
sb.append(',');
sb.append("url");
sb.append('=');
sb.append(((this.url == null)?"<null>":this.url));
sb.append(',');
sb.append("embedUrl");
sb.append('=');
sb.append(((this.embedUrl == null)?"<null>":this.embedUrl));
sb.append(',');
sb.append("broadcasterId");
sb.append('=');
sb.append(((this.broadcasterId == null)?"<null>":this.broadcasterId));
sb.append(',');
sb.append("broadcasterName");
sb.append('=');
sb.append(((this.broadcasterName == null)?"<null>":this.broadcasterName));
sb.append(',');
sb.append("creatorId");
sb.append('=');
sb.append(((this.creatorId == null)?"<null>":this.creatorId));
sb.append(',');
sb.append("creatorName");
sb.append('=');
sb.append(((this.creatorName == null)?"<null>":this.creatorName));
sb.append(',');
sb.append("videoId");
sb.append('=');
sb.append(((this.videoId == null)?"<null>":this.videoId));
sb.append(',');
sb.append("gameId");
sb.append('=');
sb.append(((this.gameId == null)?"<null>":this.gameId));
sb.append(',');
sb.append("language");
sb.append('=');
sb.append(((this.language == null)?"<null>":this.language));
sb.append(',');
sb.append("title");
sb.append('=');
sb.append(((this.title == null)?"<null>":this.title));
sb.append(',');
sb.append("viewCount");
sb.append('=');
sb.append(((this.viewCount == null)?"<null>":this.viewCount));
sb.append(',');
sb.append("createdAt");
sb.append('=');
sb.append(((this.createdAt == null)?"<null>":this.createdAt));
sb.append(',');
sb.append("thumbnailUrl");
sb.append('=');
sb.append(((this.thumbnailUrl == null)?"<null>":this.thumbnailUrl));
sb.append(',');
sb.append("duration");
sb.append('=');
sb.append(((this.duration == null)?"<null>":this.duration));
sb.append(',');
sb.append("vodOffset");
sb.append('=');
sb.append(((this.vodOffset == null)?"<null>":this.vodOffset));
sb.append(',');
sb.append("isFeatured");
sb.append('=');
sb.append(((this.isFeatured == null)?"<null>":this.isFeatured));
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
