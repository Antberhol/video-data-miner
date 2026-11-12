package com.example.dailymotionminer.service;


import com.example.dailymotionminer.model.dailymotion.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class UserService {


	private static final RestTemplate restTemplate = new RestTemplate();
	private static String baseUrl;

	public UserService(@Value("${dailymotion.base-url:https://api.dailymotion.com}") String baseUrl) {
		UserService.baseUrl = baseUrl;
	}

	public static User getUser(String userId) {
			String url = baseUrl + "/user/{userId}?fields=id,screenname,avatar_720_url";
			Map<String, Object> vars = Map.of("userId", userId);
			ResponseEntity<User> user = restTemplate.exchange(
							url,
							HttpMethod.GET,
							HttpEntity.EMPTY,
							User.class,
							vars
			);
			User body = user.getBody();
			return body == null ? new User() : body;
	}
}
