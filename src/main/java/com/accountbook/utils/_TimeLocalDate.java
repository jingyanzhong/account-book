package com.accountbook.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * [時間] LocalDate
 * 
 * @author cano.su
 * @since 2024/08/18
 */
class _TimeLocalDate extends _Time<LocalDate> {

    @Override
    String format(LocalDate localDate) {
        Objects.requireNonNull(localDate, "待轉換本地日期 must not be null.");
        return localDate.format(FORMAT_DATETIMEFORMATTER_YYYYMMDD);
    }

    @Override
    LocalDate getFirstDay(LocalDate localDate) {
        Objects.requireNonNull(localDate, "待轉換本地日期 must not be null.");
        return localDate.with(TemporalAdjusters.firstDayOfMonth());
    }

    @Deprecated
    @Override
    LocalDate getFirstDayTime(LocalDate localDate) {
        throw new UnsupportedOperationException("LocalDate 不支援處理時間!!");
    }

    @Deprecated
    @Override
    LocalDate getFirstTime(LocalDate localDate) {
        throw new UnsupportedOperationException("LocalDate 不支援處理時間!!");
    }

    @Override
    LocalDate getLastDay(LocalDate localDate) {
        Objects.requireNonNull(localDate, "待轉換本地日期 must not be null.");
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }

    @Deprecated
    @Override
    LocalDate getLastDayTime(LocalDate localDate) {
        throw new UnsupportedOperationException("LocalDate 不支援處理時間!!");
    }

    @Deprecated
    @Override
    LocalDate getLastTime(LocalDate localDate) {
        throw new UnsupportedOperationException("LocalDate 不支援處理時間!!");
    }

    @Override
    Calendar toCalendar(LocalDate localDate) {
        Objects.requireNonNull(localDate, "待轉換本地日期 must not be null.");
        final ZoneId zoneId = ZoneId.systemDefault();
        final ZonedDateTime zonedDateTime = localDate.atStartOfDay(zoneId);
        final Instant instant = zonedDateTime.toInstant();
        final Date date = Date.from(instant);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    @Override
    Date toDate(LocalDate localDate) {
        Objects.requireNonNull(localDate, "待轉換本地日期 must not be null.");
        final ZoneId zoneId = ZoneId.systemDefault();
        final ZonedDateTime zonedDateTime = localDate.atStartOfDay(zoneId);
        final Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }

    @Deprecated
    @Override
    LocalDate toLocalDate(LocalDate localDate) {
        throw new UnsupportedOperationException("LocalDate 轉換 LocalDate 不需透過工具處理!!");
    }

    @Override
    LocalDateTime toLocalDateTime(LocalDate localDate) {
        Objects.requireNonNull(localDate, "待轉換本地日期 must not be null.");
        return localDate.atStartOfDay();
    }

    @Override
    YearMonth toYearMonth(LocalDate localDate) {
        Objects.requireNonNull(localDate, "待轉換本地日期 must not be null.");
        return YearMonth.from(localDate);
    }

    @Override
    Timestamp toTimeStamp(LocalDate time) {
        throw new UnsupportedOperationException("LocalDate 不支援處理時間!!");
    }

}
