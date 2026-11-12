package peertubeminer.peertube.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import peertubeminer.peertube.model.peertube.VideoResponse;

import java.util.Map;

@Service
public class VideoService {

	private final RestTemplate restTemplate = new RestTemplate();
	private final String baseUrl;

	public VideoService(@Value("${peertube.base-url:https://peertube.cpy.re}") String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public VideoResponse getVideosByChannel(String channelHandle, int maxVideos) {
		String url = baseUrl + "/api/v1/video-channels/{channelHandle}/videos?start=0&count={count}";
		Map<String, Object> vars = Map.of(
				"channelHandle", channelHandle,
				"count", maxVideos
		);
		ResponseEntity<VideoResponse> response = restTemplate.exchange(
				url,
				HttpMethod.GET,
				HttpEntity.EMPTY,
				VideoResponse.class,
				vars
		);
		VideoResponse body = response.getBody();
		return body == null ? new VideoResponse() : body;
	}
}
