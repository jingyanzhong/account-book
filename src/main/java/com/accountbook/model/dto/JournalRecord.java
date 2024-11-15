package com.accountbook.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * [DTO]
 * 
 * @author cano.su
 * @since 2024/11/14
 */
public class JournalRecord {

    /** 交易時間 */
    private final LocalDateTime txTime;

    /** 借項 */
    private final Subject debit;

    /** 貸項 */
    private final Subject credit;

    /** 金額 */
    private final BigDecimal amount;

    /** 摘要 */
    private final String memo;

    private JournalRecord(LocalDateTime txTime, Subject debit, Subject credit, BigDecimal amount, String memo) {
        super();
        this.txTime = Objects.requireNonNull(txTime, "交易時間 must not be null.");
        this.debit = Objects.requireNonNull(debit, "借項 must not be null.");
        this.credit = Objects.requireNonNull(credit, "貸項 must not be null.");
        this.amount = Objects.requireNonNull(amount, "金額 must not be null.");
        this.memo = Objects.requireNonNull(memo, "摘要 must not be null.");
    }

    public static JournalRecord of(LocalDateTime txTime, Subject debit, Subject credit, BigDecimal amount, String memo) {
        return new JournalRecord(txTime, debit, credit, amount, memo);
    }

    public LocalDateTime getTxTime() {
        return txTime;
    }

    public Subject getDebit() {
        return debit;
    }

    public Subject getCredit() {
        return credit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getMemo() {
        return memo;
    }

}
