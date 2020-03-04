package com.self.base.services.works;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 示例：定时任务
 * 去掉注释生效
 */
/*@Component*/
@EnableScheduling
public class ExampleWork {

    @Scheduled(fixedRate=5000)
    public void example(){
        System.out.println("执行了示例定时任务,每5000毫秒执行.");
    }

}
