package com.accountbook.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accountbook.model.po.ViewJournalRecordYearMonthPo;
import com.accountbook.model.po.ViewJournalRecordYearMonthPoKey;

/**
 * [DAO]
 * 
 * @author cano.su
 * @since 2024/11/21
 */
public interface ViewJournalRecordYearMonthDao extends JpaRepository<ViewJournalRecordYearMonthPo, ViewJournalRecordYearMonthPoKey> {

    List<ViewJournalRecordYearMonthPo> findAll();

}
