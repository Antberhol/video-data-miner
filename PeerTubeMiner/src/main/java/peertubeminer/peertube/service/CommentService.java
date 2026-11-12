package peertubeminer.peertube.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import peertubeminer.peertube.model.peertube.CommentResponse;

import java.util.Map;

@Service
public class CommentService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl;

    public CommentService(@Value("${peertube.base-url:https://peertube.cpy.re}") String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public CommentResponse getCommentsByVideo(Integer videoId, int maxComments) {
        String url = baseUrl + "/api/v1/videos/{videoId}/comment-threads?start=0&count={count}";
        Map<String, Object> vars = Map.of(
                "videoId", videoId,
                "count", maxComments
        );

        ResponseEntity<CommentResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                CommentResponse.class,
                vars
        );

        return response.getBody() == null ? new CommentResponse() : response.getBody();
    }
}
