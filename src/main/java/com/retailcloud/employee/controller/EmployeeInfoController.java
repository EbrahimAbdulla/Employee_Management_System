package com.retailcloud.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailcloud.employee.dto.EmployeDto;
import com.retailcloud.employee.model.EmployeeInfoModel;
import com.retailcloud.employee.model.ResponseModel;
import com.retailcloud.employee.model.SummaryResponseModel;
import com.retailcloud.employee.service.EmployeeInfoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee-info")
public class EmployeeInfoController {
	
	/**
	 * This field used to call the method of EmployeeInfoService interface.
	 */	
	@Autowired
	EmployeeInfoService employeeService;

	/**
	 * Saves or updates an Employee info based on the provided model.
	 * 
	 * @param employeeModel The EmployeeInfoModel Model Of The EmployeeInfo
	 * @return An ResponseModel indicating the result of the insert or update
	 *         operation
	 */
	@PostMapping
	public ResponseEntity<ResponseModel> saveOrUpdateEmployeeInfo(@RequestBody EmployeeInfoModel employeeModel) {
		ResponseModel response = employeeService.saveOrUpdateEmployeeInfo(employeeModel);
		return new ResponseEntity<>(response, response.getStatus());
	}
	
	/**
	 * Retrieves a list of Employees.
	 * 
	 * @param EmployeDto It contain a list of parameter that use for Fetching The
	 *                  EmployeDto data according to parameter.
	 * @return An SummaryResponseModel containing the list of retrieved EmployeDto
	 *         with metadata.
	 */	
	@GetMapping
	public ResponseEntity<?> fetchAllEmployeeDetails(@Valid EmployeDto dto) {
		SummaryResponseModel model = employeeService.fetchAllEmployeeDetails(dto);
		return new ResponseEntity<>(model, model.getStatus());
	}
	
	/**
	 * Retrieves a list of Employees information.
	 * 
	 * @param EmployeDto It contain a list of parameter that use for Fetching The
	 *                  EmployeDto data according to parameter.
	 * @return An SummaryResponseModel containing the list of retrieved EmployeDto
	 *         with metadata.
	 */
	@GetMapping("/get-employee-details")
	public ResponseEntity<?> fetchAllEmployeeInfo(@Valid EmployeDto dto) {
		SummaryResponseModel model = employeeService.fetchAllEmployeeInfo(dto);
		return new ResponseEntity<>(model, model.getStatus());
	}

}
