package com.retailcloud.employee.dto;

import lombok.Data;

@Data
public class EmployeDto {

	private Integer page;
	private Integer size;
	private Boolean lookup;
}
