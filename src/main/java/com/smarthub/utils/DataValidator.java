package com.smarthub.utils;

import com.smarthub.exception.ValidateException;
import org.apache.coyote.BadRequestException;

import java.util.regex.Pattern;

public class DataValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,}$");

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$");

    private static final Pattern PHONE_PATTERN = Pattern.compile("^(\\+84|084|0)[0-9]{9}$");

    /**
     * validate email format
     * @param email
     */
    public static void validateEmail(String email) {
        if(email == null || email.trim().isEmpty()) {
            throw new ValidateException("Email không được để trống");
        }
        if(!EMAIL_PATTERN.matcher(email).matches()){
            throw new ValidateException("Email không hợp lệ");
        }
    }

    /**
     * validate password: at least 1 digit, 1 lower letter, 1 upper letter, 1 special character, lenght minimum 8
     * @param password
     */
    public static void validatePassword(String password) {
        if(password == null || password.trim().isEmpty()){
            throw new ValidateException("Password không được để trống");
        }
        if(!PASSWORD_PATTERN.matcher(password).matches()){
            throw new ValidateException("Mật khẩu phải chứa ít nhất một chữ HOA, một chữ thường, một chữ số, một ký tự đặc biệt");
        }
        if(password.length() < 8){
            throw new ValidateException("Mật khẩu phải có ít nhất 8 ký tự");
        }
    }

    /**
     * validate phone number: 0xxxxxxxxx, +84xxxxxxxxx, 084xxxxxxxxx
     * @param phone
     */
    public static void validatePhone(String phone) {
        if(phone == null || phone.trim().isEmpty()) {
            throw new ValidateException("Số điện thoại không được để trống");
        }
        if(!PHONE_PATTERN.matcher(phone).matches()){
            throw new ValidateException("Số điện thoại không hợp lệ");
        }
    }

    public static void fullname(String fullname) {
        if(fullname == null || fullname.trim().isEmpty()) {
            throw new ValidateException("Họ tên không được để trống");
        }
        if(fullname.length()<2){
            throw new ValidateException("Họ tên phải dài hơn 2 ký tự");
        }
    }

    
}
