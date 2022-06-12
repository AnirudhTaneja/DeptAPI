package com.example.secondapi.service;

import com.example.secondapi.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
   public Department saveDepartment(Department department);

   List<Department> getAllDepartments();

   public Department findDepartmentById(Long departmentId);

   public void deleteDepartmentById(Long departmentId);

   public Department updateDepartmentById(Long departmentId, Department department);
}