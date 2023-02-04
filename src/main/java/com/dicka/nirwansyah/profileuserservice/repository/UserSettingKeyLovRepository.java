package com.dicka.nirwansyah.profileuserservice.repository;

import com.dicka.nirwansyah.profileuserservice.entity.UserSettingKeyLov;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSettingKeyLovRepository extends JpaRepository<UserSettingKeyLov, Long> {
}
