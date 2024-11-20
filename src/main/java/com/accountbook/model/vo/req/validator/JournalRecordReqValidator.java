package com.accountbook.model.vo.req.validator;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.accountbook.exception.JournalRecordException;
import com.accountbook.model.vo.req.JournalRecordReqAdd;
import com.accountbook.utils.TimeUtils;

/**
 * [VALIDATOR]
 * 
 * @author cano.su
 * @since 2024/11/16
 */
public enum JournalRecordReqValidator {

    TX_TIME {
        @Override
        public void valid(JournalRecordReqValidatorLib lib, JournalRecordReqAdd req) throws JournalRecordException {
            if (lib == null) {
                throw JournalRecordException.ofAddValidError("lib 不可為空");
            }
            if (req == null) {
                throw JournalRecordException.ofAddValidError("req 不可為空");
            }
            final String txTimeStr = req.getTxTime();
            try {
                TimeUtils.parseLocalDateTime(TimeUtils.DATEPATTERN_YYYYMMDD_HHMMSS, txTimeStr);
            } catch (Exception e) {
                throw JournalRecordException.ofAddValidErrorParam(this.name() + " 轉換格式異常(" + TimeUtils.PATTERN_YYYYMMDD_HHMMSS + ") [" + txTimeStr + "]");
            }
        }
    },
    DEBIT {
        @Override
        public void valid(JournalRecordReqValidatorLib lib, JournalRecordReqAdd req) throws JournalRecordException {
            if (lib == null) {
                throw JournalRecordException.ofAddValidError("lib 不可為空");
            }
            if (req == null) {
                throw JournalRecordException.ofAddValidError("req 不可為空");
            }
            final String debit = req.getDebit();
            if (StringUtils.isBlank(debit)) {
                throw JournalRecordException.ofAddValidErrorParam(this.name() + " 不可為空");
            }
            if (!lib.getSubjectRepository().findByCode(debit).isPresent()) {
                throw JournalRecordException.ofAddValidErrorParam(this.name() + " 查無對應會計科目 [debit=" + debit + "]");
            }
        }
    },
    CREDIT {
        @Override
        public void valid(JournalRecordReqValidatorLib lib, JournalRecordReqAdd req) throws JournalRecordException {
            if (lib == null) {
                throw JournalRecordException.ofAddValidError("lib 不可為空");
            }
            if (req == null) {
                throw JournalRecordException.ofAddValidError("req 不可為空");
            }
            final String credit = req.getCredit();
            if (StringUtils.isBlank(credit)) {
                throw JournalRecordException.ofAddValidErrorParam(this.name() + " 不可為空");
            }
            if (!lib.getSubjectRepository().findByCode(credit).isPresent()) {
                throw JournalRecordException.ofAddValidErrorParam(this.name() + " 查無對應會計科目 [credit=" + credit + "]");
            }
        }
    },
    AMOUNT {
        @Override
        public void valid(JournalRecordReqValidatorLib lib, JournalRecordReqAdd req) throws JournalRecordException {
            if (lib == null) {
                throw JournalRecordException.ofAddValidError("lib 不可為空");
            }
            if (req == null) {
                throw JournalRecordException.ofAddValidError("req 不可為空");
            }
            final BigDecimal amount = req.getAmount();
            if (amount == null) {
                throw JournalRecordException.ofAddValidErrorParam(this.name() + " 不可為空");
            }
            if (BigDecimal.ZERO.equals(amount)) {
                throw JournalRecordException.ofAddValidErrorParam(this.name() + " 不可為0");
            }
        }
    },
    MEMO {
        @Override
        public void valid(JournalRecordReqValidatorLib lib, JournalRecordReqAdd req) throws JournalRecordException {
            if (lib == null) {
                throw JournalRecordException.ofAddValidError("lib 不可為空");
            }
            if (req == null) {
                throw JournalRecordException.ofAddValidError("req 不可為空");
            }
            if (StringUtils.isBlank(req.getMemo())) {
                throw JournalRecordException.ofAddValidError(this.name() + " 不可為空");
            }
        }
    };

    /** 參數驗證 */
    public abstract void valid(JournalRecordReqValidatorLib lib, JournalRecordReqAdd req) throws JournalRecordException;

}
