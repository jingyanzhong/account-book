package com.accountbook.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accountbook.model.dao.JournalRecordDao;
import com.accountbook.model.dto.JournalRecord;
import com.accountbook.model.dto.Subject;
import com.accountbook.model.po.JournalRecordPo;
import com.accountbook.utils.TimeUtils;

/**
 * [REPOSITORY]
 * 
 * @author cano.su
 * @since 2024/11/15
 */
@Repository
public class JournalRecordRepositoryImpl implements JournalRecordRepository {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private JournalRecordDao journalRecordDao;

    @Override
    public JournalRecord save(JournalRecord journalRecord) {
        Objects.requireNonNull(journalRecord, "日記帳資料 must not be null.");
        final JournalRecordPo po = this.toPo(journalRecord);
        final JournalRecordPo savedPo = journalRecordDao.save(po);
        return this.toDto(savedPo);
    }

    @Override
    public void remove(Long key) {
        Objects.requireNonNull(key, "日記帳流水號 must not be null.");
        journalRecordDao.deleteById(key);
    }

    @Override
    public Optional<JournalRecord> findByKey(Long key) {
        Objects.requireNonNull(key, "日記帳流水號 must not be null.");
        final Optional<JournalRecordPo> poOpt = journalRecordDao.findById(key);
        return (poOpt.isPresent()
                ? poOpt.map(this::toDto)
                : Optional.empty());
    }

    @Override
    public List<JournalRecord> findAll() {
        final List<JournalRecordPo> pos = journalRecordDao.findAll();
        return pos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
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

    private JournalRecordPo toPo(JournalRecord dto) {
        Objects.requireNonNull(dto, "JournalRecord must not be null.");
        final JournalRecordPo po = new JournalRecordPo();
        dto.getKey().ifPresent(key -> po.setJournalRecordKey(key));
        po.setTxTime(TimeUtils.toCalendar(dto.getTxTime()));
        po.setDebit(dto.getDebit().getCode());
        po.setCredit(dto.getCredit().getCode());
        po.setAmount(dto.getAmount());
        po.setMemo(dto.getMemo());
        po.setRemark(dto.getRemark());
        return po;
    }

    private JournalRecord toDto(JournalRecordPo po) {
        Objects.requireNonNull(po, "JournalRecordPo must not be null.");
        final Long key = po.getJournalRecordKey();
        final LocalDateTime txTime = TimeUtils.toLocalDateTime(po.getTxTime());
        final String debitCode = po.getDebit();
        final String creditCode = po.getCredit();
        final Subject debit = subjectRepository.findByCode(debitCode)
                .orElseThrow(() -> new IllegalArgumentException("查無對應的借項 [debit=" + debitCode + "]"));
        final Subject credit = subjectRepository.findByCode(creditCode)
                .orElseThrow(() -> new IllegalArgumentException("查無對應的貸項 [debit=" + creditCode + "]"));
        final BigDecimal amount = po.getAmount();
        final String memo = po.getMemo();
        final String remark = po.getRemark();
        return JournalRecord.ofRead(key, txTime, debit, credit, amount, memo, remark);
    }
}
