

package com.grievance.repository;

import com.grievance.entity.Department;
import com.grievance.entity.Employee;
import com.grievance.entity.Ticket;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import com.grievance.entity.Status;



public interface TicketRepository extends JpaRepository<Ticket, Integer> {
  /**
   * method to access all the tickets by their department name.
   * @param department
   * @param pageRequest
   * @return Page of tickets.
   */
  Page<Ticket> findByDepartment(Department department, PageRequest pageRequest);

  /**
   * method to access all the tickets by their owner(employee) name.
   * @param employee
   * @param pageRequest
   * @return Page of tickets.
   */
  Page<Ticket> findByEmployee(Employee employee, PageRequest pageRequest);

  /**
   * method to access all the tickets by deparment and employee.
   * @param status
   * @param employee
   * @param pageRequest
   * @return Page of tickets.
   */
  Page<Ticket> findByStatusAndEmployee(
          Status status,
          Employee employee,
          PageRequest pageRequest);

  /**
   * method to access all the tickets by status.
   * @param status
   * @param pageRequest
   * @return Page of tickets by status.
   */
  Page<Ticket> findByStatus(Status status, PageRequest pageRequest);

  /**
   * @param department
   * @param status
   * @param pageRequest
   * @return Page of tickets by status department
   */
  Page<Ticket> findByDepartmentAndStatus(
      Department department, Status status,
      PageRequest pageRequest);
  /**
   *
   * @param department
   * @param status
   * @param employee
   * @param pageRequest
   * @return Page of tickets by status department and employee.
   */
  Page<Ticket> findByDepartmentAndStatusAndEmployee(
      Department department,
      Status status,
      Employee employee,
      PageRequest pageRequest);

  /**
  *
  * @param department
  * @param employee
  * @param pageRequest
  * @return Page of tickets by status department and employee.
  */
 Page<Ticket> findByDepartmentAndEmployee(
     Department department,
     Employee employee,
     PageRequest pageRequest);
}

