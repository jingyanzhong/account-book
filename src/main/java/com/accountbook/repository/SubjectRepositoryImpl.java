package com.accountbook.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accountbook.model.dao.SubjectDao;
import com.accountbook.model.dto.Subject;
import com.accountbook.model.po.SubjectPo;

/**
 * [REPOSITORY]
 * 
 * @author cano.su
 * @since 2024/11/15
 */
@Repository
public class SubjectRepositoryImpl implements SubjectRepository {

    public Map<String, Subject> cacheCodeMapSubject = new HashMap<>();
    public List<Subject> cacheSubjects = new ArrayList<>();

    @Autowired
    private SubjectDao subjectDao;

    @PostConstruct
    public void initSubjectMap() {
        final List<SubjectPo> pos = subjectDao.findAll();
        pos.stream()
                .map(this::toDto)
                .forEach(dto -> {
                    cacheCodeMapSubject.put(dto.getCode(), dto);
                    cacheSubjects.add(dto);
                });
    }

    @Override
    public List<Subject> findAll() {
        return cacheSubjects.stream()
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Subject> findByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return Optional.empty();
        }
        return Optional.ofNullable(cacheCodeMapSubject.get(code));
    }

    private Subject toDto(SubjectPo po) {
        Objects.requireNonNull(po, "SubjectPo must not be null.");
        return Subject.of(po.getCode(), po.getName());
    }
}
