package com.smarthub.repository.basic;

import com.smarthub.domain.entity.RefreshTokenEntity;
import com.smarthub.repository.custom.RefreshTokenRepositoryCustom;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshTokenEntity, Long>, RefreshTokenRepositoryCustom {

    @Query("SELECT rt FROM RefreshTokenEntity rt WHERE rt.refreshToken = :token")
    Optional<RefreshTokenEntity> findByToken(@Param("token") String token);

    @Modifying
    @Query("delete from RefreshTokenEntity tk where tk.userID = :userId")
    void deleteRefreshTokenByUserId(Integer userId);

    @Modifying
    @Query("delete from RefreshTokenEntity tk where tk.expiredDate < :now")
    void deleteExpiredRefreshTokens(LocalDateTime now);
}
