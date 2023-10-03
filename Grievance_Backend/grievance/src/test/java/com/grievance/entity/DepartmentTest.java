package com.grievance.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DepartmentTest {

  @Test
  void testDepartmentEquals() {
    Department department1 = new Department("HR");
    department1.setDepartmentId(101);

    Department department2 = new Department("FINANCE");
    department2.setDepartmentId(102);

    Department department3 = new Department("HR");
    department1.setDepartmentId(101);

    assertThat(department1.equals(department3));

    assertThat(!department1.equals(department3));
  }

  @Test
  void testDepartmentHashCode() {
    Department department1 = new Department("HR");
    department1.setDepartmentId(101);

    Department department2 = new Department("FINANCE");
    department2.setDepartmentId(102);

    Department department3 = new Department("HR");
    department3.setDepartmentId(101);
    
    Assertions.assertEquals(department1.hashCode(), department3.hashCode());
    Assertions.assertNotEquals(department1.hashCode(), department2.hashCode());
  }

}
