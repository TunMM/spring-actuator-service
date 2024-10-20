package com.tiem.spring_actuator_service;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port=0"})
class SpringActuatorServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Value("${local.management.port}")
	private int mgmtPort;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void shouldReturn200WhenSendingRequestToController() {
		ResponseEntity<String> response = this.testRestTemplate.getForEntity("http://localhost:" + this.port + "/hello-world",
				String.class);

		then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void shouldReturn100WnenSendingRequestToManagementEndPoint() {
		ResponseEntity<String> response = this.testRestTemplate
				.getForEntity("http://localhost:" + mgmtPort + "/actuator", String.class);

		then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
