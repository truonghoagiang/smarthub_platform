package com.smarthub.repository.implement;

import com.smarthub.domain.entity.UserDeviceEntity;
import com.smarthub.repository.custom.UserDeviceRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDeviceRepositoryImpl implements UserDeviceRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<UserDeviceEntity> findByBrandIdAndActive(Long brandId, Boolean active) {
        logger.info(Thread.currentThread().getStackTrace()[0].getClassName() + "---brand_id={} active={}", brandId, active);
        Query query = entityManager.createNativeQuery(
                "select * from \"smarthub\".f_get_devices_by_brand_and_active(?,?)",
                UserDeviceEntity.class);
        query.setParameter(1, brandId);
        query.setParameter(2, active);
        return query.getResultList();
    }

    @Override
    public Optional<UserDeviceEntity> findByBrandAndDeviceId(Long brandId, Long deviceId) {
        logger.info(Thread.currentThread().getStackTrace()[0].getClassName() + "---brand_id={} deviceId={}", brandId, deviceId);
        Query query = entityManager.createNativeQuery(
                "select * from \"smarthub\".f_get_devices_by_brand_and_device(?,?)",
                UserDeviceEntity.class);
        query.setParameter(1, brandId);
        query.setParameter(2, deviceId);
        return Optional.ofNullable((UserDeviceEntity) query.getSingleResult());
    }

    @Override
    public List<UserDeviceEntity> findAllByUserId(Long userId) {
        logger.info(Thread.currentThread().getStackTrace()[0].getClassName() + "---userId={}", userId);
        Query query = entityManager.createNativeQuery(
                "select * from \"smarthub\".f_get_devices_by_user(?)",
                UserDeviceEntity.class);

        query.setParameter(1, userId);
        return query.getResultList();
    }

    @Override
    public List<UserDeviceEntity> findAllByUserAndDeviceType(Long userId, Long deviceTypeId) {
        logger.info(Thread.currentThread().getStackTrace()[0].getClassName() + "---userId={} deviceTypeId={}", userId, deviceTypeId);
        Query query = entityManager.createNativeQuery(
                "select * from \"smarthub\".f_get_devices_by_user_and_device_type(?,?)",
                UserDeviceEntity.class);
        query.setParameter(1, userId);
        query.setParameter(2, deviceTypeId);
        return query.getResultList();
    }

    @Override
    public List<UserDeviceEntity> findAllByUserAndRoom(Long userId, Integer roomId) {
        logger.info(Thread.currentThread().getStackTrace()[0].getClassName() + "---userId={} roomId={}", userId, roomId);
        Query query = entityManager.createNativeQuery(
                "select * from \"smarthub\".f_get_devices_by_user_and_room(?,?)",
                UserDeviceEntity.class);
        query.setParameter(1, userId);
        query.setParameter(2, roomId);
        return query.getResultList();
    }

    @Override
    public Boolean isExistsByBrandAndDeviceId(Long brandId, Long deviceId) {
        Query query;
        query = entityManager.createNativeQuery("select * from \"smarthub\".f_is_exists_by_brand_and_device(?,?)"
                , UserDeviceEntity.class);
        query.setParameter(1, brandId);
        query.setParameter(2, deviceId);
        return query.getSingleResult() != null;

    }
}
