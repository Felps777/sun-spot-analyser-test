package com.example.sunspotanalysertest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.sunspotanalysertest.apirest.dto.ScoreDTO;
import com.example.sunspotanalysertest.apirest.dto.ScoresDTO;

@Component
public class TestObjectsFactory {

	public static List<ScoreDTO> generateScoreDTOListBasedOn_12345Sequence() {

		// based on an array of 1,2,3,4,5
		// score values a correct following the calculation rules
		ScoreDTO scoreDTO00 = ScoreDTO.builder().x(0).y(0).score(12).build();
		ScoreDTO scoreDTO01 = ScoreDTO.builder().x(0).y(1).score(21).build();
		ScoreDTO scoreDTO02 = ScoreDTO.builder().x(0).y(2).score(16).build();
		ScoreDTO scoreDTO10 = ScoreDTO.builder().x(1).y(0).score(27).build();
		ScoreDTO scoreDTO11 = ScoreDTO.builder().x(1).y(1).score(45).build();

		// considering there is not input valor for the 6th element, by default is is
		// considered = 0. So the calculations are done normally for it.
		// in this sequence it is 16
		ScoreDTO scoreDTO12 = ScoreDTO.builder().x(1).y(2).score(16).build();

		List<ScoreDTO> listScoreDto = new ArrayList<>();
		listScoreDto.add(scoreDTO00);
		listScoreDto.add(scoreDTO01);
		listScoreDto.add(scoreDTO02);
		listScoreDto.add(scoreDTO10);
		listScoreDto.add(scoreDTO11);
		listScoreDto.add(scoreDTO12);

		return listScoreDto;
	}

	public static List<ScoreDTO> generateScoreDTOListBasedOn_123456789Sequence() {

		// based on an array of 1,2,3,4,5,6,7,8,9
		// score values a correct following the calculation rules
		ScoreDTO scoreDTO00 = ScoreDTO.builder().x(0).y(0).score(12).build();
		ScoreDTO scoreDTO01 = ScoreDTO.builder().x(0).y(1).score(21).build();
		ScoreDTO scoreDTO02 = ScoreDTO.builder().x(0).y(2).score(16).build();
		ScoreDTO scoreDTO10 = ScoreDTO.builder().x(1).y(0).score(27).build();
		ScoreDTO scoreDTO11 = ScoreDTO.builder().x(1).y(1).score(45).build();
		ScoreDTO scoreDTO12 = ScoreDTO.builder().x(1).y(2).score(33).build();
		ScoreDTO scoreDTO20 = ScoreDTO.builder().x(2).y(0).score(24).build();
		ScoreDTO scoreDTO21 = ScoreDTO.builder().x(2).y(1).score(39).build();
		ScoreDTO scoreDTO22 = ScoreDTO.builder().x(2).y(2).score(28).build();

		List<ScoreDTO> listScoreDto = new ArrayList<>();
		listScoreDto.add(scoreDTO00);
		listScoreDto.add(scoreDTO01);
		listScoreDto.add(scoreDTO02);
		listScoreDto.add(scoreDTO10);
		listScoreDto.add(scoreDTO11);
		listScoreDto.add(scoreDTO12);
		listScoreDto.add(scoreDTO20);
		listScoreDto.add(scoreDTO21);
		listScoreDto.add(scoreDTO22);

		return listScoreDto;
	}

	public static ScoresDTO generateScoresDTO_BasedOn_123456789Sequence() {

		ScoresDTO mockedScoresDTO = ScoresDTO.builder().build();
		mockedScoresDTO.getScores().addAll(generateScoreDTOListBasedOn_123456789Sequence());
		return mockedScoresDTO;
	}

	public static ScoresDTO generateScoresDTO_BasedOn_12345Sequence() {

		ScoresDTO mockedScoresDTO = ScoresDTO.builder().build();
		mockedScoresDTO.getScores().addAll(generateScoreDTOListBasedOn_12345Sequence());
		return mockedScoresDTO;
	}
}
