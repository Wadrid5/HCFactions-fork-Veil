package net.bfcode.bfhcf.utils;

import java.text.DecimalFormat;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

import net.minecraft.util.org.apache.commons.lang3.time.FastDateFormat;

public class DateTimeFormats
{
    public static TimeZone SERVER_TIME_ZONE;
    public static ZoneId SERVER_ZONE_ID;
    public static FastDateFormat DAY_MTH_HR_MIN;
    public static FastDateFormat DAY_MTH_HR_MIN_SECS;
    public static FastDateFormat DAY_MTH_YR_HR_MIN_AMPM;
    public static FastDateFormat DAY_MTH_HR_MIN_AMPM;
    public static FastDateFormat HR_MIN_AMPM;
    public static FastDateFormat HR_MIN_AMPM_TIMEZONE;
    public static FastDateFormat HR_MIN;
    public static FastDateFormat MIN_SECS;
    public static FastDateFormat KOTH_FORMAT;
    public static FastDateFormat PALACE_FORMAT;
    public static ThreadLocal<DecimalFormat> REMAINING_SECONDS;
    public static ThreadLocal<DecimalFormat> REMAINING_SECONDS_TRAILING;
    
    static {
        SERVER_TIME_ZONE = TimeZone.getTimeZone("EST");
        SERVER_ZONE_ID = DateTimeFormats.SERVER_TIME_ZONE.toZoneId();
        DAY_MTH_HR_MIN = FastDateFormat.getInstance("dd/MM HH:mm", DateTimeFormats.SERVER_TIME_ZONE, Locale.ENGLISH);
        DAY_MTH_HR_MIN_SECS = FastDateFormat.getInstance("dd/MM HH:mm:ss", DateTimeFormats.SERVER_TIME_ZONE, Locale.ENGLISH);
        DAY_MTH_YR_HR_MIN_AMPM = FastDateFormat.getInstance("dd/MM/yy hh:mma", DateTimeFormats.SERVER_TIME_ZONE, Locale.ENGLISH);
        DAY_MTH_HR_MIN_AMPM = FastDateFormat.getInstance("dd/MM hh:mma", DateTimeFormats.SERVER_TIME_ZONE, Locale.ENGLISH);
        HR_MIN_AMPM = FastDateFormat.getInstance("hh:mma", DateTimeFormats.SERVER_TIME_ZONE, Locale.ENGLISH);
        HR_MIN_AMPM_TIMEZONE = FastDateFormat.getInstance("hh:mma z", DateTimeFormats.SERVER_TIME_ZONE, Locale.ENGLISH);
        HR_MIN = FastDateFormat.getInstance("hh:mm", DateTimeFormats.SERVER_TIME_ZONE, Locale.ENGLISH);
        MIN_SECS = FastDateFormat.getInstance("mm:ss", DateTimeFormats.SERVER_TIME_ZONE, Locale.ENGLISH);
        KOTH_FORMAT = FastDateFormat.getInstance("m:ss", DateTimeFormats.SERVER_TIME_ZONE, Locale.ENGLISH);
        PALACE_FORMAT = FastDateFormat.getInstance("m:ss", DateTimeFormats.SERVER_TIME_ZONE, Locale.ENGLISH);
        REMAINING_SECONDS = new ThreadLocal<DecimalFormat>() {
            @Override
            protected DecimalFormat initialValue() {
                return new DecimalFormat("0.#");
            }
        };
        REMAINING_SECONDS_TRAILING = ThreadLocal.withInitial(() -> new DecimalFormat("0.0"));
    }
}
