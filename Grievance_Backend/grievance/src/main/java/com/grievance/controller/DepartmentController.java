package com.grievance.controller;

import com.grievance.dto.DepartmentInDto;
import com.grievance.dto.DepartmentOutDto;
import com.grievance.exception.DepartmentAlreadyExists;
import com.grievance.service.DepartmentService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
   *
   * Department Controller method for saving given Department.
   *
   * @param departmentInDto
   * @return department saved.
   */
//  @PostMapping("/save")
//  public ResponseEntity<?> saveDepartment(
//    @RequestBody final DepartmentInDto departmentInDto
//  ) {
//    return ResponseEntity.ok(departmentService
//      .saveDepartment(departmentInDto));
//  }
  @PostMapping("/save")
  public ResponseEntity<?> saveDepartment(
    @RequestBody final DepartmentInDto departmentInDto
  ) {
          try {
              Optional<DepartmentOutDto> optional =
                      departmentService.saveDepartment(departmentInDto);
              return new ResponseEntity<>(optional, HttpStatus.CREATED);
          } catch (DepartmentAlreadyExists e) {
             return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
          }
  }

  /**
   *
   * Department Controller method to return the list of all Departments.
   *
   * @return ResponseEntity with list of All Departments.
   *
   */
  @GetMapping("/listDepartments")
  public ResponseEntity<?> listDepartments(
    ) {
        Optional<List<DepartmentOutDto>> departmentOutDtos =
               departmentService.listAllDepartment();
        return new ResponseEntity<>(departmentOutDtos, HttpStatus.ACCEPTED);
  }
}
