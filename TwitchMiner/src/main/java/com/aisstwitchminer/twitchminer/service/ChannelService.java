package com.aisstwitchminer.twitchminer.service;

import com.aisstwitchminer.twitchminer.exception.ExternalServiceException;
import com.aisstwitchminer.twitchminer.exception.ResourceNotFoundException;
import com.aisstwitchminer.twitchminer.model.twitchminer.Channel;
import com.aisstwitchminer.twitchminer.model.twitchminer.ChannelResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ChannelService {

	private final RestTemplate restTemplate;
	private final String baseUrl;

	public ChannelService(RestTemplate restTemplate, @Value("${twitch.base-url:https://api.twitch.tv/helix}") String baseUrl) {
		this.restTemplate = restTemplate;
		this.baseUrl = baseUrl;
	}

	public Channel getChannel(String broadcasterId) {
		try {
			String url = baseUrl + "/channels?broadcaster_id={broadcasterId}";
			ResponseEntity<ChannelResponse> response = restTemplate.exchange(
					url,
					HttpMethod.GET,
					HttpEntity.EMPTY,
					ChannelResponse.class,
					Map.of("broadcasterId", broadcasterId)
			);
			ChannelResponse body = response.getBody();
			if (body == null || body.getData() == null || body.getData().isEmpty()) {
				throw new ResourceNotFoundException("Canal no encontrado: " + broadcasterId);
			}
			return body.getData().getFirst();
		} catch (ResourceNotFoundException ex) {
			throw ex;
		} catch (HttpClientErrorException.NotFound | HttpClientErrorException.BadRequest ex) {
			throw new ResourceNotFoundException("Canal no encontrado: " + broadcasterId);
		} catch (RuntimeException ex) {
			throw new ExternalServiceException("Error consultando canal en Twitch", ex);
		}
	}
}
