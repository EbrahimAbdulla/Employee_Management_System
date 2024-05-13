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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EMPLOYEE_INFO", schema = "RETAIL_CLOUD")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeInfo implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "employee_id")
	private Long employeeId;

	private String emplyeeName;

	private Date dateOfBirth;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
	private List<AddressInfo> addressInfo;

	private String role;

	private Date joiningDate;

	private String yearlyBonusPercentage;
	
	private Boolean isReportingManager;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "reporting_manager_id",referencedColumnName ="employee_id",nullable   = true)
	private EmployeeInfo reportingManagerDetails;

}
