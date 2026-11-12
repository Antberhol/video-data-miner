package com.aisstwitchminer.twitchminer.service;

import com.aisstwitchminer.twitchminer.exception.ExternalServiceException;
import com.aisstwitchminer.twitchminer.exception.ResourceNotFoundException;
import com.aisstwitchminer.twitchminer.model.twitchminer.StreamResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class VideoService {

	private final RestTemplate restTemplate;
	private final String baseUrl;

	public VideoService(RestTemplate restTemplate, @Value("${twitch.base-url:https://api.twitch.tv/helix}") String baseUrl) {
		this.restTemplate = restTemplate;
		this.baseUrl = baseUrl;
	}

	public StreamResponse getVideosByChannel(String broadcasterId, int maxVideos) {
		if (maxVideos == 0) {
			StreamResponse empty = new StreamResponse();
			empty.setData(new ArrayList<>());
			return empty;
		}
		try {
			String url = baseUrl + "/videos?user_id={broadcasterId}&first={first}";
			ResponseEntity<StreamResponse> response = restTemplate.exchange(
					url,
					HttpMethod.GET,
					HttpEntity.EMPTY,
					StreamResponse.class,
					Map.of("broadcasterId", broadcasterId, "first", Math.min(maxVideos, 100))
			);
			StreamResponse body = response.getBody();
			if (body == null) {
				body = new StreamResponse();
			}
			if (body.getData() == null) {
				body.setData(new ArrayList<>());
			}
			return body;
		} catch (HttpClientErrorException.NotFound ex) {
			throw new ResourceNotFoundException("Canal no encontrado para videos: " + broadcasterId);
		} catch (RuntimeException ex) {
			throw new ExternalServiceException("Error consultando videos en Twitch", ex);
		}
	}

	public static String joinedTags(List<String> tags) {
		if (tags == null || tags.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (String tag : tags) {
			if (tag != null && !tag.trim().isEmpty()) {
				if (!sb.isEmpty()) {
					sb.append(", ");
				}
				sb.append(tag.trim());
			}
		}
		return sb.toString();
	}
}
