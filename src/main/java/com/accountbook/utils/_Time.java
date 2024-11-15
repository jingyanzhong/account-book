package com.accountbook.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * [時間]
 * 
 * @author cano.su
 * @since 2024/08/18
 */
abstract class _Time<TIME> {

    public static final String PATTERN_HHMMSS = "HH:mm:ss";
    public static final String PATTERN_YYYYMM = "yyyy-MM";
    public static final String PATTERN_YYYYMMDD = "yyyy-MM-dd";
    public static final String PATTERN_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

    public static final TimePattern DATEPATTERN_HHMMSS = TimePattern.of(PATTERN_HHMMSS);
    public static final TimePattern DATEPATTERN_YYYYMM = TimePattern.of(PATTERN_YYYYMM);
    public static final TimePattern DATEPATTERN_YYYYMMDD = TimePattern.of(PATTERN_YYYYMMDD);
    public static final TimePattern DATEPATTERN_YYYYMMDD_HHMMSS = TimePattern.of(PATTERN_YYYYMMDD_HHMMSS);

    public static final SimpleDateFormat FORMAT_SIMPLEDATEFORMAT_HHMMSS = DATEPATTERN_HHMMSS.getSimpleDateFormat();
    public static final SimpleDateFormat FORMAT_SIMPLEDATEFORMAT_YYYYMM = DATEPATTERN_YYYYMM.getSimpleDateFormat();
    public static final SimpleDateFormat FORMAT_SIMPLEDATEFORMAT_YYYYMMDD = DATEPATTERN_YYYYMMDD.getSimpleDateFormat();
    public static final SimpleDateFormat FORMAT_SIMPLEDATEFORMAT_YYYYMMDD_HHMMSS = DATEPATTERN_YYYYMMDD_HHMMSS.getSimpleDateFormat();

    public static final DateTimeFormatter FORMAT_DATETIMEFORMATTER_HHMMSS = DATEPATTERN_HHMMSS.getDateTimeFormatter();
    public static final DateTimeFormatter FORMAT_DATETIMEFORMATTER_YYYYMM = DATEPATTERN_YYYYMM.getDateTimeFormatter();
    public static final DateTimeFormatter FORMAT_DATETIMEFORMATTER_YYYYMMDD = DATEPATTERN_YYYYMMDD.getDateTimeFormatter();
    public static final DateTimeFormatter FORMAT_DATETIMEFORMATTER_YYYYMMDD_HHMMSS = DATEPATTERN_YYYYMMDD_HHMMSS.getDateTimeFormatter();

    abstract String format(TIME time);

    abstract TIME getFirstDay(TIME time);

    abstract TIME getFirstDayTime(TIME time);

    abstract TIME getFirstTime(TIME time);

    abstract TIME getLastDay(TIME time);

    abstract TIME getLastDayTime(TIME time);

    abstract TIME getLastTime(TIME time);

    abstract Calendar toCalendar(TIME time);

    abstract Date toDate(TIME time);

    abstract LocalDate toLocalDate(TIME time);

    abstract LocalDateTime toLocalDateTime(TIME time);

    abstract YearMonth toYearMonth(TIME time);

    abstract Timestamp toTimeStamp(TIME time);
}
