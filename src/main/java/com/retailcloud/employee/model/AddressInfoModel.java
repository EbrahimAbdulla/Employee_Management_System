package com.retailcloud.employee.model;

import lombok.Data;

@Data
public class AddressInfoModel {
	
	private Long addressId;

	private String address1;

	private String address2;

	private String pincode;

	private String cityName;

	private String stateName;

	private String countryName;

}
