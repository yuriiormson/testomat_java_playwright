package io.testomat.confutils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
@Slf4j
public final class ConfigReader {
	private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";
	private static final String BASE_URL_KEY = "BASE_URL";

	public static String getBaseUrl() {
		Properties props = new Properties();
			try {
				FileInputStream input = new FileInputStream(CONFIG_FILE_PATH);
				props.load(input);
			} catch (IOException e) {
				e.printStackTrace();
				log.error("ERROR: No config.properties file found in src/test/resources/ directory");
			}

		String baseUrl = props.getProperty(BASE_URL_KEY);
		if (baseUrl == null || baseUrl.isEmpty()) {
			log.error("ERROR: No matching BASE_URL found in config.properties file");
		}

		return baseUrl;
	}
}
