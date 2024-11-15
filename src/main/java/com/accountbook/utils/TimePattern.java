package com.accountbook.utils;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

/**
 * [時間模版]
 * 
 * @author cano.su
 * @since 2022/06/18
 */
public class TimePattern {

    /** 時間模版 */
    private String pattern;

    private TimePattern(String pattern) {
        super();
        this.pattern = pattern;
    }

    public static TimePattern of(String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            throw new IllegalArgumentException("pattern 不可為空!!");
        }
        return new TimePattern(pattern);
    }

    /**
     * [取得 SimpleDateFormat]
     * 
     * @author cano.su
     * @since 2024/08/16
     * @return
     */
    public SimpleDateFormat getSimpleDateFormat() {
        return new SimpleDateFormat(this.pattern);
    }

    /**
     * [取得 DateTimeFormatter]
     * 
     * @author cano.su
     * @since 2024/05/04
     * @return
     */
    public DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(this.pattern);
    }

    public String get() {
        return pattern;
    }

}
