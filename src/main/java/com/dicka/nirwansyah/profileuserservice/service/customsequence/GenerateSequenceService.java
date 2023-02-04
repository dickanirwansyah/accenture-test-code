package com.dicka.nirwansyah.profileuserservice.service.customsequence;

import com.dicka.nirwansyah.profileuserservice.common.BaseService;
import com.dicka.nirwansyah.profileuserservice.entity.CustomSequences;
import com.dicka.nirwansyah.profileuserservice.model.GenerateSequenceRequest;
import com.dicka.nirwansyah.profileuserservice.model.GenerateSequenceResponse;
import com.dicka.nirwansyah.profileuserservice.repository.CustomSequenceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
public class GenerateSequenceService implements BaseService<GenerateSequenceResponse, GenerateSequenceRequest> {

    private CustomSequenceRepository customSequenceRepository;
    public GenerateSequenceService(CustomSequenceRepository customSequenceRepository){
        this.customSequenceRepository = customSequenceRepository;
    }

    @Override
    public GenerateSequenceResponse execute(GenerateSequenceRequest request) {
        log.info("generate sequence..");
        AtomicReference<CustomSequences> responseSequence = new AtomicReference<>(new CustomSequences());
        customSequenceRepository.getSequenceBySeqNameAndSeqType(request.getSequencePrefix(), request.getSequenceType())
                .ifPresentOrElse(currentCustomSequences -> {
                    log.info("increment sequence..");
                    currentCustomSequences.setSequenceNumber(currentCustomSequences.getSequenceNumber() + 1);
                    responseSequence.set(customSequenceRepository.save(currentCustomSequences));
                }, ()-> {
                    log.info("new sequence..");
                    CustomSequences customSequences = new CustomSequences();
                    customSequences.setSequenceNumber(1);
                    customSequences.setSequenceName(request.getSequencePrefix());
                    customSequences.setSequenceType(request.getSequenceType());
                    responseSequence.set(customSequenceRepository.save(customSequences));
                });

        /** combine with date and month **/
        StringBuilder stringBuilder = new StringBuilder();
        String dateAndMonth = new SimpleDateFormat("yyMM").format(new Date());
        Integer lengthOfSequenceId = request.getLengthOfSequence() - (request.getSequencePrefix() + dateAndMonth).length();
        String sequenceId = String.format("%" + lengthOfSequenceId + "s", responseSequence.get().getSequenceNumber()).replace(' ', '0');
        stringBuilder.append(request.getSequencePrefix());
        stringBuilder.append(dateAndMonth);
        stringBuilder.append(sequenceId);
        return GenerateSequenceResponse.builder()
                .resultSequence(stringBuilder.toString())
                .build();
    }
}
