package com.smarthub.repository.basic;

import com.smarthub.domain.entity.UserDeviceEntity;
import com.smarthub.repository.custom.UserDeviceRepositoryCustom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDeviceRepository extends CrudRepository<UserDeviceEntity, Long>, UserDeviceRepositoryCustom {

}
