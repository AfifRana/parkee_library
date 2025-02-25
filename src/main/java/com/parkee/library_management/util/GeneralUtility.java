package com.parkee.library_management.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author Afif Rana M
 * on 25/02/2025
 */
public class GeneralUtility {
    public static boolean isMoreThan30DaysFromNow(LocalDate dateToCheck) {
        LocalDate today = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(today, dateToCheck);
        return daysBetween > 30;
    }
}
