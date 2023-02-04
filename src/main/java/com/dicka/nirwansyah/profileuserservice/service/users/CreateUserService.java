package com.dicka.nirwansyah.profileuserservice.service.users;

import com.dicka.nirwansyah.profileuserservice.common.BaseService;
import com.dicka.nirwansyah.profileuserservice.common.Constants;
import com.dicka.nirwansyah.profileuserservice.common.GetIdRequest;
import com.dicka.nirwansyah.profileuserservice.entity.User;
import com.dicka.nirwansyah.profileuserservice.model.CreateUserRequest;
import com.dicka.nirwansyah.profileuserservice.model.CreateUserResponse;
import com.dicka.nirwansyah.profileuserservice.model.GenerateSequenceRequest;
import com.dicka.nirwansyah.profileuserservice.repository.UserRepository;
import com.dicka.nirwansyah.profileuserservice.service.customsequence.GenerateSequenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@Service
public class CreateUserService implements BaseService<CreateUserResponse, CreateUserRequest> {


    private UserRepository userRepository;
    private GenerateSequenceService generateSequenceService;
    private CreateUserSettingService createUserSettingService;
    public CreateUserService(UserRepository userRepository,
                             GenerateSequenceService generateSequenceService,
                             CreateUserSettingService createUserSettingService){
        this.userRepository = userRepository;
        this.generateSequenceService = generateSequenceService;
        this.createUserSettingService = createUserSettingService;
    }

    @Override
    public CreateUserResponse execute(CreateUserRequest request) {
       log.info("request create user={}",request.toString());
        User user = new User();
        user.setSsn(!ObjectUtils.isEmpty(request.getSsn()) ? request.getSsn() : this.generateSequenceService.execute(GenerateSequenceRequest.builder()
                .lengthOfSequence(16)
                .sequencePrefix("ACNTURE")
                .sequenceType("USER")
                .build()).getResultSequence());
        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBirthDate(request.getBirthDate());
        user.setCreatedBy(Constants.DEFAULT_SYSTEM);
        user.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
        user.setUpdatedTime(Timestamp.valueOf(LocalDateTime.now()));
        user.setActive(Boolean.TRUE);
        user.setUpdatedBy(Constants.DEFAULT_SYSTEM);

        User responseSaveUser = userRepository.save(user);

        return CreateUserResponse.builder()
                .id(responseSaveUser.getId())
                .ssn(responseSaveUser.getSsn())
                .firstName(responseSaveUser.getFirstName())
                .lastName(responseSaveUser.getLastName())
                .birthDate(responseSaveUser.getBirthDate())
                .createdTime(responseSaveUser.getCreatedTime().toLocalDateTime())
                .updatedTime(responseSaveUser.getUpdatedTime().toLocalDateTime())
                .isActive(responseSaveUser.isActive())
                .userSettingsMap(createUserSettingService.execute(GetIdRequest.builder()
                                .id(responseSaveUser.getId())
                        .build()).getUserSettingResponses())
                .build();
    }
}
