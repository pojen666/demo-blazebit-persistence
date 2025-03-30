package com.demo.service;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.demo.domain.Department;
import com.demo.domain.User;
import com.demo.dto.FullUserWithDepartment;
import com.demo.dto.SimpleUserWithDepartment;
import com.demo.repository.UserRepository;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CriteriaBuilderFactory cbf;

    public UserService(UserRepository userRepository, CriteriaBuilderFactory cbf) {
        this.userRepository = userRepository;
        this.cbf = cbf;
    }

    // 把使用者跟單位join起來的builder
    public CriteriaBuilder<Tuple> getSimpleUserWithDepartmentCriteriaBuilder() {
        return cbf.create(userRepository.getEntityManager(), Tuple.class)
                .from(User.class, "u")
                .select("u.no", "empNo")
                .select("u.name", "name")
                .select("d.no", "departmentNo")
                .select("d.name", "departmentName")
                .innerJoinOnEntitySubquery(Department.class, "d")
                .end()
                .on("u.departmentNo").eqExpression("d.no")
                .end();
    }

    // 使用員工編號查詢
    public CriteriaBuilder<Tuple> getSimpleUserWithDepartmentByEmpNoCriteriaBuilder(String empNo) {
        return getSimpleUserWithDepartmentCriteriaBuilder().where("u.no").eq(empNo);
    }

    // 使用單位代碼查詢簡易使用者+單位資訊
    public List<SimpleUserWithDepartment> findAllSimpleUserWithDepartment(String departmentNo) {
        return getSimpleUserWithDepartmentCriteriaBuilder()
                .where("d.no").eq(departmentNo)
                .getResultStream()
                .map(tuple -> new SimpleUserWithDepartment().of(tuple))
                .toList();
    }

    // 使用員工編號查詢簡易使用者+單位資訊
    public SimpleUserWithDepartment findSimpleUserWithDepartmentByEmpNo(String empNo) {
        return getSimpleUserWithDepartmentByEmpNoCriteriaBuilder(empNo)
                .getResultStream()
                .findFirst()
                .map(tuple -> new SimpleUserWithDepartment().of(tuple))
                .orElse(null);
    }

    // 使用員工編號查詢完整使用者+單位資訊
    public FullUserWithDepartment findFullUserWithDepartmentByEmpNo(String empNo) {
        return getSimpleUserWithDepartmentByEmpNoCriteriaBuilder(empNo)
                .select("u.email", "email")
                .select("u.phone", "phone")
                .select("u.active", "active")
                .getResultStream()
                .findFirst()
                .map(tuple -> new FullUserWithDepartment().of(tuple))
                .orElse(null);
    }

}
