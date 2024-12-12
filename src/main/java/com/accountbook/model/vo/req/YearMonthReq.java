package com.accountbook.model.vo.req;

import java.time.YearMonth;

import com.accountbook.exception.JournalRecordException;

/**
 * [REQ]
 * 
 * @author cano.su
 * @since 2024/12/11
 */
public class YearMonthReq implements JournalRecordReqBase {

    /** 年 */
    private int year;

    /** 月 */
    private int month;

    /**
     * [取得 年月]
     * 
     * @author cano.su
     * @since 2024/11/21
     * @return 年月
     * @throws JournalRecordException
     */
    public YearMonth getYearMonth() throws JournalRecordException {
        try {
            return YearMonth.of(year, month);
        } catch (Exception e) {
            throw JournalRecordException.ofQueryValidError();
        }
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

}
