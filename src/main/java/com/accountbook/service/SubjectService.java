package com.accountbook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accountbook.model.dto.Subject;
import com.accountbook.repository.SubjectRepository;

/**
 * [SERVICE]
 * 
 * @author cano.su
 * @since 2024/11/15
 */
@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    /**
     * [取得 所有會計科目]
     * 
     * @author cano.su
     * @since 2024/11/15
     * @return
     */
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    /**
     * [取得 某會計科目]
     * 
     * @author cano.su
     * @since 2024/11/15
     * @param code
     * @return
     */
    public Optional<Subject> findByCode(String code) {
        return subjectRepository.findByCode(code);
    }
}
