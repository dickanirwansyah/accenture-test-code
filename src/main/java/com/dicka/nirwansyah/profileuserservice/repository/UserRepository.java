package com.dicka.nirwansyah.profileuserservice.repository;

import com.dicka.nirwansyah.profileuserservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
