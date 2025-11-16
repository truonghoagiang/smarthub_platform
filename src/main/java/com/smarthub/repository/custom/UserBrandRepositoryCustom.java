package com.smarthub.repository.custom;

import com.smarthub.domain.entity.UserBrandEntity;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserBrandRepositoryCustom {
    List<UserBrandEntity> findByUserId(String userId);

    List<UserBrandEntity> findByUserAndActive(String userId);

    Optional<UserBrandEntity> findByUserAndBrandId(String userId, Long brandId);

    List<UserBrandEntity> findByExpiredDateBeforeAndActive(Date expiredDate);

    List<UserBrandEntity> findBySyncedEnable();

    boolean existsByUserIdAndBrandId(String userId, Long brandId);
}
