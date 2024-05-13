package com.retailcloud.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.retailcloud.employee.entity.DepartmentInfo;

@Repository
public interface DepartmentInfoRepository extends JpaRepository<DepartmentInfo,Long>{

	 @Query("SELECT d FROM DepartmentInfo d JOIN d.employee e WHERE e.employeeId = :employeeId")
	 DepartmentInfo findByEmployeeId(Long employeeId);

}
