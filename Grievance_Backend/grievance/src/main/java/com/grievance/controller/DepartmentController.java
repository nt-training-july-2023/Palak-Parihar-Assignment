package com.grievance.controller;

import com.grievance.dto.DepartmentInDto;
import com.grievance.dto.DepartmentOutDto;
import com.grievance.exception.ResourceNotFoundException;
import com.grievance.service.DepartmentService;
import com.grievance.constants.ControllerURLS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.List;
import java.util.Optional;

/**
 * Rest Controller for department.
 */
@RestController
@CrossOrigin("*")
@RequestMapping(ControllerURLS.DEPARTMENT_BASE_URL)
public class DepartmentController {

  /**
   * Instance to create logs.
   */
  private static final Logger LOGGER =
      LoggerFactory.getLogger(DepartmentController.class);

  /**
   * Autowiring departmentService.
   */
  @Autowired
  private DepartmentService departmentService;

  /**
   * Department Controller method for saving a given Department.
   *
   * @param departmentInDto The DepartmentInDto to save.
   * @return ResponseEntity containing the saved department.
   */
  @PostMapping(path = ControllerURLS.SAVE_DATA)
  public ResponseEntity<?> saveDepartment(
      @RequestBody final DepartmentInDto departmentInDto) {
    LOGGER.info("Received request to save a department.");

    Optional<DepartmentOutDto> optional =
        departmentService.saveDepartment(departmentInDto);

    if (optional.isPresent()) {
      LOGGER.info("Department saved successfully.");
      return new ResponseEntity<>(optional.get(), HttpStatus.CREATED);
    } else {
      LOGGER.error("Failed to save department.");
      return new ResponseEntity<>("Failed to save department.",
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Department Controller method to return the list of all Departments.
   *
   * @param page The page number (optional).
   * @return ResponseEntity with a list of all Departments.
   */
  @GetMapping(path = ControllerURLS.GET_ALL_DATA)
  public ResponseEntity<?> listDepartments(
      @RequestParam(required = false) final Integer page) {
    LOGGER.info("Received request to list all departments.");

    Optional<List<DepartmentOutDto>> departmentOutDtos =
        departmentService.listAllDepartment(page);

    if (departmentOutDtos.isPresent()) {
      LOGGER.info("Listed all departments successfully.");
      return new ResponseEntity<>(departmentOutDtos.get(), HttpStatus.ACCEPTED);
    } else {
      LOGGER.error("Failed to list departments.");
      return new ResponseEntity<>("Failed to list departments.",
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Department Controller method to delete a department by its ID.
   *
   * @param departmentId The ID of the department to delete.
   * @return ResponseEntity indicating the result of the delete operation.
   */
  @DeleteMapping(path = ControllerURLS.DELETE_DATA_BY_ID)
  public ResponseEntity<?> deleteDepartmentById(
      @RequestParam final Integer departmentId) {
    LOGGER.info("Received request to delete department with ID: {}",
        departmentId);

    try {
      departmentService.deleteDepartment(departmentId);
      LOGGER.info("Department deleted successfully.");
      return new ResponseEntity<>("Department Deleted Successfully",
          HttpStatus.NO_CONTENT);
    } catch (ResourceNotFoundException e) {
      LOGGER.error("Failed to delete department.", e.getMessage());
      return new ResponseEntity<>("Failed to delete department.",
          HttpStatus.NOT_FOUND);
    }
  }
}
