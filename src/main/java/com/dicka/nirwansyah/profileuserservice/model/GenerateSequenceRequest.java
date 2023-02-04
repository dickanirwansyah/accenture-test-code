package com.dicka.nirwansyah.profileuserservice.model;

import com.dicka.nirwansyah.profileuserservice.common.BaseRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenerateSequenceRequest extends BaseRequest {

    @JsonProperty("sequence_prefix")
    private String sequencePrefix;

    @JsonProperty("sequence_type")
    private String sequenceType;

    @JsonProperty("length_of_sequence")
    private Integer lengthOfSequence;
}
