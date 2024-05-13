package com.haduc.go_travel_system.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class VnPayTimeHelper {
    public static String getCurrentTimeInVnPayFormat() {
        // get time in server
        LocalDateTime currentTime = LocalDateTime.now(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return formatter.format(currentTime);
    }

    public static String getExpireTimeInVnPayFormat(int minutesToAdd) {
        LocalDateTime currentTime = LocalDateTime.now(ZoneId.systemDefault());

        LocalDateTime expireTime = currentTime.plusMinutes(minutesToAdd);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        return formatter.format(expireTime);
    }
}
