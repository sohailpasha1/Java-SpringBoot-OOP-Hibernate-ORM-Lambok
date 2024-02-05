package com.customerdetails.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerdetails.dao.CustomerDetailsDao;
import com.customerdetails.entity.CustomerDetails;
import com.customerdetails.request.CustomerDetailRequestResponse;
import com.customerdetails.service.CustomerDetailsService;

@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService {

	@Autowired
	private CustomerDetailsDao customerDetailsDao;

	@Override
	public CustomerDetailRequestResponse saveCustomerDetails(CustomerDetailRequestResponse request) {
		CustomerDetails customerDetails = CustomerDetails.builder().id(request.getAppId()).dob(request.getDob())
				.fatherName(request.getFatherName()).firstName(request.getFirstName()).gender(request.getGender())
				.isActive(request.getIsActive()).kyccity(request.getKyccity()).kycstate(request.getKycstate())
				.lastName(request.getLastName()).middleName(request.getMiddleName())
				.mobileNumber(request.getMobileNumber()).pannumber(request.getPannumber())
				.personalEmail(request.getPersonalEmail()).pincode(request.getPincode()).build();
		Serializable id = customerDetailsDao.save(customerDetails);
		customerDetails.setId((Integer) id);
		request.setAppId(customerDetails.getId());
		return request;
	}

	@Override
	public CustomerDetailRequestResponse getCustomerDetails(Integer appId) {
		CustomerDetails customerDetailsData = new CustomerDetails();
		customerDetailsData = customerDetailsDao.findEntityById(CustomerDetails.class, appId);
		CustomerDetailRequestResponse response = new CustomerDetailRequestResponse();
		if (customerDetailsData != null) {
			response = CustomerDetailRequestResponse.builder().appId(customerDetailsData.getId())
					.dob(customerDetailsData.getDob()).fatherName(customerDetailsData.getFatherName())
					.firstName(customerDetailsData.getFirstName()).gender(customerDetailsData.getGender())
					.isActive(customerDetailsData.getIsActive()).kyccity(customerDetailsData.getKyccity())
					.kycstate(customerDetailsData.getKycstate()).lastName(customerDetailsData.getLastName())
					.middleName(customerDetailsData.getMiddleName()).mobileNumber(customerDetailsData.getMobileNumber())
					.pannumber(customerDetailsData.getPannumber()).personalEmail(customerDetailsData.getPersonalEmail())
					.pincode(customerDetailsData.getPincode()).build();
		}
		return response;
	}

	@Override
	public CustomerDetailRequestResponse updateCustomerDetails(CustomerDetailRequestResponse request) {
		CustomerDetails customerDetails = null;
		if (request != null && request.getLeadId() != null) {
			customerDetails = CustomerDetails.builder().id(request.getAppId()).dob(request.getDob())
					.fatherName(request.getFatherName()).firstName(request.getFirstName()).gender(request.getGender())
					.isActive(request.getIsActive()).kyccity(request.getKyccity()).kycstate(request.getKycstate())
					.lastName(request.getLastName()).middleName(request.getMiddleName())
					.mobileNumber(request.getMobileNumber()).pannumber(request.getPannumber())
					.personalEmail(request.getPersonalEmail()).pincode(request.getPincode()).leadId(request.getLeadId())
					.build();
			customerDetailsDao.update(customerDetails);
		}
		return request;
	}

	public List<CustomerDetails> getAll() {
		return customerDetailsDao.loadEntity(CustomerDetails.class);
	}

}
