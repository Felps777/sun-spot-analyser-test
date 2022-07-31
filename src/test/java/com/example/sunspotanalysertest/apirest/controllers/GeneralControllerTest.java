package com.example.sunspotanalysertest.apirest.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.sunspotanalysertest.apirest.dto.GridDTO;
import com.example.sunspotanalysertest.apirest.dto.ScoreDTO;
import com.example.sunspotanalysertest.apirest.dto.ScoresDTO;
import com.example.sunspotanalysertest.persistence.GridEntity;
import com.example.sunspotanalysertest.services.GridService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = GeneralController.class)
class GeneralControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GridService mockGridService;

	@Test
	void testAddGridSucess() throws Exception {
		int[] linearMatrix = new int[] { 1, 2, 3, 4, 5, 6 };
		GridDTO gridDTO = GridDTO.builder().size(3).values(Arrays.toString(linearMatrix)).build();
		String serializedGridDto = new ObjectMapper().writeValueAsString(gridDTO);
		GridEntity gridEntity = GridEntity.builder().id(1L).size(3).linearMatrix(linearMatrix).build();
		Mockito.when(mockGridService.addGrid(Mockito.any(GridDTO.class))).thenReturn(gridEntity);

		// prepare the request mocked
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sun-spot-analyser-api/grid")
				.accept(MediaType.APPLICATION_JSON).content(serializedGridDto).contentType(MediaType.APPLICATION_JSON);

		// run the mocked request
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());

		Map<String, Integer> expectedMap = new HashMap<>();
		expectedMap.put("id", 1);
		String expected = new ObjectMapper().writeValueAsString(expectedMap);

		// assertions
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		// assertEquals(expected.toString(), response.getContentAsString());
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	void testScoresSucess() throws Exception {

		ScoreDTO scoreDTO00 = ScoreDTO.builder().x(0).y(0).score(12).build();
		ScoreDTO scoreDTO01 = ScoreDTO.builder().x(0).y(1).score(21).build();
		ScoreDTO scoreDTO02 = ScoreDTO.builder().x(0).y(2).score(16).build();
		ScoreDTO scoreDTO10 = ScoreDTO.builder().x(1).y(0).score(27).build();
		ScoreDTO scoreDTO11 = ScoreDTO.builder().x(1).y(1).score(45).build();
		ScoreDTO scoreDTO12 = ScoreDTO.builder().x(1).y(2).score(33).build();

		ScoresDTO mockedScoresDTO = ScoresDTO.builder().build();
		mockedScoresDTO.getScores().add(scoreDTO00);
		mockedScoresDTO.getScores().add(scoreDTO01);
		mockedScoresDTO.getScores().add(scoreDTO02);
		mockedScoresDTO.getScores().add(scoreDTO10);
		mockedScoresDTO.getScores().add(scoreDTO11);
		mockedScoresDTO.getScores().add(scoreDTO12);

		String serializedScoresDto = new ObjectMapper().writeValueAsString(mockedScoresDTO);

		Mockito.when(mockGridService.findById(Mockito.anyLong())).thenReturn(mockedScoresDTO);

		// prepare the request mocked
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sun-spot-analyser-api/scores")
				.accept(MediaType.APPLICATION_JSON).param("id", "1");

		// run the mocked request
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());

		// assertions
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		JSONAssert.assertEquals(serializedScoresDto, result.getResponse().getContentAsString(), false);
	}

}
