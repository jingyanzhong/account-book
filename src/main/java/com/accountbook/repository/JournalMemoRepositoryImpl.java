package com.accountbook.repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accountbook.model.dao.JournalMemoDao;
import com.accountbook.model.dto.JournalMemo;
import com.accountbook.model.po.JournalMemoPo;

/**
 * [REPOSITORY]
 * 
 * @author cano.su
 * @since 2024/12/08
 */
@Repository
public class JournalMemoRepositoryImpl implements JournalMemoRepository {

    @Autowired
    private JournalMemoDao journalMemoDao;

    @Override
    public List<JournalMemo> findAll() {
        return journalMemoDao.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private JournalMemoPo toPo(JournalMemo dto) {
        Objects.requireNonNull(dto, "JournalMemo must not be null.");
        final JournalMemoPo po = new JournalMemoPo();
        po.setCode(dto.getCode());
        po.setName(dto.getName());
        return po;
    }

    private JournalMemo toDto(JournalMemoPo po) {
        Objects.requireNonNull(po, "JournalMemoPo must not be null.");
        final String code = po.getCode();
        final String name = po.getName();
        return JournalMemo.ofRead(code, name);
    }
}
