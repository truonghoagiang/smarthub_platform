package com.smarthub.repository.basic;

import com.smarthub.domain.entity.UserBrandEntity;
import com.smarthub.repository.custom.UserBrandRepositoryCustom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBrandRepository extends CrudRepository<UserBrandEntity, Integer>, UserBrandRepositoryCustom {
}
