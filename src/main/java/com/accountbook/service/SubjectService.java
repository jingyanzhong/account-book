package com.accountbook.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

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

    public Map<String, Subject> cacheCodeMapSubject = new HashMap<>();
    public List<Subject> cacheSubjects = new ArrayList<>();

    @Autowired
    private SubjectRepository subjectRepository;

    @PostConstruct
    public void initSubjectMap() {
        final List<Subject> dtos = subjectRepository.findAll();
        dtos.forEach(dto -> {
            cacheCodeMapSubject.put(dto.getCode(), dto);
            cacheSubjects.add(dto);
        });
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
        return Optional.ofNullable(cacheCodeMapSubject.get(code));
    }

    /**
     * [取得 所有會計科目]
     * 
     * @author cano.su
     * @since 2024/11/15
     * @return
     */
    public List<Subject> findAll() {
        return cacheSubjects.stream()
                .collect(Collectors.toList());
    }
}
