package com.coderdream;

import static org.junit.Assert.assertTrue;

import io.thekraken.grok.api.Grok;
import io.thekraken.grok.api.Match;
import io.thekraken.grok.api.exception.GrokException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {


    private static Logger LOG = (Logger) LogManager.getLogger(AppTest.class);
    /**
     * Rigorous Test :-)
     */
//    @Test
//    public void shouldAnswerWithTrue() {
//        assertTrue(true);
//    }

    // 打印各种级别的日志用于测试
    @Test
    public void logAll() {
        LOG.trace("trace level log");
        LOG.debug("debug level log");
        LOG.info("info level log");
        LOG.error("error level log");
        LOG.fatal("fatal level log");
        // 设置休眠时间(单位ms)，控制日志打印速度
    }

    private static final String GROK_PATTERN_PATH = "/Users/coderdream/Documents/04_GitHub/grok-demo/src/test/resources/patterns";


    @Test
    public void testMethod_01() {
        //assertTrue(true);
        String pattern="%{MONTH}\\s+%{MONTHDAY}\\s+%{TIME}\\s+%{YEAR}.*%{fromIP}";
        String message = "Mon Nov  9 06:47:33 2015; UDP; eth1; 461 bytes; from 88.150.240.169:tag-pm";
        Match match = null;
        try {
            Grok grok = new Grok();
            //添加patter配置文件,默认的grok的pattern是null
            grok.addPatternFromFile(GROK_PATTERN_PATH);
            //添加自定义pattern，当然%{IPV4}可以不用已有pattern，也可以自定义正则表达式
            grok.addPattern("fromIP", "%{IPV4}");
            grok.compile(pattern);
            match = grok.match(message);
            match.captures();
            if(!match.isNull()){
                System.out.println(match.toMap().toString());
                System.out.println(match.toJson().toString());
            }else{
                System.out.println("not match");
            }
        } catch (GrokException e) {
            e.printStackTrace();
            match = null;
        }

    }

}
