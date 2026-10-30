package solutions.segorus.ofbiz.quickstart

import org.springframework.boot.web.server.servlet.context.ServletWebServerInitializedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class BrowserLauncher {

    @EventListener
    static void onApplicationEvent(final ServletWebServerInitializedEvent event) {
        var port = event.getWebServer().getPort();

        String os = System.getProperty("os.name").toLowerCase();

        Runtime rt = Runtime.getRuntime();
        String url = "http://localhost:" + port;

        println("Opening browser... " + url)

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
