package cxy.project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication
@ServletComponentScan
public class BonusServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BonusServerApplication.class, args);
        log.info("init...");
    }

}
