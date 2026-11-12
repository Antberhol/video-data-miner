package com.example.dailymotionminer.model.dailymotion;

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
"screenname",
"url",
"avatar_720_url"
})
@Generated("jsonschema2pojo")
public class User {

@JsonProperty("id")
private String id;
@JsonProperty("screenname")
private String screenname;
@JsonProperty("url")
private String url;
@JsonProperty("avatar_720_url")
private String avatar720Url;
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

@JsonProperty("screenname")
public String getScreenname() {
return screenname;
}

@JsonProperty("screenname")
public void setScreenname(String screenname) {
this.screenname = screenname;
}

@JsonProperty("url")
public String getUrl() {
return url;
}

@JsonProperty("url")
public void setUrl(String url) {
this.url = url;
}

@JsonProperty("avatar_720_url")
public String getAvatar720Url() {
return avatar720Url;
}

@JsonProperty("avatar_720_url")
public void setAvatar720Url(String avatar720Url) {
this.avatar720Url = avatar720Url;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}