package com.dicka.nirwansyah.profileuserservice.common;

public interface BaseService <T extends BaseResponse, R extends BaseRequest>{
    T execute(R request);
}
