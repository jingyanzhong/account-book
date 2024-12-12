package com.accountbook.service;

import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accountbook.model.dto.GetMonthlySummary;
import com.accountbook.model.dto.JournalReport;
import com.accountbook.repository.ProcedureRepository;

/**
 * [SERVICE]
 * 
 * @author cano.su
 * @since 2024/12/08
 */
@Service
public class JournalReportService {

    @Autowired
    private ProcedureRepository procedureRepository;

    /**
     * [月收支報告]
     * 
     * @author cano.su
     * @since 2024/12/11
     * @param yearMonth 年月
     * @return
     */
    public JournalReport monthlySummary(YearMonth yearMonth) {
        final List<GetMonthlySummary> debits = procedureRepository.getMonthlySummaryDebit(yearMonth);
        final List<GetMonthlySummary> credits = procedureRepository.getMonthlySummaryCredit(yearMonth);
        return JournalReport.of(yearMonth, debits, credits);
    }

}
