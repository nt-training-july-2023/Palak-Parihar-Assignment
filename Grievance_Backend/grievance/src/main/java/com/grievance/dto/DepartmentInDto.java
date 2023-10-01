/**
 * The DepartmentDto class is used for
 * transferring data from the
 * DAO (Data Access Object) to the service layer.
 *
 * @author palak
 */

package com.grievance.dto;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;

/**
 * The DepartmentDto class represents a data transfer
 * object for department-related information.
 */
public class DepartmentInDto {
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
  @NotEmpty
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
  public DepartmentInDto() {
    super();
  }

  /**
   * hashCode method of this departmentInDto.
   */
  @Override
  public int hashCode() {
    return Objects.hash(departmentId, departmentName);
  }

  /**
   * method to compare this departmentInDto with objects.
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
    DepartmentInDto other = (DepartmentInDto) obj;
    return Objects.equals(departmentId, other.departmentId)
        && Objects.equals(departmentName, other.departmentName);
  }

  /**
   * parameterized constructor.
   * @param departmentIdField
   * @param departmentNameField
   */
  public DepartmentInDto(
      final Integer departmentIdField,
      final String departmentNameField) {
    super();
    this.departmentId = departmentIdField;
    this.departmentName = departmentNameField;
  }
}
