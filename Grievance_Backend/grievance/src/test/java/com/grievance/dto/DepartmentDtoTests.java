/**
 * 
 */
package com.grievance.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 *
 */
@SpringBootTest
class DepartmentDtoTests {
  @Autowired
  ModelMapper modelMapper;

  @Test
  void test_departmentInDto_hashcode() {
    DepartmentInDto departmentInDto = new DepartmentInDto(101, "HR");
    DepartmentInDto departmentInDto2 = new DepartmentInDto(102, "FINANCE");
    DepartmentInDto departmentInDto3 = new DepartmentInDto(101, "HR");

    assertThat(!departmentInDto.equals(departmentInDto2));

    assertThat(departmentInDto.equals(departmentInDto3));
  }
  
  @Test
  void test_departmentInDto_equals() {
    DepartmentInDto departmentInDto = new DepartmentInDto(101, "HR");
    DepartmentInDto departmentInDto2 = new DepartmentInDto(102, "FINANCE");
    DepartmentInDto departmentInDto3 = new DepartmentInDto(101, "HR");

    assertEquals(departmentInDto.hashCode(), departmentInDto3.hashCode());
    assertNotEquals(departmentInDto.hashCode(), departmentInDto2.hashCode());

  }
  
  @Test
  void test_departmentOutDto_hashcode() {
    DepartmentOutDto departmentOutDto = new DepartmentOutDto(101, "HR");
    DepartmentOutDto departmentOutDto2 = new DepartmentOutDto(102, "FINANCE");
    DepartmentOutDto departmentOutDto3 = new DepartmentOutDto(101, "HR");

    assertThat(!departmentOutDto.equals(departmentOutDto2));

    assertThat(departmentOutDto.equals(departmentOutDto3));
  }
  
  @Test
  void test_departmentOutDto_equals() {
    DepartmentOutDto departmentOutDto = new DepartmentOutDto(101, "HR");
    DepartmentOutDto departmentOutDto2 = new DepartmentOutDto(102, "FINANCE");
    DepartmentOutDto departmentOutDto3 = new DepartmentOutDto(101, "HR");

    assertEquals(departmentOutDto.hashCode(), departmentOutDto3.hashCode());
    assertNotEquals(departmentOutDto.hashCode(), departmentOutDto2.hashCode());

  }
}
