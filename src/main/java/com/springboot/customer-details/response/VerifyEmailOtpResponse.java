package com.jocata.amazon.response;

import java.io.Serializable;

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
public class VerifyEmailOtpResponse implements Serializable {

	private static final long serialVersionUID = 7518289051450011798L;

	@JsonProperty(value = "retStatus")
	private String retStatus;

	@JsonProperty(value = "Message")
	private String message;

	@JsonProperty(value = "errorMessage")
	private String errorMessage;

	@JsonProperty(value = "sysErrorCode")
	private String sysErrorCode;

	@JsonProperty(value = "sysErrorMessage")
	private String sysErrorMessage;
}
