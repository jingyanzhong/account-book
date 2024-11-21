package com.accountbook.repository;

import java.util.List;

import com.accountbook.model.dto.ViewJournalRecordYearMonth;

/**
 * [REPOSITORY]
 * 
 * @author cano.su
 * @since 2024/11/21
 */
public interface ViewJournalRecordYearMonthRepository {

    /** 取得 所有日記帳資料 */
    List<ViewJournalRecordYearMonth> findAll();

}
