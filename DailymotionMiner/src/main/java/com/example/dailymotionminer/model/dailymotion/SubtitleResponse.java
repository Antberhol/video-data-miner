package com.example.dailymotionminer.model.dailymotion;

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
        "page",
        "limit",
        "explicit",
        "total",
        "has_more",
        "subtitle"
})
@Generated("jsonschema2pojo")
public class SubtitleResponse {

    @JsonProperty("page")
    private Integer page;
    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("explicit")
    private Boolean explicit;
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("has_more")
    private Boolean hasMore;
    @JsonProperty("subtitle")
    private List<Subtitle> subtitle;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public SubtitleResponse() {
    }

    public SubtitleResponse(Integer page, Integer limit, Boolean explicit, Integer total, Boolean hasMore, List<Subtitle> subtitle) {
        super();
        this.page = page;
        this.limit = limit;
        this.explicit = explicit;
        this.total = total;
        this.hasMore = hasMore;
        this.subtitle = subtitle;
    }

    @JsonProperty("page")
    public Integer getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(Integer page) {
        this.page = page;
    }

    @JsonProperty("limit")
    public Integer getLimit() {
        return limit;
    }

    @JsonProperty("limit")
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @JsonProperty("explicit")
    public Boolean getExplicit() {
        return explicit;
    }

    @JsonProperty("explicit")
    public void setExplicit(Boolean explicit) {
        this.explicit = explicit;
    }

    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
    }

    @JsonProperty("has_more")
    public Boolean getHasMore() {
        return hasMore;
    }

    @JsonProperty("has_more")
    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    @JsonProperty("subtitle")
    public List<Subtitle> getSubtitle() {
        return subtitle;
    }

    @JsonProperty("subtitle")
    public void setSubtitle(List<Subtitle> subtitle) {
        this.subtitle = subtitle;
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
        result = ((result* 31)+((this.explicit == null)? 0 :this.explicit.hashCode()));
        result = ((result* 31)+((this.total == null)? 0 :this.total.hashCode()));
        result = ((result* 31)+((this.subtitle == null)? 0 :this.subtitle.hashCode()));
        result = ((result* 31)+((this.limit == null)? 0 :this.limit.hashCode()));
        result = ((result* 31)+((this.hasMore == null)? 0 :this.hasMore.hashCode()));
        result = ((result* 31)+((this.page == null)? 0 :this.page.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SubtitleResponse) == false) {
            return false;
        }
        SubtitleResponse rhs = ((SubtitleResponse) other);
        return ((((((((this.explicit == rhs.explicit)||((this.explicit!= null)&&this.explicit.equals(rhs.explicit)))&&((this.total == rhs.total)||((this.total!= null)&&this.total.equals(rhs.total))))&&((this.subtitle == rhs.subtitle)||((this.subtitle!= null)&&this.subtitle.equals(rhs.subtitle))))&&((this.limit == rhs.limit)||((this.limit!= null)&&this.limit.equals(rhs.limit))))&&((this.hasMore == rhs.hasMore)||((this.hasMore!= null)&&this.hasMore.equals(rhs.hasMore))))&&((this.page == rhs.page)||((this.page!= null)&&this.page.equals(rhs.page))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}