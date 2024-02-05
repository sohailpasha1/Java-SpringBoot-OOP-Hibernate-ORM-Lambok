package com.customerdetails.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "customer_details")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetails implements Serializable {

	private static final long serialVersionUID = -5497822911560966186L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "mobilenumber")
	private String mobileNumber;

	@Column(name = "pannumber")
	private String pannumber;

	@JsonProperty(value = "first_name")
	@Column(name = "firstname")
	private String firstName;

	@Column(name = "middlename")
	private String middleName;

	@JsonProperty(value = "last_name")
	@Column(name = "lastname")
	private String lastName;

	@JsonProperty(value = "father_name")
	@Column(name = "fathername")
	private String fatherName;

	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@Column(name = "dob")
	private LocalDate dob;

	@JsonProperty(value = "personal_email")
	@Column(name = "personalemail")
	private String personalEmail;

	@Column(name = "gender")
	private String gender;

	@Column(name = "pincode")
	private String pincode;

	@Column(name = "kyccity")
	private String kyccity;

	@Column(name = "kycstate")
	private String kycstate;

	@JsonProperty(value = "is_active")
	@Column(name = "isActive")
	private Boolean isActive;

	@JsonProperty(value = "leadId")
	@Column(name = "leadid")
	private String leadId;

	@JsonProperty(value = "webtopNo")
	@Column(name = "webtopno")
	private String webtopNo;
}
