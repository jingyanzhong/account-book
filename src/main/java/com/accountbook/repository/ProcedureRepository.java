package com.accountbook.repository;

import java.time.YearMonth;
import java.util.List;

import com.accountbook.model.dto.GetMonthlySummary;

/**
 * [REPOSITORY]
 * 
 * @author cano.su
 * @since 2024/12/08
 */
public interface ProcedureRepository {

    /** 查詢 某年月借項小計 */
    List<GetMonthlySummary> getMonthlySummaryDebit(YearMonth yearMonth);

    /** 查詢 某年月貸項小計 */
    List<GetMonthlySummary> getMonthlySummaryCredit(YearMonth yearMonth);

}
