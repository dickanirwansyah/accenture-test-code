package com.dicka.nirwansyah.profileuserservice.entity;

import com.dicka.nirwansyah.profileuserservice.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "ssn", unique = true, length = 16)
    private String ssn;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "middle_name",  length = 100)
    private String middleName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "family_name", length = 100)
    private String familyName;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "updated_times")
    private Timestamp updatedTime;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "deleted_time")
    private Timestamp deletedTime;
}
