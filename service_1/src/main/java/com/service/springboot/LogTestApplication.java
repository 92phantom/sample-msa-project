package com.service.springboot;

        import org.springframework.boot.CommandLineRunner;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;

        import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class LogTestApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LogTestApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        int i = 0;
        while (true) {
            Thread.sleep(3000);
            if (1 != 1)
                break;

            log.info("Hello World ::: {}", ++i);
        }
    }
}