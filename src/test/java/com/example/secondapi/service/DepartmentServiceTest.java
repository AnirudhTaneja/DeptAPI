package com.example.secondapi.service;

import com.example.secondapi.entity.Department;
import com.example.secondapi.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
    Department department = Department.builder()
                            .departmentName("IT")
                            .departmentAddress("Amritsar")
                            .departmentCode("IT-007")
                            .departmentId(1L)
                            .build();
        Mockito.when(departmentRepository.findByDepartmentName("IT"))
                .thenReturn(department);

    }

    @Test
    @DisplayName("Check Fetch by department name")
    public void whenValidDepartmentName_ThenDepartmentShouldBeFound(){
            String departmentName = "IT";
        Department found = departmentService.findDepartmentByName(departmentName);
        assertEquals(departmentName, found.getDepartmentName());
    }
}