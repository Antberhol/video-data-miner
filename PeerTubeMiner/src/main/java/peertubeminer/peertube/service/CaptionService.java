package peertubeminer.peertube.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import peertubeminer.peertube.model.peertube.CaptionResponse;

import java.util.Map;

@Service
public class CaptionService {

	private final RestTemplate restTemplate = new RestTemplate();
	private final String baseUrl;

	public CaptionService(@Value("${peertube.base-url:https://peertube.cpy.re}") String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public CaptionResponse getCaptionsByVideo(Integer videoId) {
		String url = baseUrl + "/api/v1/videos/{videoId}/captions";
		Map<String, Object> vars = Map.of("videoId", videoId);
		ResponseEntity<CaptionResponse> response = restTemplate.exchange(
				url,
				HttpMethod.GET,
				HttpEntity.EMPTY,
				CaptionResponse.class,
				vars
		);
		CaptionResponse body = response.getBody();
		return body == null ? new CaptionResponse() : body;
	}
}
