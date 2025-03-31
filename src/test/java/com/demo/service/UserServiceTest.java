package com.demo.service;

import com.demo.domain.Department;
import com.demo.domain.User;
import com.demo.dto.FullUserWithDepartment;
import com.demo.dto.SimpleUserWithDepartment;
import com.demo.repository.DepartmentRepository;
import com.demo.repository.UserRepository;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Resource
    private DepartmentRepository departmentRepository;

    @Resource
    private UserRepository userRepository;

    private final String departmentNo = "D001";
    private final String departmentName = "IT";
    private final String userName = "tester";
    private final String userEmpNo = "U001";
    private final String userEmail = "test@test.com.tw";
    private final String userPhone = "0912345678";

    private final boolean userActive = true;

    private void prepareData() {
        Department department = new Department();
        department.setNo(departmentNo);
        department.setName(departmentName);
        departmentRepository.save(department);
        User user = new User();
        user.setDepartmentNo(departmentNo);
        user.setNo(userEmpNo);
        user.setName(userName);
        user.setEmail(userEmail);
        user.setPhone(userPhone);
        user.setActive(userActive);
        userRepository.save(user);
    }

    @DisplayName("測試用使用者編號查詢簡易使用者+單位資訊")
    @Test
    void findSimpleUserWithDepartmentByEmpNo() {
        prepareData();
        SimpleUserWithDepartment simpleUserWithDepartment = userService.findSimpleUserWithDepartmentByEmpNo(userEmpNo);
        assertThat(simpleUserWithDepartment).isNotNull();
        assertThat(simpleUserWithDepartment.getEmpNo()).isEqualTo(userEmpNo);
        assertThat(simpleUserWithDepartment.getName()).isEqualTo(userName);
        assertThat(simpleUserWithDepartment.getDepartmentNo()).isEqualTo(departmentNo);
        assertThat(simpleUserWithDepartment.getDepartmentName()).isEqualTo(departmentName);
    }

    @DisplayName("測試用使用者編號查詢完整使用者+單位完整資訊")
    @Test
    void findFullUserWithDepartmentByEmpNo() {
        prepareData();
        FullUserWithDepartment fullUserWithDepartment = userService.findFullUserWithDepartmentByEmpNo(userEmpNo);
        assertThat(fullUserWithDepartment).isNotNull();
        assertThat(fullUserWithDepartment.getEmpNo()).isEqualTo(userEmpNo);
        assertThat(fullUserWithDepartment.getName()).isEqualTo(userName);
        assertThat(fullUserWithDepartment.getDepartmentNo()).isEqualTo(departmentNo);
        assertThat(fullUserWithDepartment.getDepartmentName()).isEqualTo(departmentName);
        assertThat(fullUserWithDepartment.getEmail()).isEqualTo(userEmail);
        assertThat(fullUserWithDepartment.getPhone()).isEqualTo(userPhone);
        assertThat(fullUserWithDepartment.getActive()).isEqualTo(userActive);
    }

    @DisplayName("測試查詢用單位編號查詢該單位下所有簡易使用者+單位資訊")
    @Test
    void findAllSimpleUserWithDepartmentNo() {
        prepareData();
        List<SimpleUserWithDepartment> target = userService.findAllSimpleUserWithDepartmentNo(departmentNo);
        assertThat(target).isNotEmpty();
    }

}
