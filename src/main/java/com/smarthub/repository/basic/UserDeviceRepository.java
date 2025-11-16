package com.smarthub.repository.basic;

import com.smarthub.domain.entity.UserDeviceEntity;
import com.smarthub.repository.custom.UserDeviceRepositoryCustom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDeviceRepository extends CrudRepository<UserDeviceEntity, Long>, UserDeviceRepositoryCustom {
    List<UserDeviceEntity> findByBrandId(String brandId);
}
