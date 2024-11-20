package com.accountbook.model.vo.req.validator;

import com.accountbook.exception.JournalRecordException;
import com.accountbook.model.vo.req.JournalRecordReqRemove;

/**
 * [VALIDATOR]
 * 
 * @author cano.su
 * @since 2024/11/20
 */
public enum JournalRecordReqRemoveValidator {

    KEY {
        @Override
        public void valid(JournalRecordReqValidatorLib lib, JournalRecordReqRemove req) throws JournalRecordException {
            if (lib == null) {
                throw JournalRecordException.ofRemoveValidError("lib 不可為空");
            }
            if (req == null) {
                throw JournalRecordException.ofRemoveValidError("req 不可為空");
            }
            final Long key = req.getKey();
            if (key == null) {
                throw JournalRecordException.ofRemoveValidErrorParam(this.name() + " 不可為空");
            }
        }
    };

    /** 參數驗證 */
    public abstract void valid(JournalRecordReqValidatorLib lib, JournalRecordReqRemove req) throws JournalRecordException;

}
