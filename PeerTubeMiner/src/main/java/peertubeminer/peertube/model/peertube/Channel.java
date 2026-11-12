package peertubeminer.peertube.model.peertube;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {
    // Estos son los únicos datos que necesitas para tu proyecto
    private Integer id;
    private String displayName; // PeerTube usa displayName para el nombre visible
    private String description;
    private String createdAt;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", displayName='" + displayName + '\'' +
                ", description='" + description + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
