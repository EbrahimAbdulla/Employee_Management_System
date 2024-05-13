package com.retailcloud.employee.serviceImpl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.reflect.TypeToken;
import com.retailcloud.employee.dto.DepartmentDto;
import com.retailcloud.employee.entity.DepartmentInfo;
import com.retailcloud.employee.entity.EmployeeInfo;
import com.retailcloud.employee.exception.NoDataFoundException;
import com.retailcloud.employee.model.DepartmentInfoModel;
import com.retailcloud.employee.model.DepartmentInformationResponseModel;
import com.retailcloud.employee.model.EmployeeInfoModel;
import com.retailcloud.employee.model.ManagingDirectorsDetails;
import com.retailcloud.employee.model.PageMetaModel;
import com.retailcloud.employee.model.ResponseModel;
import com.retailcloud.employee.model.SummaryResponseModel;
import com.retailcloud.employee.repository.DepartmentInfoRepository;
import com.retailcloud.employee.repository.EmployeeInfoRepository;
import com.retailcloud.employee.service.DepartmentInfoService;
import com.retailcloud.employee.utiil.Utils;

import jakarta.validation.Valid;
@Service
public class DepartmentInfoServiceImpl implements DepartmentInfoService{
	
	 /**
     * The ModelMapper is automatically injected to facilitate object-to-object
     * mapping and conversion.
     */

    @Autowired
    ModelMapper mapper;
    
    /**
     * This field used to call the method of DepartmentInfoRepository interface.
     */
    @Autowired
    DepartmentInfoRepository departmentInfoRepo;
    
    /**
     * This field used to call the method of Utils class.
     */
    @Autowired
    Utils utils;
    
    
    /**
     * This field used to call the method of EmployeeInfoRepository interface.
     */
    @Autowired
    EmployeeInfoRepository employeeRepo;
    
    

