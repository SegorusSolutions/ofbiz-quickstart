package solutions.segorus.ofbiz.quickstart

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackages = "solutions.segorus.ofbiz.quickstart")
class QuickstartApplication {
    static void main(String[] args) {
        SpringApplication.run(QuickstartApplication, args)
    }
}
