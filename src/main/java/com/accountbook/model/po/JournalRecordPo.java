package com.accountbook.model.po;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * [PO]
 * 
 * @author cano.su
 * @since 2024/11/14
 */
@Entity
@Table(name = "JOURNAL_RECORD")
public class JournalRecordPo {

    /** 日記帳檔流水號 */
    @Id
    @Column(name = "JOURNAL_RECORD_KEY")
    private Long journalRecordKey;

    /** 交易時間 */
    @Column(name = "TX_TIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar txTime;

    /** 借項 */
    @Column(name = "DEBIT")
    private String debit;

    /** 貸項 */
    @Column(name = "CREDIT")
    private String credit;

    /** 金額 */
    @Column(name = "AMOUNT")
    private BigDecimal amount;

    /** 摘要 */
    @Column(name = "MEMO")
    private String memo;

    /** 建立時間 */
    @Column(name = "CREATE_TIME", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createTime;

    public void setJournalRecordKey(Long journalRecordKey) {
        this.journalRecordKey = journalRecordKey;
    }

    public void setTxTime(Calendar txTime) {
        this.txTime = txTime;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public Long getJournalRecordKey() {
        return journalRecordKey;
    }

    public Calendar getTxTime() {
        return txTime;
    }

    public String getDebit() {
        return debit;
    }

    public String getCredit() {
        return credit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getMemo() {
        return memo;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

}
