package com.mythlin.main;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by huanglin on 2017/5/27.
 */
public class TestLog4j {
    private static Logger log = Logger.getLogger(TestLog4j.class);

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            log.debug("HellWord");
        }
    }
}
