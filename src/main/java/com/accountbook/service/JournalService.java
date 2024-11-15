package com.accountbook.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accountbook.model.dao.JournalRecordDao;
import com.accountbook.model.dto.JournalRecord;
import com.accountbook.model.dto.Subject;
import com.accountbook.model.po.JournalRecordPo;
import com.accountbook.utils.TimeUtils;

/**
 * [SERVICE]
 * 
 * @author cano.su
 * @since 2024/11/14
 */
@Service
public class JournalService {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private JournalRecordDao journalRecordDao;

    /**
     * [取得 某年月日記帳紀錄]
     * 
     * @author cano.su
     * @since 2024/11/15
     * @param yearMonth
     * @return
     */
    public List<JournalRecord> findByYearMonth(YearMonth yearMonth) {
        Objects.requireNonNull(yearMonth, "年月 must not be null.");
        final Calendar time = TimeUtils.toCalendar(yearMonth);
        final Calendar startCalendar = TimeUtils.getFirstDayTime(time);
        time.set(Calendar.DAY_OF_MONTH, 1);
        time.add(Calendar.MONDAY, 1);
        time.add(Calendar.DAY_OF_MONTH, -1);
        final Calendar endedCalendar = TimeUtils.getLastDayTime(time);
        final List<JournalRecordPo> pos = journalRecordDao.findByTxTimeBetween(startCalendar, endedCalendar);
        return pos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private JournalRecord toDto(JournalRecordPo po) {
        final LocalDateTime txTime = TimeUtils.toLocalDateTime(po.getTxTime());
        final String debitCode = po.getDebit();
        final String creditCode = po.getCredit();
        final Subject debit = subjectService.findByCode(debitCode)
                .orElseThrow(() -> new IllegalArgumentException("查無對應的借項 [debit=" + debitCode + "]"));
        final Subject credit = subjectService.findByCode(creditCode)
                .orElseThrow(() -> new IllegalArgumentException("查無對應的貸項 [debit=" + creditCode + "]"));
        final BigDecimal amount = po.getAmount();
        final String memo = po.getMemo();
        return JournalRecord.of(txTime, debit, credit, amount, memo);
    }

}
