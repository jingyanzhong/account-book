package com.accountbook.repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accountbook.model.dao.ViewJournalRecordYearMonthDao;
import com.accountbook.model.dto.ViewJournalRecordYearMonth;
import com.accountbook.model.po.ViewJournalRecordYearMonthPo;

/**
 * [REPOSITORY]
 * 
 * @author cano.su
 * @since 2024/11/21
 */
@Repository
public class ViewJournalRecordYearMonthRepositoryImpl implements ViewJournalRecordYearMonthRepository {

    @Autowired
    private ViewJournalRecordYearMonthDao viewJournalRecordYearMonthDao;


    @Override
    public List<ViewJournalRecordYearMonth> findAll() {
        final List<ViewJournalRecordYearMonthPo> pos = viewJournalRecordYearMonthDao.findAll();
        return pos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private ViewJournalRecordYearMonth toDto(ViewJournalRecordYearMonthPo po) {
        Objects.requireNonNull(po, "ViewJournalRecordYearMonthPo must not be null.");
        return ViewJournalRecordYearMonth.of(po.getKey().getYear(), po.getKey().getMonth());
    }
}
