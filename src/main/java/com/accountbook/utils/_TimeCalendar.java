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
 * [時間] Calendar
 * 
 * @author cano.su
 * @since 2024/08/18
 */
class _TimeCalendar extends _Time<Calendar> {

    @Override
    String format(Calendar calendar) {
        Objects.requireNonNull(calendar, "待轉換日曆 must not be null.");
        return FORMAT_SIMPLEDATEFORMAT_YYYYMMDD_HHMMSS.format(calendar.getTime());
    }

    @Deprecated
    @Override
    Calendar getFirstDay(Calendar calendar) {
        throw new UnsupportedOperationException("Calendar 不支援只轉換起始日期!!");
    }

    @Override
    Calendar getFirstDayTime(Calendar calendar) {
        Objects.requireNonNull(calendar, "待轉換日曆 must not be null.");
        final Date startOfDay = DateUtils.truncate(calendar.getTime(), Calendar.DAY_OF_MONTH);
        final Calendar newCalendar = Calendar.getInstance();
        newCalendar.setTime(startOfDay);
        newCalendar.set(Calendar.DAY_OF_MONTH, 1);
        return newCalendar;
    }

    @Override
    Calendar getFirstTime(Calendar calendar) {
        Objects.requireNonNull(calendar, "待轉換日曆 must not be null.");
        return DateUtils.truncate(calendar, Calendar.DAY_OF_MONTH);
    }

    @Deprecated
    @Override
    Calendar getLastDay(Calendar calendar) {
        throw new UnsupportedOperationException("Calendar 不支援只轉換迄止日期!!");
    }

    @Override
    Calendar getLastDayTime(Calendar calendar) {
        Objects.requireNonNull(calendar, "待轉換日曆 must not be null.");
        final Date startOfDay = DateUtils.truncate(calendar.getTime(), Calendar.DAY_OF_MONTH);
        final Calendar newCalendar = Calendar.getInstance();
        newCalendar.setTime(startOfDay);
        newCalendar.set(Calendar.DAY_OF_MONTH, 1);
        newCalendar.add(Calendar.MONTH, 1);
        newCalendar.add(Calendar.SECOND, -1);
        return newCalendar;
    }

    @Override
    Calendar getLastTime(Calendar calendar) {
        Objects.requireNonNull(calendar, "待轉換日曆 must not be null.");
        final Date startOfDay = DateUtils.truncate(calendar.getTime(), Calendar.DAY_OF_MONTH);
        final Calendar newCalendar = Calendar.getInstance();
        newCalendar.setTime(startOfDay);
        newCalendar.add(Calendar.DAY_OF_MONTH, 1);
        newCalendar.add(Calendar.SECOND, -1);
        return newCalendar;
    }

    @Deprecated
    @Override
    Calendar toCalendar(Calendar calendar) {
        throw new UnsupportedOperationException("Calendar 轉換 Calendar 不需透過工具處理!!");
    }

    @Override
    Date toDate(Calendar calendar) {
        Objects.requireNonNull(calendar, "待轉換日曆 must not be null.");
        return calendar.getTime();
    }

    @Override
    LocalDate toLocalDate(Calendar calendar) {
        Objects.requireNonNull(calendar, "待轉換日曆 must not be null.");
        final Instant instant = calendar.toInstant();
        final ZoneId zoneId = ZoneId.systemDefault();
        final ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        return zonedDateTime.toLocalDate();
    }

    @Override
    LocalDateTime toLocalDateTime(Calendar calendar) {
        Objects.requireNonNull(calendar, "待轉換日曆 must not be null.");
        final Instant instant = calendar.toInstant();
        final ZoneId zoneId = ZoneId.systemDefault();
        final ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        return zonedDateTime.toLocalDateTime();
    }

    @Override
    YearMonth toYearMonth(Calendar calendar) {
        Objects.requireNonNull(calendar, "待轉換日曆 must not be null.");
        final Instant instant = calendar.toInstant();
        final ZoneId zoneId = ZoneId.systemDefault();
        final ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        final LocalDate localDate = zonedDateTime.toLocalDate();
        return YearMonth.from(localDate);
    }

    @Override
    Timestamp toTimeStamp(Calendar calendar) {
        Objects.requireNonNull(calendar, "待轉換日曆 must not be null.");
        final Instant instant = calendar.toInstant();
        return Timestamp.from(instant);
    }

}
