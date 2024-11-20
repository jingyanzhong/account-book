package com.accountbook.validator;

import java.util.Arrays;
import java.util.List;

import com.accountbook.model.vo.req.validator.JournalRecordReqAddValidator;
import com.accountbook.model.vo.req.validator.JournalRecordReqValidatorLib;

/**
 * [TODO]
 * 
 * @author cano.su
 * @since 2024/11/20
 */
public class JournalRecordValidator extends ValidatorBase {

//    static class TxTimeValidator
//            implements ValidatorConsumer<JournalRecordReqValidatorLib, JournalRecordReqAddValidator> {
//        private static TxTimeValidator instance = new TxTimeValidator();
//
//        @Override
//        public void valid(JournalRecordReqValidatorLib lib, JournalRecordReqAddValidator data)
//                throws ValidatorException {
//            // TODO Auto-generated method stub
//
//        }
//
//        @Override
//        public ValidatorConsumer<JournalRecordReqValidatorLib, JournalRecordReqAddValidator> getInstance() {
//            return instance;
//        }
//    }
//
//    static class DebitValidator
//            implements ValidatorConsumer<JournalRecordReqValidatorLib, JournalRecordReqAddValidator> {
//        private static DebitValidator instance = new DebitValidator();
//
//        @Override
//        public void valid(JournalRecordReqValidatorLib lib, JournalRecordReqAddValidator data)
//                throws ValidatorException {
//        }
//
//        @Override
//        public ValidatorConsumer<JournalRecordReqValidatorLib, JournalRecordReqAddValidator> getInstance() {
//            return instance;
//        }
//    }
//
//    public static List<?> getAddValidator() {
//        return ValidatorBase.getValidators(
//                TxTimeValidator.get,
//                DebitValidator.class);
//    }
}
