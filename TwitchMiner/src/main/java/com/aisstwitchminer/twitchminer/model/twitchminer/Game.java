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
"name",
"box_art_url",
"igdb_id"
})
@Generated("jsonschema2pojo")
public class Game {

@JsonProperty("id")
private String id;
@JsonProperty("name")
private String name;
@JsonProperty("box_art_url")
private String boxArtUrl;
@JsonProperty("igdb_id")
private String igdbId;
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

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("box_art_url")
public String getBoxArtUrl() {
return boxArtUrl;
}

@JsonProperty("box_art_url")
public void setBoxArtUrl(String boxArtUrl) {
this.boxArtUrl = boxArtUrl;
}

@JsonProperty("igdb_id")
public String getIgdbId() {
return igdbId;
}

@JsonProperty("igdb_id")
public void setIgdbId(String igdbId) {
this.igdbId = igdbId;
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
sb.append(Game.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
sb.append("id");
sb.append('=');
sb.append(((this.id == null)?"<null>":this.id));
sb.append(',');
sb.append("name");
sb.append('=');
sb.append(((this.name == null)?"<null>":this.name));
sb.append(',');
sb.append("boxArtUrl");
sb.append('=');
sb.append(((this.boxArtUrl == null)?"<null>":this.boxArtUrl));
sb.append(',');
sb.append("igdbId");
sb.append('=');
sb.append(((this.igdbId == null)?"<null>":this.igdbId));
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
