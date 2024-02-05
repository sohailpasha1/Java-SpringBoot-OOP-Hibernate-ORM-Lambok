package com.jocata.amazon.request;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebTopRequest implements Serializable {

	private static final long serialVersionUID = -4522371218149173258L;

	private String dmsBranchCode;

	private String sageProduct;

	private Optional<String> applicantName;

	private Optional<String> applicantType;

	private Optional<String> dsaName;
}
