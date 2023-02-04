package com.dicka.nirwansyah.profileuserservice.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetIdRequest extends BaseRequest{

    @JsonProperty("id")
    private Long id;
}
