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
@Table(name = "custom_sequences")
public class CustomSequences extends BaseEntity {

    @Column(name = "sequence_name", nullable = false, unique = true)
    private String sequenceName;
    @Column(name = "sequence_type", nullable = false, unique = true)
    private String sequenceType;
    @Column(name = "sequence_number", nullable = false)
    private Integer sequenceNumber;
}
