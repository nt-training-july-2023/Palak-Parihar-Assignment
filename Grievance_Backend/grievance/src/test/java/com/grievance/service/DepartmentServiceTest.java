package com.grievance.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.grievance.dto.DepartmentInDto;
import com.grievance.dto.DepartmentOutDto;
import com.grievance.entity.Department;
import com.grievance.entity.Employee;
import com.grievance.exception.RecordAlreadyExistException;
import com.grievance.exception.ResourceNotFoundException;
import com.grievance.exception.CustomException;
import com.grievance.repository.DepartmentRepository;
import com.grievance.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

  private Department department;

  private DepartmentInDto departmentInDto;

  private DepartmentOutDto departmentOutDto;

  @Mock
  DepartmentRepository departmentRepository;
  
  @Mock
  EmployeeRepository employeeRepository;

  @InjectMocks
  DepartmentServiceImpl departmentService;

  @Mock
  ModelMapper modelMapper;

  @BeforeEach
  void setUp() {
    department = new Department();
    department.setDepartmentId(101);
    department.setDepartmentName("HR");

    departmentInDto = new DepartmentInDto();
    departmentInDto.setDepartmentName("HR");

    departmentOutDto = new DepartmentOutDto();
    departmentOutDto.setDepartmentId(12);
    departmentOutDto.setDepartmentName("HR");

    lenient().when(modelMapper.map(departmentInDto, Department.class)).thenReturn(department);

    lenient().when(modelMapper.map(department, DepartmentOutDto.class)).thenReturn(departmentOutDto);
  }

@Test
	public void when_save_department_successfully_return_department() {
		
		when(departmentRepository.findByDepartmentName(Mockito.anyString())).thenReturn(null);
		
		when(departmentRepository.save(Mockito.any(Department.class))).thenReturn(department);
		
		Optional<DepartmentOutDto> optional = departmentService.saveDepartment(departmentInDto);
		
		assertEquals(optional.get().getDepartmentName(), departmentInDto.getDepartmentName());
	}

@Test
	public void when_save_department_fails_return_department() {
		when(departmentRepository.findByDepartmentName(Mockito.anyString())).thenReturn(department);
				
		try {
			departmentService.saveDepartment(departmentInDto);
		}catch (RecordAlreadyExistException e) {
			assertThat(e.getMessage().equals("Department Already Exist Exception with email=HR"));
        }	
	}

  @Test
  public void fetch_all_departments_with_pagination() {

    List<Department> departments = new ArrayList<Department>();
    departments.add(department);
    
    Page<Department> pageDepartments = new PageImpl<Department>(departments);

    when(departmentRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(pageDepartments);

    Optional<List<DepartmentOutDto>> result = departmentService.listAllDepartment(1);

    assertThat(result).hasValueSatisfying(departmentOutDtos -> {
      assertThat(departmentOutDtos).hasSize(departments.size());
    });  
    }
  
  @Test
  public void fetch_all_departments() {

    List<Department> departments = new ArrayList<Department>();
    departments.add(department);
    
    when(departmentRepository.findAll()).thenReturn(departments);

    Optional<List<DepartmentOutDto>> result = departmentService.listAllDepartment(null);

    assertThat(result).hasValueSatisfying(departmentOutDtos -> {
      assertThat(departmentOutDtos).hasSize(departments.size());
    });  
    }

  @Test
  public void delete_department_success() {
    Employee employee = new Employee();
    Department department = new Department();
    department.setDepartmentId(102);
    department.setDepartmentName("CRM");
    employee.setDepartment(department);
    
    when(departmentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(this.department));
    when(employeeRepository.findByEmail(Mockito.eq("ayushi@nucleusteq.com"))).thenReturn(employee);
    doNothing().when(departmentRepository).deleteById(Mockito.anyInt());

    departmentService.deleteDepartment(101, "ayushi@nucleusteq.com");

    Mockito.verify(departmentRepository, Mockito.times(1)).deleteById(101);
    ;
  }

  @Test
  public void delete_department_fails() {
    assertThrows(ResourceNotFoundException.class, () -> {
      departmentService.deleteDepartment(101, null);
    });

  }
  
  @Test
  public void delete_department_fails_when_user_delete_their_department() {
    Employee employee = new Employee();
    Department department = new Department();
    department.setDepartmentId(101);
    department.setDepartmentName("HR");
    employee.setDepartment(department);
    
    when(departmentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(this.department));
    when(employeeRepository.findByEmail(Mockito.eq("ayushi@nucleusteq.com"))).thenReturn(employee);
    assertThrows(CustomException.class, () -> {
      departmentService.deleteDepartment(101, "ayushi@nucleusteq.com");
    });

  }

}
