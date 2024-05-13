package com.retailcloud.employee.model;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel {
	private int statusCode;
	private HttpStatus status;
	private Object data;
	private String message;
	
	
}

