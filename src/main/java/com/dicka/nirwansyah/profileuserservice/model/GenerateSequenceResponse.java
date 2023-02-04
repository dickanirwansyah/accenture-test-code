package com.dicka.nirwansyah.profileuserservice.model;

import com.dicka.nirwansyah.profileuserservice.common.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenerateSequenceResponse extends BaseResponse {

    @JsonProperty("result_sequence")
    private String resultSequence;
}
