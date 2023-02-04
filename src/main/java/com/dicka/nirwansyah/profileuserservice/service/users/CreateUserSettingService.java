package com.dicka.nirwansyah.profileuserservice.service.users;

import com.dicka.nirwansyah.profileuserservice.common.BaseService;
import com.dicka.nirwansyah.profileuserservice.common.GetIdRequest;
import com.dicka.nirwansyah.profileuserservice.common.Utils;
import com.dicka.nirwansyah.profileuserservice.entity.UserSetting;
import com.dicka.nirwansyah.profileuserservice.model.ListUserSettingResponse;
import com.dicka.nirwansyah.profileuserservice.repository.UserSettingKeyLovRepository;
import com.dicka.nirwansyah.profileuserservice.repository.UserSettingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CreateUserSettingService implements BaseService<ListUserSettingResponse, GetIdRequest> {

    private UserSettingKeyLovRepository userSettingKeyLovRepository;
    private UserSettingRepository userSettingRepository;
    public CreateUserSettingService(UserSettingKeyLovRepository userSettingKeyLovRepository,
                                    UserSettingRepository userSettingRepository){
        this.userSettingRepository = userSettingRepository;
        this.userSettingKeyLovRepository = userSettingKeyLovRepository;
    }

    @Override
    public ListUserSettingResponse execute(GetIdRequest request) {
        log.info("save user settings lov by id user = {}",request.getId());
        List<UserSetting> userSettingResponses = new ArrayList<>();
        this.userSettingKeyLovRepository.findAll()
                .forEach(dataUserSettingLov -> {
                    UserSetting userSettingBiometricLogin = new UserSetting();
                    userSettingBiometricLogin.setUserId(request.getId());
                    userSettingBiometricLogin.setUserSettingKey("biometric_login");
                    userSettingBiometricLogin.setUserSettingValue(dataUserSettingLov.getBiometricLogin());
                    userSettingRepository.save(userSettingBiometricLogin);

                    UserSetting userPushNotification = new UserSetting();
                    userPushNotification.setUserId(request.getId());
                    userPushNotification.setUserSettingKey("push_notification");
                    userPushNotification.setUserSettingValue(dataUserSettingLov.getPushNotification());
                    userSettingRepository.save(userPushNotification);

                    UserSetting smsNotification = new UserSetting();
                    smsNotification.setUserId(request.getId());
                    smsNotification.setUserSettingKey("sms_notification");
                    smsNotification.setUserSettingValue(dataUserSettingLov.getSmsNotification());
                    userSettingRepository.save(smsNotification);

                    UserSetting userShowOnBoarding = new UserSetting();
                    userShowOnBoarding.setUserId(request.getId());
                    userShowOnBoarding.setUserSettingKey("show_onboarding");
                    userShowOnBoarding.setUserSettingValue(dataUserSettingLov.getShowOnBoarding());
                    userSettingRepository.save(userShowOnBoarding);

                    UserSetting userWidgetOrder = new UserSetting();
                    userWidgetOrder.setUserId(request.getId());
                    userWidgetOrder.setUserSettingKey("widget_order");
                    userWidgetOrder.setUserSettingValue(dataUserSettingLov.getWidgetOrder());
                    userSettingRepository.save(userWidgetOrder);

                    userSettingResponses.add(userShowOnBoarding);
                    userSettingResponses.add(userSettingBiometricLogin);
                    userSettingResponses.add(userPushNotification);
                    userSettingResponses.add(smsNotification);
                    userSettingResponses.add(userWidgetOrder);
                });

        return ListUserSettingResponse.builder()
                .userSettingResponses(userSettingResponses.stream().map(Utils::mapData)
                        .collect(Collectors.toList()))
                .build();
    }



}
