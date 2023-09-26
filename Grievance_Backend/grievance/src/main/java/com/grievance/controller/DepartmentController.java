package com.grievance.controller;

import com.grievance.dto.DepartmentInDto;
import com.grievance.dto.DepartmentOutDto;
import com.grievance.service.DepartmentService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  @PostMapping("/save")
  public ResponseEntity<?> saveDepartment(
      @RequestBody final DepartmentInDto departmentInDto) {
    Optional<DepartmentOutDto> optional =
        departmentService.saveDepartment(departmentInDto);
    return new ResponseEntity<>(optional.get(), HttpStatus.CREATED);
  }

  /**
   *
   * Department Controller method to return the list of all Departments.
   * @param page
   * @return ResponseEntity with list of All Departments.
   *
   */
  @GetMapping("/listDepartments")
  public ResponseEntity<?> listDepartments(
      @RequestParam(required = false) final Integer page) {
    Optional<List<DepartmentOutDto>> departmentOutDtos =
        departmentService.listAllDepartment(page);
    return new ResponseEntity<>(departmentOutDtos.get(), HttpStatus.ACCEPTED);
  }

  /**
   * @param departmentId
   * @return responseentity.
   */
  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteDepartmentById(
      @RequestParam final Integer departmentId) {
    departmentService.deleteDepartment(departmentId);
    return new ResponseEntity<>("Department Deleted Successfully",
        HttpStatus.NO_CONTENT);
  }
}
