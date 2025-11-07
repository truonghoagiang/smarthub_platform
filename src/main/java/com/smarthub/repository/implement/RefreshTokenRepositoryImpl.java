package com.smarthub.repository.implement;

import com.smarthub.repository.custom.RefreshTokenRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

}
