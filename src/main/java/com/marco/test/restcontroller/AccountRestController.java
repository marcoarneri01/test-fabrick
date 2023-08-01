package com.marco.test.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marco.test.model.BalanceResponse;
import com.marco.test.model.BalanceResponsePayload;
import com.marco.test.model.MoneyTransferRequest;
import com.marco.test.model.Payload;
import com.marco.test.model.TransactionResponse;
import com.marco.test.model.TransactionResponsePayload;
import com.marco.test.repository.BalanceResponseRepository;
import com.marco.test.repository.TransactionRepository;
import com.marco.test.repository.TransactionTypeRepository;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "")
public class AccountRestController {
	public static final String BASE_URL = "https://sandbox.platfr.io";
	public static final String API_KEY = "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP";
	public static final Long ACCOUNT_ID = 14537780L;
	private final RestTemplate restTemplate;
	private ObjectMapper objectMapper;

	@Autowired
	public BalanceResponseRepository balanceResponseRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	TransactionTypeRepository transactionTypeRepository;

	public AccountRestController(RestTemplate restTemplate, ObjectMapper objectMapper) {
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
	}

	@GetMapping("/balance")
	public String getBalance() {
		String endpoint = BASE_URL + "/api/gbs/banking/v4.0/accounts/" + ACCOUNT_ID + "/balance";

		HttpHeaders headers = new HttpHeaders();
		headers.set("API-Key", API_KEY);
		headers.set("Auth-Schema", "S2S");

		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(endpoint, HttpMethod.GET, entity, String.class);

		String responseBody = response.getBody();
		System.out.println("Response Body: " + responseBody);

		BalanceResponse balanceResponse = null;
		try {
			balanceResponse = objectMapper.readValue(responseBody, BalanceResponse.class);
		} catch (JsonProcessingException exc) {
			exc.printStackTrace();
		}

		if (balanceResponse != null && balanceResponse.getPayload() != null) {
			BalanceResponsePayload payload = balanceResponse.getPayload();

			balanceResponseRepository.save(payload);

			return "Response: " + payload;
		} else {
			return "Failed to deserialize the response";
		}
		// Test execution: return "Response: " + responseBody;
	}

	@PostMapping("/transfer")
	public String transfer(
			@RequestParam("accountId") Long accountId, 
			@RequestParam("receiverName") String receiverName,
			@RequestParam("description") String description, 
			@RequestParam("currency") String currency,
			@RequestParam("amount") String amount, 
			@RequestParam("executionDate") String executionDate) {

		String endpoint = BASE_URL + "/api/gbs/banking/v4.0/accounts/" + accountId + "/payments/money-transfers";

		MoneyTransferRequest transferRequest = new MoneyTransferRequest(
				receiverName, 
				description, 
				currency, 
				amount,
				executionDate);

		HttpHeaders headers = new HttpHeaders();
		headers.set("API-Key", API_KEY);
		headers.set("Auth-Schema", "S2S");

		HttpEntity<MoneyTransferRequest> requestEntity = new HttpEntity<>(transferRequest, headers);

		try {
			ResponseEntity<String> response = new RestTemplate().exchange(
					endpoint, 
					HttpMethod.POST, 
					requestEntity,
					String.class);

			return "Response money transfer API: " + response.getBody();
		} catch (HttpClientErrorException ex) {
			return "Error during money transfer: " + ex.getMessage();
		}
	}

	@GetMapping("/transactions")
	public String getTransactions(@RequestParam("fromAccountingDate") String fromAccountingDate,
			@RequestParam("toAccountingDate") String toAccountingDate) {

		String endpoint = BASE_URL + "/api/gbs/banking/v4.0/accounts/" + ACCOUNT_ID + "/transactions";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(endpoint)
				.queryParam("fromAccountingDate", fromAccountingDate)
				.queryParam("toAccountingDate", toAccountingDate);

		HttpHeaders headers = new HttpHeaders();
		headers.set("API-Key", API_KEY);
		headers.set("Auth-Schema", "S2S");
		headers.set("X-Time-Zone", "Europe/Rome");

		HttpEntity<?> entity = new HttpEntity<>(headers);

		try {
			ResponseEntity<String> response = new RestTemplate().exchange(
					builder.toUriString(), 
					HttpMethod.GET, 
					entity,
					String.class);

			String responseBody = response.getBody();

			System.out.println("Response Body: " + responseBody);

			TransactionResponse transactionResponse = objectMapper.readValue(responseBody, TransactionResponse.class);

			if (transactionResponse != null && transactionResponse.getPayload() != null) {
				Payload payload = transactionResponse.getPayload();
				List<TransactionResponsePayload> payloadList = payload.getList();

				if (payloadList != null && !payloadList.isEmpty()) {
					for (TransactionResponsePayload transactionPayload : payloadList) {
						transactionPayload.setTransactionId(endpoint);
						transactionPayload.setOperationId(transactionPayload.getOperationId());
						transactionPayload.setAccountingDate(transactionPayload.getAccountingDate());
						transactionPayload.setValueDate(transactionPayload.getValueDate());
						transactionPayload.setType(transactionPayload.getType());
						transactionPayload.setAmount(transactionPayload.getAmount());
						transactionPayload.setCurrency(transactionPayload.getCurrency());
						transactionPayload.setDescription(transactionPayload.getDescription());
						System.out.println(payloadList.toString());

						transactionRepository.save(transactionPayload);
					}
				}
				return "Response: " + response.getBody();
			} else {
				return "Failed to deserialize the response";
			}

		} catch (HttpClientErrorException ex) {
			return "Error during transactions request: " + ex.getMessage();
		} catch (JsonProcessingException ex) {
			return "Error parsing API response: " + ex.getMessage();
		}
	}
}
