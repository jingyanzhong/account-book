package com.accountbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accountbook.exception.JournalRecordException;
import com.accountbook.model.dto.JournalMemo;
import com.accountbook.repository.JournalMemoRepository;

/**
 * [SERVICE]
 * 
 * @author cano.su
 * @since 2024/12/08
 */
@Service
public class JournalMemoService {

    @Autowired
    private JournalMemoRepository journalMemoRepository;

    /**
     * [取得 日記帳年月資料]
     * 
     * @author cano.su
     * @since 2024/12/08
     * @return 日記帳摘要資料
     * @throws JournalRecordException
     */
    public List<JournalMemo> findAll() throws JournalRecordException {
        try {
            return journalMemoRepository.findAll();
        } catch (Exception e) {
            throw JournalRecordException.ofQueryValidError();
        }
    }

}
