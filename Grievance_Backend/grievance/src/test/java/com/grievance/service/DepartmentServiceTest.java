package com.grievance.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.grievance.dto.DepartmentInDto;
import com.grievance.dto.DepartmentOutDto;
import com.grievance.entity.Department;
import com.grievance.exception.DepartmentAlreadyExists;
import com.grievance.repository.DepartmentRepository;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
	
	private Department department;
	
	private DepartmentInDto departmentInDto;
	
	private DepartmentOutDto departmentOutDto;
	
	@Mock
	DepartmentRepository departmentRepository;
	
	@InjectMocks
	DepartmentServiceImpl departmentService;
	
	@Mock
	ModelMapper modelMapper;
	
	@BeforeEach
	void setUp() {
		department = new Department();
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
		}catch (DepartmentAlreadyExists e) {
			assertThat(e.getMessage().equals("Department Already Exist Exception with email=HR"));
        }	
	}
	
	@Test
	public void fetch_all_departments() {
		List<DepartmentOutDto> departmentOutDtos = new ArrayList<DepartmentOutDto>();
		departmentOutDtos.add(departmentOutDto);
		
		List<Department> departments = new ArrayList<Department>();
		departments.add(department);
		
		when(departmentRepository.findAll()).thenReturn(departments);
		
		Optional<List<DepartmentOutDto>> expectedEepartmentOutDtos = departmentService.listAllDepartment();
		
		assertEquals(expectedEepartmentOutDtos.get().get(0).getDepartmentName(), departments.get(0).getDepartmentName());
	}
}

