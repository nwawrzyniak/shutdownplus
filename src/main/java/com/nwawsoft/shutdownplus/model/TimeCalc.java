package com.nwawsoft.shutdownplus.model;

import java.time.OffsetTime;

public class TimeCalc {
    public static long getSecondsFromDurationString(final String durationString) {
        long durationSeconds;
        String[] durationSegments = durationString.split(":");
        if (durationSegments.length == 1) {
            durationSeconds = Long.parseLong(durationSegments[0]);
        } else if (durationSegments.length == 2) {
            durationSeconds = Long.parseLong(durationSegments[0]) * 60;
            durationSeconds += Long.parseLong(durationSegments[1]);
        } else if (durationSegments.length == 3) {
            durationSeconds = Long.parseLong(durationSegments[0]) * 3600;
            durationSeconds += Long.parseLong(durationSegments[1]) * 60;
            durationSeconds += Long.parseLong(durationSegments[2]);
        } else if (durationSegments.length == 4) {
            durationSeconds = Long.parseLong(durationSegments[0]) * 86400;
            durationSeconds += Long.parseLong(durationSegments[1]) * 3600;
            durationSeconds += Long.parseLong(durationSegments[2]) * 60;
            durationSeconds += Long.parseLong(durationSegments[3]);
        } else {
            throw new IllegalArgumentException("Unknown format of durationString: " + durationString);
        }
        return durationSeconds;
    }

    public static long getSecondsFromTimeString(final String timeString) {
        long timeSeconds;
        OffsetTime ot = OffsetTime.now();
        int hourNow = ot.getHour();
        int minuteNow = ot.getMinute();
        int secondNow = ot.getSecond();
        String[] timeSegmentStrings = timeString.split(":");
        int length = timeSegmentStrings.length;
        int[] timeSegments = new int[length];
        for (int i = 0; i < length; i++) {
            timeSegments[i] = Integer.parseInt(timeSegmentStrings[i]);
        }
        long hoursUntilShutDown = timeSegments[0] - hourNow;
        long minutesUntilShutDown = timeSegments[1] - minuteNow;
        long secondsUntilShutDown;
        if (length == 3) {
            secondsUntilShutDown = timeSegments[2] - secondNow;
        } else {
            secondsUntilShutDown = 0;
        }
        if (length == 2 || length == 3) {
            timeSeconds = hoursUntilShutDown * 3600 + minutesUntilShutDown * 60 + secondsUntilShutDown;
        } else {
            throw new IllegalArgumentException("Unknown format of durationString: " + timeString);
        }
        if (timeSeconds < 0) {
            timeSeconds += 86400;
        }
        return timeSeconds;
    }

