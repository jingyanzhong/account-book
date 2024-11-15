package com.accountbook.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * [時間工具]
 * 
 * @author cano.su
 * @since 2022/06/18
 */
public class TimeUtils extends org.apache.commons.lang3.time.DateUtils {

    /**
     * [日期字串依照 pattern 轉換成 Calendar]
     * 
     * @author cano.su
     * @since 2022/06/18
     * @param dateStr 日期字串
     * @param pattern 日期模板
     */
    public static Calendar parseDateStrByPattern(String dateStr, TimePattern pattern) {
        try {
            return DateUtils.toCalendar(new SimpleDateFormat(pattern.get()).parse(dateStr));
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * [時間轉換]
     * 
     * @author cano.su
     * @since 2024/03/18
     * @param date 待轉換日期
     * @return
     */
    public static LocalDateTime parseLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault(); // 系統默認時區
        return LocalDateTime.ofInstant(instant, zoneId);
    }

    /**
     * [時間轉換]
     * 
     * @author cano.su
     * @since 2024/03/18
     * @param date 待轉換日期
     * @return
     */
    public static LocalDateTime parseLocalDateTime(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
    }

    /**
     * [時間轉換]
     * 
     * @author cano.su
     * @since 2024/05/04
     * @param datePattern 日期模版
     * @param date        待轉換日期
     * @return
     */
    public static LocalDateTime parseLocalDateTime(TimePattern datePattern, String timeStr) {
        return LocalDateTime.parse(timeStr, datePattern.getDateTimeFormatter());
    }

    /**
     * [時間轉換]
     * 
     * @author cano.su
     * @since 2024/03/28
     * @param localDateTime 待轉換日期
     * @return
     */
    public static Calendar parseCalendar(LocalDateTime localDateTime) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.set(localDateTime.getYear(), localDateTime.getMonthValue() - 1, localDateTime.getDayOfMonth(),
                localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond());
        return calendar;
    }

    /**
     * [時間轉換]
     * 
     * @author cano.su
     * @since 2024/03/28
     * @param localDateTime 待轉換日期
     * @return
     */
    public static Date parseDate(LocalDateTime localDateTime) {
        return parseCalendar(localDateTime).getTime();
    }

    /**
     * [取得差異時間文字]
     * 
     * @author cano.su
     * @since 2023/03/22
     * @param startTime 起始時間
     * @param endedTime 迄止時間
     * @return
     */
    public static String getDiffTimeStr(Calendar startTime, Calendar endedTime) {
        if (Objects.isNull(startTime)) {
            throw new NullArgumentException("起始時間");
        }
        if (Objects.isNull(endedTime)) {
            endedTime = Calendar.getInstance();
        }
        Duration diff = getDiffTime(startTime, endedTime);
        String[] symbol = { "PT| ", "H|時", "M|分", "S|秒" };
        String timeStr = diff.toString();
        for (int i = 0; i < symbol.length; i++) {
            String[] arr = symbol[i].split("\\|");
            timeStr = timeStr.replace(arr[0], arr[1]);
        }
        return timeStr;
    }

    /**
     * [取得差異時間]
     * 
     * @author cano.su
     * @since 2023/03/22
     * @param startTime 起始時間
     * @param endedTime 迄止時間
     * @return
     */
    public static Duration getDiffTime(Calendar startTime, Calendar endedTime) {
        if (Objects.isNull(startTime)) {
            throw new NullArgumentException("起始時間");
        }
        if (Objects.isNull(endedTime)) {
            endedTime = Calendar.getInstance();
        }
        return Duration.between(
                LocalDateTime.ofInstant(startTime.toInstant(), ZoneId.systemDefault()),
                LocalDateTime.ofInstant(endedTime.toInstant(), ZoneId.systemDefault()));
    }

    /**
     * [格式化日期]
     * 
     * @author cano.su
     * @since 2024/05/04
     * @param datePattern   日期模版
     * @param localDateTime 待轉換日期
     * @return
     */
    public static String format(TimePattern datePattern, LocalDateTime localDateTime) {
        if (localDateTime == null) {
            throw new NullArgumentException("待轉換日期");
        }
        return localDateTime.format(datePattern.getDateTimeFormatter());
    }

    public static final String PATTERN_HHMMSS = _Time.PATTERN_HHMMSS;
    public static final String PATTERN_YYYYMM = _Time.PATTERN_YYYYMM;
    public static final String PATTERN_YYYYMMDD = _Time.PATTERN_YYYYMMDD;
    public static final String PATTERN_YYYYMMDD_HHMMSS = _Time.PATTERN_YYYYMMDD_HHMMSS;

    public static final TimePattern DATEPATTERN_HHMMSS = _Time.DATEPATTERN_HHMMSS;
    public static final TimePattern DATEPATTERN_YYYYMM = _Time.DATEPATTERN_YYYYMM;
    public static final TimePattern DATEPATTERN_YYYYMMDD = _Time.DATEPATTERN_YYYYMMDD;
    public static final TimePattern DATEPATTERN_YYYYMMDD_HHMMSS = _Time.DATEPATTERN_YYYYMMDD_HHMMSS;

