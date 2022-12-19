package utils;

import org.apache.log4j.*;

public class Log {
    //understand the scope
    //if my test case start
    //if my test case end
    //if any message I need in between

    public static Logger log = Logger.getLogger(Log.class.getName());
    public static void startTestCase(String testCaseName){
        log.info("**");
        log.info("**");
        log.info("############   " + testCaseName + " ################");
        log.info("**");
        log.info("**");
    }

    public static void endTestCase(String testCaseName){
        log.info("##################################");
        log.info("##########################################");
        log.info("############   " + testCaseName + " ################");
        log.info("##########################");
        log.info("##########################################");
    }

    //to print some text in between my code
    public static void info(String message){
        log.info(message);
    }

    public static void warning(String warning){
        log.info(warning);
    }


}
