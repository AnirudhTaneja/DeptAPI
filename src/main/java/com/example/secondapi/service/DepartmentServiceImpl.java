package com.example.secondapi.service;

import com.example.secondapi.entity.Department;
import com.example.secondapi.error.DepartmentNotFoundException;
import com.example.secondapi.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findDepartmentById(Long departmentId) throws DepartmentNotFoundException {
       Optional<Department> department = departmentRepository.findById(departmentId);

       if (!department.isPresent()){
           throw new DepartmentNotFoundException("Department Not Available");
       }
       return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartmentById(Long departmentId, Department department) {
        //find department object from DB by Id
       Department deptDB = departmentRepository.findById(departmentId).get();

       if(Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())){
           deptDB.setDepartmentName(department.getDepartmentName());
       }

       if (Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())){
           deptDB.setDepartmentAddress(department.getDepartmentAddress());
       }

       if (Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())){
           deptDB.setDepartmentCode(department.getDepartmentCode());
       }

       return departmentRepository.save(deptDB);
    }

    @Override
    public Department findDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentName(departmentName);
    }
}
