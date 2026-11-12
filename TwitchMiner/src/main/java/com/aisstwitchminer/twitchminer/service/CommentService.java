package com.aisstwitchminer.twitchminer.service;

import com.aisstwitchminer.twitchminer.exception.ExternalServiceException;
import com.aisstwitchminer.twitchminer.exception.ResourceNotFoundException;
import com.aisstwitchminer.twitchminer.model.twitchminer.ClipResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Map;

@Service
public class CommentService {

	private final RestTemplate restTemplate;
	private final String baseUrl;

	public CommentService(RestTemplate restTemplate, @Value("${twitch.base-url:https://api.twitch.tv/helix}") String baseUrl) {
		this.restTemplate = restTemplate;
		this.baseUrl = baseUrl;
	}

	public ClipResponse getClipsByBroadcaster(String broadcasterId) {
		try {
			String url = baseUrl + "/clips?broadcaster_id={broadcasterId}&first=100";
			ResponseEntity<ClipResponse> response = restTemplate.exchange(
					url,
					HttpMethod.GET,
					HttpEntity.EMPTY,
					ClipResponse.class,
					Map.of("broadcasterId", broadcasterId)
			);
			ClipResponse body = response.getBody();
			if (body == null) {
				body = new ClipResponse();
			}
			if (body.getData() == null) {
				body.setData(new ArrayList<>());
			}
			return body;
		} catch (HttpClientErrorException.NotFound ex) {
			throw new ResourceNotFoundException("Canal no encontrado para clips: " + broadcasterId);
		} catch (RuntimeException ex) {
			throw new ExternalServiceException("Error consultando clips en Twitch", ex);
		}
	}
}
