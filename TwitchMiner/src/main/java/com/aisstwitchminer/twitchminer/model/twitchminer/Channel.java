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
"broadcaster_id",
"broadcaster_login",
"broadcaster_name",
"broadcaster_language",
"game_id",
"game_name",
"title",
"delay",
"tags",
"content_classification_labels",
"is_branded_content"
})
@Generated("jsonschema2pojo")
public class Channel {

    @JsonProperty("broadcaster_id")
    private String BroadcasterId;
    @JsonProperty("broadcaster_login")
    private String BroadcasterLogin;
    @JsonProperty("broadcaster_name")
    private String BroadcasterName;
    @JsonProperty("broadcaster_language")
    private String BroadcasterLanguage;
    @JsonProperty("game_id")
    private String gameId;
    @JsonProperty("game_name")
    private String gameName;
    @JsonProperty("title")
    private String title;
    @JsonProperty("delay")
    private Integer delay;
    @JsonProperty("tags")
    private List<String> tags;
    @JsonProperty("content_classification_labels")
    private List<Object> contentClassificationLabels;
    @JsonProperty("is_branded_content")
    private Boolean isBrandedContent;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("broadcaster_id")
    public String getBroadcasterId() {
        return BroadcasterId;
    }

    @JsonProperty("broadcaster_id")
    public void setBroadcasterId(String BroadcasterId) {
        this.BroadcasterId = BroadcasterId;
    }

    @JsonProperty("broadcaster_login")
    public String getBroadcasterLogin() {
        return BroadcasterLogin;
    }

    @JsonProperty("broadcaster_login")
    public void setBroadcasterLogin(String BroadcasterLogin) {
        this.BroadcasterLogin = BroadcasterLogin;
    }

    @JsonProperty("broadcaster_name")
    public String getBroadcasterName() {
        return BroadcasterName;
    }

    @JsonProperty("broadcaster_name")
    public void setBroadcasterName(String BroadcasterName) {
        this.BroadcasterName = BroadcasterName;
    }

    @JsonProperty("broadcaster_language")
    public String getBroadcasterLanguage() {
        return BroadcasterLanguage;
    }

    @JsonProperty("broadcaster_language")
    public void setBroadcasterLanguage(String BroadcasterLanguage) {
        this.BroadcasterLanguage = BroadcasterLanguage;
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

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("delay")
    public Integer getDelay() {
        return delay;
    }

    @JsonProperty("delay")
    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @JsonProperty("content_classification_labels")
    public List<Object> getContentClassificationLabels() {
        return contentClassificationLabels;
    }

    @JsonProperty("content_classification_labels")
    public void setContentClassificationLabels(List<Object> contentClassificationLabels) {
        this.contentClassificationLabels = contentClassificationLabels;
    }

    @JsonProperty("is_branded_content")
    public Boolean getIsBrandedContent() {
        return isBrandedContent;
    }

    @JsonProperty("is_branded_content")
    public void setIsBrandedContent(Boolean isBrandedContent) {
        this.isBrandedContent = isBrandedContent;
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
        sb.append(Channel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("BroadcasterId");
        sb.append('=');
        sb.append(((this.BroadcasterId == null) ? "<null>" : this.BroadcasterId));
        sb.append(',');
        sb.append("BroadcasterLogin");
        sb.append('=');
        sb.append(((this.BroadcasterLogin == null) ? "<null>" : this.BroadcasterLogin));
        sb.append(',');
        sb.append("BroadcasterName");
        sb.append('=');
        sb.append(((this.BroadcasterName == null) ? "<null>" : this.BroadcasterName));
        sb.append(',');
        sb.append("BroadcasterLanguage");
        sb.append('=');
        sb.append(((this.BroadcasterLanguage == null) ? "<null>" : this.BroadcasterLanguage));
        sb.append(',');
        sb.append("gameId");
        sb.append('=');
        sb.append(((this.gameId == null) ? "<null>" : this.gameId));
        sb.append(',');
        sb.append("gameName");
        sb.append('=');
        sb.append(((this.gameName == null) ? "<null>" : this.gameName));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null) ? "<null>" : this.title));
        sb.append(',');
        sb.append("delay");
        sb.append('=');
        sb.append(((this.delay == null) ? "<null>" : this.delay));
        sb.append(',');
        sb.append("tags");
        sb.append('=');
        sb.append(((this.tags == null) ? "<null>" : this.tags));
        sb.append(',');
        sb.append("contentClassificationLabels");
        sb.append('=');
        sb.append(((this.contentClassificationLabels == null) ? "<null>" : this.contentClassificationLabels));
        sb.append(',');
        sb.append("isBrandedContent");
        sb.append('=');
        sb.append(((this.isBrandedContent == null) ? "<null>" : this.isBrandedContent));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null) ? "<null>" : this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}