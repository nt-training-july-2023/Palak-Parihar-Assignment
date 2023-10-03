package com.grievance.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.grievance.entity.UserType;

@SpringBootTest
class EmployeeDtoTest {
  @Autowired
  ModelMapper modelMapper;

  @Test
  void test_employeeInDto_equals() {

    EmployeeInDto employeeInDto = new EmployeeInDto("ayushi@nucleusteq.com", "Ayushi", "PaadfgWSdf==", UserType.ADMIN,
        null);
    EmployeeInDto employeeInDto1 = new EmployeeInDto("ayushi@nucleusteq.com", "Ayushi", "PaadfgWSdf==", UserType.MEMBER,
        null);
    EmployeeInDto employeeInDto2 = new EmployeeInDto("ayushi@nucleusteq.com", "Ayushi", "PaadfgWSdf==", UserType.ADMIN,
        null);

    assertThat(!employeeInDto.equals(employeeInDto1));

    assertThat(!employeeInDto.equals(employeeInDto2));

  }

  @Test
  void test_employeeInDto_hashCode() {
    EmployeeInDto employeeInDto = new EmployeeInDto("ayushi@nucleusteq.com", "Ayushi", "PaadfgWSdf==", UserType.ADMIN,
        null);
    EmployeeInDto employeeInDto1 = new EmployeeInDto("ayushi@nucleusteq.com", "Ayushi", "PaadfgWSdf==", UserType.MEMBER,
        null);
    EmployeeInDto employeeInDto2 = new EmployeeInDto("ayushi@nucleusteq.com", "Ayushi", "PaadfgWSdf==", UserType.ADMIN,
        null);

    assertEquals(employeeInDto.hashCode(), employeeInDto2.hashCode());

    assertNotEquals(employeeInDto.hashCode(), employeeInDto1.hashCode());
  }

  @Test
  void test_employeeLoginDto_equals() {
    EmployeeLoginDto employeeLoginDto = new EmployeeLoginDto("ayushi@nucleusteq.com", "Password");
    EmployeeLoginDto employeeLoginDto1 = new EmployeeLoginDto("aaayushi@nucleusteq.com", "Password");
    EmployeeLoginDto employeeLoginDto2 = new EmployeeLoginDto("ayushi@nucleusteq.com", "Password");

    assertThat(employeeLoginDto.equals(employeeLoginDto2));

    assertThat(!employeeLoginDto.equals(employeeLoginDto1));
  }

  @Test
  void test_employeeLoginDto_hashCode() {
    EmployeeLoginDto employeeLoginDto = new EmployeeLoginDto("ayushi@nucleusteq.com", "Password");
    EmployeeLoginDto employeeLoginDto1 = new EmployeeLoginDto("aaayushi@nucleusteq.com", "Password");
    EmployeeLoginDto employeeLoginDto2 = new EmployeeLoginDto("ayushi@nucleusteq.com", "Password");

    assertEquals(employeeLoginDto.hashCode(), employeeLoginDto2.hashCode());

    assertNotEquals(employeeLoginDto1.hashCode(), employeeLoginDto2.hashCode());
  }

  @Test
  void test_employeeOutDto_equals() {
    EmployeeOutDto employeeOutDto = new EmployeeOutDto("ayushi@nucleusteq.com", "Ayushi", UserType.ADMIN, false, "HR");
    EmployeeOutDto employeeOutDto1 = new EmployeeOutDto("aayushi@nucleusteq.com", "Ayushi", UserType.MEMBER, false, "HR");
    EmployeeOutDto employeeOutDto2 = new EmployeeOutDto("aayushi@nucleusteq.com", "Ayushi", UserType.ADMIN, false, "HR");

    assertThat(!employeeOutDto.equals(employeeOutDto1));

    assertThat(employeeOutDto.equals(employeeOutDto2));
    
    assertThat(!employeeOutDto.equals(null));
    
    assertThat(employeeOutDto.equals(employeeOutDto));
  }

  @Test
  void test_employeeOutDto_hashCode() {
    EmployeeOutDto employeeOutDto = new EmployeeOutDto("ayushi@nucleusteq.com", "Ayushi", UserType.ADMIN, false, "HR");
    EmployeeOutDto employeeOutDto1 = new EmployeeOutDto("aayushi@nucleusteq.com", "Ayushi", UserType.ADMIN, false, "HR");
    EmployeeOutDto employeeOutDto2 = new EmployeeOutDto("ayushi@nucleusteq.com", "Ayushi", UserType.ADMIN, false, "HR");
    
    assertEquals(employeeOutDto.hashCode(), employeeOutDto2.hashCode());
    
    assertNotEquals(employeeOutDto.hashCode(), employeeOutDto1.hashCode());
  }
  
}
