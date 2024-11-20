package com.accountbook.model.vo.req;

/**
 * [REQ]
 * 
 * @author cano.su
 * @since 2024/11/20
 */
public class JournalRecordReqRemove implements JournalRecordReqBase {
    
    /** 日記帳流水號 */
    private Long key;

    public void setKey(Long key) {
        this.key = key;
    }

    public Long getKey() {
        return key;
    }

}
