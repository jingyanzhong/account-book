package com.accountbook.repository.procedure;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

import com.accountbook.model.dto.GetMonthlySummary;

/**
 * [PROCEDURE]
 * 
 * @author cano.su
 * @since 2024/12/09
 */
public class GetMonthlySummaryDebit extends ProcedureDto<GetMonthlySummary> {

    public GetMonthlySummaryDebit(List<ProcedureParam> params) {
        super(params);
    }

    @Override
    public String getName() {
        return "get_monthly_summary_debit";
    }

    @Override
    public Function<Object[], GetMonthlySummary> getConvertor() {
        return (result -> {
            int year = Integer.parseInt(String.valueOf(result[0]));
            int month = Integer.parseInt(String.valueOf(result[1]));
            String code = String.valueOf(result[2]);
            String name = String.valueOf(result[3]);
            BigDecimal totalAmount = BigDecimal.valueOf(Integer.parseInt(String.valueOf(result[4])));
            return GetMonthlySummary.ofRead(year, month, code, name, totalAmount);
        });
    }

}
