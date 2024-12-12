package com.accountbook.repository;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accountbook.model.dto.GetMonthlySummary;
import com.accountbook.repository.procedure.GetMonthlySummaryCredit;
import com.accountbook.repository.procedure.GetMonthlySummaryDebit;
import com.accountbook.repository.procedure.ProcedureParam;

/**
 * [REPOSITORY]
 * 
 * @author cano.su
 * @since 2024/11/15
 */
@Repository
public class ProcedureRepositoryImpl implements ProcedureRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<GetMonthlySummary> getMonthlySummaryDebit(YearMonth yearMonth) {
        final List<ProcedureParam> params = Arrays.asList(
                ProcedureParam.of(1, Integer.class, yearMonth.getYear()),
                ProcedureParam.of(2, Integer.class, yearMonth.getMonthValue()));
        final GetMonthlySummaryDebit procedure = new GetMonthlySummaryDebit(params);
        return procedure.execute(entityManager);
    }

    @Override
    public List<GetMonthlySummary> getMonthlySummaryCredit(YearMonth yearMonth) {
        final List<ProcedureParam> params = Arrays.asList(
                ProcedureParam.of(1, Integer.class, yearMonth.getYear()),
                ProcedureParam.of(2, Integer.class, yearMonth.getMonthValue()));
        final GetMonthlySummaryCredit procedure = new GetMonthlySummaryCredit(params);
        return procedure.execute(entityManager);
    }

}
