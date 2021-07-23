package com.gmc.gmccoin.api;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan("com.gmc.gmccoin.common.model")
@ComponentScan("com.gmc.gmccoin")
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "30s")
public class GmcCoinApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(GmcCoinApiApplication.class, args);
	}

}