    public static final SimpleDateFormat FORMAT_SIMPLEDATEFORMAT_HHMMSS = _Time.FORMAT_SIMPLEDATEFORMAT_HHMMSS;
    public static final SimpleDateFormat FORMAT_SIMPLEDATEFORMAT_YYYYMM = _Time.FORMAT_SIMPLEDATEFORMAT_YYYYMM;
    public static final SimpleDateFormat FORMAT_SIMPLEDATEFORMAT_YYYYMMDD = _Time.FORMAT_SIMPLEDATEFORMAT_YYYYMMDD;
    public static final SimpleDateFormat FORMAT_SIMPLEDATEFORMAT_YYYYMMDD_HHMMSS = _Time.FORMAT_SIMPLEDATEFORMAT_YYYYMMDD_HHMMSS;

    public static final DateTimeFormatter FORMAT_DATETIMEFORMATTER_HHMMSS = _Time.FORMAT_DATETIMEFORMATTER_HHMMSS;
    public static final DateTimeFormatter FORMAT_DATETIMEFORMATTER_YYYYMM = _Time.FORMAT_DATETIMEFORMATTER_YYYYMM;
    public static final DateTimeFormatter FORMAT_DATETIMEFORMATTER_YYYYMMDD = _Time.FORMAT_DATETIMEFORMATTER_YYYYMMDD;
    public static final DateTimeFormatter FORMAT_DATETIMEFORMATTER_YYYYMMDD_HHMMSS = _Time.FORMAT_DATETIMEFORMATTER_YYYYMMDD_HHMMSS;

    private static final _TimeDate _timeDate = new _TimeDate();
    private static final _TimeCalendar _timeCalendar = new _TimeCalendar();
    private static final _TimeYearMonth _timeYearMonth = new _TimeYearMonth();
    private static final _TimeLocalDate _timeLocalDate = new _TimeLocalDate();
    private static final _TimeLocalDateTime _timeLocalDateTime = new _TimeLocalDateTime();

    /**
     * [轉換 起始日期時間] Calendar
     * 
     * <pre>
     * Calendar calendar = TimeUtils.getFirstTime(Calendar.getInstance()); // 2024-08-17 00:00:00
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/17
     * @param calendar 待轉換日曆
     * @return 日曆
     */
    public static Calendar getFirstDayTime(Calendar calendar) {
        Objects.requireNonNull(calendar, "待轉換日曆 must not be null.");
        return _timeCalendar.getFirstDayTime(calendar);
    }

    /**
     * [取得 起始日期時間] Date
     * 
     * <pre>
     * Date date = TimeUtils.getFirstTime(new Date()); // 2024-08-17 00:00:00
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/17
     * @param date 待轉換日期
     * @return 日期
     */
    public static Date getFirstDayTime(Date date) {
        Objects.requireNonNull(date, "待轉換日期 must not be null.");
        return _timeDate.getFirstDayTime(date);
    }

