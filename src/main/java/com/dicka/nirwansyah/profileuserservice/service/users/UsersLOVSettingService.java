package com.dicka.nirwansyah.profileuserservice.service.users;

import com.dicka.nirwansyah.profileuserservice.entity.UserSettingKeyLov;
import com.dicka.nirwansyah.profileuserservice.repository.UserSettingKeyLovRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsersLOVSettingService  {

    private UserSettingKeyLovRepository userSettingKeyLovRepository;
    public UsersLOVSettingService(UserSettingKeyLovRepository userSettingKeyLovRepository){
        this.userSettingKeyLovRepository = userSettingKeyLovRepository;
    }

    @PostConstruct
    public void execute() {

        if (this.userSettingKeyLovRepository.findAll().isEmpty()){
            log.info("initial data..");
            this.userSettingKeyLovRepository.save(UserSettingKeyLov
                    .builder()
                    .biometricLogin("false")
                    .pushNotification("false")
                    .smsNotification("false")
                    .showOnBoarding("false")
                    .widgetOrder("1,2,3,4,5")
                    .build());
        }
    }
}
