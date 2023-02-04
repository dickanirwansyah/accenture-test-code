package com.dicka.nirwansyah.profileuserservice.model;

import com.dicka.nirwansyah.profileuserservice.common.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListUserSettingResponse extends BaseResponse {

    private List<HashMap<String, Object>> userSettingResponses;
}
