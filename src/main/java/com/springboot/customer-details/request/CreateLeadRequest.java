package com.jocata.amazon.request;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLeadRequest implements Serializable {

	private static final long serialVersionUID = -6080373286579644672L;

	@JsonProperty(value = "RefID")
	private String refId;

	@JsonProperty(value = "FirstName")
	private String firstname;

	@JsonProperty(value = "ModdleName")
	private String middlename;

	@JsonProperty(value = "LastName")
	private String lastname;

	@JsonProperty(value = "MobileNo")
	private String mobileno;

	@JsonProperty(value = "EmailId")
	private String emailId;

	@JsonProperty(value = "Pincode")
	private String pincode;

	@JsonProperty(value = "City")
	private String city;

	@JsonProperty(value = "State")
	private String state;

	@JsonProperty(value = "PAN")
	private String pan;

	@JsonProperty(value = "Source")
	private String source;

	@JsonProperty(value = "Subsource")
	private String subsource;

	@JsonProperty(value = "Product")
	private String product;
}
