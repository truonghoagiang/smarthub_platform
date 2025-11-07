package com.smarthub.repository.custom;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryCustom {

    boolean isExistsEmail(String email);

    boolean isExistsPhoneNumber(String phoneNumber);
}
