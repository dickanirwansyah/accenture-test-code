package com.dicka.nirwansyah.profileuserservice.model;

import com.dicka.nirwansyah.profileuserservice.common.BaseResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUserResponse extends BaseResponse {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("ssn")
    private String ssn;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("birth_date")
    private Date birthDate;
    @JsonProperty("created_time")
    private LocalDateTime createdTime;
    @JsonProperty("updated_time")
    private LocalDateTime updatedTime;
    @JsonProperty("created_by")
    private String createdBy;
    @JsonProperty("updated_by")
    private String updatedBy;
    @JsonProperty("is_active")
    private boolean isActive;

    @JsonProperty("user_settings")
    private List<HashMap<String, Object>> userSettingsMap;
}

