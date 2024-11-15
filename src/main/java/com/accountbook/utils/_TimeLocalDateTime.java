package com.accountbook.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * [時間] LocalDateTime
 * 
 * @author cano.su
 * @since 2024/08/18
 */
class _TimeLocalDateTime extends _Time<LocalDateTime> {

    @Override
    String format(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "待轉換本地日期時間 must not be null.");
        return localDateTime.format(FORMAT_DATETIMEFORMATTER_YYYYMMDD_HHMMSS);
    }

    @Override
    LocalDateTime getFirstDay(LocalDateTime localDateTime) {
        throw new UnsupportedOperationException("LocalDateTime 不支援只轉換起始日期!!");
    }

    @Override
    LocalDateTime getFirstDayTime(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "待轉換本地日期時間 must not be null.");
        final LocalDateTime firstDayOfMonth = localDateTime.with(TemporalAdjusters.firstDayOfMonth());
        final LocalDate truncateTime = firstDayOfMonth.toLocalDate();
        return truncateTime.atStartOfDay();
    }

    @Override
    LocalDateTime getFirstTime(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "待轉換本地日期時間 must not be null.");
        final LocalDate truncateTime = localDateTime.toLocalDate();
        return truncateTime.atStartOfDay();
    }

    @Override
    LocalDateTime getLastDay(LocalDateTime localDateTime) {
        throw new UnsupportedOperationException("LocalDateTime 不支援只轉換迄止日期!!");
    }

    @Override
    LocalDateTime getLastDayTime(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "待轉換本地日期時間 must not be null.");
        return localDateTime
                .with(TemporalAdjusters.lastDayOfMonth())
                .with(LocalTime.MAX);
    }

    @Override
    LocalDateTime getLastTime(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "待轉換本地日期時間 must not be null.");
        return localDateTime.with(LocalTime.MAX);
    }

    @Override
    Calendar toCalendar(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "待轉換本地日期時間 must not be null.");
        final ZoneId zoneId = ZoneId.systemDefault();
        final ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        final Instant instant = zonedDateTime.toInstant();
        final Date date = Date.from(instant);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    @Override
    Date toDate(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "待轉換本地日期時間 must not be null.");
        final ZoneId zoneId = ZoneId.systemDefault();
        final ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        final Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }

    @Override
    YearMonth toYearMonth(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "待轉換本地日期時間 must not be null.");
        return YearMonth.from(localDateTime);
    }

    @Override
    LocalDate toLocalDate(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "待轉換本地日期時間 must not be null.");
        return localDateTime.toLocalDate();
    }

    @Deprecated
    @Override
    LocalDateTime toLocalDateTime(LocalDateTime localDateTime) {
        throw new UnsupportedOperationException("LocalDateTime 轉換 LocalDateTime 不需透過工具處理!!");
    }

    @Override
    Timestamp toTimeStamp(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "待轉換本地日期時間 must not be null.");
        final ZoneId zoneId = ZoneId.systemDefault();
        final ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        final Instant instant = zonedDateTime.toInstant();
        return Timestamp.from(instant);
    }

}
