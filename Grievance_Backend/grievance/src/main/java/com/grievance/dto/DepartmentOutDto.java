package com.grievance.dto;

import java.util.Objects;

public class DepartmentOutDto {
  /**
   * The unique identifier for the department.
   */
  private Integer departmentId;

  /**
   * The name of the department.
   */
  private String departmentName;

  /**
   * @return the departmentId
   */
  public Integer getDepartmentId() {
    return departmentId;
  }

  /**
   * @param departmentIdField the departmentId to set
   */
  public void setDepartmentId(final Integer departmentIdField) {
    this.departmentId = departmentIdField;
  }

  /**
   * @return the departmentName
   */
  public String getDepartmentName() {
    return departmentName;
  }

  /**
   * @param departmentNameField the departmentName to set
   */
  public void setDepartmentName(final String departmentNameField) {
    this.departmentName = departmentNameField;
  }

  /**
   * parameterized constructor.
   * @param departmentIdField
   * @param departmentNameField
   */
  public DepartmentOutDto(
      final Integer departmentIdField,
      final String departmentNameField) {
    super();
    this.departmentId = departmentIdField;
    this.departmentName = departmentNameField;
  }

  /**
   * hashCode of this departmentOutDto.
   */
  @Override
  public int hashCode() {
    return Objects.hash(departmentId, departmentName);
  }

  /**
   * method to compare departmentOutDto with object.
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    DepartmentOutDto other = (DepartmentOutDto) obj;
    return Objects.equals(departmentId, other.departmentId)
        && Objects.equals(departmentName, other.departmentName);
  }

  /**
   * default constructor.
   */
  public DepartmentOutDto() {
    super();
  }
}
