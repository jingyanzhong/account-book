package com.accountbook.model.dao;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accountbook.model.po.JournalRecordPo;

/**
 * [DAO]
 * 
 * @author cano.su
 * @since 2024/11/14
 */
public interface JournalRecordDao extends JpaRepository<JournalRecordPo, Long> {

    /** 取得 某一筆字記帳紀錄 */
    JournalRecordPo findByJournalRecordKey(Long key);

    /** 取得 某期間的日記帳紀錄 */
    List<JournalRecordPo> findByTxTimeBetween(Calendar startCalendar, Calendar endedCalendar);

}
