package com.grievance.controller;

import com.grievance.dto.DepartmentDto;
import com.grievance.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller for department.
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/department")
public class DepartmentController {
  /**
   * Autowiring departmentService.
   */
  @Autowired
  private DepartmentService departmentService;

  /**
   * Department Controller method for saving given Department.
   *
   * @param departmentDto of DepartmentDto.
   * @return Responseentity of Department DTO.
   *
   */
  @PostMapping("/save")
  public ResponseEntity<?> saveDepartment(
    @RequestBody final DepartmentDto departmentDto
  ) {
    return ResponseEntity.ok(departmentService.saveDepartment(departmentDto));
  }

  /**
   * Department Controller method to return the list of all Departments.
   *
   * @return ResponseEntity with list of All Departments.
   *
   */
  @GetMapping("/listDepartments")
  public ResponseEntity<?> listDepartments() {
    return ResponseEntity.ok(departmentService.listAllDepartment());
  }
}
