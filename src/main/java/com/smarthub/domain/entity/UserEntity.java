package com.smarthub.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "UserEntity.GetUserByEmail",
                procedureName = "f_get_user_by_email",
                resultClasses = UserEntity.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "i_email",type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR,name = "o_result",type = void.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "UserEntity.FindUserByPhoneNumber",
                procedureName = "f_find_user_by_phone_number",
                resultClasses = UserEntity.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "i_phone_number",type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "o_result",type = void.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "UserEntity.CheckExistsEmail",
                procedureName = "f_is_exists_email",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "i_email",type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_result", type = String.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "UserEntity.CheckExistsPhoneNumber",
                procedureName = "f_is_exists_phone_number",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "i_phone_number", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_result", type = String.class)
                }
        )
})

@Entity
@Table(name = "T_USER")
public class UserEntity {

    private int id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String role;
    private boolean enabled;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean passwordExpired;
    private boolean emailVerified;
    private boolean phoneNumberVerified;
    private String avatarUrl;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime lastLoginDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "enabled")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(name = "account_expired")
    public boolean isAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    @Column(name = "account_locked")
    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    @Column(name = "password_expired")
    public boolean isPasswordExpired() {
        return passwordExpired;
    }

    public void setPasswordExpired(boolean passwordExpired) {
        this.passwordExpired = passwordExpired;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "email_verified")
    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    @Column(name = "phone_verified")
    public boolean isPhoneNumberVerified() {
        return phoneNumberVerified;
    }

    public void setPhoneNumberVerified(boolean phoneNumberVerified) {
        this.phoneNumberVerified = phoneNumberVerified;
    }

    @Column(name = "avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Column(name = "created_date")
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "updated_date")
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Column(name = "last_login_date")
    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

}
