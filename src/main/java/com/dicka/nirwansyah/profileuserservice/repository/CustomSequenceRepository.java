package com.dicka.nirwansyah.profileuserservice.repository;

import com.dicka.nirwansyah.profileuserservice.entity.CustomSequences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomSequenceRepository extends JpaRepository<CustomSequences, Long> {

    @Query(value = "select * from custom_sequences where sequence_name=:seqName and sequence_type=:seqType", nativeQuery = true)
    Optional<CustomSequences> getSequenceBySeqNameAndSeqType(@Param("seqName") String seqName,
                                                             @Param("seqType") String seqType);
}
