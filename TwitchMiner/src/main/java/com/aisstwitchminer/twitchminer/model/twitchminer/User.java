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
        "login",
        "display_name",
        "type",
        "broadcaster_type",
        "description",
        "profile_image_url",
        "offline_image_url",
        "view_count",
        "created_at"
})
@Generated("jsonschema2pojo")
public class User {

    @JsonProperty("id")
    private String id;
    @JsonProperty("login")
    private String login;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("type")
    private String type;
    @JsonProperty("broadcaster_type")
    private String broadcasterType;
    @JsonProperty("description")
    private String description;
    @JsonProperty("profile_image_url")
    private String profileImageUrl;
    @JsonProperty("offline_image_url")
    private String offlineImageUrl;
    @JsonProperty("view_count")
    private Integer viewCount;
    @JsonProperty("created_at")
    private String createdAt;
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

    @JsonProperty("login")
    public String getLogin() {
        return login;
    }

    @JsonProperty("login")
    public void setLogin(String login) {
        this.login = login;
    }

    @JsonProperty("display_name")
    public String getDisplayName() {
        return displayName;
    }

    @JsonProperty("display_name")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("broadcaster_type")
    public String getBroadcasterType() {
        return broadcasterType;
    }

    @JsonProperty("broadcaster_type")
    public void setBroadcasterType(String broadcasterType) {
        this.broadcasterType = broadcasterType;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("profile_image_url")
    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    @JsonProperty("profile_image_url")
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    @JsonProperty("offline_image_url")
    public String getOfflineImageUrl() {
        return offlineImageUrl;
    }

    @JsonProperty("offline_image_url")
    public void setOfflineImageUrl(String offlineImageUrl) {
        this.offlineImageUrl = offlineImageUrl;
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
        sb.append(User.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("login");
        sb.append('=');
        sb.append(((this.login == null)?"<null>":this.login));
        sb.append(',');
        sb.append("displayName");
        sb.append('=');
        sb.append(((this.displayName == null)?"<null>":this.displayName));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("broadcasterType");
        sb.append('=');
        sb.append(((this.broadcasterType == null)?"<null>":this.broadcasterType));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("profileImageUrl");
        sb.append('=');
        sb.append(((this.profileImageUrl == null)?"<null>":this.profileImageUrl));
        sb.append(',');
        sb.append("offlineImageUrl");
        sb.append('=');
        sb.append(((this.offlineImageUrl == null)?"<null>":this.offlineImageUrl));
        sb.append(',');
        sb.append("viewCount");
        sb.append('=');
        sb.append(((this.viewCount == null)?"<null>":this.viewCount));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null)?"<null>":this.createdAt));
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
