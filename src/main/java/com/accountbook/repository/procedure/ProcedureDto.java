package com.accountbook.repository.procedure;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

/**
 * [DTO]
 * 
 * @author cano.su
 * @since 2024/12/09
 */
public abstract class ProcedureDto<DTO> {

    /** Procedure 參數 */
    private List<ProcedureParam> params;

    /**
     * [取得 procedure 名稱]
     * 
     * @author cano.su
     * @since 2024/12/09
     * @return
     */
    public abstract String getName();

    /**
     * [取得 DTO 轉換器]
     * 
     * @author cano.su
     * @since 2024/12/09
     * @return
     */
    public abstract Function<Object[], DTO> getConvertor();

    /**
     * [取得 傳入參數]
     * 
     * @author cano.su
     * @since 2024/12/10
     * @return
     */
    public ProcedureDto(List<ProcedureParam> params) {
        this.params = params;
    }

    /**
     * [轉換 DTO]
     * 
     * @author cano.su
     * @since 2024/12/09
     * @param <T>       DTO 型態
     * @param results   Procedure 查詢結果
     * @param convertor 轉換 DTO Function
     * @return
     */
    private List<DTO> toDto(List<Object[]> results) {
        return results.stream()
                .map(this.getConvertor())
                .collect(Collectors.toList());
    }

    /**
     * [執行]
     * 
     * @author cano.su
     * @since 2024/12/09
     * @param entityManager
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<DTO> execute(EntityManager entityManager) {
        final StoredProcedureQuery query = entityManager.createStoredProcedureQuery(this.getName());
        this.params.stream().forEach(param -> {
            final int index = param.getNo();
            final Class<?> type = param.getType();
            final Object value = param.getValue();
            query.registerStoredProcedureParameter(index, type, ParameterMode.IN);
            query.setParameter(index, value);
        });
        final List<Object[]> results = query.getResultList();
        return this.toDto(results);
    }

}
