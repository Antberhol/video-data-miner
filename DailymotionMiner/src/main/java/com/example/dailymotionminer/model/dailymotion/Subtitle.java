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
"language",
"url"
})
@Generated("jsonschema2pojo")
public class Subtitle {

@JsonProperty("id")
private String id;
@JsonProperty("language")
private String language;
@JsonProperty("url")
private String url;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

/**
* No args constructor for use in serialization
*
*/
public Subtitle() {
}

public Subtitle(String id, String language, String url) {
super();
this.id = id;
this.language = language;
this.url = url;
}

@JsonProperty("id")
public String getId() {
return id;
}

@JsonProperty("id")
public void setId(String id) {
this.id = id;
}

@JsonProperty("language")
public String getLanguage() {
return language;
}

@JsonProperty("language")
public void setLanguage(String language) {
this.language = language;
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
public int hashCode() {
int result = 1;
result = ((result* 31)+((this.language == null)? 0 :this.language.hashCode()));
result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
return result;
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof Subtitle) == false) {
return false;
}
Subtitle rhs = ((Subtitle) other);
return (((((this.language == rhs.language)||((this.language!= null)&&this.language.equals(rhs.language)))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))));
}

}