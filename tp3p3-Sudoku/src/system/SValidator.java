package system;

public class SValidator {
	
	public boolean validateSudoku(int[][] sudoku) {
		for (int row = 0; row < sudoku.length; row++) {
			for (int col = 0; col < sudoku.length; col++) {
				if (row_contradiction(sudoku,col, sudoku[row][col], row))
					return false;
				if (column_contradiction(sudoku,row, sudoku[row][col], col))
					return false;
				if (square_contradiction(sudoku,row,col))
					return false;
			}
		}
		return true;
	}

	private boolean square_contradiction(int[][] sudoku, int row, int col) {
		int BaseRow = (row / 3) * 3;
		int Basecol = (col / 3) * 3;
		
		int numberToCheck = sudoku[row][col];
		
		for(int r = 0; r<3; r++){
			for(int c = 0; c<3; c++){
				if(sudoku[BaseRow + r][Basecol + c] == numberToCheck && BaseRow + r != row && Basecol + c != col
						&& sudoku[BaseRow + r][Basecol + c] != 0){
					return true;
				}
			}
		}
		return false;
	}

	private boolean row_contradiction(int[][] sudoku, int row, int numberToCheck, int ogCol) {
		for (int col = 0; col < sudoku.length; col++) {
			if (sudoku[row][col] == numberToCheck && col != ogCol && sudoku[row][col] != 0)
			return true;
		}
		return false;
	}

	private boolean column_contradiction(int[][] sudoku, int col, int numberToCheck, int ogRow) {
		for (int row = 0; row < sudoku.length; row++) {
			if (sudoku[row][col] == numberToCheck && row != ogRow && sudoku[row][col] != 0)
			return true;
		}
		return false;
	}
}
