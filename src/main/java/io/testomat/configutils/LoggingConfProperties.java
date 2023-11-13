package io.testomat.configutils;
import lombok.extern.slf4j.Slf4j;
import org.testng.Reporter;
@Slf4j
public class LoggingConfProperties extends ConfProperties {
    private final ConfProperties properties;

    public LoggingConfProperties(ConfProperties properties) {
        this.properties = properties;
    }

    public String getProperty(String key) {
        var value = properties.getProperty(key);
        log.info("key: " + key + " value: " + value, true);
        return value;
    }
}
