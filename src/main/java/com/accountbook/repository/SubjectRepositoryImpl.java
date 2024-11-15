package com.accountbook.repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Autowired
    private SubjectDao subjectDao;

    @Override
    public List<Subject> findAll() {
        final List<SubjectPo> pos = subjectDao.findAll();
        return pos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private Subject toDto(SubjectPo po) {
        Objects.requireNonNull(po, "SubjectPo must not be null.");
        return Subject.of(po.getCode(), po.getName());
    }
}
