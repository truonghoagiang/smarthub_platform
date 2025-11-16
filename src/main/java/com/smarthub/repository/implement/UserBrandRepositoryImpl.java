package com.smarthub.repository.implement;

import com.smarthub.domain.entity.UserBrandEntity;
import com.smarthub.repository.custom.UserBrandRepositoryCustom;
import jakarta.persistence.*;
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
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "-----user_id={}", userId);
        Query query = entityManager.createNativeQuery(
                "select * from \"smarthub\".f_get_brand_connect_by_user(?)",
                UserBrandEntity.class);
        query.setParameter(1, userId);
        return query.getResultList();
    }

    @Override
    public List<UserBrandEntity> findByUserAndActive(String userId) {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "-----user_id=  {}", userId);
        Query query = entityManager.createNativeQuery(
                "select * from \"smarthub\".f_get_brand_connect_by_user_and_active(?)",
                UserBrandEntity.class);
        query.setParameter(1, userId);
        return query.getResultList();
    }

    @Override
    public Optional<UserBrandEntity> findByUserAndBrandId(String userId, Long brandId) {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "-----user_id={}, brand_id={}", userId, brandId);
        Query query = entityManager.createNativeQuery(
                "select * from \"smarthub\".f_get_brand_connect_by_user_and_brand(?,?)",
                UserBrandEntity.class);
        query.setParameter(1, userId);
        query.setParameter(2, brandId);
        return Optional.ofNullable((UserBrandEntity) query.getSingleResult());
    }

    @Override
    public List<UserBrandEntity> findByExpiredDateBeforeAndActive(Date expiredDate) {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "-----expired_date={}", expiredDate);
        Query query = entityManager.createNativeQuery(
                "select * from \"smarthub\".f_get_brand_connect_expired_and_active(?)",
                UserBrandEntity.class);
        query.setParameter(1, expiredDate);
        return query.getResultList();
    }

    @Override
    public List<UserBrandEntity> findBySyncedEnable() {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
        Query query = entityManager.createNativeQuery(
                "select * from \"smarthub\".f_get_brand_connect_synced_enable()",
                UserBrandEntity.class);
        return query.getResultList();
    }

    @Override
    public boolean existsByUserIdAndBrandId(String userId, Long brandId) {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "user_id= {}, brand_id={}", userId, brandId);
        Query query = entityManager.createNativeQuery(
                "select * from \"smarthub\".f_is_exists_by_user_and_brand(?,?)",
                UserBrandEntity.class);
        query.setParameter(1, userId);
        query.setParameter(2, brandId);
        return query.getSingleResult() != null;
    }
}
