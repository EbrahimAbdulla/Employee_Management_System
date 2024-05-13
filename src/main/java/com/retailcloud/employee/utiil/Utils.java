package com.retailcloud.employee.utiil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.retailcloud.employee.entity.AddressInfo;
import com.retailcloud.employee.entity.DepartmentInfo;
import com.retailcloud.employee.entity.EmployeeInfo;
import com.retailcloud.employee.model.AddressInfoModel;
import com.retailcloud.employee.model.DepartmentInfoModel;
import com.retailcloud.employee.model.DepartmentInformationResponseModel;
import com.retailcloud.employee.model.EmployeeInfoModel;
import com.retailcloud.employee.model.ManagingDirectorsDetails;

@Service
public class Utils {
	
	/**
     * This method will update department table
     */
	public void updateDepartmentInfo(DepartmentInfo dataFromDb, DepartmentInfoModel newData) {
		if (!Strings.isNullOrEmpty(newData.getDepartmentName()))
			dataFromDb.setDepartmentName(newData.getDepartmentName());
		if (newData.getCreationDate() != null)
			dataFromDb.setCreationDate(newData.getCreationDate());
		if (!Strings.isNullOrEmpty(newData.getLocation()))
			dataFromDb.setLocation(newData.getLocation());
		if (newData.getNoOfEmployees() != null)
			dataFromDb.setNoOfEmployees(newData.getNoOfEmployees());
		if (newData.getBudget() != null)
			dataFromDb.setNoOfEmployees(newData.getBudget());
		if (newData.getEmployee() != null && newData.getEmployee() != null)
			validateEmployeeInfo(dataFromDb.getEmployee(), newData.getEmployee());
		dataFromDb.setEmployee(dataFromDb.getEmployee());
	}

	/**
     * This method will update employee table
     */
	private void validateEmployeeInfo(List<EmployeeInfo> dataFromDb, List<EmployeeInfoModel> newData) {
		Iterator<EmployeeInfo> dataFromDbIterator = dataFromDb.iterator();
		Iterator<EmployeeInfoModel> newDataIterator = newData.iterator();
		while (dataFromDbIterator.hasNext() && newDataIterator.hasNext()) {
			EmployeeInfo dataFromDbNext = dataFromDbIterator.next();
			EmployeeInfoModel newDataNext = newDataIterator.next();
			if (newDataNext.getDateOfBirth() != null)
				dataFromDbNext.setDateOfBirth(newDataNext.getDateOfBirth());
			if (!Strings.isNullOrEmpty(newDataNext.getEmplyeeName()))
				dataFromDbNext.setEmplyeeName(newDataNext.getEmplyeeName());
			if (!Strings.isNullOrEmpty(newDataNext.getRole()))
				dataFromDbNext.setRole(newDataNext.getRole());
			if (!Strings.isNullOrEmpty(newDataNext.getYearlyBonusPercentage()))
				dataFromDbNext.setYearlyBonusPercentage(newDataNext.getYearlyBonusPercentage());
			if (dataFromDbNext.getAddressInfo() != null && newDataNext.getAddressInfo() != null) {
				validateAddressInfo(dataFromDbNext.getAddressInfo(), newDataNext.getAddressInfo());
				dataFromDbNext.setAddressInfo(dataFromDbNext.getAddressInfo());
			}
		}
	}

	/**
     * This method will update Address table
     */
	private void validateAddressInfo(List<AddressInfo> dataFromDb, List<AddressInfoModel> newDataFromDb) {
		Iterator<AddressInfo> dataFromDbIterator = dataFromDb.iterator();
		Iterator<AddressInfoModel> newDataIterator = newDataFromDb.iterator();
		while (dataFromDbIterator.hasNext() && newDataIterator.hasNext()) {
			AddressInfo dataNext = dataFromDbIterator.next();
			AddressInfoModel newNext = newDataIterator.next();
			if (!Strings.isNullOrEmpty(newNext.getAddress1()))
				dataNext.setAddress1(newNext.getAddress1());
			if (!Strings.isNullOrEmpty(newNext.getAddress2()))
				dataNext.setAddress2(newNext.getAddress2());
			if (!Strings.isNullOrEmpty(newNext.getPincode()))
				dataNext.setPincode(newNext.getPincode());
			if (!Strings.isNullOrEmpty(newNext.getCityName()))
				dataNext.setCityName(newNext.getCityName());
			if (!Strings.isNullOrEmpty(newNext.getStateName()))
				dataNext.setStateName(newNext.getStateName());
			if (!Strings.isNullOrEmpty(newNext.getCountryName()))
				dataNext.setCountryName(newNext.getCountryName());
		}
	}

