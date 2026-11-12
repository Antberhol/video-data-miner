package com.example.dailymotionminer.service;

import com.example.dailymotionminer.model.dailymotion.SubtitleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class SubtitleService {

	// Variable para inyectar RestTemplate (refactorizado por IA)
	private final RestTemplate restTemplate = new RestTemplate();
	private static String baseUrl;

	public SubtitleService(@Value("${dailymotion.base-url:https://api.dailymotion.com}") String baseUrl) {
		SubtitleService.baseUrl = baseUrl;
	}

	public SubtitleResponse getSubtitlesByVideo(String videoId) {
			String url = baseUrl + "/video/{videoId}/subtitles?fields=id,language,url";
			Map<String, Object> vars = Map.of("videoId", videoId);
			ResponseEntity<SubtitleResponse> response = restTemplate.exchange(
							url,
							HttpMethod.GET,
							HttpEntity.EMPTY,
							SubtitleResponse.class,
							vars
			);
			SubtitleResponse body = response.getBody();
			return body == null ? new SubtitleResponse() : body;
	}
}
