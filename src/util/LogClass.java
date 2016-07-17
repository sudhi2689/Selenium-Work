package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class LogClass {

private static Logger log =  Logger.getLogger(LogClass.class);
private static boolean initializationFlag = false;
private static String fileName;

private static void intializeLogger(){
    log.setLevel(Level.DEBUG);

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();

    RollingFileAppender appender = new RollingFileAppender();
    appender.setAppend(true);
    appender.setMaxFileSize("1MB");
    appender.setMaxBackupIndex(1);
    String fileName = System.getProperty("user.dir")+"\\Application_log" + "_" + dateFormat.format(date) + ".log";
    System.out.println("fileName: "+fileName);
    appender.setFile("E:\\Eclipse_Selenium\\TestNGTest\\Application_log" + "_" + dateFormat.format(date) + ".log");

    PatternLayout layOut = new PatternLayout();
    layOut.setConversionPattern("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
    appender.setLayout(layOut);

    log.addAppender(appender);
}

public static Logger getLogger(){
    if(initializationFlag == false){
        intializeLogger();
        initializationFlag = true;
        return LogClass.log;
    }
    else{
        return LogClass.log;
    }
}

public static void setFileName(String fileName){
    LogClass.fileName = fileName;
}


}