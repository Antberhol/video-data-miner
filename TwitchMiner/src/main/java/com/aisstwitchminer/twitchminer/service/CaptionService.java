package com.aisstwitchminer.twitchminer.service;

import com.aisstwitchminer.twitchminer.exception.ExternalServiceException;
import com.aisstwitchminer.twitchminer.exception.ResourceNotFoundException;
import com.aisstwitchminer.twitchminer.model.twitchminer.GameResponse;
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
public class CaptionService {

	private final RestTemplate restTemplate;
	private final String baseUrl;

	public CaptionService(RestTemplate restTemplate, @Value("${twitch.base-url:https://api.twitch.tv/helix}") String baseUrl) {
		this.restTemplate = restTemplate;
		this.baseUrl = baseUrl;
	}

	public GameResponse getGame(String gameId) {
		GameResponse empty = new GameResponse();
		empty.setData(new ArrayList<>());
		if (gameId == null || gameId.isBlank()) {
			return empty;
		}
		try {
			String url = baseUrl + "/games?id={gameId}";
			ResponseEntity<GameResponse> response = restTemplate.exchange(
					url,
					HttpMethod.GET,
					HttpEntity.EMPTY,
					GameResponse.class,
					Map.of("gameId", gameId)
			);
			GameResponse body = response.getBody();
			return body == null || body.getData() == null ? empty : body;
		} catch (HttpClientErrorException.NotFound ex) {
			return empty;
		} catch (RuntimeException ex) {
			throw new ExternalServiceException("Error consultando juegos en Twitch", ex);
		}
	}
}
