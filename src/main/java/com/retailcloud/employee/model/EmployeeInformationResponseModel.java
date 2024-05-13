package com.retailcloud.employee.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EmployeeInformationResponseModel {
	
	private Long employeeId;

	private String emplyeeName;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "IST")
	private Date dateOfBirth;

	private List<AddressInfoModel> addressInfo;

	private String role;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "IST")
	private Date joiningDate;

	private String yearlyBonusPercentage;
	
	private Boolean isReportingManager;

	private EmployeeInfoModel reportingManagerDetails;
	
	private Long reportingManagerId;
	
	private DepartmentInfoModel department;

}
