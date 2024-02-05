package com.customerdetails.service;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.customerdetails.request.WebTopRequest;
import com.customerdetails.response.WebTopResponse;

@Service
public class CreateWebTopService {

	@Value("${createWebtopAndFolder}")
	private String createWebtopAndFolderURL;

	public WebTopResponse createWebTop(WebTopRequest webTopRequest) {
		ObjectMapper objectMapper = new ObjectMapper();
		String webtopAsString = null;
		try {
			webtopAsString = objectMapper.writeValueAsString(webTopRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}

		JSONObject jsonObject = new JSONObject(webtopAsString);
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept-Encoding", "gzip,deflate");
		headers.add("Authorization", "DemoToken");
		headers.add("ConversationID", "Demo_" + new Date().getTime());
		headers.add("SourceName", "Demo");
		headers.add("Content-Type", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);

		ResponseEntity<WebTopResponse> responseEntity = (new RestTemplate(
				getClientHttpRequestFactory(30000, 60000, 30000))).exchange(createWebtopAndFolderURL, HttpMethod.POST,
						entity, WebTopResponse.class);
		return responseEntity.getBody();
	}

	public static ClientHttpRequestFactory getClientHttpRequestFactory(int httpConnectTimeout, int httpReadTimeout,
			int connectionRequestTimeout) {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(httpConnectTimeout);
		clientHttpRequestFactory.setReadTimeout(httpReadTimeout);
		clientHttpRequestFactory.setConnectionRequestTimeout(connectionRequestTimeout);
		return clientHttpRequestFactory;
	}
}
