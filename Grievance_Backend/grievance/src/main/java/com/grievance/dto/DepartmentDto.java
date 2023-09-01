/**
 *
 */
package com.grievance.dto;

import com.grievance.entity.Department;

/**
 *
 */
public class DepartmentDto {
  private Integer departmentId;

  /**
   * @return the departmentId
   */
  public Integer getDepartmentId() {
    return departmentId;
  }

  /**
   * @param departmentId the departmentId to set
   */
  public void setDepartmentId(Integer departmentId) {
    this.departmentId = departmentId;
  }

  private String departmentName;

  /**
   * @return the departmentName
   */
  public String getDepartmentName() {
    return departmentName;
  }

  /**
   * @param departmentName the departmentName to set
   */
  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  public DepartmentDto(Department department) {
    this.setDepartmentName(department.getDepartmentName());
  }

  public DepartmentDto() {
    super();
    // TODO Auto-generated constructor stub
  }

  public DepartmentDto(String departmentName) {
    super();
    this.departmentName = departmentName;
  }

  public Department toEntity() {
    Department department = new Department();
    department.setDepartmentId(departmentId);
    department.setDepartmentName(departmentName);
    return department;
  }
}
