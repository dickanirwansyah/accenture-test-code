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
@Table(name = "user_setting_key_lov")
public class UserSettingKeyLov extends BaseEntity {


    @Column(name = "biometric_login")
    private String biometricLogin;

    @Column(name = "push_notification")
    private String pushNotification;

    @Column(name = "sms_notification")
    private String smsNotification;

    @Column(name = "show_on_boarding")
    private String showOnBoarding;

    @Column(name = "widget_order")
    private String widgetOrder;
}
