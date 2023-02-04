package com.dicka.nirwansyah.profileuserservice.service.users;

import com.dicka.nirwansyah.profileuserservice.common.BaseService;
import com.dicka.nirwansyah.profileuserservice.common.GetIdRequest;
import com.dicka.nirwansyah.profileuserservice.common.Utils;
import com.dicka.nirwansyah.profileuserservice.model.ListUserSettingResponse;
import com.dicka.nirwansyah.profileuserservice.repository.UserSettingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GetUserSettingService implements BaseService<ListUserSettingResponse, GetIdRequest> {

    private UserSettingRepository userSettingRepository;
    public GetUserSettingService(UserSettingRepository userSettingRepository){
        this.userSettingRepository = userSettingRepository;
    }

    @Override
    public ListUserSettingResponse execute(GetIdRequest request) {
        log.info("request user setting id = {} ",request.getId());
        return ListUserSettingResponse.builder()
                .userSettingResponses(
                        this.userSettingRepository.findAll().stream()
                                .filter(userSetting -> Objects.equals(userSetting.getUserId(), request.getId()))
                                .map(Utils::mapData)
                                .collect(Collectors.toList()))
                .build();
    }
}
