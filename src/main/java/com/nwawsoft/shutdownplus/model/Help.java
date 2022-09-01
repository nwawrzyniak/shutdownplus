package com.nwawsoft.shutdownplus.model;

import com.nwawsoft.shutdownplus.SDPConstants;

public class Help {
    public static void printHelp() {
        System.out.println("ShutDownPlus " + SDPConstants.VERSION + "\n" +
                "\n" +
                "  Syntax:\n" +
                "  sdp [duration]         shuts down in [duration].\n" +
                "  sdp -t [time]          shuts down at [time].\n" +
                "\n" +
                "  duration format: (dd:(hh:(mm:[ss])))\n" +
                "  time     format: ([hh:mm]:ss)");

        // ToDo: Add examples
    }
}
