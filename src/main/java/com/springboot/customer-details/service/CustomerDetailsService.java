package com.jocata.amazon.service;

import org.springframework.stereotype.Component;

import com.jocata.amazon.request.CustomerDetailRequestResponse;

@Component
public interface CustomerDetailsService {

	public CustomerDetailRequestResponse saveCustomerDetails(CustomerDetailRequestResponse request);

	public CustomerDetailRequestResponse getCustomerDetails(Integer appId);

	public CustomerDetailRequestResponse updateCustomerDetails(CustomerDetailRequestResponse request);
}
