package com.example.secondapi.controller;

import com.example.secondapi.entity.Department;
import com.example.secondapi.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @MockBean
    DepartmentService departmentService;

    @Autowired
    MockMvc mockMvc;

    private Department department;

    @BeforeEach
    void setUp() {
        department= Department.builder()
                .departmentName("IT")
                .departmentAddress("Delhi")
                .departmentCode("IT-007")
                .departmentId(1L)
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment =  Department.builder()
                .departmentName("IT")
                .departmentAddress("Delhi")
                .departmentCode("IT-007")
                .build();

        Mockito.when(departmentService.saveDepartment(inputDepartment))
                .thenReturn(department);

        mockMvc.perform(post("/departments")
        .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"departmentName\":\"IT\",\n" +
                        "\t\"departmentAddress\":\"Delhi\",\n" +
                        "\t\"departmentCode\":\"IT-007\"\n" +
                        "}"))
                .andExpect(status().isOk());

    }

    @Test
    void findDepartmentById() throws Exception {
            Mockito.when(departmentService.findDepartmentById(1L))
                    .thenReturn(department);
            mockMvc.perform(get("/departments/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
                    .andExpect(jsonPath("$.departmentName")
                            .value(department.getDepartmentName()));
    }
}