package com.retailcloud.employee.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class DepartmentInfoModel {
	
	private Long departmentId;

	private String departmentName;

	private Date creationDate;

	private String location;

	private Double noOfEmployees;

	private Double budget;
	
	private List<EmployeeInfoModel> employee;

}
