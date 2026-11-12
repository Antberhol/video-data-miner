package peertubeminer.peertube.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptionResponse {
    private Integer total;
    private List<Caption> data = new ArrayList<>();

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Caption> getData() {
        return data;
    }

    public void setData(List<Caption> data) {
        this.data = data;
    }
}

