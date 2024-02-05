package com.customerdetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customerdetails.request.CustomerDetailRequestResponse;
import com.customerdetails.request.VerifyEmailOtpRequest;
import com.customerdetails.response.GenerateEmailOtpResponse;
import com.customerdetails.response.VerifyEmailOtpResponse;
import com.customerdetails.service.CreateLeadService;
import com.customerdetails.service.GenerateEmailOtpService;
import com.customerdetails.service.VerifyEmailOtpService;
import com.customerdetails.service.impl.CustomerDetailsServiceImpl;

/**
 * 
 * @author Sohail.Pasha
 *
 */

@RequestMapping(value = "/customerdetails/")
@RestController
@CrossOrigin(value = "http://localhost:4200/")
public class CustomerDetailsController {

	@Autowired
	private CustomerDetailsServiceImpl customerservice;
	@Autowired
	private CreateLeadService createLeadService;
	@Autowired
	private GenerateEmailOtpService generateOtpService;
	@Autowired
	private VerifyEmailOtpService verifyOtpService;

	/**
	 * to save customer details
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "save")
	public ResponseEntity<CustomerDetailRequestResponse> saveCustomerDetails(
			@RequestBody CustomerDetailRequestResponse request) throws Exception {
		CustomerDetailRequestResponse response = customerservice.saveCustomerDetails(request);
		createLeadService.initiate(response);
		return new ResponseEntity<CustomerDetailRequestResponse>(response, HttpStatus.OK);
	}

	/**
	 * generate email otp
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "generateemailotp")
	public ResponseEntity<GenerateEmailOtpResponse> emailOtp(@RequestParam("email") String email) throws Exception {
		GenerateEmailOtpResponse emailOtpResponse = generateOtpService.generateEmailOtp(email);
		return new ResponseEntity<>(emailOtpResponse, HttpStatus.OK);
	}

	/**
	 * verify Otp Request
	 * 
	 * @param verifyOtpRequest
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "verifyotp")
	public ResponseEntity<VerifyEmailOtpResponse> verifyOtp(@RequestBody VerifyEmailOtpRequest verifyOtpRequest)
			throws Exception {
		VerifyEmailOtpResponse verifyEmailOtpResponse = verifyOtpService.verifyEmailOtp(verifyOtpRequest);
		return new ResponseEntity<>(verifyEmailOtpResponse, HttpStatus.OK);
	}

	/**
	 * to fetch all details
	 * 
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "getbyid")
	public ResponseEntity<CustomerDetailRequestResponse> getAllCustomerDetails(
			@RequestParam(value = "app_id") Integer appId) throws Exception {
		CustomerDetailRequestResponse response = customerservice.getCustomerDetails(appId);
		return new ResponseEntity<CustomerDetailRequestResponse>(response, HttpStatus.OK);
	}

	@GetMapping(value = "getall")
	public ResponseEntity<?> fetchAll() throws Exception {
		return new ResponseEntity<>(customerservice.getAll(), HttpStatus.OK);
	}
}
