package com.customerdetails.service;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.customerdetails.request.VerifyEmailOtpRequest;
import com.customerdetails.response.VerifyEmailOtpResponse;

@Service
public class VerifyEmailOtpService {

	@Value("${verifyOTP}")
	private String verifyOTPURl;

	public VerifyEmailOtpResponse verifyEmailOtp(VerifyEmailOtpRequest verifyOtpRequest) {

		ObjectMapper mapper = new ObjectMapper();
		String otpRequest = null;
		try {
			otpRequest = mapper.writeValueAsString(verifyOtpRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject(otpRequest);

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", "application/json");
		headers.add("Accept-Encoding", "gzip,deflate");
		headers.add("ConversationID", "Demo_" + new Date().getTime());
		headers.add("SourceName", "Demo");
		headers.add("Authorization", "Token");

		VerifyEmailOtpResponse verifyEmailOtpResponse = null;
		HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);
		RestTemplate restTemplate = new RestTemplate();
		verifyEmailOtpResponse = restTemplate.postForObject(verifyOTPURl, entity, VerifyEmailOtpResponse.class);
		return verifyEmailOtpResponse;
	}
}
