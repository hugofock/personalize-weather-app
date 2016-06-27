package com.pwa.common.util;

import org.joda.time.DateTime;

import java.math.BigInteger;

public class ConverterUtil {

    public static String toJodaDateTime(final String unixTimeStamp) {
        return new DateTime(Long.valueOf(unixTimeStamp) * 1000L).toString("yyyy-MM-dd HH:mm:ss");
    }

    public static String fromMeterPerSecondToKiloMeterPerHour(final String meterPerSecond) {
        return String.valueOf(Math.round((Double.valueOf(meterPerSecond) * 3.6) * 100.0) / 100.0);
    }
}
