package solutions.segorus.ofbiz.quickstart

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackages = "solutions.segorus.ofbiz.quickstart")
class QuickstartApplication {
    static void main(String[] args) {
        SpringApplication.run(QuickstartApplication, args)

        println("Opening browser...")
        String os = System.getProperty("os.name").toLowerCase();

        Runtime rt = Runtime.getRuntime();
        String url = "http://localhost:8080";

        if (os.indexOf("win") >= 0) {
            rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
        }
        else if (os.indexOf("mac") >= 0) {
            rt.exec("open " + url);
        }
        else {
            rt.exec("xdg-open " + url)
        }
    }
}
