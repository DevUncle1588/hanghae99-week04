package com.sparta.week03board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@EnableScheduling // 스프링 부트에서 스케줄러가 작동하게 합니다.
@EnableJpaAuditing // 시간 자동 변경이 가능하도록 합니다.
@SpringBootApplication
public class Week03BoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week03BoardApplication.class, args);
    }
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        return new HiddenHttpMethodFilter();
    }

}
