package com.accountbook.model.vo;

import java.math.BigDecimal;

import com.accountbook.model.dto.JournalRecord;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * [JSON]
 * 
 * @author cano.su
 * @since 2024/11/15
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JsonJournalRecord implements JsonVo<JournalRecord> {

    /** 流水號 */
    private Long key;

    /** 交易時間(yyyy-MM-dd HH:mm:ss) */
    private JsonTime txTime;

    /** 借項 */
    private JsonSubject debit;

    /** 貸項 */
    private JsonSubject credit;

    /** 金額 */
    private BigDecimal amount;

    /** 摘要 */
    private String memo;

    /** 備註 */
    private String remark;

    private JsonJournalRecord() {
        super();
    }

    public static JsonJournalRecord of(JournalRecord dto) {
        return new JsonJournalRecord()
                .setKey(dto.getKey().orElse(null))
                .setTxTime(JsonTime.of(dto.getTxTime()))
                .setDebit(JsonSubject.of(dto.getDebit()))
                .setCredit(JsonSubject.of(dto.getCredit()))
                .setAmount(dto.getAmount())
                .setMemo(dto.getMemo())
                .setRemark(dto.getRemark());
    }

    public JsonJournalRecord setKey(Long key) {
        this.key = key;
        return this;
    }

    public JsonJournalRecord setTxTime(JsonTime txTime) {
        this.txTime = txTime;
        return this;
    }

    public JsonJournalRecord setDebit(JsonSubject debit) {
        this.debit = debit;
        return this;
    }

    public JsonJournalRecord setCredit(JsonSubject credit) {
        this.credit = credit;
        return this;
    }

    public JsonJournalRecord setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public JsonJournalRecord setMemo(String memo) {
        this.memo = memo;
        return this;
    }

    public JsonJournalRecord setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public Long getKey() {
        return key;
    }

    public JsonTime getTxTime() {
        return txTime;
    }

    public JsonSubject getDebit() {
        return debit;
    }

    public JsonSubject getCredit() {
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

}
