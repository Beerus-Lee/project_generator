package com.lianjia.${project_name}.${module_name}.web.logger;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class HomeThreadConverter extends ClassicConverter {
    /**
     * 当需要显示线程ID的时候，返回当前调用线程的ID
     */
    @Override
    public String convert(ILoggingEvent event) {
        return String.valueOf(Thread.currentThread().getId());
    }
}