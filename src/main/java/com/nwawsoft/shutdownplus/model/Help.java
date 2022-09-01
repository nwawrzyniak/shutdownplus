package com.nwawsoft.shutdownplus.model;

import com.nwawsoft.shutdownplus.SDPConstants;

public class Help {
    public static void printHelp() {
        System.out.println("ShutDownPlus " + SDPConstants.VERSION + "\n" +
                "\n" +
                "  Syntax:\n" +
                "    sdp [duration]     shuts down in [duration].\n" +
                "    sdp t [time]       shuts down at [time].\n" +
                "    sdp c              cancels the shutdown.\n" +
                "\n" +
                "  duration format:   (dd:(hh:(mm:[ss])))\n" +
                "  time     format:   ([hh:mm]:ss)\n" +
                "\n" +
                "  Examples:\n" +
                "    sdp 60             turns off machine in 60 seconds\n" +
                "    sdp 180            turns off machine in 3 minutes\n" +
                "    sdp 03:00          turns off machine in 3 minutes\n" +
                "    sdp 20:00          turns off machine in 20 minutes\n" +
                "    sdp 1:00:00        turns off machine in 1 hour\n" +
                "    sdp 1:12:00:00     turns off machine in 1.5 days\n" +
                "    sdp t 01:30        turns off machine at 01:30 (a.m.)\n" +
                "    sdp t 01:30:00     turns off machine at 01:30 (a.m.)\n" +
                "    sdp t 22:30        turns off machine at 22:30 aka 10:30 p.m.\n" +
                "    sdp t 22:30:30     turns off machine at 22:30 and 30 seconds\n" +
                "\n" +
                "  Leading zeros don't matter, so you could write\n" +
                "    sdp 03:00 as sdp 3:0.");
    }
}
