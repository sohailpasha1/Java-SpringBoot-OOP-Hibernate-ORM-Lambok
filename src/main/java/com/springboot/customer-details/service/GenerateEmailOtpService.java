package com.jocata.amazon.service;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.jocata.amazon.response.GenerateEmailOtpResponse;

@Service
public class GenerateEmailOtpService {

	@Value("${generateOTPByEmail}")
	private String generateOTPByEmailURl;

	public GenerateEmailOtpResponse generateEmailOtp(String otpRequest) {
		JSONObject jsonObject = new JSONObject(otpRequest);

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", "application/json");
		headers.add("Accept-Encoding", "gzip,deflate");
		headers.add("ConversationID", "Demo_" + new Date().getTime());
		headers.add("SourceName", "Demo");
		headers.add("Authorization", "Token");

		GenerateEmailOtpResponse emailOtpResponse = null;
		HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();
		emailOtpResponse = restTemplate.postForObject(generateOTPByEmailURl, entity, GenerateEmailOtpResponse.class);
		return emailOtpResponse;
	}

}
