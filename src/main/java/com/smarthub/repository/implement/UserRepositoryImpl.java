package com.smarthub.repository.implement;

import com.smarthub.repository.custom.UserRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.Objects;


@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean isExistsEmail(String email) {
        String result;
        StoredProcedureQuery query;
        query = entityManager.createNamedStoredProcedureQuery("UserEntity.CheckExistsEmail");
        query.setParameter("i_email", email);
        result = (String) query.getOutputParameterValue("o_result");
        return Objects.equals(result, "Y") ? true : false;
    }

    @Override
    public boolean isExistsPhoneNumber(String phoneNumber) {
        String result;
        StoredProcedureQuery query;
        query = entityManager.createNamedStoredProcedureQuery("UserEntity.CheckExistsPhoneNumber");
        query.setParameter("i_phone_number", phoneNumber);
        result = (String) query.getOutputParameterValue("o_result");
        return Objects.equals(result, "Y") ? true : false;
    }
}
