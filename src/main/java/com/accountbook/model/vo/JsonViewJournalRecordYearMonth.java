package com.accountbook.model.vo;

import com.accountbook.model.dto.JournalRecord;
import com.accountbook.model.dto.ViewJournalRecordYearMonth;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * [JSON]
 * 
 * @author cano.su
 * @since 2024/11/21
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JsonViewJournalRecordYearMonth implements JsonVo<JournalRecord> {

    /** 年 */
    private int year;

    /** 月 */
    private int month;

    private JsonViewJournalRecordYearMonth() {
        super();
    }

    public static JsonViewJournalRecordYearMonth of(ViewJournalRecordYearMonth dto) {
        return new JsonViewJournalRecordYearMonth()
                .setYear(dto.getYear())
                .setMonth(dto.getMonth());
    }

    public JsonViewJournalRecordYearMonth setYear(Integer year) {
        this.year = year;
        return this;
    }

    public JsonViewJournalRecordYearMonth setMonth(Integer month) {
        this.month = month;
        return this;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

}
