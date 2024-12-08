package com.accountbook.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accountbook.model.po.JournalMemoPo;

/**
 * [DAO]
 * 
 * @author cano.su
 * @since 2024/12/08
 */
public interface JournalMemoDao extends JpaRepository<JournalMemoPo, String> {

}
