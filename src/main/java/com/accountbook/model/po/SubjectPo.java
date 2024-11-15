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
 * @since 2024/11/14
 */
@Entity
@Table(name = "SUBJECT")
public class SubjectPo {

    /** 會計科目代號 */
    @Id
    @Column(name = "CODE")
    private String code;

    /** 會計科目名稱 */
    @Column(name = "NAME")
    private String name;

    /** 建立時間 */
    @Column(name = "CREATE_TIME", insertable = false, updatable = false)
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
