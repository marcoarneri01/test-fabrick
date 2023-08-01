package com.marco.test;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AccountRestControllerTest {

	private int port = 8080;

	@Autowired
	private RestTemplate restTemplate;

	private String baseUrl;

	@BeforeEach
	public void setUp() {
		this.baseUrl = "http://localhost:" + port + "/api/accounts";
	}

	@Test
	public void testGetBalance() {
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/balance", 
					String.class);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (Exception exc) {
			fail(exc.getMessage());
		}
	}

	@Test
	// error 403: [no body] the test is waiting for an HTTP response with code 200 (OK)
	// instead the server is returning an HTTP response with code 403 (Forbidden)
	// no content in response body (Bad parameters)
	public void testTransfer() {
		try {
			ResponseEntity<String> response = restTemplate.postForEntity(baseUrl
					+ "/transfer?accountId=123456&receiverName=receiverName&description=description&currency=USD&amount=50&executionDate=2023-08-01",
					null, String.class);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (Exception exc) {
			fail(exc.getMessage());
		}
	}

	@Test
	public void testGetTransactions() {
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(
					baseUrl + "/transactions?fromAccountingDate=2023-07-01&toAccountingDate=2023-07-31", 
					String.class);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (Exception exc) {
			fail(exc.getMessage());
		}
	}
}
