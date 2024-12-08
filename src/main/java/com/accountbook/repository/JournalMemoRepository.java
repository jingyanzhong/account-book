package com.accountbook.repository;

import java.util.List;

import com.accountbook.model.dto.JournalMemo;

/**
 * [REPOSITORY]
 * 
 * @author cano.su
 * @since 2024/12/08
 */
public interface JournalMemoRepository {

    /** 取得 日記帳摘要資料 */
    List<JournalMemo> findAll();

}
