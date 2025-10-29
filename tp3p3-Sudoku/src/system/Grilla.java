package system;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Grilla {
	private int[][] _grid;
	
	public Grilla() {
		_grid = new int[9][9];
	}
	
	public Grilla(int[][] grid) {
		_grid = grid;
	}
	
	
	public Grilla(int randomValuesAmount) {
		_grid = new int[9][9];
		int addedvalues = 0;
		Random rand = new Random();
		while (addedvalues < randomValuesAmount) {
			int r1 = rand.nextInt(9);
			int r2 = rand.nextInt(9);
			if (_grid[r1][r2] == 0) {
				_grid[r1][r2] = rand.nextInt(9)+1;
				addedvalues++;
			}
		}
	}
	
	public void setValueAt(int val,int row, int col) {
		if (val < 1 || val > 9)
			throw new IllegalArgumentException("Valor fuera de rango para sudoku, es de 1 a 9");
		_grid[row][col] = val;
	}
	
	public int length() {
		return _grid.length;
	}
	
	public int getValue(int row, int col) {
		return _grid[row][col];
	}
	
	public Set<Integer> getAllRowAndColumnValuesFor(int row, int column) {
		HashSet<Integer> values = new HashSet<Integer>();
		for (int rowExtra = 0; rowExtra < _grid.length; rowExtra++) {
			if (_grid[rowExtra][column] != 0)
				values.add(_grid[rowExtra][column]);
		}
		for (int col = 0; col < _grid[0].length; col++) {
			if (_grid[row][col] != 0)
				values.add(_grid[row][col]);
		}
		return values;
	}
}
