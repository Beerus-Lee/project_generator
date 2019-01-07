package com.lianjia.${project_name}.${module_name}.web;

import com.qyp.config.FreeConfigSpringApplication;
import com.qyp.config.spring.annotation.FreeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;


@RestController
@ComponentScan(basePackages = {"com.lianjia"})
@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class ManageApplication implements EmbeddedServletContainerCustomizer, SchedulingConfigurer {

    Logger logger = LoggerFactory.getLogger(ManageApplication.class);

    @FreeConfig
    private Integer openPort;

    public static void main(String[] args) {
        FreeConfigSpringApplication.run(ManageApplication.class);
    }

    @RequestMapping("/")
    public String index() {
        return "hello ca-driver!";
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(openPort);
        container.setContextPath("");
        container.setSessionTimeout(300);
        container.setAddress(null);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
    }

    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(20);
        scheduler.setThreadNamePrefix("task-");
//        scheduler.setAwaitTerminationSeconds(60);
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        return scheduler;
    }
}
