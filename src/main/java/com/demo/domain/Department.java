package com.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "DEPARTMENT")
@Data
public class Department {

    @Id
    @Column(name = "NO")
    private String no;

    @Column(name = "NAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_NO", foreignKey = @ForeignKey(name = "FK_DEPARTMENT_PARENT"))
    private Department parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Department> children;

}
