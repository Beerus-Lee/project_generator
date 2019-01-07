package com.lianjia.${project_name}.${module_name}.web.logger;

import java.io.IOException;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class HomeLogBackEncoder extends PatternLayoutEncoder {
    static {
        PatternLayout.defaultConverterMap.put("T", HomeThreadConverter.class.getName());
        PatternLayout.defaultConverterMap.put("threadNum", HomeThreadConverter.class.getName());
    }

    @Override
    public void doEncode(ILoggingEvent event) throws IOException {
        super.doEncode(event);
    }
}