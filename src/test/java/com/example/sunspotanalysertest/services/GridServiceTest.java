package com.example.sunspotanalysertest.services;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.sunspotanalysertest.TestObjectsFactory;
import com.example.sunspotanalysertest.apirest.dto.GridDTO;
import com.example.sunspotanalysertest.apirest.dto.ScoreDTO;
import com.example.sunspotanalysertest.apirest.dto.ScoresDTO;
import com.example.sunspotanalysertest.engine.GridTools;
import com.example.sunspotanalysertest.mappers.GridMapper;
import com.example.sunspotanalysertest.persistence.GridEntity;
import com.example.sunspotanalysertest.persistence.GridRepository;

@ExtendWith(MockitoExtension.class)
class GridServiceTest {

	@Mock
	private GridRepository mockGridRepository;

	@Spy
	private GridMapper gridMapper;

	@Spy
	private GridTools gridTools;

	@InjectMocks
	private GridService gridService;

	String inputArrSequence; // input number sequence to be used as array of int;
	final int NUM_COLS_3 = 3; // input size simulation
	final long ID_1 = 1L;
	int[] linearMatrix; // array to be subdivided into a 2D matrix based on numCols;

	@Test
	void testAddGridOK() {

		inputArrSequence = "1, 2, 3, 4, 5, 6, 7, 8, 9"; // white spaces is ignored later.
		linearMatrix = GridTools.parseStringToIntArray(inputArrSequence);

		GridDTO gridDTO = GridDTO.builder().size(NUM_COLS_3).values(inputArrSequence).build();
		GridEntity mockGridEntity = GridEntity.builder().id(ID_1).size(NUM_COLS_3).linearMatrix(linearMatrix).build();

		Mockito.when(mockGridRepository.save(Mockito.any(GridEntity.class))).thenReturn(mockGridEntity);
		gridService.addGrid(gridDTO);

		assertEquals(ID_1, mockGridEntity.getId());
		assertEquals(gridDTO.getSize(), mockGridEntity.getSize());
		assertArrayEquals(GridTools.parseStringToIntArray(gridDTO.getValues()), mockGridEntity.getLinearMatrix());
	}

	@Test
	void testfindByIdOK() {

		inputArrSequence = "1, 2, 3, 4, 5, 6, 7, 8, 9"; // white spaces is ignored later.
		linearMatrix = GridTools.parseStringToIntArray(inputArrSequence);

		List<ScoreDTO> mockListScores = TestObjectsFactory.generateScoreDTOListBasedOn_123456789Sequence();
		ScoresDTO mockScoresDTO = ScoresDTO.builder().scores(mockListScores).build();
		GridEntity mockGridEntity = GridEntity.builder().id(ID_1).size(NUM_COLS_3).linearMatrix(linearMatrix).build();

		Mockito.when(gridTools.calculateScores(mockGridEntity, true)).thenReturn(mockListScores);
		Mockito.when(mockGridRepository.findById(ID_1)).thenReturn(Optional.of(mockGridEntity));
		ScoresDTO result = gridService.findById(ID_1);

		assertEquals(mockScoresDTO.getScores().size(), result.getScores().size());
		assertEquals(mockScoresDTO, result);
	}

}
