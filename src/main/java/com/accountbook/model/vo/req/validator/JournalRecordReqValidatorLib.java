package com.accountbook.model.vo.req.validator;

import java.util.Objects;

import com.accountbook.repository.SubjectRepository;

/**
 * [VALIDATOR LIBRARY]
 * 
 * @author cano.su
 * @since 2024/11/16
 */
public class JournalRecordReqValidatorLib {

    private SubjectRepository subjectRepository;

    private JournalRecordReqValidatorLib(SubjectRepository subjectRepository) {
        super();
        this.subjectRepository = Objects.requireNonNull(subjectRepository, "subjectRepository must not be null.");
    }
    
    public static JournalRecordReqValidatorLib of(SubjectRepository subjectRepository) {
        return new JournalRecordReqValidatorLib(subjectRepository);
    }

    public SubjectRepository getSubjectRepository() {
        return subjectRepository;
    }

}
