package com.jocata.amazon.response;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown =true)
@Component
public class WebTopResponse implements Serializable{
	
	private static final long serialVersionUID = -6649781240942450491L;
	
	private String retStatus;
	
	private String dmsResponse;
	
	private String errorMessage;
	
	private String objected;
	
	private String sysErrorCode;
	
	private String sysErrorMessage;
	
	private String webtopNo;

}
