package com.example.sunspotanalysertest.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.example.sunspotanalysertest.apirest.dto.ScoreDTO;
import com.example.sunspotanalysertest.persistence.GridEntity;

@Component
public class GridTools {

	public List<ScoreDTO> calculateScores(GridEntity entity, final boolean printMatrix) {

		int numRows = calcNumRowsFor2dMatrix(entity.getLinearMatrix().length, entity.getSize());

		List<ScoreDTO> result = new ArrayList<>();
		int[][] matrix2d = transform1DArrayTo2D(entity.getLinearMatrix(), entity.getSize());
		int[][] matrix2dResult = new int[numRows][entity.getSize()];

		if (printMatrix) {
			System.out.println(printPrettyMatrix(matrix2d, "SOURCE 2D MATRIX"));
			System.out.println("----------------------------");
		}
		// for each x,y position/cell, we calculates the score
		for (int x = 0; x < matrix2d.length; x++) {
			for (int y = 0; y < entity.getSize() && y < matrix2d[x].length; y++) {
				matrix2dResult[x][y] = calculateCellScore(x, y, matrix2d);
				result.add(ScoreDTO.builder().x(x).y(y).score(matrix2dResult[x][y]).build());
			}
		}

		if (printMatrix) {
			System.out.println("----------------------------");
			System.out.println(printPrettyMatrix(matrix2dResult, "CALCULATED 2D MATRIX"));
		}

		return result;
	}

	/**
	 * Function to calculate an the sum of a matrix position with the area
	 * surrounding it by 1 cell. <br>
	 * Ex.: for a <b> x,y </b> position on a matrix the result would be: <br>
	 * (x-1,y-1) + (x-1,y) + (x-1,y+1) + <br>
	 * (x,y-1) + (x,y) + (x,y+1) + <br>
	 * (x+1,y-1) + (x+1,y) + (x+1,y+1) + <br>
	 * 
	 * @param x        reference row position
	 * @param y        reference column position
	 * @param matrix2d The base matrix to lookup for the cell position values.
	 * @return The total sum of cell values surrounding by 1 cell the reference
	 *         position cell.
	 */
	public int calculateCellScore(final int x, final int y, final int[][] matrix2d) {

		int result = 0;
		int maxRow = matrix2d.length;
//		int maxCol = matrix2d[0].length;
		for (int row = x - 1; row <= x + 1; row++) {

			// check valid matrix boundaries
			if (row >= 0 && row < maxRow) {
				for (int col = y - 1; col <= y + 1; col++) {
					if (col >= 0 && col < matrix2d[row].length) {
						result = result + matrix2d[row][col];
					}
				}
			}
		}
		String msg = String.format("Score: (%d,%d) = %d", x, y, result);
		System.out.println(msg);

		return result;

	}

//	public List<Integer> transform2DmatrixToList(final int[][] matrix) {
//
//		ArrayList<Integer> arrList = new ArrayList<>(matrix.length * 2);
//		for (int[] array : matrix) {
//			arrList.addAll(IntStream.of(array).boxed().collect(Collectors.toCollection(ArrayList::new)));
//		}
//		return arrList;
//	}

	public String printPrettyMatrix(final int[][] matrix2D, final String title) {

		String mtx = Arrays.deepToString(matrix2D);
		mtx = mtx.substring(1, mtx.length() - 1);
		mtx = mtx.replaceAll("], ", "]");
		mtx = mtx.replaceAll("]", ("]" + System.getProperty("line.separator")));

		int cols = matrix2D[0].length;
		String lineSeparator = "-".repeat(cols + title.length()).concat("\n");
		StringBuilder str = new StringBuilder();
		str.append(lineSeparator);
		str.append(" ".concat(title).concat(" \n"));
		str.append(lineSeparator);
		str.append(mtx);
		str.append(lineSeparator);
		return str.toString();
	}

//	public List<Integer> transformArrayToList(int[] arr) {
//		List<Integer> listInt = IntStream.of(arr).boxed().collect(Collectors.toCollection(ArrayList::new));
//		return listInt;
//	}

	public static int[] parseStringToIntArray(final String arrStr) {
		List<String> list = Arrays.asList(arrStr.split(","));
		return list.stream().mapToInt(val -> Integer.valueOf(val.trim())).toArray();
	}

	public static int calcNumRowsFor2dMatrix(final int arrLength, final int numcols) {
		int resto = arrLength % numcols;
		return arrLength / numcols + (resto == 0 ? 0 : 1);
	}

	public static int[][] transform1DArrayTo2D(final int[] arr, final int numCols) {
		// row count
		int m = calcNumRowsFor2dMatrix(arr.length, numCols);
		// last row length
		int lastRow = arr.length % numCols == 0 ? numCols : arr.length % numCols;
		return IntStream.range(0, m)
				.mapToObj(
						i -> IntStream.range(0, i < m - 1 ? numCols : lastRow).map(j -> arr[j + i * numCols]).toArray())
				.toArray(int[][]::new);
	}

}
