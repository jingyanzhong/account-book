package com.accountbook.model.dto;

import java.math.BigDecimal;

/**
 * [DTO]
 * 
 * @author cano.su
 * @since 2024/12/08
 */
public class GetMonthlySummary {

    /** 年 */
    private Integer year;

    /** 月 */
    private Integer month;

    /** 會計科目代號 */
    private String code;

    /** 會計科目名稱 */
    private String name;

    /** 小計 */
    private BigDecimal totalAmount;

    private GetMonthlySummary(Integer year, Integer month, String code, String name, BigDecimal totalAmount) {
        super();
        this.year = year;
        this.month = month;
        this.code = code;
        this.name = name;
        this.totalAmount = totalAmount;
    }

    public static GetMonthlySummary ofRead(Integer year, Integer month, String code, String name, BigDecimal totalAmount) {
        return new GetMonthlySummary(year, month, code, name, totalAmount);
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

}
