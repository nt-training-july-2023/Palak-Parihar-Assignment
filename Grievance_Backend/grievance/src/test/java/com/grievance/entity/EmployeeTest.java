package com.grievance.entity;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeTest {
  
  @Test
  void testEmployeeEquals() {
    Employee employee1 = new Employee(1,"ayushi@nucleusteq.com", "Ayushi", "Qwergfds==", UserType.ADMIN, false, null);
    Employee employee2 = new Employee(1,"ayushi@nucleusteq.com", "Ayushi", "Qwergfds==", UserType.ADMIN, false, null);
    
    assertThat(employee1.equals(employee2));
    
    assertThat(!employee1.equals(null));
    
    assertThat(!employee1.equals(new Department()));
    
    assertThat(employee1.equals(employee1));
  }
  
  @Test
  void testEmployeeHashCode() {
    Employee employee1 = new Employee(1,"ayushi@nucleusteq.com", "Ayushi", "Qwergfds==", UserType.ADMIN, false, null);
    Employee employee2 = new Employee(1,"ayushi@nucleusteq.com", "Ayushi", "Qwergfds==", UserType.ADMIN, false, null);
    Employee employee3 = new Employee(1,"anita@nucleusteq.com", "Ayushi", "Qwergfds==", UserType.ADMIN, false, null);

    Assertions.assertEquals(employee1.hashCode(), employee2.hashCode());
    Assertions.assertNotEquals(employee1.hashCode(), employee3.hashCode());
  }

}
