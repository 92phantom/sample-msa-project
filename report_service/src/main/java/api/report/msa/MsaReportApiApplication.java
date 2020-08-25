package api.report.msa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsaReportApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaReportApiApplication.class, args);
    }

}