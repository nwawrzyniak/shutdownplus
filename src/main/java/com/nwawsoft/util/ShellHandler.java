package com.nwawsoft.util;

import java.io.IOException;
import java.util.concurrent.Executors;

/**
 * https://www.baeldung.com/run-shell-command-in-java
 */
public class ShellHandler {
    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().startsWith("windows");
    }

    public static int spawnShutDownProcess(final long seconds) {
        int exitCode = 0;
        ProcessBuilder builder = new ProcessBuilder();
        if (isWindows()) {
            builder.command("cmd.exe", "/c", "shutdown", "/s", "/t", "" + seconds);
        } else {
            builder.command("sudo", "sh", "-c", "sleep " + seconds + "; shutdown -h now &"); // ToDo: Test
        }
        Process process;
        try {
            process = builder.start();
        } catch (final IOException e) {
            e.printStackTrace();
            return -1;
        }
        StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
        Executors.newSingleThreadExecutor().submit(streamGobbler);
        try {
            exitCode = process.waitFor();
        } catch (final InterruptedException e) {
            e.printStackTrace();
            return -2;
        }
        process.destroy();
        return exitCode;
    }

    public static void cancelShutDownProcess() {
        ProcessBuilder builder = new ProcessBuilder();
        if (isWindows()) {
            builder.command("cmd.exe", "/c", "shutdown", "/a");
        } else {
            builder.command("sudo", "sh", "-c", "shutdown -c");
        }
        Process process;
        try {
            process = builder.start();
        } catch (final IOException e) {
            e.printStackTrace();
            return;
        }
        StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
        Executors.newSingleThreadExecutor().submit(streamGobbler);
        try {
            process.waitFor();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        process.destroy();
    }
}
