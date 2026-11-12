package peertubeminer.peertube.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentResponse {
    private Integer total;
    private Integer totalNotDeletedComments;
    private List<Comment> data = new ArrayList<>();

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalNotDeletedComments() {
        return totalNotDeletedComments;
    }

    public void setTotalNotDeletedComments(Integer totalNotDeletedComments) {
        this.totalNotDeletedComments = totalNotDeletedComments;
    }

    public List<Comment> getData() {
        return data;
    }

    public void setData(List<Comment> data) {
        this.data = data;
    }
}

