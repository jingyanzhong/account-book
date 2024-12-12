package com.accountbook.model.vo;

import java.math.BigDecimal;

import com.accountbook.model.dto.GetMonthlySummary;

/**
 * [JSON]
 * 
 * @author cano.su
 * @since 2024/12/12
 */
public class JsonGetMonthlySummary {

    /** 年 */
    private Integer year;

    /** 月 */
    private Integer month;

    /** 會計科目 */
    private JsonSubject subject;

    /** 小計 */
    private BigDecimal totalAmount;

    public JsonGetMonthlySummary() {
        super();
    }

    private JsonGetMonthlySummary(Integer year, Integer month, String code, String name, BigDecimal totalAmount) {
        super();
        this.year = year;
        this.month = month;
        this.subject = JsonSubject.of(code, name);
        this.totalAmount = totalAmount;
    }

    public static JsonGetMonthlySummary of(GetMonthlySummary monthlySummary) {
        return new JsonGetMonthlySummary(
                monthlySummary.getYear(),
                monthlySummary.getMonth(),
                monthlySummary.getCode(),
                monthlySummary.getName(),
                monthlySummary.getTotalAmount());
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public void setSubject(JsonSubject subject) {
        this.subject = subject;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

    public JsonSubject getSubject() {
        return subject;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

}
