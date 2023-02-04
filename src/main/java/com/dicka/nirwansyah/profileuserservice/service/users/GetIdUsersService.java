package com.dicka.nirwansyah.profileuserservice.service.users;

import com.dicka.nirwansyah.profileuserservice.common.BaseService;
import com.dicka.nirwansyah.profileuserservice.common.GetIdRequest;
import com.dicka.nirwansyah.profileuserservice.exception.CustomErrorException;
import com.dicka.nirwansyah.profileuserservice.model.CreateUserResponse;
import com.dicka.nirwansyah.profileuserservice.repository.UserRepository;
import com.dicka.nirwansyah.profileuserservice.repository.UserSettingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GetIdUsersService implements BaseService<CreateUserResponse, GetIdRequest> {

    private GetUserSettingService getUserSettingService;
    private UserSettingRepository userSettingRepository;
    private UserRepository userRepository;
    public GetIdUsersService(UserRepository userRepository,
                             GetUserSettingService getUserSettingService){
        this.userRepository = userRepository;
        this.getUserSettingService = getUserSettingService;
    }

    @Override
    public CreateUserResponse execute(GetIdRequest request) {
        log.info("request get user id = {} ",request.getId());
        return userRepository.findById(request.getId())
                .map(dataUser -> CreateUserResponse.builder()
                        .id(dataUser.getId())
                        .ssn(dataUser.getSsn())
                        .firstName(dataUser.getFirstName())
                        .lastName(dataUser.getLastName())
                        .createdBy(dataUser.getCreatedBy())
                        .createdTime(dataUser.getCreatedTime().toLocalDateTime())
                        .updatedTime(dataUser.getUpdatedTime().toLocalDateTime())
                        .updatedBy(dataUser.getUpdatedBy())
                        .isActive(dataUser.isActive())
                        .userSettingsMap(getUserSettingService.execute(GetIdRequest.builder()
                                        .id(dataUser.getId())
                                .build()).getUserSettingResponses())
                        .build())
                .orElseThrow(() -> new CustomErrorException("sorry user id not found"));
    }
}
