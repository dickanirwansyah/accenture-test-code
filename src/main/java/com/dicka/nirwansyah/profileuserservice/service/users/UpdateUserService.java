package com.dicka.nirwansyah.profileuserservice.service.users;

import com.dicka.nirwansyah.profileuserservice.common.BaseService;
import com.dicka.nirwansyah.profileuserservice.common.GetIdRequest;
import com.dicka.nirwansyah.profileuserservice.entity.User;
import com.dicka.nirwansyah.profileuserservice.exception.CustomErrorException;
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
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
public class UpdateUserService implements BaseService<CreateUserResponse, CreateUserRequest> {

    private UserRepository userRepository;
    private GenerateSequenceService generateSequenceService;
    private GetUserSettingService getUserSettingService;
    public UpdateUserService(UserRepository userRepository,
                             GetUserSettingService getUserSettingService,
                             GenerateSequenceService generateSequenceService){
        this.userRepository = userRepository;
        this.getUserSettingService = getUserSettingService;
        this.generateSequenceService = generateSequenceService;
    }

    @Override
    public CreateUserResponse execute(CreateUserRequest request) {
        log.info("request update user = {}",request.toString());
        AtomicReference<User> responseUser = new AtomicReference<>(new User());
        this.userRepository.findById(request.getId())
                .ifPresentOrElse(data -> {
                    data.setSsn(!ObjectUtils.isEmpty(request.getSsn()) ? request.getSsn() :
                            this.generateSequenceService.execute(GenerateSequenceRequest.builder()
                                            .lengthOfSequence(16)
                                            .sequencePrefix("ACNTURE")
                                            .sequenceType("USER")
                                    .build()).getResultSequence());
                    data.setBirthDate(request.getBirthDate());
                    data.setLastName(request.getLastName());
                    data.setFirstName(request.getFirstName());
                    data.setUpdatedBy("SYSTEM");
                    data.setUpdatedTime(Timestamp.valueOf(LocalDateTime.now()));
                    responseUser.set(userRepository.save(data));
                }, ()-> {
                    log.error("error because id {} not found",request.getId());
                    throw new CustomErrorException("sorry id not found");
                });
        return CreateUserResponse.builder()
                .id(responseUser.get().getId())
                .ssn(responseUser.get().getSsn())
                .updatedBy(responseUser.get().getUpdatedBy())
                .isActive(responseUser.get().isActive())
                .updatedTime(responseUser.get().getUpdatedTime().toLocalDateTime())
                .createdTime(responseUser.get().getCreatedTime().toLocalDateTime())
                .birthDate(responseUser.get().getBirthDate())
                .lastName(responseUser.get().getLastName())
                .firstName(responseUser.get().getFirstName())
                .userSettingsMap(this.getUserSettingService.execute(GetIdRequest.builder()
                                .id(responseUser.get().getId())
                        .build()).getUserSettingResponses())
                .build();
    }
}
