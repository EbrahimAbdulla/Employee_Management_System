package com.retailcloud.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.retailcloud.employee.entity.EmployeeInfo;
@Repository
public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Long>{

	/**
     * Its a jpa query which will retrive reporting manager details by using employeeId
     */
	@Query("from EmployeeInfo em WHERE em.reportingManagerDetails.employeeId =:employeeId")
	List<EmployeeInfo> findByManagingDirectorId(Long employeeId);

}
