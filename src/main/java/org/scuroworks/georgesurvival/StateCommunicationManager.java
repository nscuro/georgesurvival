package org.scuroworks.georgesurvival;

public class StateCommunicationManager {

    private static boolean mRestartRequested = false;
    private static boolean mInputPaused = false;

    public static boolean GetRestartRequested() {
        return mRestartRequested;
    }

    public static boolean GetInputPaused() {
        return mInputPaused;
    }

    public static void SetRestartRequested(boolean restart) {
        mRestartRequested = restart;
    }

    public static void SetInputPaused(boolean paused) {
        mInputPaused = paused;
    }

}
