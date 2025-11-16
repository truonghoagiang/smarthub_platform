package com.smarthub.repository.custom;

import com.smarthub.domain.entity.UserDeviceEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDeviceRepositoryCustom {

    List<UserDeviceEntity> findByBrandIdAndActive(Long brandId, Boolean active);

    Optional<UserDeviceEntity> findByBrandAndDeviceId(Long brandId, Long deviceId);

    List<UserDeviceEntity> findAllByUserId(Long userId);

    List<UserDeviceEntity> findAllByUserAndDeviceType(Long userId, Long deviceTypeId);

    List<UserDeviceEntity> findAllByUserAndRoom(Long userId, Integer roomId);

    Boolean isExistsByBrandAndDeviceId(Long brandId, Long deviceId);

}
