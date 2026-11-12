package peertubeminer.peertube.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import peertubeminer.peertube.model.peertube.Channel;

import java.util.Map;

@Service
public class ChannelService {

	private final RestTemplate restTemplate = new RestTemplate();
	private final String baseUrl;

	public ChannelService(@Value("${peertube.base-url:https://peertube.cpy.re}") String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public Channel getChannel(String channelHandle) {
		String url = baseUrl + "/api/v1/video-channels/{channelHandle}";
		Map<String, Object> vars = Map.of("channelHandle", channelHandle);
		ResponseEntity<Channel> response = restTemplate.exchange(
				url,
				HttpMethod.GET,
				HttpEntity.EMPTY,
				Channel.class,
				vars
		);
		Channel body = response.getBody();
		if (body == null) {
			throw new org.springframework.web.client.HttpServerErrorException(
					org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR,
					"PeerTube devolvio respuesta vacia para el canal: " + channelHandle
			);
		}
		return body;
	}
}
