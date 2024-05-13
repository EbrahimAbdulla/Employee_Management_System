package com.retailcloud.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retailcloud.employee.entity.DepartmentInfo;

@Repository
public interface DepartmentInfoRepository extends JpaRepository<DepartmentInfo,Long>{

}
