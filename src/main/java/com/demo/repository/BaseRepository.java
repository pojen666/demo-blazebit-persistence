package com.demo.repository;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T, K extends Serializable> extends JpaRepositoryImplementation<T, K> {

    /**
     * @return 取得實體管理員
     */
    EntityManager getEntityManager();
}
