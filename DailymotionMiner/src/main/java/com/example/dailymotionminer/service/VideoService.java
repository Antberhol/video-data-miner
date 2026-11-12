package com.example.dailymotionminer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.dailymotionminer.model.dailymotion.VideoResponse;

import java.util.Map;

@Service
public class VideoService {

	private final RestTemplate restTemplate = new RestTemplate();
	private final String baseUrl;

	public VideoService(@Value("${dailymotion.base-url}") String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public VideoResponse getVideosByChannel(String channelHandle, int maxVideos, int maxPages) {
			java.util.List<com.example.dailymotionminer.model.dailymotion.Video> allVideos = new java.util.ArrayList<>();
			for (int page = 1; page <= maxPages; page++) {
					String url = baseUrl + "/channel/{channelHandle}/videos?fields=id,title,description,created_time,tags,owner&limit={limit}&page={page}";
					Map<String, Object> vars = Map.of(
							"channelHandle", channelHandle,
							"limit", maxVideos,
							"page", page
					);
					ResponseEntity<VideoResponse> response = restTemplate.exchange(
							url,
							HttpMethod.GET,
							HttpEntity.EMPTY,
							VideoResponse.class,
							vars
					);
					VideoResponse body = response.getBody();
					if (body != null && body.getVideo() != null) {
							allVideos.addAll(body.getVideo());
							if (body.getHasMore() != null && !body.getHasMore()) {
									break;
							}
					} else {
							break;
					}
			}
			VideoResponse result = new VideoResponse();
			result.setVideo(allVideos);
			return result;
	}
}
