package com.accountbook.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * [DTO]
 * 
 * @author cano.su
 * @since 2024/11/14
 */
public class JournalRecord {

    /** 流水號 */
    private Long key;

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

    private JournalRecord(Long key, LocalDateTime txTime, Subject debit, Subject credit, BigDecimal amount,
            String memo) {
        super();
        this.key = key;
        this.txTime = Objects.requireNonNull(txTime, "交易時間 must not be null.");
        this.debit = Objects.requireNonNull(debit, "借項 must not be null.");
        this.credit = Objects.requireNonNull(credit, "貸項 must not be null.");
        this.amount = Objects.requireNonNull(amount, "金額 must not be null.");
        this.memo = Objects.requireNonNull(memo, "摘要 must not be null.");
    }

    /** 新增紀錄 */
    public static JournalRecord ofNew(LocalDateTime txTime, Subject debit, Subject credit, BigDecimal amount,
            String memo) {
        return new JournalRecord(null, txTime, debit, credit, amount, memo);
    }

    /** 更新紀錄 */
    public static JournalRecord ofModify(Long key, LocalDateTime txTime, Subject debit, Subject credit, BigDecimal amount,
            String memo) {
        return new JournalRecord(key, txTime, debit, credit, amount, memo);
    }

    /** 讀取紀錄 */
    public static JournalRecord ofRead(Long key, LocalDateTime txTime, Subject debit, Subject credit, BigDecimal amount,
            String memo) {
        return new JournalRecord(key, txTime, debit, credit, amount, memo);
    }

    public Optional<Long> getKey() {
        return Optional.ofNullable(key);
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
