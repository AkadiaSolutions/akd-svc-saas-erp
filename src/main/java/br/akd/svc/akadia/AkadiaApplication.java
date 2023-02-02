package br.akd.svc.akadia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFeignClients
@SpringBootApplication
@EntityScan(basePackages = {"br.akd.svc.akadia.models"})
@EnableJpaRepositories("br.akd.svc.akadia.repositories")
@ComponentScan("br.akd.svc.akadia.controllers")
@ComponentScan("br.akd.svc.akadia.services")
@ComponentScan("br.akd.svc.akadia.proxy")
@ComponentScan("br.akd.svc.akadia.config")
@ComponentScan("br.akd.svc.akadia.utils")
public class AkadiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkadiaApplication.class, args);
	}

}
