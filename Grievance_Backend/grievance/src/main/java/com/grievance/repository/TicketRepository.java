package com.grievance.repository;

import com.grievance.entity.Department;
import com.grievance.entity.Employee;
import com.grievance.entity.Ticket;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketRepository extends JpaRepository<Ticket, Integer> {
  /**
   * method to access all the tickets by their department name.
   * @param department
   * @return list of tickets.
   */
  List<Ticket> findByDepartment(Department department);

  /**
   * method to access all the tickets by their owner(employee) name.
   * @param employee
   * @return list of tickets.
   */
  List<Ticket> findByEmployee(Employee employee);

//  /**
//   * method to access all the tickets by their status.
//   * @param status
//   * @param pageRequest
//   * @return list of tickets.
//   */
//  List<Ticket> findAllByStatus( PageRequest pageRequest);
}
