package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.demo.service.SendToQueueService;

@Component
@Order(0)
public class KafkaParticoesApplication implements ApplicationListener<ApplicationReadyEvent>{
    @Autowired
    private SendToQueueService queueService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2000; i++) {
            Runnable worker = new MyRunnable(String.valueOf(i));
            worker.run();
        }
        System.out.println("\nFinished all threads");

        
    }
    public class MyRunnable implements Runnable {
        private final String index;
    
        MyRunnable(String index) {
            this.index = index;
        }
    
        @Override
        public void run() {
            try {
                Map<String, Object> testeMap = new HashMap<>();
                testeMap.put("name", "teste" + " " + index);
                queueService.execute(testeMap);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
}


