package peertubeminer.peertube.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Caption {
    private Integer id;
    private Language language;
    private Boolean automaticallyGenerated;
    private String captionPath;
    private String fileUrl;
    private String m3u8Url;
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Boolean getAutomaticallyGenerated() {
        return automaticallyGenerated;
    }

    public void setAutomaticallyGenerated(Boolean automaticallyGenerated) {
        this.automaticallyGenerated = automaticallyGenerated;
    }

    public String getCaptionPath() {
        return captionPath;
    }

    public void setCaptionPath(String captionPath) {
        this.captionPath = captionPath;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getM3u8Url() {
        return m3u8Url;
    }

    public void setM3u8Url(String m3u8Url) {
        this.m3u8Url = m3u8Url;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}

