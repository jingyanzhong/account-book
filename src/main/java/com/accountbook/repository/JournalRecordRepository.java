package com.accountbook.repository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import com.accountbook.model.dto.JournalRecord;

/**
 * [REPOSITORY]
 * 
 * @author cano.su
 * @since 2024/11/15
 */
public interface JournalRecordRepository {

    /**
     * [儲存 日記帳資料]
     * 
     * @author cano.su
     * @since 2024/11/17
     * @param journalRecord 日記帳資料
     */
    JournalRecord save(JournalRecord journalRecord);

    /**
     * [移除 日記帳資料]
     * 
     * @author cano.su
     * @since 2024/11/20
     * @param key 日記帳流水號
     */
    void remove(Long key);

    /**
     * [查詢 某一筆日記帳資料]
     * 
     * @author cano.su
     * @since 2024/11/17
     * @param key 日記帳流水號
     */
    Optional<JournalRecord> findByKey(Long key);

    /** 取得 所有日記帳資料 */
    List<JournalRecord> findAll();

    /** 取得 日記帳資料 by 年月 */
    List<JournalRecord> findByYearMonth(YearMonth yearMonth);

}
