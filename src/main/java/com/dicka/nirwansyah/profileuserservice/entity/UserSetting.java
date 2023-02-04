package com.dicka.nirwansyah.profileuserservice.entity;

import com.dicka.nirwansyah.profileuserservice.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users_setting")
public class UserSetting extends BaseEntity {

    @Column(name = "user_setting_key",  length = 100)
    private String userSettingKey;

    @Column(name = "user_setting_value", length = 100)
    private String userSettingValue;

    @Column(name = "users_id")
    private Long userId;
}
