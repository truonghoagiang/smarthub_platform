package com.smarthub.repository.basic;

import com.smarthub.domain.entity.UserEntity;
import com.smarthub.repository.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>, UserRepositoryCustom {

    @Procedure(name = "UserEntity.FindUserByEmail")
    Optional<UserEntity> findByEmail(@Param("i_email") String email);

    @Procedure(name = "UserEntity.FindUserByPhoneNumber")
    Optional<UserEntity> findByPhoneNumber(@Param("i_phone_number") String phoneNumber);
}
