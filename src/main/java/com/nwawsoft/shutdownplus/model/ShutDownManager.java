package com.nwawsoft.shutdownplus.model;

import com.nwawsoft.util.ShellHandler;

public class ShutDownManager {
    private static ShutDownManager shutDownManagerInstance;

    private ShutDownManager() {}

    public static ShutDownManager getInstance() {
        if (shutDownManagerInstance == null) {
            shutDownManagerInstance = new ShutDownManager();
        }
        return shutDownManagerInstance;
    }

    public void addShutDown(final ShutDownMode shutDownMode, final String shutDownString) {
        long duration;
        switch (shutDownMode) {
            case DURATION:
                duration = TimeCalc.getSecondsFromDurationString(shutDownString);
                ShellHandler.spawnShutDownProcess(duration);
                break;
            case TIME:
                duration = TimeCalc.getSecondsFromTimeString(shutDownString);
                ShellHandler.spawnShutDownProcess(duration);
                break;
            default:
                throw new IllegalArgumentException("shutDownMode must be either DURATION or TIME.");
        }
    }

    public void cancelShutDown() {
        ShellHandler.cancelShutDownProcess();
    }
}
