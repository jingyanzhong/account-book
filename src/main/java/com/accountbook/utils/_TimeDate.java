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

import org.apache.commons.lang3.time.DateUtils;

/**
 * [時間] Date
 * 
 * @author cano.su
 * @since 2024/08/18
 */
class _TimeDate extends _Time<Date> {

    @Override
    String format(Date date) {
        Objects.requireNonNull(date, "待轉換日期 must not be null.");
        return FORMAT_SIMPLEDATEFORMAT_YYYYMMDD_HHMMSS.format(date);
    }

    @Deprecated
    @Override
    Date getFirstDay(Date time) {
        throw new UnsupportedOperationException("Date 不支援只轉換起始日期!!");
    }

    @Override
    Date getFirstDayTime(Date date) {
        Objects.requireNonNull(date, "待轉換日期 must not be null.");
        final Date startOfDay = DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(startOfDay);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    @Override
    Date getFirstTime(Date date) {
        Objects.requireNonNull(date, "待轉換日期 must not be null.");
        return DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
    }

    @Deprecated
    @Override
    Date getLastDay(Date date) {
        throw new UnsupportedOperationException("Date 不支援只轉換迄止日期!!");
    }

    @Override
    Date getLastDayTime(Date date) {
        Objects.requireNonNull(date, "待轉換日期 must not be null.");
        final Date startOfDay = DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(startOfDay);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.SECOND, -1);
        return calendar.getTime();
    }

    @Override
    Date getLastTime(Date date) {
        Objects.requireNonNull(date, "待轉換日期 must not be null.");
        final Date startOfDay = DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(startOfDay);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.SECOND, -1);
        return calendar.getTime();
    }

    @Override
    Calendar toCalendar(Date date) {
        Objects.requireNonNull(date, "待轉換日期 must not be null.");
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    @Deprecated
    @Override
    Date toDate(Date time) {
        throw new UnsupportedOperationException("Date 轉換 Date 不需透過工具處理!!");
    }

    @Override
    LocalDate toLocalDate(Date date) {
        Objects.requireNonNull(date, "待轉換日期 must not be null.");
        final Instant instant = date.toInstant();
        final ZoneId zoneId = ZoneId.systemDefault();
        final ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        return zonedDateTime.toLocalDate();
    }

    @Override
    LocalDateTime toLocalDateTime(Date date) {
        Objects.requireNonNull(date, "待轉換日期 must not be null.");
        final Instant instant = date.toInstant();
        final ZoneId zoneId = ZoneId.systemDefault();
        final ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        return zonedDateTime.toLocalDateTime();
    }

    @Override
    YearMonth toYearMonth(Date date) {
        Objects.requireNonNull(date, "待轉換日期 must not be null.");
        final Instant instant = date.toInstant();
        final ZoneId zoneId = ZoneId.systemDefault();
        final ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        final LocalDate localDate = zonedDateTime.toLocalDate();
        return YearMonth.from(localDate);
    }

    @Override
    Timestamp toTimeStamp(Date date) {
        Objects.requireNonNull(date, "待轉換日期 must not be null.");
        final Instant instant = date.toInstant();
        return Timestamp.from(instant);
    }

}
