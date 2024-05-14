package com.haduc.go_travel_system.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class VnPayTimeHelper {
    public static LocalDateTime getCurrentTimeInVnPayFormat() {
        // get time in server
        return LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
    }

    public static LocalDateTime getExpireTimeInVnPayFormat(LocalDateTime createdDate, int minutesToAdd) {
        createdDate.atZone(ZoneId.of("Asia/Ho_Chi_Minh"))
                .withZoneSameInstant(ZoneId.of("Asia/Singapore"));
        return createdDate.plusMinutes(minutesToAdd);
    }
}
