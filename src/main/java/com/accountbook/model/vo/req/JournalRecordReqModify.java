package com.accountbook.model.vo.req;

import java.math.BigDecimal;

/**
 * [REQ]
 * 
 * @author cano.su
 * @since 2024/11/19
 */
public class JournalRecordReqModify implements JournalRecordReqBase {
    
    /** 日記帳流水號 */
    private Long key;

    /** 交易時間: yyyy-MM-dd HH:mm:ss */
    private String txTime;

    /** 借項 */
    private String debit;

    /** 貸項 */
    private String credit;

    /** 金額 */
    private BigDecimal amount;

    /** 摘要 */
    private String memo;

    public void setKey(Long key) {
        this.key = key;
    }

    public void setTxTime(String txTime) {
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

    public Long getKey() {
        return key;
    }

    public String getTxTime() {
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

}
