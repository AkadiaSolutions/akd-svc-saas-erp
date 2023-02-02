package br.akd.svc.akadia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AkadiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkadiaApplication.class, args);
	}

}
