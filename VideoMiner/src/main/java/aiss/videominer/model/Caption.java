package aiss.videominer.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

/**
 * @author Juan C. Alonso
 */
@Entity
@Table(name = "Caption")
public class Caption {

    @Id
    @JsonProperty("id")
    private String id;

    @Column(name = "link")
    @NotEmpty(message = "Caption link cannot be empty")
    @JsonProperty("link")
    @JsonAlias({"name"})
    private String link;

    @NotEmpty(message = "Caption language cannot be empty")
    @Column(name = "language")
    @JsonProperty("language")
    private String language;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getlink() {
        return link;
    }

    public void setlink(String link) {
        this.link = link;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Caption{" +
                "id='" + id + '\'' +
                ", link='" + link + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
