package com.accountbook.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * [時間] YearMonth
 * 
 * @author cano.su
 * @since 2024/08/18
 */
class _TimeYearMonth extends _Time<YearMonth> {

    @Override
    String format(YearMonth yearMonth) {
        Objects.requireNonNull(yearMonth, "待轉換年月 must not be null.");
        return yearMonth.format(FORMAT_DATETIMEFORMATTER_YYYYMM);
    }

    @Deprecated
    @Override
    YearMonth getFirstDay(YearMonth yearMonth) {
        throw new UnsupportedOperationException("YearMonth 不支援處理日期!!");
    }

    @Deprecated
    @Override
    YearMonth getFirstDayTime(YearMonth yearMonth) {
        throw new UnsupportedOperationException("YearMonth 不支援處理日期時間!!");
    }

    @Deprecated
    @Override
    YearMonth getFirstTime(YearMonth yearMonth) {
        throw new UnsupportedOperationException("YearMonth 不支援處理時間!!");
    }

    @Deprecated
    @Override
    YearMonth getLastDay(YearMonth yearMonth) {
        throw new UnsupportedOperationException("YearMonth 不支援處理日期!!");
    }

    @Deprecated
    @Override
    YearMonth getLastDayTime(YearMonth yearMonth) {
        throw new UnsupportedOperationException("YearMonth 不支援處理日期時間!!");
    }

    @Deprecated
    @Override
    YearMonth getLastTime(YearMonth yearMonth) {
        throw new UnsupportedOperationException("YearMonth 不支援處理時間!!");
    }

    @Override
    Calendar toCalendar(YearMonth yearMonth) {
        Objects.requireNonNull(yearMonth, "待轉換年月 must not be null.");
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        final ZoneId zoneId = ZoneId.systemDefault();
        final ZonedDateTime zonedDateTime = firstDayOfMonth.atStartOfDay(zoneId);
        final Instant instant = zonedDateTime.toInstant();
        final Date date = Date.from(instant);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    @Override
    Date toDate(YearMonth yearMonth) {
        Objects.requireNonNull(yearMonth, "待轉換年月 must not be null.");
        final LocalDate firstDayOfMonth = yearMonth.atDay(1);
        final LocalDateTime firstDayOfMonthatStart = firstDayOfMonth.atStartOfDay();
        final ZoneId zoneId = ZoneId.systemDefault();
        final ZonedDateTime zonedDateTime = firstDayOfMonthatStart.atZone(zoneId);
        final Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }

    @Override
    LocalDate toLocalDate(YearMonth yearMonth) {
        Objects.requireNonNull(yearMonth, "待轉換年月 must not be null.");
        return yearMonth.atDay(1);
    }

    @Override
    LocalDateTime toLocalDateTime(YearMonth yearMonth) {
        Objects.requireNonNull(yearMonth, "待轉換年月 must not be null.");
        final LocalDate firstDayOfMonth = yearMonth.atDay(1);
        return firstDayOfMonth.atStartOfDay();
    }

    @Deprecated
    @Override
    YearMonth toYearMonth(YearMonth yearMonth) {
        throw new UnsupportedOperationException("YearMonth 轉換 YearMonth 不需透過工具處理!!");
    }

    @Override
    Timestamp toTimeStamp(YearMonth yearMonth) {
        throw new UnsupportedOperationException("YearMonth 不支援處理日期時間!!");
    }

}
