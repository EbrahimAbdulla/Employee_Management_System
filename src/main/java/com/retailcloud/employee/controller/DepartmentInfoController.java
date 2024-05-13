package com.retailcloud.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailcloud.employee.dto.DepartmentDto;
import com.retailcloud.employee.model.DepartmentInfoModel;
import com.retailcloud.employee.model.ResponseModel;
import com.retailcloud.employee.model.SummaryResponseModel;
import com.retailcloud.employee.service.DepartmentInfoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/department-info")
public class DepartmentInfoController {
	
	/**
	 * This field used to call the method of DepartmentInfoService interface.
	 */	
	@Autowired
	DepartmentInfoService departmentInfoService;
	
	
	/**
	 * Saves or updates an Department info based on the provided model.
	 * 
	 * @param employeeModel The DepartmentInfoModel Model Of The EmployeeInfo
	 * @return An ResponseModel indicating the result of the insert or update
	 *         operation
	 */
	@PostMapping
	public ResponseEntity<ResponseModel> saveOrUpdateDepartmentInfo(@RequestBody DepartmentInfoModel departmentModel) {
		ResponseModel response = departmentInfoService.saveOrUpdateDepartmentInfo(departmentModel);
		return new ResponseEntity<>(response, response.getStatus());
	}
	
	/**
	 * Retrieves a list of Department.
	 * 
	 * @param DepartmentDto It contain a list of parameter that use for Fetching The
	 *                  Department data according to parameter.
	 * @return An SummaryResponseModel containing the list of retrieved Department details with managing
	 *         director details and wh ever working under the managing director details
	 *         with metadata.
	 */
	@GetMapping
	public ResponseEntity<?> fetchDepartmentDetails(@Valid DepartmentDto dto) {
		SummaryResponseModel model = departmentInfoService.fetchDepartmentDetails(dto);
		return new ResponseEntity<>(model, model.getStatus());
	}
	
	/**
	 * Retrieves a list of Department.
	 * 
	 * @param DepartmentDto It contain a list of parameter that use for Fetching The
	 *                  Department data according to parameter.
	 * @return An SummaryResponseModel containing the list of retrieved Department
	 *         with metadata.
	 */	
	@GetMapping("/fetch-all")
	public ResponseEntity<?> fetchAll(@Valid DepartmentDto dto) {
		SummaryResponseModel model = departmentInfoService.fetchAll(dto);
		return new ResponseEntity<>(model, model.getStatus());
	}
	
	/**
	 * Delete a Department by its unique ID.
	 * 
	 * @param id The unique identifier of the Department to be deleted.
	 * @return An ResponseModel indicating the result of the deletion operation.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseModel> deleteDepartmentById(@PathVariable("id") Long id) {
		ResponseModel response = departmentInfoService.deleteDepartmentById(id);
		return new ResponseEntity<>(response, response.getStatus());
	}

}
