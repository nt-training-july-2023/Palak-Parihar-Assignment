package com.grievance.repository;

import com.grievance.entity.Department;
import com.grievance.entity.Employee;
import com.grievance.entity.Ticket;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import com.grievance.entity.Status;



public interface TicketRepository extends JpaRepository<Ticket, Integer> {
  /**
   * method to access all the tickets by their department name.
   * @param department
   * @param pageRequest
   * @return list of tickets.
   */
  List<Ticket> findByDepartment(Department department, PageRequest pageRequest);

  /**
   * method to access all the tickets by their owner(employee) name.
   * @param employee
   * @param pageRequest
   * @return list of tickets.
   */
  List<Ticket> findByEmployee(Employee employee, PageRequest pageRequest);

  /**
   * method to access all the tickets by deparment and employee.
   * @param status
   * @param employee
   * @param pageRequest
   * @return list of tickets.
   */
  List<Ticket> findByStatusAndEmployee(
          Status status,
          Employee employee,
          PageRequest pageRequest);

  /**
   * method to access all the tickets by status.
   * @param status
   * @param pageRequest
   * @return list of tickets by status.
   */
  List<Ticket> findByStatus(Status status, PageRequest pageRequest);

  /**
   * @param department
   * @param status
   * @param pageRequest
   * @return list of tickets by status department and employee.
   */
  List<Ticket> findByDepartmentAndStatus(
      Department department, Status status,
      PageRequest pageRequest);
}
