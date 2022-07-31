package com.example.sunspotanalysertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.sunspotanalysertest.apirest.dto.GridDTO;
import com.example.sunspotanalysertest.persistence.GridEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SunSpotAnalyserTestApplicationTests {

	@LocalServerPort
	int randomServerPort;

	@Test
	void testInputDataOK() throws URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + randomServerPort + "/sun-spot-analyser-api/grid";
		URI uri = new URI(baseUrl);
		GridDTO dto = GridDTO.builder().size(3).values("1,2,3,4,5,6,7,8,9").build();

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");

		HttpEntity<GridDTO> request = new HttpEntity<>(dto, headers);

		ResponseEntity<GridEntity> entity = restTemplate.postForEntity(uri, request, GridEntity.class);

		assertEquals(GridEntity.class, entity.getBody().getClass());
		assertEquals(1, entity.getBody().getId());
	}

	@Test
	void testInputDataNotOK() throws URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + randomServerPort + "/sun-spot-analyser-api/grid";
		URI uri = new URI(baseUrl);
		// GridDTO dto = GridDTO.builder().size(3).values("1,2,3,4,5,6,7,8,9").build();

		HttpHeaders headers = new HttpHeaders();
		// headers.set("X-COM-PERSIST", "true");
		headers.setContentType(MediaType.APPLICATION_JSON);

		// null input entity
		HttpEntity<GridDTO> request = new HttpEntity<>(null, headers);

		try {
			restTemplate.postForEntity(uri, request, String.class);
			fail();
		} catch (HttpClientErrorException ex) {
			// Verify bad request and missing header
			assertEquals(400, ex.getRawStatusCode());
			assertEquals(true, ex.getResponseBodyAsString().contains("Bad Request"));
		}
	}
}
