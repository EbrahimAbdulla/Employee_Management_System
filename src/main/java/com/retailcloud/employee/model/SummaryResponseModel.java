package com.retailcloud.employee.model;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SummaryResponseModel {
    private int statusCode;
    private HttpStatus status;
    private Object data;
    private String message;
    private PageMetaModel meta;
}