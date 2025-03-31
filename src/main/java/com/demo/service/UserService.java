package com.demo.service;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.SelectBaseCTECriteriaBuilder;
import com.demo.domain.Department;
import com.demo.domain.User;
import com.demo.dto.FullUserWithDepartment;
import com.demo.dto.SimpleUserWithDepartment;
import com.demo.repository.UserRepository;
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

    // 把使用者跟單位join起來的設定
    public <B extends SelectBaseCTECriteriaBuilder<?>> void buildGetSimpleUserWithDepartmentSQL(B cb) {
        cb.from(User.class, "u")
                .bind("empNo").select("u.no")
                .bind("name").select("u.name")
                .bind("departmentNo").select("d.no")
                .bind("departmentName").select("d.name")
                .innerJoinOnEntitySubquery(Department.class, "d")
                .end()
                .on("u.departmentNo").eqExpression("d.no")
                .end();
    }

    // 使用單位代碼查詢簡易使用者+單位資訊
    public List<SimpleUserWithDepartment> findAllSimpleUserWithDepartmentNo(String departmentNo) {
        var cb = cbf.create(userRepository.getEntityManager(), SimpleUserWithDepartment.class)
                .with(SimpleUserWithDepartment.class);
        buildGetSimpleUserWithDepartmentSQL(cb);
        return cb
                .where("d.no").eq(departmentNo)
                .end()
                .getResultStream()
                .toList();
    }

    // 使用員工編號查詢簡易使用者+單位資訊
    public SimpleUserWithDepartment findSimpleUserWithDepartmentByEmpNo(String empNo) {
        var cb = cbf.create(userRepository.getEntityManager(), SimpleUserWithDepartment.class)
                .with(SimpleUserWithDepartment.class);
        buildGetSimpleUserWithDepartmentSQL(cb);
        return cb.where("u.no").eq(empNo).end().getSingleResult();
    }

    // 使用員工編號查詢完整使用者+單位資訊
    public FullUserWithDepartment findFullUserWithDepartmentByEmpNo(String empNo) {
        var cb = cbf.create(userRepository.getEntityManager(), FullUserWithDepartment.class)
                .with(FullUserWithDepartment.class);
        buildGetSimpleUserWithDepartmentSQL(cb);
        return cb.where("u.no").eq(empNo)
                .bind("email").select("u.email")
                .bind("phone").select("u.phone")
                .bind("active").select("u.active")
                .end()
                .getSingleResult();
    }

}
