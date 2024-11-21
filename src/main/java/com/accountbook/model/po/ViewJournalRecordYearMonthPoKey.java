package com.accountbook.model.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * [PO KEY]
 * 
 * @author cano.su
 * @since 2024/11/21
 */
@Embeddable
public class ViewJournalRecordYearMonthPoKey implements Serializable {

    private static final long serialVersionUID = -1028813110525240196L;

    /** 年 */
    @Column(name = "year")
    private Integer year;

    /** 月 */
    @Column(name = "month")
    private Integer month;

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

}
