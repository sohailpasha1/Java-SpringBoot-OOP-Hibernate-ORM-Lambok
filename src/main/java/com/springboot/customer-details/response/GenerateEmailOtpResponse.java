package com.jocata.amazon.response;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class GenerateEmailOtpResponse {

	@JsonProperty(value = "retStatus")
	private String retStatus;

	@JsonProperty(value = "otpRefNo")
	private String otpRefNo;

	@JsonProperty(value = "errorMessage")
	private String errorMessage;

	@JsonProperty(value = "sysErrorCode")
	private String sysErrorCode;

	@JsonProperty(value = "sysErrorMessage")
	private String sysErrorMessage;
}
