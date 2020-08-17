package api.enroll.msa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsaEnrollApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaEnrollApiApplication.class, args);
    }

}