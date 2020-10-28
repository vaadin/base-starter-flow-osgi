package com.example.starter.flow.better;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.HttpURLConnection;
import java.net.URL;

public class Test {

	private static final String HTTP_LOCALHOST_8080 = "http://localhost:8080/";

	private static final String HTTP_LOCALHOST_8080_ICONS_ICON_PNG = HTTP_LOCALHOST_8080
			+ "icons/icon.png";

	private static final String HTTP_LOCALHOST_8080_VAADIN_CONFIG_STATS_JSON = HTTP_LOCALHOST_8080
			+ "VAADIN/config/stats.json";

	@org.junit.jupiter.api.Test
	void testRootConnection() throws Exception {

		System.out.println();
		URL url = new URL(HTTP_LOCALHOST_8080);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();

		int code = connection.getResponseCode();

		assertThat(code).isEqualTo(200);

	}

	@org.junit.jupiter.api.Test
	void testIcon() throws Exception {

		URL url = new URL(HTTP_LOCALHOST_8080_ICONS_ICON_PNG);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();

		int code = connection.getResponseCode();

		assertThat(code).isEqualTo(200);

	}

}
