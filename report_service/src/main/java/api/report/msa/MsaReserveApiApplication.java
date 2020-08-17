package api.report.msa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsaReserveApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaReserveApiApplication.class, args);
    }

}