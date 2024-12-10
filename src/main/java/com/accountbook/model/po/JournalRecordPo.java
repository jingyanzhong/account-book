package com.accountbook.model.po;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "journal_record")
public class JournalRecordPo {

    /** 日記帳檔流水號 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "journal_record_key", updatable = false, nullable = false)
    private Long journalRecordKey;

    /** 交易時間 */
    @Column(name = "tx_time", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar txTime;

    /** 借項 */
    @Column(name = "debit")
    private String debit;

    /** 貸項 */
    @Column(name = "credit")
    private String credit;

    /** 金額 */
    @Column(name = "amount")
    private BigDecimal amount;

    /** 摘要 */
    @Column(name = "memo")
    private String memo;

    /** 備註 */
    @Column(name = "remark")
    private String remark;

    /** 建立時間 */
    @Column(name = "create_time", insertable = false, updatable = false)
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

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getRemark() {
        return remark;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

}
