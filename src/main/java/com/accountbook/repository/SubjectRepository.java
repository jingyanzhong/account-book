package com.accountbook.repository;

import java.util.List;

import com.accountbook.model.dto.Subject;

/**
 * [REPOSITORY]
 * 
 * @author cano.su
 * @since 2024/11/15
 */
public interface SubjectRepository {

    /** 取得 所有會計科目 */
    List<Subject> findAll();

}
