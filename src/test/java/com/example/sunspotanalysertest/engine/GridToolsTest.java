package com.example.sunspotanalysertest.engine;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.sunspotanalysertest.TestObjectsFactory;
import com.example.sunspotanalysertest.apirest.dto.ScoreDTO;
import com.example.sunspotanalysertest.persistence.GridEntity;

@ExtendWith(SpringExtension.class)
class GridToolsTest {

	@Spy
	private GridTools gridTools;

	String inputArrSequence; // input number sequence to be used as array of int;
	final int NUM_COLS_3 = 3; // input size simulation
	final long ID_1 = 1L;
	int[] linearMatrix; // array to be subdivided into a 2D matrix based on numCols;

	@ParameterizedTest
	@ValueSource(strings = { "1,2,3,4,5", "1,2,3,4,5,6,7,8,9" })
	void testCalculateScores(String inputArrayValuesSequence) {

		int[] linearMatrix = GridTools.parseStringToIntArray(inputArrayValuesSequence);

		GridEntity mockGridEntity = GridEntity.builder().id(ID_1).size(NUM_COLS_3).linearMatrix(linearMatrix).build();
		List<ScoreDTO> mockedListScoreDto = new ArrayList<>();
		if (linearMatrix.length == 5) {
			mockedListScoreDto = TestObjectsFactory.generateScoreDTOListBasedOn_12345Sequence();
		} else {
			mockedListScoreDto = TestObjectsFactory.generateScoreDTOListBasedOn_123456789Sequence();
		}

		// test
		List<ScoreDTO> resultList = gridTools.calculateScores(mockGridEntity, true);

		// assertions
		assertFalse(resultList.isEmpty());
		assertEquals(mockedListScoreDto.size(), resultList.size());
		assertEquals(mockedListScoreDto, resultList);

	}

	@ParameterizedTest
	@CsvSource(nullValues = "null", value = { "5,3,2", "9,3,3", "10,3,4" }) // simulated arrays length
	void testCalcNumRowsFor2dMatrix(int inputLength, int size, int expectedRows) {

		int numRows = GridTools.calcNumRowsFor2dMatrix(inputLength, size);

		assertEquals(expectedRows, numRows);

	}

	@Test
	void testPrintPrttyMatrix() {

		int[][] inputMatrix2D = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		String dummyTitle = "DummyTitle";

		String mtx = Arrays.deepToString(inputMatrix2D);
		mtx = mtx.substring(1, mtx.length() - 1);
		mtx = mtx.replaceAll("], ", "]");
		mtx = mtx.replaceAll("]", ("]" + System.getProperty("line.separator")));

		int cols = 3;
		String lineSeparator = "-".repeat(cols + dummyTitle.length()).concat("\n");
		StringBuilder str = new StringBuilder();
		str.append(lineSeparator);
		str.append(" ".concat(dummyTitle).concat(" \n"));
		str.append(lineSeparator);
		str.append(mtx);
		str.append(lineSeparator);

		String result = gridTools.printPrettyMatrix(inputMatrix2D, dummyTitle);
		String expectedMatrix = str.toString();
		// assertions
		assertEquals(expectedMatrix, result);
	}

	@ParameterizedTest
	@MethodSource("provideParamsForArrayTransformationTest")
	void testTransform1DArrayTo2D(int[] linearMatrix, int size, int[][] expectedMatrix) {

		int[][] matrix2D = GridTools.transform1DArrayTo2D(linearMatrix, size);
		assertArrayEquals(expectedMatrix, matrix2D);
	}

	/**
	 * Method to generate a pack of parameters that simulates input Arrays values of
	 * different lengths, the number of columns to be used as length divisor and the
	 * respectives expected matrix results after applying the specified numColls
	 * 
	 * @return
	 */
	private static Stream<Arguments> provideParamsForArrayTransformationTest() {

		int[] linearMatrix1 = new int[] { 1, 2, 3, 4, 5 };
		int[] linearMatrix2 = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] linearMatrix3 = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		int[][] matrix2D_1 = new int[][] { { 1, 2, 3 }, { 4, 5 } };
		int[][] matrix2D_2 = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int[][] matrix2D_3 = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10 } };

		return Stream.of(Arguments.of(linearMatrix1, 3, matrix2D_1), Arguments.of(linearMatrix2, 3, matrix2D_2),
				Arguments.of(linearMatrix3, 3, matrix2D_3));
	}

}
