package peertubeminer.peertube.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoResponse {
    private Integer total;
    private List<Video> data = new ArrayList<>();

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Video> getData() {
        return data;
    }

    public void setData(List<Video> data) {
        this.data = data;
    }
}

