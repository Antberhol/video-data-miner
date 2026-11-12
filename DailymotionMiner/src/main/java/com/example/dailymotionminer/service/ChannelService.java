package com.example.dailymotionminer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.dailymotionminer.model.dailymotion.Channel;

import java.util.Map;

@Service
public class ChannelService {

	private final RestTemplate restTemplate = new RestTemplate();
	private final String baseUrl;

	public ChannelService(@Value("${dailymotion.base-url}") String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public Channel getChannel(String channelHandle) {
			String url = baseUrl + "/channel/{channelHandle}?fields=id,name,description,created_time";
			Map<String, Object> vars = Map.of("channelHandle", channelHandle);
			ResponseEntity<Channel> response = restTemplate.exchange(
							url,
							HttpMethod.GET,
							HttpEntity.EMPTY,
							Channel.class,
							vars
			);
			Channel body = response.getBody();
			return body;
	}
}
