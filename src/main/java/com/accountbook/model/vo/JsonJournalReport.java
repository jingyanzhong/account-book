package com.accountbook.model.vo;

import java.math.BigDecimal;
import java.util.List;

import com.accountbook.model.dto.JournalReport;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * [JSON]
 * 
 * @author cano.su
 * @since 2024/12/11
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JsonJournalReport implements JsonVo<JournalReport> {

    /** 年 */
    private Integer year;

    /** 月 */
    private Integer month;

    /** 收入資料 */
    private List<JsonGetMonthlySummary> debits;

    /** 收入小計 */
    private BigDecimal sumDebit;

    /** 支出資料 */
    private List<JsonGetMonthlySummary> credits;

    /** 支出小計 */
    private BigDecimal sumCredit;

    private JsonJournalReport() {
        super();
    }

    public static JsonJournalReport of(JournalReport dto) {
        final List<JsonGetMonthlySummary> credits = dto.getCredits().stream()
                .map(JsonGetMonthlySummary::of)
                .toList();
        final List<JsonGetMonthlySummary> debits = dto.getDebits().stream()
                .map(JsonGetMonthlySummary::of)
                .toList();
        return new JsonJournalReport()
                .setYear(dto.getYearMonth().getYear())
                .setMonth(dto.getYearMonth().getMonthValue())
                .setCredits(credits)
                .setDebits(debits)
                .setSumCredit(dto.getSumCredit())
                .setSumDebit(dto.getSumDebit());
    }

    public JsonJournalReport setYear(Integer year) {
        this.year = year;
        return this;
    }

    public JsonJournalReport setMonth(Integer month) {
        this.month = month;
        return this;
    }

    public JsonJournalReport setDebits(List<JsonGetMonthlySummary> debits) {
        this.debits = debits;
        return this;
    }

    public JsonJournalReport setSumDebit(BigDecimal sumDebit) {
        this.sumDebit = sumDebit;
        return this;
    }

    public JsonJournalReport setCredits(List<JsonGetMonthlySummary> credits) {
        this.credits = credits;
        return this;
    }

    public JsonJournalReport setSumCredit(BigDecimal sumCredit) {
        this.sumCredit = sumCredit;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

    public List<JsonGetMonthlySummary> getDebits() {
        return debits;
    }

    public BigDecimal getSumDebit() {
        return sumDebit;
    }

    public List<JsonGetMonthlySummary> getCredits() {
        return credits;
    }

    public BigDecimal getSumCredit() {
        return sumCredit;
    }

}
