package com.dicka.nirwansyah.profileuserservice.repository;

import com.dicka.nirwansyah.profileuserservice.entity.UserSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSettingRepository extends JpaRepository<UserSetting, Long> {
}
