/**
 * The DepartmentDto class is used for
 * transferring data from the
 * DAO (Data Access Object) to the service layer.
 *
 * @author palak
 */

package com.grievance.dto;

/**
 * The DepartmentDto class represents a data transfer
 * object for department-related information.
 */
public class DepartmentDto {
  /**
   * departmentId of Department DTO.
   *
   */
  private Integer departmentId;

  /**
   * Gets the department ID.
   *
   * @return the departmentId
   */
  public Integer getDepartmentId() {
    return departmentId;
  }

  /**
   * Sets the department ID.
   *
   * @param departmentIdField the departmentId to set
   */
  public void setDepartmentId(final Integer departmentIdField) {
    this.departmentId = departmentIdField;
  }

  /**
   * departmentName of Department Dto.
   */
  private String departmentName;

  /**
   * Gets the department name.
   *
   * @return the departmentName
   */
  public String getDepartmentName() {
    return departmentName;
  }

  /**
   * Sets the department name.
   *
   * @param departmentNameField the departmentName to set
   */
  public void setDepartmentName(final String departmentNameField) {
    this.departmentName = departmentNameField;
  }

  /**
   * Constructs a new DepartmentDto object.
   */
  public DepartmentDto() {
    super();
    // TODO Auto-generated constructor stub
  }
}