    /**
	 * Saves or updates an Department info based on the provided model.
	 * 
	 * @param employeeModel The DepartmentInfoModel Model Of The EmployeeInfo
	 * @return An ResponseModel indicating the result of the insert or update
	 *         operation
	 */
	@Override
	public ResponseModel saveOrUpdateDepartmentInfo(DepartmentInfoModel departmentModel) {
		try {
			
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	        DepartmentInfo departmentObj = mapper.map(departmentModel, DepartmentInfo.class);
	        LocalDate today = LocalDate.now();
	        Date creationDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
	        departmentObj.setCreationDate(creationDate);
	        DepartmentInfo data = null;
	        if(departmentModel.getDepartmentId() != null) {
	        	Optional<DepartmentInfo> existingDepartmentInfo = departmentInfoRepo.findById(departmentModel.getDepartmentId());
	        	if(existingDepartmentInfo.isPresent()) {
	        		DepartmentInfo departmentInfo = existingDepartmentInfo.get();
	        		utils.updateDepartmentInfo(departmentInfo,departmentModel);
	        		data = departmentInfoRepo.save(departmentInfo);
	        	}
	        	
	        }else {
    	        data = departmentInfoRepo.save(departmentObj);   		
        	}
	        
	    	DepartmentInfoModel responseObj = mapper.map(data, DepartmentInfoModel.class);  
	    	
	        return new ResponseModel(HttpStatus.CREATED.value(), HttpStatus.CREATED, responseObj, "Successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
	        return new ResponseModel(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, null, "Exception while department save.");		
		}     
	}


	/**
	 * Delete a Department by its unique ID.
	 * 
	 * @param id The unique identifier of the Department to be deleted.
	 * @return An ResponseModel indicating the result of the deletion operation.
	 */
	@Override
	public ResponseModel deleteDepartmentById(Long id) {
		Optional<DepartmentInfo> deparmentObj = departmentInfoRepo.findById(id);
		if (!deparmentObj.isPresent()) {
			throw new NoDataFoundException("Department details not found for given id :" + id);
		}
		try {
			List<EmployeeInfo> employee = deparmentObj.get().getEmployee();
			if (CollectionUtils.isEmpty(employee)) {
				departmentInfoRepo.deleteById(id);
				return new ResponseModel(HttpStatus.OK.value(), HttpStatus.OK, true, "Deleted successfully");
			} else {
				return new ResponseModel(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, false,
						"Department has employees.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseModel(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, false,
					"Exception occured while excecuting method");
		}
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
	@Override
	public SummaryResponseModel fetchDepartmentDetails(DepartmentDto dto) {
		PageMetaModel metaModel = null;
        List<DepartmentInformationResponseModel> responseList = new ArrayList<>();
        if ("employee".equalsIgnoreCase(dto.getExpand())) {
            List<DepartmentInfo> departmentInfoList = departmentInfoRepo.findAll();
            for (DepartmentInfo departmentInfo : departmentInfoList) {
                DepartmentInformationResponseModel deptInfoMainModel = new DepartmentInformationResponseModel();
                utils.updateDeptMainModel(deptInfoMainModel, departmentInfo);
                if (!CollectionUtils.isEmpty(departmentInfo.getEmployee())) {
                    List<ManagingDirectorsDetails> managingDirectorList = new ArrayList<>();
                    for (EmployeeInfo reportingManger : departmentInfo.getEmployee()) {
                        ManagingDirectorsDetails managingDirectorsDetails = new ManagingDirectorsDetails();
                        ManagingDirectorsDetails updateManagingDirectorDetails = utils.updateManagingDirectorDetails(managingDirectorsDetails, reportingManger);
                        List<EmployeeInfo> employees = employeeRepo.findByManagingDirectorId(reportingManger.getEmployeeId());
                        ArrayList<EmployeeInfoModel> employeeModelList = new ArrayList<>();
                        for (EmployeeInfo empl : employees) {
                            EmployeeInfoModel employee = mapper.map(empl, EmployeeInfoModel.class);
                            employeeModelList.add(employee);
                        }
                        updateManagingDirectorDetails.setEmployees(employeeModelList);
                        managingDirectorList.add(updateManagingDirectorDetails);
                    }
                    deptInfoMainModel.setManagingDirectorDetails(managingDirectorList);
                }
                responseList.add(deptInfoMainModel);
            }
            if(dto.getPage() != null && dto.getSize()!=  null) {
            	Long totalElements = (long) responseList.size();
    			int totalPages = (int) Math.ceil((double) totalElements / dto.getSize());
    			metaModel = new PageMetaModel(dto.getPage(), dto.getSize(), totalElements, totalPages);
    			int firstIndex = (dto.getPage() - 1) * dto.getSize();
    			int maxIndex = dto.getSize() + firstIndex;
    			List<DepartmentInformationResponseModel> paginatedData = null;

    			if (responseList != null && responseList.size() <= maxIndex) {
    				firstIndex = Math.min((dto.getPage() - 1) * dto.getSize(), responseList.size());
    				maxIndex = Math.min(dto.getSize() + firstIndex, responseList.size());
    			}

    			paginatedData = responseList.subList(firstIndex, maxIndex);
    			return new SummaryResponseModel(HttpStatus.OK.value(), HttpStatus.OK, paginatedData,
    					"Data fetched successfully", metaModel);   	
            }
            return new SummaryResponseModel(HttpStatus.OK.value(), HttpStatus.OK, responseList,
                    "Employee data fetched successfully", metaModel);
        }
        return null;
    }

	/**
	 * Retrieves a list of Department.
	 * 
	 * @param DepartmentDto It contain a list of parameter that use for Fetching The
	 *                  Department data according to parameter.
	 * @return An SummaryResponseModel containing the list of retrieved Department
	 *         with metadata.
	 */
	@Override
	public SummaryResponseModel fetchAll(@Valid DepartmentDto dto) {

        List<DepartmentInfo> departmentInfoList = departmentInfoRepo.findAll();   
        if(CollectionUtils.isEmpty(departmentInfoList)) {
        	return new SummaryResponseModel(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, null,
	                "Employee Details not found.", null);
        }
        List<DepartmentInfoModel> responseList = mapper.map(departmentInfoList, new TypeToken<List<DepartmentInfoModel>>(){}.getType());
		PageMetaModel metaModel = null;
        if(dto.getPage() != null && dto.getSize()!=  null) {
        	Long totalElements = (long) responseList.size();
			int totalPages = (int) Math.ceil((double) totalElements / dto.getSize());
			metaModel = new PageMetaModel(dto.getPage(), dto.getSize(), totalElements, totalPages);
			int firstIndex = (dto.getPage() - 1) * dto.getSize();
			int maxIndex = dto.getSize() + firstIndex;
			List<DepartmentInfoModel> paginatedData = null;

			if (responseList != null && responseList.size() <= maxIndex) {
				firstIndex = Math.min((dto.getPage() - 1) * dto.getSize(), responseList.size());
				maxIndex = Math.min(dto.getSize() + firstIndex, responseList.size());
			}
			paginatedData = responseList.subList(firstIndex, maxIndex);
			return new SummaryResponseModel(HttpStatus.OK.value(), HttpStatus.OK, paginatedData,
					"Data fetched successfully", metaModel);   	
        }    
        return new SummaryResponseModel(HttpStatus.OK.value(), HttpStatus.OK, responseList,
                "Fetched details successfully.", null);		
	}
}
