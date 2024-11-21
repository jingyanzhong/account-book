package com.accountbook.service;

import java.time.YearMonth;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accountbook.exception.JournalRecordException;
import com.accountbook.model.dto.JournalRecord;
import com.accountbook.model.vo.req.JournalRecordReqAdd;
import com.accountbook.model.vo.req.JournalRecordReqModify;
import com.accountbook.model.vo.req.JournalRecordReqRemove;
import com.accountbook.model.vo.req.JournalRecordReqYearMonth;
import com.accountbook.model.vo.req.validator.JournalRecordReqAddValidator;
import com.accountbook.model.vo.req.validator.JournalRecordReqModifyValidator;
import com.accountbook.model.vo.req.validator.JournalRecordReqRemoveValidator;
import com.accountbook.model.vo.req.validator.JournalRecordReqValidatorLib;
import com.accountbook.repository.JournalRecordRepository;
import com.accountbook.repository.SubjectRepository;
import com.accountbook.utils.TimeUtils;

/**
 * [SERVICE]
 * 
 * @author cano.su
 * @since 2024/11/14
 */
@Service
public class JournalRecordService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private JournalRecordRepository journalRecordRepository;

    /**
     * [新增 日記帳紀錄]
     * 
     * @author cano.su
     * @since 2024/11/16
     */
    public JournalRecord add(JournalRecordReqAdd req) throws JournalRecordException {
        Objects.requireNonNull(req, "Add 日記帳資料 API 請求 must not be null.");
        final JournalRecordReqValidatorLib lib = JournalRecordReqValidatorLib.of(subjectRepository);
        // 驗證
        for (JournalRecordReqAddValidator validator : JournalRecordReqAddValidator.values()) {
            validator.valid(lib, req);
        }
        final JournalRecord journalRecord = JournalRecord.ofNew(
                TimeUtils.parseLocalDateTime(TimeUtils.DATEPATTERN_YYYYMMDD_HHMMSS, req.getTxTime()),
                subjectRepository.findByCode(req.getDebit()).get(),
                subjectRepository.findByCode(req.getCredit()).get(),
                req.getAmount(), req.getMemo());
        return journalRecordRepository.save(journalRecord);
    }

    /**
     * [更新 日記帳紀錄]
     * 
     * @author cano.su
     * @since 2024/11/20
     */
    public JournalRecord modify(JournalRecordReqModify req) throws JournalRecordException {
        Objects.requireNonNull(req, "Modify 日記帳資料 API 請求 must not be null.");
        final JournalRecordReqValidatorLib lib = JournalRecordReqValidatorLib.of(subjectRepository);
        // 驗證
        for (JournalRecordReqModifyValidator validator : JournalRecordReqModifyValidator.values()) {
            validator.valid(lib, req);
        }
        final JournalRecord journalRecord = JournalRecord.ofModify(
                req.getKey(),
                TimeUtils.parseLocalDateTime(TimeUtils.DATEPATTERN_YYYYMMDD_HHMMSS, req.getTxTime()),
                subjectRepository.findByCode(req.getDebit()).get(),
                subjectRepository.findByCode(req.getCredit()).get(),
                req.getAmount(), req.getMemo());
        return journalRecordRepository.save(journalRecord);
    }

    /**
     * [移除 日記帳紀錄]
     * 
     * @author cano.su
     * @throws JournalRecordException 日記帳處理異常
     * @since 2024/11/20
     */
    public void remove(JournalRecordReqRemove req) throws JournalRecordException {
        Objects.requireNonNull(req, "Remove 日記帳資料 API 請求 must not be null.");
        final JournalRecordReqValidatorLib lib = JournalRecordReqValidatorLib.of(subjectRepository);
        // 驗證
        for (JournalRecordReqRemoveValidator validator : JournalRecordReqRemoveValidator.values()) {
            validator.valid(lib, req);
        }
        journalRecordRepository.remove(req.getKey());

    }

    /**
     * [查詢 某一筆日記帳資料]
     * 
     * @author cano.su
     * @since 2024/11/16
     * @param key 日記帳流水號
     */
    public JournalRecord findByKey(Long key) throws JournalRecordException {
        Objects.requireNonNull(key, "日記帳流水號 must not be null.");
        final Optional<JournalRecord> journalRecordOpt = journalRecordRepository.findByKey(key);
        if (journalRecordOpt.isPresent()) {
            return journalRecordOpt.get();
        } else {
            throw JournalRecordException.ofQueryNotFound();
        }
    }

    /**
     * [取得 所有日記帳紀錄]
     * 
     * @author cano.su
     * @since 2024/11/21
     * @return
     * @throws JournalRecordException
     */
    public List<JournalRecord> findAll() throws JournalRecordException {
        try {
            return journalRecordRepository.findAll();
        } catch (Exception e) {
            throw JournalRecordException.ofQueryValidError();
        }
    }

    /**
     * [取得 某年月日記帳紀錄]
     * 
     * @author cano.su
     * @since 2024/11/15
     * @param yearMonth 年月
     * @return 日記帳資料
     * @throws JournalRecordException
     */
    public List<JournalRecord> findByYearMonth(JournalRecordReqYearMonth req) throws JournalRecordException {
        try {
            Objects.requireNonNull(req, "YearMonth 日記帳資料 API 請求 must not be null.");
            final YearMonth yearMonth = req.getYearMonth();
            return journalRecordRepository.findByYearMonth(yearMonth);

        } catch (Exception e) {
            throw JournalRecordException.ofQueryValidError();
        }
    }

}
