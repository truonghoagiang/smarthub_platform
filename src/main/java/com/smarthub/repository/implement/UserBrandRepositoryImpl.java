package com.smarthub.repository.implement;

import com.smarthub.domain.entity.UserBrandEntity;
import com.smarthub.repository.custom.UserBrandRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class UserBrandRepositoryImpl implements UserBrandRepositoryCustom {

    Logger logger = LoggerFactory.getLogger(UserBrandRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserBrandEntity> findByUserId(String userId) {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "-----user_id= , {}" + userId);
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("UserBrandEntity.findByUserId");
        query.setParameter("i_user_id", userId);
        return query.getResultList();
    }

    @Override
    public List<UserBrandEntity> findByUserAndActive(String userId, Boolean active) {
        return List.of();
    }

    @Override
    public Optional<UserBrandEntity> findByUserAndBrandId(String userId, Long brandId) {
        return Optional.empty();
    }

    @Override
    public List<UserBrandEntity> findByExpiredDateBeforeAndActive(Date expiredDate, Boolean active) {
        return List.of();
    }

    @Override
    public List<UserBrandEntity> findBySyncedEnable() {
        return List.of();
    }

    @Override
    public boolean existsByUserIdAndBrandId(String userId, Long brandId) {
        return false;
    }
}
