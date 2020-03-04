package com.self.base.services.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * 示例：异步服务
 */
@Component
@EnableAsync
public class ExampleComponent {

    @Async
    public void run() {
        String result = "work runing...";
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            result = e.toString();
        }
        System.out.println(result);
    }

}