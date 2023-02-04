package com.dicka.nirwansyah.profileuserservice.model;

import com.dicka.nirwansyah.profileuserservice.common.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserSettingResponse extends BaseResponse {
    private HashMap<String, Object> dataMap = new LinkedHashMap<>();
}
