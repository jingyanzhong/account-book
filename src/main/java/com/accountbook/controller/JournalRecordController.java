package com.accountbook.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountbook.controller.base.ApiController;
import com.accountbook.exception.JournalRecordException;
import com.accountbook.model.dto.JournalRecord;
import com.accountbook.model.dto.ViewJournalRecordYearMonth;
import com.accountbook.model.vo.JsonJournalRecord;
import com.accountbook.model.vo.JsonViewJournalRecordYearMonth;
import com.accountbook.model.vo.req.JournalRecordReqAdd;
import com.accountbook.model.vo.req.JournalRecordReqModify;
import com.accountbook.model.vo.req.JournalRecordReqRemove;
import com.accountbook.model.vo.req.JournalRecordReqYearMonth;
import com.accountbook.model.vo.resp.Resp;
import com.accountbook.service.JournalRecordService;

/**
 * [JournalRecord API]
 * 
 * @author cano.su
 * @since 2024/11/15
 */
@RestController
@RequestMapping(value = "/journalRecord", produces = "application/json; charset=UTF-8")
public class JournalRecordController extends ApiController {

    @Autowired
    private JournalRecordService journalRecordService;

    /**
     * [新增 日記帳資料]
     * 
     * @author cano.su
     * @since 2024/11/17
     * @param req 日記帳資料 API 請求
     * @return 已寫入的日記帳紀錄
     */
    @PostMapping("/add")
    public String add(JournalRecordReqAdd req) {
        try {
            final JournalRecord journalRecord = journalRecordService.add(req);
            return Resp.ofSuccess(JsonJournalRecord.of(journalRecord));

        } catch (JournalRecordException e) {
            return Resp.ofAddException(e);

        } catch (Exception e) {
            return Resp.ofException(e);
        }
    }

    /**
     * [查詢 某一筆日記帳資料]
     * 
     * @author cano.su
     * @since 2024/11/16
     * @param key 日記帳流水號
     * @return 日記帳紀錄
     */
    @PostMapping("/find")
    public String find(Long key) {
        try {
            final JournalRecord journalRecord = journalRecordService.findByKey(key);
            return Resp.ofSuccess(JsonJournalRecord.of(journalRecord));

        } catch (JournalRecordException e) {
            switch (e.getType()) {
            case QUERY_NOT_FOUND:
                // 查無資料, 算正常回應
                break;

            default:
                logger.error(e.getMessage(), e);
                break;
            }
            return Resp.ofQueryNotFound();

        } catch (Exception e) {
            return Resp.ofException(e);
        }
    }

    /**
     * [更新 某一筆日記帳資料]
     * 
     * @author cano.su
     * @since 2024/11/19
     * @param req 日記帳資料 API 請求
     * @return 已更新的日記帳紀錄
     */
    @PostMapping("/modify")
    public String modify(JournalRecordReqModify req) {
        try {
            final JournalRecord journalRecord = journalRecordService.modify(req);
            return Resp.ofSuccess(JsonJournalRecord.of(journalRecord));

        } catch (JournalRecordException e) {
            return Resp.ofModifyException(e);

        } catch (Exception e) {
            return Resp.ofException(e);
        }
    }

    /**
     * [移除 某一筆日記帳資料]
     * 
     * @author cano.su
     * @since 2024/11/20
     * @param key 某一筆日記帳資料流水號
     * @return 回應
     */
    @PostMapping("/remove")
    public String remove(JournalRecordReqRemove req) {
        try {
            journalRecordService.remove(req);
            return Resp.ofSuccess();

        } catch (JournalRecordException e) {
            return Resp.ofRemoveException(e);

        } catch (Exception e) {
            return Resp.ofException(e);
        }
    }

    /**
     * [查 全部日記帳資料]
     * 
     * @author cano.su
     * @since 2024/11/21
     * @return 回應
     */
    @PostMapping("/findAll")
    public String findAll() {
        try {
            final List<JournalRecord> journalRecords = journalRecordService.findAll();
            final List<JsonJournalRecord> jsonJournalRecords = journalRecords.stream()
                    .map(JsonJournalRecord::of)
                    .collect(Collectors.toList());
            return Resp.ofSuccess(jsonJournalRecords);

        } catch (Exception e) {
            return Resp.ofQueryException(e);
        }
    }

    /**
     * [查 某年月日記帳紀錄]
     * 
     * @author cano.su
     * @since 2024/11/21
     * @param req 日記帳資料 API 請求
     * @return 回應
     */
    @PostMapping("/findByYearMonth")
    public String findByYearMonth(JournalRecordReqYearMonth req) {
        try {
            final List<JournalRecord> journalRecords = journalRecordService.findByYearMonth(req);
            final List<JsonJournalRecord> jsonJournalRecords = journalRecords.stream()
                    .map(JsonJournalRecord::of)
                    .collect(Collectors.toList());
            return Resp.ofSuccess(jsonJournalRecords);

        } catch (Exception e) {
            return Resp.ofQueryException(e);
        }
    }

    /**
     * [查 日記帳年月資料]
     * 
     * @author cano.su
     * @since 2024/11/21
     * @return 回應
     */
    @PostMapping("/list/yearMonth")
    public String findYearMonthByJournalRecord() {
        try {
            final List<ViewJournalRecordYearMonth> viewJournalRecordYearMonths = journalRecordService.listYearMonth();
            final List<JsonViewJournalRecordYearMonth> jsonViewJournalRecordYearMonths = viewJournalRecordYearMonths
                    .stream()
                    .map(JsonViewJournalRecordYearMonth::of)
                    .collect(Collectors.toList());
            return Resp.ofSuccess(jsonViewJournalRecordYearMonths);

        } catch (Exception e) {
            return Resp.ofQueryException(e);
        }
    }

}
