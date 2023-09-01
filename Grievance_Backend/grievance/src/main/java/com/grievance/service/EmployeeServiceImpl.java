/**
 *
 */
package com.grievance.service;

import com.grievance.dto.EmployeeDto;
import com.grievance.entity.Employee;
import com.grievance.repository.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {
  @Autowired
  EmployeeRepository employeeRepository;

  @Override
  public Optional<EmployeeDto> saveEmployee(EmployeeDto employeeDto) { // TODO Auto-generated method stub
    EmployeeDto employeeDto2 = employeeRepository.save(employeeDto.toEntity()).toDto();
    return Optional.ofNullable(employeeDto2);
  }

@Override public Optional<List<EmployeeDto>> listAllEmployees(){// TODO Auto-generated method stub
	List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
	for(Employee employee : employeeRepository.findAll()) {
		employeeDtos.add(employee.toDto());
	}
	return Optional.ofNullable(employeeDtos);
}
}
