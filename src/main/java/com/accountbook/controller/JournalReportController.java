package com.accountbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountbook.controller.base.ApiController;
import com.accountbook.exception.JournalRecordException;
import com.accountbook.model.dto.JournalReport;
import com.accountbook.model.vo.JsonJournalReport;
import com.accountbook.model.vo.req.YearMonthReq;
import com.accountbook.model.vo.resp.Resp;
import com.accountbook.service.JournalReportService;

/**
 * [JournalReport API]
 * 
 * @author cano.su
 * @since 2024/12/08
 */
@RestController
@RequestMapping(value = "/journalReport", produces = "application/json; charset=UTF-8")
public class JournalReportController extends ApiController {

    @Autowired
    private JournalReportService journalReportService;

    /**
     * [月收支報告]
     * 
     * @author cano.su
     * @since 2024/12/11
     * @param req 年月資料 API 請求
     * @return
     */
    @PostMapping("/monthly")
    public String monthly(YearMonthReq req) {
        try {
            final JournalReport journalReport = journalReportService.monthlySummary(req.getYearMonth());
            return Resp.ofSuccess(JsonJournalReport.of(journalReport));

        } catch (JournalRecordException e) {
            return Resp.ofAddException(e);

        } catch (Exception e) {
            return Resp.ofException(e);
        }
    }

}
