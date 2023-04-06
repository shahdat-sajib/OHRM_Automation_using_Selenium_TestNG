package org.shahdat.log;

import org.shahdat.reporter.ExtentManager;

public class Logger {
    public static void logstep(String msg ){
        System.out.println(msg);
        ExtentManager.logStep(msg);
    }
}