    public static boolean isDurationString(final String input) {
        String[] values = input.split(":");
        switch (values.length) {
            case 1: {
                long seconds = Long.parseLong(values[0]);
                String reConvertSeconds = "" + seconds;
                String reConvertSecondsLeadingZero = "0" + seconds;
                return reConvertSeconds.equals(values[0]) ||
                        reConvertSecondsLeadingZero.equals(values[0]);
            }
            case 2: {
                long minutes = Long.parseLong(values[0]);
                String reConvertMinutes = "" + minutes;
                String reConvertMinutesLeadingZero = "0" + minutes;
                long seconds = Long.parseLong(values[1]);
                String reConvertSeconds = "" + seconds;
                String reConvertSecondsLeadingZero = "0" + seconds;
                return (reConvertMinutes.equals(values[0]) || reConvertMinutesLeadingZero.equals(values[0])) &&
                        (reConvertSeconds.equals(values[1]) || reConvertSecondsLeadingZero.equals(values[1])) &&
                        minutes >= 0 &&
                        minutes <= 59 &&
                        seconds >= 0 &&
                        seconds <= 59;
            }
            case 3: {
                long hours = Long.parseLong(values[0]);
                String reConvertHours = "" + hours;
                String reConvertHoursLeadingZero = "0" + hours;
                long minutes = Long.parseLong(values[1]);
                String reConvertMinutes = "" + minutes;
                String reConvertMinutesLeadingZero = "0" + minutes;
                long seconds = Long.parseLong(values[2]);
                String reConvertSeconds = "" + seconds;
                String reConvertSecondsLeadingZero = "0" + seconds;
                return (reConvertHours.equals(values[0]) || reConvertHoursLeadingZero.equals(values[0])) &&
                        (reConvertMinutes.equals(values[1]) || reConvertMinutesLeadingZero.equals(values[1])) &&
                        (reConvertSeconds.equals(values[2]) || reConvertSecondsLeadingZero.equals(values[2])) &&
                        hours >= 0 &&
                        hours <= 23 &&
                        minutes >= 0 &&
                        minutes <= 59 &&
                        seconds >= 0 &&
                        seconds <= 59;
            }
            case 4: {
                long days = Long.parseLong(values[0]);
                String reConvertDays = "" + days;
                String reConvertDaysLeadingZero = "0" + days;
                long hours = Long.parseLong(values[1]);
                String reConvertHours = "" + hours;
                String reConvertHoursLeadingZero = "0" + hours;
                long minutes = Long.parseLong(values[2]);
                String reConvertMinutes = "" + minutes;
                String reConvertMinutesLeadingZero = "0" + minutes;
                long seconds = Long.parseLong(values[3]);
                String reConvertSeconds = "" + seconds;
                String reConvertSecondsLeadingZero = "0" + seconds;
                return (reConvertDays.equals(values[0]) || reConvertDaysLeadingZero.equals(values[0])) &&
                        (reConvertHours.equals(values[1]) || reConvertHoursLeadingZero.equals(values[1])) &&
                        (reConvertMinutes.equals(values[2]) || reConvertMinutesLeadingZero.equals(values[2])) &&
                        (reConvertSeconds.equals(values[3]) || reConvertSecondsLeadingZero.equals(values[3])) &&
                        days >= 0 &&
                        hours >= 0 &&
                        hours <= 23 &&
                        minutes >= 0 &&
                        minutes <= 59 &&
                        seconds >= 0 &&
                        seconds <= 59;
            }
            default:
                return false;
        }
    }

    public static boolean isTimeString(final String input) {
        String[] values = input.split(":");
        if (values.length == 2) {
            long hours = Long.parseLong(values[0]);
            String reConvertHours = "" + hours;
            String reConvertHoursLeadingZero = "0" + hours;
            long minutes = Long.parseLong(values[1]);
            String reConvertMinutes = "" + minutes;
            String reConvertMinutesLeadingZero = "0" + minutes;
            return (reConvertHours.equals(values[0]) || reConvertHoursLeadingZero.equals(values[0])) &&
                    (reConvertMinutes.equals(values[1]) || reConvertMinutesLeadingZero.equals(values[1])) &&
                    hours >= 0 &&
                    hours <= 23 &&
                    minutes >= 0 &&
                    minutes <= 59;
        } else if (values.length == 3) {
            long hours = Long.parseLong(values[0]);
            String reConvertHours = "" + hours;
            String reConvertHoursLeadingZero = "0" + hours;
            long minutes = Long.parseLong(values[1]);
            String reConvertMinutes = "" + minutes;
            String reConvertMinutesLeadingZero = "0" + minutes;
            long seconds = Long.parseLong(values[2]);
            String reConvertSeconds = "" + seconds;
            String reConvertSecondsLeadingZero = "0" + seconds;
            return (reConvertHours.equals(values[0]) || reConvertHoursLeadingZero.equals(values[0])) &&
                    (reConvertMinutes.equals(values[1]) || reConvertMinutesLeadingZero.equals(values[1])) &&
                    (reConvertSeconds.equals(values[2]) || reConvertSecondsLeadingZero.equals(values[2])) &&
                    hours >= 0 &&
                    hours <= 23 &&
                    minutes >= 0 &&
                    minutes <= 59 &&
                    seconds >= 0 &&
                    seconds <= 59;
        }
        return false;
    }
}
