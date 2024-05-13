package com.retailcloud.employee.service;

import com.retailcloud.employee.dto.EmployeDto;
import com.retailcloud.employee.model.EmployeeInfoModel;
import com.retailcloud.employee.model.ResponseModel;
import com.retailcloud.employee.model.SummaryResponseModel;

import jakarta.validation.Valid;

public interface EmployeeInfoService {

	/**
	 * Saves or updates an Employee info based on the provided model.
	 * 
	 * @param employeeModel The Employee Model Of The EmployeeInfo
	 * @return An ResponseModel indicating the result of the insert or update
	 *         operation
	 */
	ResponseModel saveOrUpdateEmployeeInfo(EmployeeInfoModel employeeModel);


	/**
	 * Retrieves a list of Employees.
	 * 
	 * @param EmployeDto It contain a list of parameter that use for Fetching The
	 *                  Department data according to parameter.
	 * @return An SummaryResponseModel containing the list of retrieved Employees details 
	 *         with metadata.
	 */
	SummaryResponseModel fetchAllEmployeeDetails(@Valid EmployeDto dto);


	/**
	 * Retrieves a list of Employees information.
	 * 
	 * @param EmployeDto It contain a list of parameter that use for Fetching The
	 *                  EmployeDto data according to parameter.
	 * @return An SummaryResponseModel containing the list of retrieved EmployeDto
	 *         with metadata.
	 */
	SummaryResponseModel fetchAllEmployeeInfo(@Valid EmployeDto dto);

}