    /**
     * [轉換 起始日期時間] LocalDateTime
     * 
     * <pre>
     * LocalDateTime localDateTime = TimeUtils.getFirstTime(LocalDateTime.of(2024, 8, 17, 23, 42, 24)); // 2024-08-17 00:00:00
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/17
     * @param localDateTime 待轉換本地日期時間
     * @return 本地日期時間
     */
    public static LocalDateTime getFirstDayTime(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "待轉換本地日期時間 must not be null.");
        return _timeLocalDateTime.getFirstDayTime(localDateTime);
    }

    /**
     * [轉換 起始時間] Calendar
     * 
     * <pre>
     * Calendar calendar = TimeUtils.getFirstTime(Calendar.getInstance()); // 2024-08-17 00:00:00
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/17
     * @param calendar 待轉換日曆
     * @return 日曆
     */
    public static Calendar getFirstTime(Calendar calendar) {
        Objects.requireNonNull(calendar, "待轉換日曆 must not be null.");
        return _timeCalendar.getFirstTime(calendar);
    }

    /**
     * [取得 起始時間] Date
     * 
     * <pre>
     * Date date = TimeUtils.getFirstTime(new Date()); // 2024-08-17 00:00:00
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/17
     * @param date 待轉換日期
     * @return 日期
     */
    public static Date getFirstTime(Date date) {
        Objects.requireNonNull(date, "待轉換日期 must not be null.");
        return _timeDate.getFirstTime(date);
    }

    /**
     * [轉換 起始時間] LocalDateTime
     * 
     * <pre>
     * LocalDateTime localDateTime = TimeUtils.getFirstTime(LocalDateTime.of(2024, 8, 17, 23, 42, 24)); // 2024-08-17 00:00:00
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/17
     * @param localDateTime 待轉換本地日期時間
     * @return 本地日期時間
     */
    public static LocalDateTime getFirstTime(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "待轉換本地日期時間 must not be null.");
        return _timeLocalDateTime.getFirstTime(localDateTime);
    }

    /**
     * [轉換 迄止日期時間] Calendar
     * 
     * <pre>
     * Calendar calendar = TimeUtils.getLastTime(Calendar.getInstance()); // 2024-08-17 23:59:59
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/17
     * @param calendar 待轉換日曆
     * @return 日曆
     */
    public static Calendar getLastDayTime(Calendar calendar) {
        Objects.requireNonNull(calendar, "待轉換日曆 must not be null.");
        return _timeCalendar.getLastDayTime(calendar);
    }

    /**
     * [轉換 迄止日期時間] Date
     * 
     * <pre>
     * Date date = TimeUtils.getLastTime(new Date()); // 2024-08-17 23:59:59
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/17
     * @param date 待轉換日期
     * @return 日期
     */
    public static Date getLastDayTime(Date date) {
        Objects.requireNonNull(date, "待轉換日期 must not be null.");
        return _timeDate.getLastDayTime(date);
    }

    /**
     * [轉換 迄止日期時間] LocalDateTime
     * 
     * <pre>
     * LocalDateTime localDateTime = TimeUtils.getLastTime(LocalDateTime.of(2024, 8, 17, 23, 42, 24)); // 2024-08-17 23:59:59
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/17
     * @param localDateTime 待轉換本地日期時間
     * @return 本地日期時間
     */
    public static LocalDateTime getLastDayTime(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "待轉換本地日期時間 must not be null.");
        return _timeLocalDateTime.getLastDayTime(localDateTime);
    }

    /**
     * [轉換 迄止時間] Calendar
     * 
     * <pre>
     * Calendar calendar = TimeUtils.getLastTime(Calendar.getInstance()); // 2024-08-17 23:59:59
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/17
     * @param calendar 待轉換日曆
     * @return 日曆
     */
    public static Calendar getLastTime(Calendar calendar) {
        Objects.requireNonNull(calendar, "待轉換日曆 must not be null.");
        return _timeCalendar.getLastTime(calendar);
    }

    /**
     * [轉換 迄止時間] Date
     * 
     * <pre>
     * Date date = TimeUtils.getLastTime(new Date()); // 2024-08-17 23:59:59
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/17
     * @param date 待轉換日期
     * @return 日期
     */
    public static Date getLastTime(Date date) {
        Objects.requireNonNull(date, "待轉換日期 must not be null.");
        return _timeDate.getLastTime(date);
    }

    /**
     * [轉換 迄止時間] LocalDateTime
     * 
     * <pre>
     * LocalDateTime localDateTime = TimeUtils.getLastTime(LocalDateTime.of(2024, 8, 17, 23, 42, 24)); // 2024-08-17 23:59:59
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/17
     * @param localDateTime 待轉換本地日期時間
     * @return 本地日期時間
     */
    public static LocalDateTime getLastTime(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "待轉換本地日期時間 must not be null.");
        return _timeLocalDateTime.getLastTime(localDateTime);
    }

    /**
     * [轉換 Calendar]
     * 
     * <pre>
     * Calendar calendar = TimeUtils.toCalendar(new Date()); // 2024-08-16 21:53:12
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/16
     * @param date 待轉換日期
     * @return 日曆
     */
    public static Calendar toCalendar(Date date) {
        Objects.requireNonNull(date, "待轉換日期 must not be null.");
        return _timeDate.toCalendar(date);
    }

    /**
     * [轉換 Calendar]
     * 
     * <pre>
     * Calendar calendar = TimeUtils.toCalendar(LocalDate.of(2024, 8, 16)); // 2024-08-16 00:00:00
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/16
     * @param localDate 待轉換本地日期
     * @return 日曆
     */
    public static Calendar toCalendar(LocalDate localDate) {
        Objects.requireNonNull(localDate, "待轉換本地日期 must not be null.");
        return _timeLocalDate.toCalendar(localDate);
    }

    /**
     * [轉換 Calendar]
     * 
     * <pre>
     * Calendar calendar = TimeUtils.toCalendar(LocalDateTime.of(2024, 8, 16, 21, 53, 12)); // 2024-08-16 21:53:12
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/16
     * @param localDateTime 待轉換本地日期時間
     * @return 日曆
     */
    public static Calendar toCalendar(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "待轉換本地日期時間 must not be null.");
        return _timeLocalDateTime.toCalendar(localDateTime);
    }

    /**
     * [轉換 Calendar]
     * 
     * <pre>
     * Calendar calendar = TimeUtils.toCalendar(YearMonth.of(2024, 8)); // 2024-08-01 00:00:00
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/16
     * @param yearMonth 待轉換年月
     * @return 日曆
     */
    public static Calendar toCalendar(YearMonth yearMonth) {
        Objects.requireNonNull(yearMonth, "待轉換年月 must not be null.");
        return _timeYearMonth.toCalendar(yearMonth);
    }

    /**
     * [轉換 Date]
     * 
     * <pre>
     * Date date = TimeUtils.toDate(Calendar.getInstance()); // 2024-08-16 21:53:12
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/16
     * @param calendar 待轉換日曆
     * @return 日期
     */
    public static Date toDate(Calendar calendar) {
        Objects.requireNonNull(calendar, "待轉換日曆 must not be null.");
        return _timeCalendar.toDate(calendar);
    }

    /**
     * [轉換 Date]
     * 
     * <pre>
     * Date date = TimeUtils.toDate(LocalDate.of(2024, 8, 16)); // 2024-08-16 00:00:00
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/16
     * @param localDate 待轉換本地日期
     * @return 日期
     */
    public static Date toDate(LocalDate localDate) {
        Objects.requireNonNull(localDate, "待轉換本地日期 must not be null.");
        return _timeLocalDate.toDate(localDate);
    }

    /**
     * [轉換 Date]
     * 
     * <pre>
     * Date date = TimeUtils.toDate(LocalDateTime.of(2024, 8, 16, 21, 53, 12)); // 2024-08-16 21:53:12
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/16
     * @param localDateTime 待轉換本地日期時間
     * @return 日期
     */
    public static Date toDate(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "待轉換本地日期時間 must not be null.");
        return _timeLocalDateTime.toDate(localDateTime);
    }

    /**
     * [轉換 Date]
     * 
     * <pre>
     * Date date = TimeUtils.toDate(YearMonth.of(2024, 8)); // 2024-08-01 00:00:00
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/16
     * @param yearMonth 待轉換年月
     * @return 日期
     */
    public static Date toDate(YearMonth yearMonth) {
        Objects.requireNonNull(yearMonth, "待轉換年月 must not be null.");
        return _timeYearMonth.toDate(yearMonth);
    }

    /**
     * [轉換 LocalDate]
     * 
     * <pre>
     * LocalDate localDate = TimeUtils.toLocalDate(Calendar.getInstance()); // 2024-08-16
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/16
     * @param calendar 待轉換日曆
     * @return 本地日期
     */
    public static LocalDate toLocalDate(Calendar calendar) {
        Objects.requireNonNull(calendar, "待轉換日曆 must not be null.");
        return _timeCalendar.toLocalDate(calendar);
    }

    /**
     * [轉換 LocalDate]
     * 
     * <pre>
     * LocalDate localDate = TimeUtils.toLocalDate(new Date()); // 2024-08-16
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/16
     * @param date 待轉換日期
     * @return 本地日期
     */
    public static LocalDate toLocalDate(Date date) {
        Objects.requireNonNull(date, "待轉換日期 must not be null.");
        return _timeDate.toLocalDate(date);
    }

    /**
     * [轉換 LocalDate]
     * 
     * <pre>
     * LocalDate localDate = TimeUtils.toLocalDate(LocalDateTime.of(2024, 8, 16, 21, 53, 12)); // 2024-08-16
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/16
     * @param localDateTime 待轉換本地日期時間
     * @return 本地日期
     */
    public static LocalDate toLocalDate(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "待轉換本地日期時間 must not be null.");
        return _timeLocalDateTime.toLocalDate(localDateTime);
    }

    /**
     * [轉換 LocalDate]
     * 
     * <pre>
     * LocalDate localDate = TimeUtils.toLocalDate(YearMonth.of(2024, 8)); // 2024-08-01
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/16
     * @param yearMonth 待轉換年月
     * @return 本地日期
     */
    public static LocalDate toLocalDate(YearMonth yearMonth) {
        Objects.requireNonNull(yearMonth, "待轉換年月 must not be null.");
        return _timeYearMonth.toLocalDate(yearMonth);
    }

    /**
     * [轉換 LocalDateTime]
     * 
     * <pre>
     * LocalDateTime localDateTime = TimeUtils.toLocalDateTime(Calendar.getInstance()); // 2024-08-16 21:53:12
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/16
     * @param calendar 待轉換日曆
     * @return 本地日期時間
     */
    public static LocalDateTime toLocalDateTime(Calendar calendar) {
        Objects.requireNonNull(calendar, "待轉換日曆 must not be null.");
        return _timeCalendar.toLocalDateTime(calendar);
    }

    /**
     * [轉換 LocalDateTime]
     * 
     * <pre>
     * LocalDateTime localDateTime = TimeUtils.toLocalDateTime(new Date()); // 2024-08-16 21:53:12
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/16
     * @param date 待轉換日期
     * @return 本地日期時間
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        Objects.requireNonNull(date, "待轉換日期 must not be null.");
        return _timeDate.toLocalDateTime(date);
    }

    /**
     * [轉換 LocalDateTime]
     * 
     * <pre>
     * LocalDateTime localDateTime = TimeUtils.toLocalDateTime(LocalDate.of(2024, 8, 16)); // 2024-08-16 00:00:00
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/16
     * @param localDate 待轉換本地日期
     * @return 本地日期時間
     */
    public static LocalDateTime toLocalDateTime(LocalDate localDate) {
        Objects.requireNonNull(localDate, "待轉換本地日期 must not be null.");
        return _timeLocalDate.toLocalDateTime(localDate);
    }

    /**
     * [轉換 LocalDateTime]
     * 
     * <pre>
     * LocalDateTime localDateTime = TimeUtils.toLocalDateTime(YearMonth.of(2024, 8)); // 2024-08-01 00:00:00
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/16
     * @param yearMonth 待轉換年月
     * @return 本地日期時間
     */
    public static LocalDateTime toLocalDateTime(YearMonth yearMonth) {
        Objects.requireNonNull(yearMonth, "待轉換年月 must not be null.");
        return _timeYearMonth.toLocalDateTime(yearMonth);
    }

    /**
     * [轉換 Timestamp]
     * 
     * <pre>
     * Timestamp timestamp = TimeUtils.toTimestamp(new Date()); // 1723909347000
     * </pre>
     * 
     * @author cano.su
     * @since 2024/09/02
     * @param date 待轉換日期
     * @return 時間戳
     */
    public static Timestamp toTimestamp(Date date) {
        Objects.requireNonNull(date, "待轉換日期 must not be null.");
        return _timeDate.toTimeStamp(date);
    }

    /**
     * [轉換 Timestamp]
     * 
     * <pre>
     * Timestamp timestamp = TimeUtils.toTimestamp(Calendar.getInstance()); // 1723909347000
     * </pre>
     * 
     * @author cano.su
     * @since 2024/09/02
     * @param calendar 待轉換日曆
     * @return 時間戳
     */
    public static Timestamp toTimestamp(Calendar calendar) {
        Objects.requireNonNull(calendar, "待轉換日曆 must not be null.");
        return _timeCalendar.toTimeStamp(calendar);
    }

    /**
     * [轉換 Date]
     * 
     * <pre>
     * Timestamp timestamp = TimeUtils.toTimestamp(LocalDateTime.of(2024, 8, 16, 21, 53, 12)); // 1723909347000
     * </pre>
     * 
     * @author cano.su
     * @since 2024/09/02
     * @param localDateTime 待轉換本地日期時間
     * @return 時間戳
     */
    public static Timestamp toTimestamp(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "待轉換本地日期時間 must not be null.");
        return _timeLocalDateTime.toTimeStamp(localDateTime);
    }

    /**
     * [轉換 YearMonth]
     * 
     * <pre>
     * YearMonth yearMonth = TimeUtils.toYearMonth(Calendar.getInstance()); // 2024-08
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/16
     * @param calendar 待轉換日曆
     * @return 年月
     */
    public static YearMonth toYearMonth(Calendar calendar) {
        Objects.requireNonNull(calendar, "待轉換日曆 must not be null.");
        return _timeCalendar.toYearMonth(calendar);
    }

    /**
     * [轉換 YearMonth]
     * 
     * <pre>
     * YearMonth yearMonth = TimeUtils.toYearMonth(new Date()); // 2024-08
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/16
     * @param date 待轉換日期
     * @return 年月
     */
    public static YearMonth toYearMonth(Date date) {
        Objects.requireNonNull(date, "待轉換日期 must not be null.");
        return _timeDate.toYearMonth(date);
    }

    /**
     * [轉換 YearMonth]
     * 
     * <pre>
     * YearMonth yearMonth = TimeUtils.toYearMonth(LocalDate.of(2024, 8, 16)); // 2024-08
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/16
     * @param localDate 待轉換本地日期
     * @return 年月
     */
    public static YearMonth toYearMonth(LocalDate localDate) {
        Objects.requireNonNull(localDate, "待轉換本地日期 must not be null.");
        return _timeLocalDate.toYearMonth(localDate);
    }

    /**
     * [轉換 YearMonth]
     * 
     * <pre>
     * YearMonth yearMonth = TimeUtils.toYearMonth(LocalDateTime.of(2024, 8, 16, 21, 53, 12)); // 2024-08
     * </pre>
     * 
     * @author cano.su
     * @since 2024/08/16
     * @param localDateTime 待轉換本地日期時間
     * @return 年月
     */
    public static YearMonth toYearMonth(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "待轉換本地日期時間 must not be null.");
        return _timeLocalDateTime.toYearMonth(localDateTime);
    }

    @Test
    public void test() throws ParseException {

        final boolean isLog = false;

        final String PATTERN_FORMAT_yyyyMM = _Time.PATTERN_YYYYMM;
        final DateTimeFormatter dtf_yyyyMM = DateTimeFormatter.ofPattern(PATTERN_FORMAT_yyyyMM);
        final String PATTERN_FORMAT_yyyyMMdd = _Time.PATTERN_YYYYMMDD;
        final DateTimeFormatter dtf_yyyyMMdd = DateTimeFormatter.ofPattern(PATTERN_FORMAT_yyyyMMdd);
        final String PATTERN_FORMAT_yyyyMMdd_HHmmss = _Time.PATTERN_YYYYMMDD_HHMMSS;
        final SimpleDateFormat sdf_yyyyMMdd_HHmmss = new SimpleDateFormat(PATTERN_FORMAT_yyyyMMdd_HHmmss);
        final DateTimeFormatter dtf_yyyyMMdd_HHmmss = DateTimeFormatter.ofPattern(PATTERN_FORMAT_yyyyMMdd_HHmmss);

        final String year = "2024";
        final String month = "08";
        final String day = "17";
        final String hour = "23";
        final String min = "42";
        final String sec = "27";
        final int yearValue = Integer.valueOf(year);
        final int monthValue = Integer.valueOf(month);
        final int dayValue = Integer.valueOf(day);
        final int hourValue = Integer.valueOf(hour);
        final int minValue = Integer.valueOf(min);
        final int secValue = Integer.valueOf(sec);
        final String sampleTime = String.join("-", year, month, day) + " " + String.join(":", hour, min, sec);
        final Date date = sdf_yyyyMMdd_HHmmss.parse(sampleTime);
        final YearMonth yearMonth = YearMonth.of(yearValue, monthValue);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        final LocalDate localDate = LocalDate.of(2024, monthValue, dayValue);
        final LocalDateTime localDateTime = LocalDateTime.of(yearValue, monthValue, dayValue, hourValue, minValue, secValue);

        Date calendarToDate = toDate(calendar);
        Date yearMonthToDate = toDate(yearMonth);
        Date localDateToDate = toDate(localDate);
        Date localDateTimeToDate = toDate(localDateTime);
        Calendar dateToCalendar = toCalendar(date);
        Calendar yearMonthToCalendar = toCalendar(yearMonth);
        Calendar localDateToCalendar = toCalendar(localDate);
        Calendar localDateTimeToCalendar = toCalendar(localDateTime);
        YearMonth dateToYearMonth = toYearMonth(date);
        YearMonth calendarToYearMonth = toYearMonth(calendar);
        YearMonth localDateToYearMonth = toYearMonth(localDate);
        YearMonth localDateTimeToYearMonth = toYearMonth(localDateTime);
        LocalDate dateToLocalDate = toLocalDate(date);
        LocalDate calendarToLocalDate = toLocalDate(calendar);
        LocalDate yearMonthToLocalDate = toLocalDate(yearMonth);
        LocalDate localDateTimeToLocalDate = toLocalDate(localDateTime);
        LocalDateTime dateToLocalDateTime = toLocalDateTime(date);
        LocalDateTime calendarToLocalDateTime = toLocalDateTime(calendar);
        LocalDateTime yearMonthToLocalDateTime = toLocalDateTime(yearMonth);
        LocalDateTime localDateToLocalDateTime = toLocalDateTime(localDate);
        Timestamp dateToTimestamp = toTimestamp(date);
        Timestamp calendarToTimestamp = toTimestamp(calendar);
        Timestamp localDateToTimestamp = toTimestamp(localDateTime);

        Date dateGetFirstDayTime = getFirstDayTime(date);
        Calendar calendarGetFirstDayTime = getFirstDayTime(calendar);
        LocalDateTime localDateTimeGetFirstDayTime = getFirstDayTime(localDateTime);
        Date dateGetFirstTime = getFirstTime(date);
        Calendar calendarGetFirstTime = getFirstTime(calendar);
        LocalDateTime localDateTimeGetFirstTime = getFirstTime(localDateTime);

        Date dateGetLastDayTime = getLastDayTime(date);
        Calendar calendarGetLastDayTime = getLastDayTime(calendar);
        LocalDateTime localDateTimeGetLastDayTime = getLastDayTime(localDateTime);
        Date dateGetLastTime = getLastTime(date);
        Calendar calendarGetLastTime = getLastTime(calendar);
        LocalDateTime localDateTimeGetLastTime = getLastTime(localDateTime);

        if (isLog) {
            System.out.println("     Calendar 轉 Date         : " + /* 2024-08-17 23:42:27 */ sdf_yyyyMMdd_HHmmss.format(calendarToDate));
            System.out.println("    YearMonth 轉 Date         : " + /* 2024-08-01 00:00:00 */ sdf_yyyyMMdd_HHmmss.format(yearMonthToDate));
            System.out.println("    LocalDate 轉 Date         : " + /* 2024-08-17 00:00:00 */ sdf_yyyyMMdd_HHmmss.format(localDateToDate));
            System.out.println("LocalDateTime 轉 Date         : " + /* 2024-08-17 23:42:27 */ sdf_yyyyMMdd_HHmmss.format(localDateTimeToDate));
            System.out.println("         Date 轉 Calendar     : " + /* 2024-08-17 23:42:27 */ sdf_yyyyMMdd_HHmmss.format(dateToCalendar.getTime()));
            System.out.println("    YearMonth 轉 Calendar     : " + /* 2024-08-01 00:00:00 */ sdf_yyyyMMdd_HHmmss.format(yearMonthToCalendar.getTime()));
            System.out.println("    LocalDate 轉 Calendar     : " + /* 2024-08-17 00:00:00 */ sdf_yyyyMMdd_HHmmss.format(localDateToCalendar.getTime()));
            System.out.println("LocalDateTime 轉 Calendar     : " + /* 2024-08-17 23:42:27 */ sdf_yyyyMMdd_HHmmss.format(localDateTimeToCalendar.getTime()));
            System.out.println("         Date 轉 YearMonth    : " + /* 2024-08 */ dateToYearMonth.format(dtf_yyyyMM));
            System.out.println("     Calendar 轉 YearMonth    : " + /* 2024-08 */ calendarToYearMonth.format(dtf_yyyyMM));
            System.out.println("    LocalDate 轉 YearMonth    : " + /* 2024-08 */ localDateToYearMonth.format(dtf_yyyyMM));
            System.out.println("LocalDateTime 轉 YearMonth    : " + /* 2024-08 */ localDateTimeToYearMonth.format(dtf_yyyyMM));
            System.out.println("         Date 轉 LocalDate    : " + /* 2024-08-17 */ dateToLocalDate.format(dtf_yyyyMMdd));
            System.out.println("     Calendar 轉 LocalDate    : " + /* 2024-08-17 */ calendarToLocalDate.format(dtf_yyyyMMdd));
            System.out.println("    YearMonth 轉 LocalDate    : " + /* 2024-08-01 */ yearMonthToLocalDate.format(dtf_yyyyMMdd));
            System.out.println("LocalDateTime 轉 LocalDate    : " + /* 2024-08-17 */ localDateTimeToLocalDate.format(dtf_yyyyMMdd));
            System.out.println("         Date 轉 LocalDateTime: " + /* 2024-08-17 23:42:27 */ dateToLocalDateTime.format(dtf_yyyyMMdd_HHmmss));
            System.out.println("     Calendar 轉 LocalDateTime: " + /* 2024-08-17 23:42:27 */ calendarToLocalDateTime.format(dtf_yyyyMMdd_HHmmss));
            System.out.println("    YearMonth 轉 LocalDateTime: " + /* 2024-08-01 00:00:00 */ yearMonthToLocalDateTime.format(dtf_yyyyMMdd_HHmmss));
            System.out.println("    LocalDate 轉 LocalDateTime: " + /* 2024-08-17 00:00:00 */ localDateToLocalDateTime.format(dtf_yyyyMMdd_HHmmss));
            System.out.println("         Date 轉 Timestramp   : " + /* 2024-08-17 23:42:27 */ sdf_yyyyMMdd_HHmmss.format(dateToTimestamp));
            System.out.println("     Calendar 轉 Timestramp   : " + /* 2024-08-17 23:42:27 */ sdf_yyyyMMdd_HHmmss.format(calendarToTimestamp));
            System.out.println("    LocalDate 轉 Timestramp   : " + /* 2024-08-17 23:42:27 */ sdf_yyyyMMdd_HHmmss.format(localDateToTimestamp));

            System.out.println("         Date 取得 起始日期時間 : " + /* 2024-08-01 00:00:00 */ sdf_yyyyMMdd_HHmmss.format(dateGetFirstDayTime));
            System.out.println("     Calendar 取得 起始日期時間 : " + /* 2024-08-01 00:00:00 */ sdf_yyyyMMdd_HHmmss.format(calendarGetFirstDayTime.getTime()));
            System.out.println("LocalDateTime 取得 起始日期時間 : " + /* 2024-08-01 00:00:00 */ localDateTimeGetFirstDayTime.format(dtf_yyyyMMdd_HHmmss));
            System.out.println("         Date 取得 起始時間　　 : " + /* 2024-08-17 00:00:00 */ sdf_yyyyMMdd_HHmmss.format(dateGetFirstTime));
            System.out.println("     Calendar 取得 起始時間　　 : " + /* 2024-08-17 00:00:00 */ sdf_yyyyMMdd_HHmmss.format(calendarGetFirstTime.getTime()));
            System.out.println("LocalDateTime 取得 起始時間　　 : " + /* 2024-08-17 00:00:00 */ localDateTimeGetFirstTime.format(dtf_yyyyMMdd_HHmmss));

            System.out.println("         Date 取得 迄止日期時間 : " + /* 2024-08-31 23:59:59 */ sdf_yyyyMMdd_HHmmss.format(dateGetLastDayTime));
            System.out.println("     Calendar 取得 迄止日期時間 : " + /* 2024-08-31 23:59:59 */ sdf_yyyyMMdd_HHmmss.format(calendarGetLastDayTime.getTime()));
            System.out.println("LocalDateTime 取得 迄止日期時間 : " + /* 2024-08-31 23:59:59 */ localDateTimeGetLastDayTime.format(dtf_yyyyMMdd_HHmmss));
            System.out.println("         Date 取得 迄止時間　　 : " + /* 2024-08-17 23:59:59 */ sdf_yyyyMMdd_HHmmss.format(dateGetLastTime));
            System.out.println("     Calendar 取得 迄止時間　　 : " + /* 2024-08-17 23:59:59 */ sdf_yyyyMMdd_HHmmss.format(calendarGetLastTime.getTime()));
            System.out.println("LocalDateTime 取得 迄止時間　　 : " + /* 2024-08-17 23:59:59 */ localDateTimeGetLastTime.format(dtf_yyyyMMdd_HHmmss));
        }

        final String validFirstDay = String.join("-", year, month, "01");
        final String validCurrentDay = String.join("-", year, month, day);
        final String validCurrentDayTime = sampleTime;
        final String validCurrentMonth = String.join("-", year, month);
        final String validFirstDayTime = String.join("-", year, month, "01") + " 00:00:00";
        final String validFirstTime = String.join("-", year, month, day) + " 00:00:00";
        final String validLastDayTime = String.join("-", year, month, "31") + " 23:59:59";
        final String validLastTime = String.join("-", year, month, day) + " 23:59:59";
        final String validTimestamp = sampleTime;

        Assert.assertEquals(validCurrentDayTime, sdf_yyyyMMdd_HHmmss.format(calendarToDate));
        Assert.assertEquals(validFirstDayTime, sdf_yyyyMMdd_HHmmss.format(yearMonthToDate));
        Assert.assertEquals(validFirstTime, sdf_yyyyMMdd_HHmmss.format(localDateToDate));
        Assert.assertEquals(validCurrentDayTime, sdf_yyyyMMdd_HHmmss.format(localDateTimeToDate));
        Assert.assertEquals(validCurrentDayTime, sdf_yyyyMMdd_HHmmss.format(dateToCalendar.getTime()));
        Assert.assertEquals(validFirstDayTime, sdf_yyyyMMdd_HHmmss.format(yearMonthToCalendar.getTime()));
        Assert.assertEquals(validFirstTime, sdf_yyyyMMdd_HHmmss.format(localDateToCalendar.getTime()));
        Assert.assertEquals(sampleTime, sdf_yyyyMMdd_HHmmss.format(localDateTimeToCalendar.getTime()));
        Assert.assertEquals(validCurrentMonth, dateToYearMonth.format(dtf_yyyyMM));
        Assert.assertEquals(validCurrentMonth, calendarToYearMonth.format(dtf_yyyyMM));
        Assert.assertEquals(validCurrentMonth, localDateToYearMonth.format(dtf_yyyyMM));
        Assert.assertEquals(validCurrentMonth, localDateTimeToYearMonth.format(dtf_yyyyMM));
        Assert.assertEquals(validCurrentDay, dateToLocalDate.format(dtf_yyyyMMdd));
        Assert.assertEquals(validCurrentDay, calendarToLocalDate.format(dtf_yyyyMMdd));
        Assert.assertEquals(validFirstDay, yearMonthToLocalDate.format(dtf_yyyyMMdd));
        Assert.assertEquals(validCurrentDay, localDateTimeToLocalDate.format(dtf_yyyyMMdd));
        Assert.assertEquals(sampleTime, dateToLocalDateTime.format(dtf_yyyyMMdd_HHmmss));
        Assert.assertEquals(sampleTime, calendarToLocalDateTime.format(dtf_yyyyMMdd_HHmmss));
        Assert.assertEquals(validFirstDayTime, yearMonthToLocalDateTime.format(dtf_yyyyMMdd_HHmmss));
        Assert.assertEquals(validFirstTime, localDateToLocalDateTime.format(dtf_yyyyMMdd_HHmmss));
        Assert.assertEquals(validTimestamp, sdf_yyyyMMdd_HHmmss.format(dateToTimestamp));
        Assert.assertEquals(validTimestamp, sdf_yyyyMMdd_HHmmss.format(calendarToTimestamp));
        Assert.assertEquals(validTimestamp, sdf_yyyyMMdd_HHmmss.format(localDateToTimestamp));

        Assert.assertEquals(validFirstDayTime, sdf_yyyyMMdd_HHmmss.format(dateGetFirstDayTime));
        Assert.assertEquals(validFirstDayTime, sdf_yyyyMMdd_HHmmss.format(calendarGetFirstDayTime.getTime()));
        Assert.assertEquals(validFirstDayTime, localDateTimeGetFirstDayTime.format(dtf_yyyyMMdd_HHmmss));
        Assert.assertEquals(validFirstTime, sdf_yyyyMMdd_HHmmss.format(dateGetFirstTime));
        Assert.assertEquals(validFirstTime, sdf_yyyyMMdd_HHmmss.format(calendarGetFirstTime.getTime()));
        Assert.assertEquals(validFirstTime, localDateTimeGetFirstTime.format(dtf_yyyyMMdd_HHmmss));

        Assert.assertEquals(validLastDayTime, sdf_yyyyMMdd_HHmmss.format(dateGetLastDayTime));
        Assert.assertEquals(validLastDayTime, sdf_yyyyMMdd_HHmmss.format(calendarGetLastDayTime.getTime()));
        Assert.assertEquals(validLastDayTime, localDateTimeGetLastDayTime.format(dtf_yyyyMMdd_HHmmss));
        Assert.assertEquals(validLastTime, sdf_yyyyMMdd_HHmmss.format(dateGetLastTime));
        Assert.assertEquals(validLastTime, sdf_yyyyMMdd_HHmmss.format(calendarGetLastTime.getTime()));
        Assert.assertEquals(validLastTime, localDateTimeGetLastTime.format(dtf_yyyyMMdd_HHmmss));

    }

}
