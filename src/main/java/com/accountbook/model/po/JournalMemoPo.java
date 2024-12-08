package com.accountbook.model.po;

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
 * @since 2024/12/08
 */
@Entity
@Table(name = "journal_memo")
public class JournalMemoPo {

    /** 摘要代號 */
    @Id
    @Column(name = "code")
    private String code;

    /** 摘要名稱 */
    @Column(name = "name")
    private String name;

    /** 建立時間 */
    @Column(name = "create_time", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createTime;

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

}
