package com.smarthub.service;

import com.smarthub.domain.dto.request.LoginRequest;
import com.smarthub.domain.dto.request.RefrestTokenRequest;
import com.smarthub.domain.dto.request.RegisterRequest;
import com.smarthub.domain.dto.response.AuthenResponse;
import com.smarthub.domain.dto.response.UserInfoResponse;
import com.smarthub.domain.entity.RefreshTokenEntity;
import com.smarthub.domain.entity.UserEntity;
import com.smarthub.domain.entity.UserRole;
import com.smarthub.exception.BadRequestException;
import com.smarthub.exception.UnauthorizedException;
import com.smarthub.repository.basic.RefreshTokenRepository;
import com.smarthub.repository.basic.UserRepository;
import com.smarthub.security.CustomUserDetails;
import com.smarthub.security.JwtService;
import com.smarthub.utils.DataValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository,
                       RefreshTokenRepository refreshTokenRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public AuthenResponse register(RegisterRequest request) {
        logger.info("Processing registration for email: {}", request.getEmail());

        // Validate input
        DataValidator.validateEmail(request.getEmail());
        DataValidator.validatePassword(request.getPassword());
        DataValidator.validatePhone(request.getPhoneNumber());
        DataValidator.fullname(request.getFullname());

        // Check if email already exists
        if (userRepository.isExistsEmail(request.getEmail())) {
            throw new BadRequestException("Email đã được sử dụng");
        }

        // Check if phone number already exists
        if (userRepository.isExistsPhoneNumber(request.getPhoneNumber())) {
            throw new BadRequestException("Số điện thoại đã được sử dụng");
        }

        // Create new user
        UserEntity newUser = new UserEntity();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setUsername(request.getFullname());
        newUser.setRole(UserRole.USER.name());
        newUser.setEnabled(true);
        newUser.setAccountExpired(false);
        newUser.setAccountLocked(false);
        newUser.setPasswordExpired(false);
        newUser.setEmailVerified(false);
        newUser.setPhoneNumberVerified(false);
        newUser.setCreatedDate(LocalDateTime.now());
        newUser.setUpdatedDate(LocalDateTime.now());

        UserEntity savedUser = userRepository.save(newUser);
        logger.info("User registered successfully with ID: {}", savedUser.getId());

        // Generate tokens
        CustomUserDetails userDetails = new CustomUserDetails(savedUser);
        String accessToken = jwtService.generateAccessToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        // Save refresh token
        saveRefreshToken(savedUser.getId(), refreshToken);

        // Build response
        return buildAuthResponse(savedUser, accessToken, refreshToken);
    }

    @Transactional
    public AuthenResponse login(LoginRequest request) {
        logger.info("Processing login for username: {}", request.getUsername());

        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UserEntity user = userDetails.getUserEntity();

        // Update last login date
        user.setLastLoginDate(LocalDateTime.now());
        userRepository.save(user);

        // Generate tokens
        String accessToken = jwtService.generateAccessToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        // Save refresh token
        saveRefreshToken(user.getId(), refreshToken);

        logger.info("User logged in successfully: {}", user.getEmail());
        return buildAuthResponse(user, accessToken, refreshToken);
    }

    @Transactional
    public AuthenResponse refreshToken(RefrestTokenRequest request) {
        logger.info("Processing refresh token request");

        String refreshTokenValue = request.getRefrestToken();
        if (refreshTokenValue == null || refreshTokenValue.trim().isEmpty()) {
            throw new BadRequestException("Refresh token không được để trống");
        }

        // Extract username from token
        String username = jwtService.extractUsername(refreshTokenValue);
        if (username == null) {
            throw new UnauthorizedException("Refresh token không hợp lệ");
        }

        // Find user
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UnauthorizedException("User không tồn tại"));

        // Validate refresh token
        Optional<RefreshTokenEntity> refreshTokenEntity = refreshTokenRepository.findByToken(refreshTokenValue);
        if (refreshTokenEntity.isEmpty()) {
            throw new UnauthorizedException("Refresh token không tồn tại");
        }

        RefreshTokenEntity token = refreshTokenEntity.get();

        // Check if token is revoked
        if (token.getRevoke() != null && token.getRevoke()) {
            throw new UnauthorizedException("Refresh token đã bị thu hồi");
        }

        // Check if token is expired
        if (token.getExpiredDate().isBefore(LocalDateTime.now())) {
            throw new UnauthorizedException("Refresh token đã hết hạn");
        }

        // Generate new tokens
        CustomUserDetails userDetails = new CustomUserDetails(user);
        String newAccessToken = jwtService.generateAccessToken(userDetails);
        String newRefreshToken = jwtService.generateRefreshToken(userDetails);

        // Revoke old refresh token
        token.setRevoke(true);
        refreshTokenRepository.save(token);

        // Save new refresh token
        saveRefreshToken(user.getId(), newRefreshToken);

        logger.info("Tokens refreshed successfully for user: {}", user.getEmail());
        return buildAuthResponse(user, newAccessToken, newRefreshToken);
    }

    private void saveRefreshToken(int userId, String refreshToken) {
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
        refreshTokenEntity.setId(System.currentTimeMillis());
        refreshTokenEntity.setRefreshToken(refreshToken);
        refreshTokenEntity.setUserID(userId);
        refreshTokenEntity.setCreatedDate(LocalDateTime.now());
        refreshTokenEntity.setExpiredDate(LocalDateTime.now().plusDays(30));
        refreshTokenEntity.setRevoke(false);
        refreshTokenRepository.save(refreshTokenEntity);
    }

    private AuthenResponse buildAuthResponse(UserEntity user, String accessToken, String refreshToken) {
        UserInfoResponse userInfo = UserInfoResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .avatarUrl(user.getAvatarUrl())
                .emailVerified(user.isEmailVerified())
                .phoneVerified(user.isPhoneNumberVerified())
                .build();

        return AuthenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(jwtService.getAccessTokenExpiration())
                .user(userInfo)
                .build();
    }
}
