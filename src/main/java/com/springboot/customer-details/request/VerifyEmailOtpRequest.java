package com.jocata.amazon.request;

import java.io.Serializable;

import org.springframework.stereotype.Component;

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
public class VerifyEmailOtpRequest implements Serializable {

	private static final long serialVersionUID = 1880088998610204492L;

	@JsonProperty("otpRefNo")
	private String otpRefNo;

	@JsonProperty("Otp")
	private String otp;
}
