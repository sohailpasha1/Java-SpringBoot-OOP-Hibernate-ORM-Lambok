package com.customerdetails.response;

import java.io.Serializable;

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
public class CreateLeadResponse implements Serializable {

	private static final long serialVersionUID = -3560719815452623757L;

	@JsonProperty("RetStatus")
	private String retStatus;

	private String sysErrorCode;

	private String sysErrorMessage;

	@JsonProperty("LAS_Create_Lead_SFDC")
	public LASCreateLeadSFDC lasCreateLeadSFDC;
}

