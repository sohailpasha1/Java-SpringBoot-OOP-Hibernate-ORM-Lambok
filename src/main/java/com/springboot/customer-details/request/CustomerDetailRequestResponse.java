package com.customerdetails.request;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDetailRequestResponse implements Serializable {

	private static final long serialVersionUID = -5938429864725712353L;

	@JsonProperty(value = "id")
	private Integer appId;

	@JsonProperty(value = "mobileNumber")
	private String mobileNumber;

	@JsonProperty(value = "pannumber")
	private String pannumber;

	@JsonProperty(value = "firstName")
	private String firstName;

	@JsonProperty(value = "middleName")
	private String middleName;

	@JsonProperty(value = "lastName")
	private String lastName;

	@JsonProperty(value = "fatherName")
	private String fatherName;

	@JsonProperty(value = "dob")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dob;

	@JsonProperty(value = "personalEmail")
	private String personalEmail;

	@JsonProperty(value = "gender")
	private String gender;

	@JsonProperty(value = "pincode")
	private String pincode;

	@JsonProperty(value = "kyccity")
	private String kyccity;

	@JsonProperty(value = "kycstate")
	private String kycstate;

	@JsonProperty(value = "isActive")
	private Boolean isActive;

	@JsonProperty(value = "leadId")
	private String leadId;

	@JsonProperty(value = "webtopNo")
	private String webtopNo;
}
