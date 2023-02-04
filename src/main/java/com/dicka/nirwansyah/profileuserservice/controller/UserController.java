package com.dicka.nirwansyah.profileuserservice.controller;

import com.dicka.nirwansyah.profileuserservice.common.GetIdRequest;
import com.dicka.nirwansyah.profileuserservice.model.CreateUserRequest;
import com.dicka.nirwansyah.profileuserservice.model.CreateUserResponse;
import com.dicka.nirwansyah.profileuserservice.service.users.CreateUserService;
import com.dicka.nirwansyah.profileuserservice.service.users.GetIdUsersService;
import com.dicka.nirwansyah.profileuserservice.service.users.UpdateUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/v1/users")
@RestController
public class UserController {

    private CreateUserService createUserService;
    private GetIdUsersService getIdUsersService;
    private UpdateUserService updateUserService;
    public UserController(CreateUserService createUserService,
                          GetIdUsersService getIdUsersService,
                          UpdateUserService updateUserService){
        this.createUserService = createUserService;
        this.getIdUsersService = getIdUsersService;
        this.updateUserService = updateUserService;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> create(@RequestBody CreateUserRequest request){
        return ResponseEntity.ok(this.createUserService
                .execute(request));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CreateUserResponse> getId(@PathVariable("id")Long id){
        return ResponseEntity.ok(this.getIdUsersService.execute(GetIdRequest.builder()
                        .id(id)
                .build()));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CreateUserResponse> update(@RequestBody CreateUserRequest request,
                                                     @PathVariable("id")Long id){
        request.setId(id);
        return ResponseEntity.ok(this.updateUserService
                .execute(request));
    }
}
