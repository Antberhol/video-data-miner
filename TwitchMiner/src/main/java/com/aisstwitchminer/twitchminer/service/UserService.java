package com.aisstwitchminer.twitchminer.service;

import com.aisstwitchminer.twitchminer.exception.ExternalServiceException;
import com.aisstwitchminer.twitchminer.exception.ResourceNotFoundException;
import com.aisstwitchminer.twitchminer.model.twitchminer.User;
import com.aisstwitchminer.twitchminer.model.twitchminer.UserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class UserService {

	private final RestTemplate restTemplate;
	private final String baseUrl;

	public UserService(RestTemplate restTemplate, @Value("${twitch.base-url:https://api.twitch.tv/helix}") String baseUrl) {
		this.restTemplate = restTemplate;
		this.baseUrl = baseUrl;
	}

	public User getUser(String userId) {
		try {
			String url = baseUrl + "/users?id={userId}";
			ResponseEntity<UserResponse> response = restTemplate.exchange(
					url,
					HttpMethod.GET,
					HttpEntity.EMPTY,
					UserResponse.class,
					Map.of("userId", userId)
			);
			UserResponse body = response.getBody();
			if (body == null || body.getData() == null || body.getData().isEmpty()) {
				throw new ResourceNotFoundException("User no encontrado: " + userId);
			}
			return body.getData().getFirst();
		} catch (ResourceNotFoundException ex) {
			throw ex;
		} catch (HttpClientErrorException.NotFound | HttpClientErrorException.BadRequest ex) {
			throw new ResourceNotFoundException("User no encontrado: " + userId);
		} catch (RuntimeException ex) {
			throw new ExternalServiceException("Error consultando User en Twitch", ex);
		}
	}
}
