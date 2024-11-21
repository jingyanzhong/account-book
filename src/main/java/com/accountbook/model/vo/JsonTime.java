package com.accountbook.model.vo;

import java.time.LocalDateTime;
import java.util.Objects;

import com.accountbook.utils.TimeUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * [JSON]
 * 
 * @author cano.su
 * @since 2024/11/15
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JsonTime {

    /** 時間 */
    private LocalDateTime time;

    public JsonTime() {
        super();
    }

    private JsonTime(LocalDateTime time) {
        super();
        this.time = time;
    }

    public static JsonTime of(LocalDateTime time) {
        Objects.requireNonNull(time, "時間 must not be null.");
        return new JsonTime(time);
    }

    public String getDate() {
        return time.format(TimeUtils.FORMAT_DATETIMEFORMATTER_YYYYMMDD);
    }

    public String getTimePoint() {
        return time.format(TimeUtils.FORMAT_DATETIMEFORMATTER_HHMMSS);
    }

}
