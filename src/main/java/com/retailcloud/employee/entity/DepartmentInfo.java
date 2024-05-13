package com.retailcloud.employee.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DEPARTMENT_INFO", schema = "RETAIL_CLOUD")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentInfo implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "department_id")
	private Long departmentId;

	private String departmentName;

	private Date creationDate;

	private String location;

	private Double noOfEmployees;

	private Double budget;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name ="department_id",referencedColumnName = "department_id")
	private List<EmployeeInfo> employee;


}
