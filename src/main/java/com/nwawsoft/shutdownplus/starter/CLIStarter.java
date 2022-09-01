package com.nwawsoft.shutdownplus.starter;

import com.nwawsoft.shutdownplus.SDPConstants;
import com.nwawsoft.shutdownplus.model.*;
import com.nwawsoft.util.*;

public class CLIStarter {
    public static void main(final String[] args) {
        if (args.length >= 1) {
            ShutDownManager sdm = ShutDownManager.getInstance();
            if (StringArrayFunctions.contains(SDPConstants.DURATION_CODES, args[0].toLowerCase())) {
                if (TimeCalc.isDurationString(args[1])) {
                    sdm.addShutDown(ShutDownMode.DURATION, args[1]);
                } else {
                    System.err.println("Invalid duration string format: " + args[1]);
                }
            } else if (StringArrayFunctions.contains(SDPConstants.TIME_CODES, args[0].toLowerCase())) {
                if (TimeCalc.isTimeString(args[1])) {
                    sdm.addShutDown(ShutDownMode.TIME, args[1]);
                } else {
                    System.err.println("Invalid time string format: " + args[1]);
                }
            } else if (StringArrayFunctions.contains(SDPConstants.HELP_CODES, args[0].toLowerCase())) {
                Help.printHelp();
            } else if (StringArrayFunctions.contains(SDPConstants.CANCEL_CODES, args[0].toLowerCase())) {
                sdm.cancelShutDown();
            } else if (TimeCalc.isDurationString(args[0])) {
                sdm.addShutDown(ShutDownMode.DURATION, args[0]);
            } else {
                System.err.println("Invalid duration string format: " + args[0]);
            }
        } else {
            Help.printHelp();
        }
        System.exit(0);
    }
}
