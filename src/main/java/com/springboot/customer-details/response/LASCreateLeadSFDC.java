package com.customerdetails.response;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class LASCreateLeadSFDC {

	@JsonProperty("Status")
	private String status;

	@JsonProperty("Message")
	private String message;

	@JsonProperty("LeadId")
	private String leadId;

	@JsonProperty("RefID")
	private String refId;
}
