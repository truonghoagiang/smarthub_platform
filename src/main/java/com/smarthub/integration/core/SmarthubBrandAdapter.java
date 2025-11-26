package com.smarthub.integration.core;

import com.smarthub.domain.entity.UserBrandEntity;
import com.smarthub.domain.entity.UserDeviceEntity;

import java.util.List;
import java.util.Map;

public interface SmarthubBrandAdapter {

    /**
     * authenticate with brand's API and return authen result
     * @return auth result
     * @throws Exception
     */
    BrandAuthResult authenticate(Map<String, String> credential) throws Exception;

    /**
     * refresh the access token using refresh token
     * @param account
     * @return
     * @throws Exception
     */
    BrandAuthResult refreshToken(UserBrandEntity account) throws Exception;

    /**
     * fetch all devices from a brand
     * @param account
     * @return
     * @throws Exception
     */
    List<UserDeviceEntity> fetchDevices(UserBrandEntity account) throws Exception;

    /**
     * Get detailed information about a specific device
     * @param account
     * @param deviceId
     * @return
     * @throws Exception
     */
    UserDeviceEntity getDeviceDetails(UserBrandEntity account, String deviceId) throws Exception;

    /**
     * Get current state of device
     * @param account
     * @param deviceId
     * @return
     * @throws Exception
     */
    Map<String, Object> getDeviceState(UserBrandEntity account, String deviceId) throws Exception;

    /**
     * Send command to device
     * @param account
     * @param deviceId
     * @param command
     * @return
     * @throws Exception
     */
    CommandResult controllDevice(UserBrandEntity account, String deviceId, DeviceCommand command) throws Exception;

    /**
     * Test brand account credential is valid
     * @param account
     * @return
     */
    boolean testConnection(UserBrandEntity account);

    /**
    * Get support device by this brand
     **/
    List<String> getSupportedDevicesType();
}