	/**
     * This method will update employee table
     */
	public void updateEmploeeInfo(EmployeeInfo dataFromDbNext, EmployeeInfoModel newDataNext) {
		if (newDataNext.getDateOfBirth() != null)
			dataFromDbNext.setDateOfBirth(newDataNext.getDateOfBirth());
		if (!Strings.isNullOrEmpty(newDataNext.getEmplyeeName()))
			dataFromDbNext.setEmplyeeName(newDataNext.getEmplyeeName());
		if (!Strings.isNullOrEmpty(newDataNext.getRole()))
			dataFromDbNext.setRole(newDataNext.getRole());
		if (!Strings.isNullOrEmpty(newDataNext.getYearlyBonusPercentage()))
			dataFromDbNext.setYearlyBonusPercentage(newDataNext.getYearlyBonusPercentage());
		if (dataFromDbNext.getAddressInfo() != null && newDataNext.getAddressInfo() != null) {
			validateAddressInfo(dataFromDbNext.getAddressInfo(), newDataNext.getAddressInfo());
			dataFromDbNext.setAddressInfo(dataFromDbNext.getAddressInfo());
		}
	}

	/**
     * This method will update DepartmentInformationResponseModel table
     */
	public DepartmentInformationResponseModel updateDeptMainModel(DepartmentInformationResponseModel model,
			DepartmentInfo departmentInfo) {
		model.setDepartmentId(departmentInfo.getDepartmentId());
		model.setDepartmentName(departmentInfo.getDepartmentName());
		model.setBudget(departmentInfo.getBudget() != null ? departmentInfo.getBudget() : null);
		model.setCreationDate(departmentInfo.getCreationDate());
		model.setLocation(departmentInfo.getLocation());
		model.setNoOfEmployees(departmentInfo.getNoOfEmployees());
		return model;

	}

	/**
     * This method will update ManagingDirectorsDetails table
     */
	public ManagingDirectorsDetails updateManagingDirectorDetails(ManagingDirectorsDetails model,
			EmployeeInfo reportingManger) {
		model.setEmployeeId(reportingManger.getEmployeeId());
		model.setDateOfBirth(reportingManger.getDateOfBirth());
		model.setEmplyeeName(reportingManger.getEmplyeeName());
		model.setRole(reportingManger.getRole());
		model.setJoiningDate(reportingManger.getJoiningDate());
		model.setYearlyBonusPercentage(reportingManger.getYearlyBonusPercentage());
		System.out.println(model.getAddressInfo());
		List<AddressInfoModel> updateAddressModel = updateAddressModel(reportingManger.getAddressInfo(),model.getAddressInfo());
		model.setAddressInfo(updateAddressModel);
		return model;

	}

	/**
     * This method will update AddressInfoModel table
     */
	private List<AddressInfoModel> updateAddressModel(List<AddressInfo> addressInfo, List<AddressInfoModel> model) {
		List<AddressInfoModel> addresList = new ArrayList<>();
		Iterator<AddressInfo> entityIterator = addressInfo.iterator();
		while(entityIterator.hasNext() ) {
			AddressInfoModel modelNext = new AddressInfoModel();
			AddressInfo entityNext = entityIterator.next();
			modelNext.setAddressId(entityNext.getAddressId());
			modelNext.setAddress1(entityNext.getAddress1());
			modelNext.setAddress2(entityNext.getAddress2() != null ?entityNext.getAddress2() :null);
			modelNext.setPincode(entityNext.getPincode());
			modelNext.setCityName(entityNext.getCityName());
			modelNext.setStateName(entityNext.getStateName());
			modelNext.setCountryName(entityNext.getCountryName());
			addresList.add(modelNext);
		}
		return addresList;	
	}
}
