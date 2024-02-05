package com.customerdetails.service;

import java.util.Date;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.customerdetails.request.CreateLeadRequest;
import com.customerdetails.request.CustomerDetailRequestResponse;
import com.customerdetails.request.WebTopRequest;
import com.customerdetails.response.CreateLeadResponse;
import com.customerdetails.response.WebTopResponse;
import com.customerdetails.service.impl.CustomerDetailsServiceImpl;

@Service
public class CreateLeadService {

	@Value("${aggregatorcreateleadsfdc}")
	private String aggregatorcreateleadsfdcURL;
	@Autowired
	private CreateLeadService createLeadService;
	@Autowired
	private CustomerDetailsServiceImpl customerservice;
	@Autowired
	private CreateWebTopService createWebTopService;

	public CreateLeadResponse createLead(CreateLeadRequest createLeadRequest) {

		ObjectMapper objectMapper = new ObjectMapper();
		String requestString = null;
		try {
			requestString = objectMapper.writeValueAsString(createLeadRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject(requestString);

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

		headers.add("Content-Type", "application/json");
		headers.add("Accept-Encoding", "gzip,deflate");
		headers.add("ConversationID", "Demo_" + new Date().getTime());
		headers.add("SourceName", "Demo");
		headers.add("Authorization", "Basic DemoTokenhdW");

		HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();
		CreateLeadResponse leadResponse = restTemplate.postForObject(aggregatorcreateleadsfdcURL, entity,
				CreateLeadResponse.class);

		return leadResponse;
	}

	public void initiate(CustomerDetailRequestResponse response) {
		CreateLeadRequest leadRequest = CreateLeadRequest.builder()
				.refId(response.getAppId() + "_" + new Date().getTime()).firstname(response.getFirstName())
				.middlename(response.getMiddleName()).lastname(response.getLastName())
				.mobileno(response.getMobileNumber()).emailId(response.getPersonalEmail())
				.pincode(response.getPincode()).city(response.getKyccity()).state(response.getKycstate())
				.pan(response.getPannumber()).source("email").subsource("email").product("Against Share").build();
		CreateLeadResponse leadResponse = this.createLead(leadRequest);
		if (leadResponse != null && leadResponse.getLasCreateLeadSFDC().getLeadId() != null) {
			response.setLeadId(leadResponse.getLasCreateLeadSFDC().getLeadId());
			response = customerservice.updateCustomerDetails(response);

			WebTopRequest webTopRequest = WebTopRequest.builder().sageProduct("Business").dmsBranchCode("284")
					.applicantName(
							Optional.of((response.getFirstName() + response.getMiddleName() + response.getLastName())))
					.applicantType(Optional.of("1")).dsaName(Optional.of("")).build();
			WebTopResponse webTopResponse = createWebTopService.createWebTop(webTopRequest);
			if (webTopResponse != null && webTopResponse.getWebtopNo() != null) {
				response.setWebtopNo(webTopResponse.getWebtopNo());
				response = customerservice.updateCustomerDetails(response);
			}
		}
	}

}
