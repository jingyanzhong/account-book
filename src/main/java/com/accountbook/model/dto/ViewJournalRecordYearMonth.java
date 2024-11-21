package com.accountbook.model.dto;

import java.util.Objects;

/**
 * [DTO]
 * 
 * @author cano.su
 * @since 2024/11/21
 */
public class ViewJournalRecordYearMonth {

    /** 年 */
    private Integer year;

    /** 月 */
    private Integer month;

    public ViewJournalRecordYearMonth(Integer year, Integer month) {
        super();
        this.year = Objects.requireNonNull(year, "年 must not be null.");
        this.month = Objects.requireNonNull(month, "月 must not be null.");
        ;
    }

    public static ViewJournalRecordYearMonth of(Integer year, Integer month) {
        return new ViewJournalRecordYearMonth(year, month);
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

}
