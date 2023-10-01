package com.grievance.controller;

import com.grievance.dto.DepartmentInDto;
import com.grievance.dto.DepartmentOutDto;
import com.grievance.response.Response;
import com.grievance.service.DepartmentService;
import com.grievance.constants.ControllerURLS;
import com.grievance.constants.ResponseConstants;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
  private static final Logger LOGGER = LoggerFactory
      .getLogger(DepartmentController.class);

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
  public ResponseEntity<Response<DepartmentOutDto>> saveDepartment(
      @Valid @RequestBody final DepartmentInDto departmentInDto) {
    LOGGER.info("Received request to save a department.");

    Optional<DepartmentOutDto> savedDepartment = departmentService
        .saveDepartment(departmentInDto);

    Response<DepartmentOutDto> response = new Response<DepartmentOutDto>(
        ResponseConstants.DEPARTMENT_CREATED,
        HttpStatus.CREATED.value(), savedDepartment.get());

    LOGGER.info("Department created successfully.");
    return new ResponseEntity<Response<DepartmentOutDto>>(response,
        HttpStatus.CREATED);
  }

  /**
   * Department Controller method to return the list of all Departments.
   *
   * @param page The page number (optional).
   * @return ResponseEntity with a list of all Departments.
   */
  @GetMapping(path = ControllerURLS.GET_ALL_DATA)
  public ResponseEntity<Response<List<DepartmentOutDto>>> listDepartments(
      @RequestParam(required = false) final Integer page) {
    LOGGER.info("Received request to list all departments.");

    Optional<List<DepartmentOutDto>> departmentOutDtos = departmentService
        .listAllDepartment(page);
    Response<List<DepartmentOutDto>> response =
        new Response<List<DepartmentOutDto>>(
        "Departments retrieved successfully",
        HttpStatus.ACCEPTED.value(), departmentOutDtos.get());
    return new ResponseEntity<Response<List<DepartmentOutDto>>>(response,
        HttpStatus.ACCEPTED);
  }

  /**
   * Department Controller method to delete a department by its ID.
   *
   * @param email of user.
   * @param departmentId The ID of the department to delete.
   * @return ResponseEntity indicating the result of the delete operation.
   */
  @DeleteMapping(path = ControllerURLS.DELETE_DATA_BY_ID)
  public ResponseEntity<Response<Boolean>> deleteDepartmentById(
      @RequestHeader final String email,
      @RequestParam final Integer departmentId) {
    LOGGER.info("Received request to delete department with ID: {}",
        departmentId);

    departmentService.deleteDepartment(departmentId, email);
    String message = ResponseConstants.DEPARTMENT_DELETED;
    Response<Boolean> response = new Response<Boolean>(message,
        HttpStatus.OK.value(), true);
    LOGGER.info("Department deleted successfully.");
    return new ResponseEntity<Response<Boolean>>(response,
        HttpStatus.OK);
  }
}
