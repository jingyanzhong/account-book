package com.accountbook.model.dto;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Objects;

/**
 * [DTO]
 * 
 * @author cano.su
 * @since 2024/12/11
 */
public class JournalReport {

    /** 年月 */
    private YearMonth yearMonth;

    /** 收入資料 */
    private List<GetMonthlySummary> debits;

    /** 收入小計 */
    private BigDecimal sumDebit;

    /** 支出資料 */
    private List<GetMonthlySummary> credits;

    /** 支出小計 */
    private BigDecimal sumCredit;

    private JournalReport(YearMonth yearMonth, List<GetMonthlySummary> debits, List<GetMonthlySummary> credits) {
        super();
        this.yearMonth = Objects.requireNonNull(yearMonth, "年月");
        this.debits = Objects.requireNonNull(debits, "收入資料");
        this.credits = Objects.requireNonNull(credits, "支出資料");
        this.sumDebit = debits.stream()
                .map(GetMonthlySummary::getTotalAmount)
                .reduce(BigDecimal.ZERO, (v1, v2) -> v1.add(v2));
        this.sumCredit = credits.stream()
                .map(GetMonthlySummary::getTotalAmount)
                .reduce(BigDecimal.ZERO, (v1, v2) -> v1.add(v2));
    }

    public static JournalReport of(YearMonth yearMonth, List<GetMonthlySummary> debits,
            List<GetMonthlySummary> credits) {
        return new JournalReport(yearMonth, debits, credits);
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public List<GetMonthlySummary> getDebits() {
        return debits;
    }

    public BigDecimal getSumDebit() {
        return sumDebit;
    }

    public List<GetMonthlySummary> getCredits() {
        return credits;
    }

    public BigDecimal getSumCredit() {
        return sumCredit;
    }

}
