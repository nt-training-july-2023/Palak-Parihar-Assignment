/**
 *
 */
package com.grievance.service;

import com.grievance.dto.DepartmentDto;
import com.grievance.entity.Department;
import com.grievance.repository.DepartmentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
  @Autowired
  DepartmentRepository departmentRepository;

  @Override
  public Optional<DepartmentDto> saveDepartment(final DepartmentDto departmentDto) { // TODO Auto-generated method stub
    Department department2 = departmentDto.toEntity();
    Department savedDepartment = departmentRepository.save(department2);
    return Optional.ofNullable(savedDepartment.toDto());
  }

  @Override
  public Optional<List<DepartmentDto>> listAllDepartment() { // TODO Auto-generated method stub
    List<DepartmentDto> list = new ArrayList<DepartmentDto>();
    for (Department department : departmentRepository.findAll()) {
      list.add(department.toDto());
    }
    return Optional.ofNullable(list);
  }
  //@Override public Department saveDepartment(Department department){// TODO Auto-generated method stub
  //    Department department2 = departmentRepository.findByDepartmentName(department.getDepartmentName());
  //	if( department2!= null) {
  //	return department2;
  //}
  //	return departmentRepository.save(department);}
  //
  //@Override public List<Department> listAllDepartments(){// TODO Auto-generated method stub
  //return departmentRepository.findAll();}

}
