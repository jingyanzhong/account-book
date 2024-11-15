package com.accountbook.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accountbook.model.po.SubjectPo;

/**
 * [DAO]
 * 
 * @author cano.su
 * @since 2024/11/14
 */
public interface SubjectDao extends JpaRepository<SubjectPo, String> {

}
