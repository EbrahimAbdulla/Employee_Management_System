package com.retailcloud.employee.service;

import com.retailcloud.employee.dto.DepartmentDto;
import com.retailcloud.employee.model.DepartmentInfoModel;
import com.retailcloud.employee.model.ResponseModel;
import com.retailcloud.employee.model.SummaryResponseModel;

import jakarta.validation.Valid;

public interface DepartmentInfoService {

	/**
	 * Saves or updates an Department info based on the provided model.
	 * 
	 * @param employeeModel The DepartmentInfoModel Model Of The EmployeeInfo
	 * @return An ResponseModel indicating the result of the insert or update
	 *         operation
	 */
	ResponseModel saveOrUpdateDepartmentInfo(DepartmentInfoModel departmentModel);

	/**
	 * Delete a Department by its unique ID.
	 * 
	 * @param id The unique identifier of the Department to be deleted.
	 * @return An ResponseModel indicating the result of the deletion operation.
	 */
	ResponseModel deleteDepartmentById(Long id);

	/**
	 * Retrieves a list of Department.
	 * 
	 * @param DepartmentDto It contain a list of parameter that use for Fetching The
	 *                  Department data according to parameter.
	 * @return An SummaryResponseModel containing the list of retrieved Department details with managing
	 *         director details and wh ever working under the managing director details
	 *         with metadata.
	 */
	SummaryResponseModel fetchDepartmentDetails(@Valid DepartmentDto dto);


	/**
	 * Retrieves a list of Department.
	 * 
	 * @param DepartmentDto It contain a list of parameter that use for Fetching The
	 *                  Department data according to parameter.
	 * @return An SummaryResponseModel containing the list of retrieved Department
	 *         with metadata.
	 */	
	SummaryResponseModel fetchAll(@Valid DepartmentDto dto);

}
