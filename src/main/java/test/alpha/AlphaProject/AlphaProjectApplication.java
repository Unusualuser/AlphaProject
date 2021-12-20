package test.alpha.AlphaProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AlphaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlphaProjectApplication.class, args);
	}

}
