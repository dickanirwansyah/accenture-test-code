package com.dicka.nirwansyah.profileuserservice.common;

import com.dicka.nirwansyah.profileuserservice.entity.UserSetting;

import java.util.HashMap;

public class Utils {

    public static final HashMap<String, Object> mapData(UserSetting userSetting){
        HashMap<String, Object> addMapData = new HashMap<>();
        addMapData.put(userSetting.getUserSettingKey(), userSetting.getUserSettingValue());
        return addMapData;
    }
}
