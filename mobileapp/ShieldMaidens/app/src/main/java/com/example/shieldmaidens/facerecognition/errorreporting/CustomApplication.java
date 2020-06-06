package com.example.shieldmaidens.facerecognition.errorreporting;

import android.app.Application;
import android.content.Intent;

public class CustomApplication extends Application {

    static volatile boolean wasErrorActivityStarted = false;
    static final boolean enableCustomErrorMessage = false;
    Thread.UncaughtExceptionHandler exceptionHandler;

    @Override
    public void onCreate ()
    {
        super.onCreate();
        exceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        // Setup handler for uncaught exceptions.
        Thread.setDefaultUncaughtExceptionHandler (new Thread.UncaughtExceptionHandler()
        {
            @Override
            public void uncaughtException (Thread thread, Throwable e)
            {
                handleUncaughtException (thread, e);
            }
        });
    }

    public void handleUncaughtException (Thread thread, Throwable e)
    {
        if (!wasErrorActivityStarted && enableCustomErrorMessage) {
            Intent intent = new Intent();
            intent.setAction("com.affectiva.REPORT_ERROR"); // see step 5.
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // required when starting from Application
            intent.putExtra("affdexme_error", e);
            startActivity(intent);
            wasErrorActivityStarted = true;
        }

        exceptionHandler.uncaughtException(thread,e);
    }
}
