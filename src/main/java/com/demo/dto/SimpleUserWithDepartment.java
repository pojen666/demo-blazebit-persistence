package com.demo.dto;

import jakarta.persistence.Tuple;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SimpleUserWithDepartment {

    private String empNo;

    private String name;

    private String departmentName;

    private String departmentNo;

    public SimpleUserWithDepartment of(Tuple tuple) {
        return new SimpleUserWithDepartment()
                .setEmpNo(tuple.get("empNo", String.class))
                .setName(tuple.get("name", String.class))
                .setDepartmentNo(tuple.get("departmentNo", String.class))
                .setDepartmentName(tuple.get("departmentName", String.class));
    }
}
