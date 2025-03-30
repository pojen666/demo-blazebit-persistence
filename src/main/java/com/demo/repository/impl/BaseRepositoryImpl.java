package com.demo.repository.impl;

import com.demo.repository.BaseRepository;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public class BaseRepositoryImpl<T, K extends Serializable> extends SimpleJpaRepository<T, K> implements BaseRepository<T, K> {

    @Getter
    private final EntityManager entityManager;


    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }
}
