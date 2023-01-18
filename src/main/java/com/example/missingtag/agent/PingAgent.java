package com.example.missingtag.agent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Component
public class PingAgent {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    private RestTemplate restTemplate;

    @EventListener(ApplicationReadyEvent.class)
    public void startPing() {
        // Configure restTemplate
        restTemplate = restTemplateBuilder.build();

        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(1);
        taskScheduler.initialize();
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        taskScheduler.setAwaitTerminationSeconds(60);

        taskScheduler.schedule(this::start, new PeriodicTrigger(1, TimeUnit.SECONDS));
    }

    public void start() {
        // To generate metrics
        restTemplate.getForObject("https://github.com/marketplace", String.class);
    }
}
