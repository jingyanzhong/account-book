package com.accountbook.model.po;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * [PO]
 * 
 * @author cano.su
 * @since 2024/11/21
 */
@Entity
@Table(name = "view_journal_record_year_month")
public class ViewJournalRecordYearMonthPo {

    @EmbeddedId
    private ViewJournalRecordYearMonthPoKey key;

    public void setKey(ViewJournalRecordYearMonthPoKey key) {
        this.key = key;
    }

    public ViewJournalRecordYearMonthPoKey getKey() {
        return key;
    }

}
