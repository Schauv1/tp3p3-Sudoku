package system;

import java.util.HashSet;
import java.util.Set;

public class Subgrilla {
	private int[][] _subGrid;
	
	public Subgrilla(int[][] grid, int focusRow, int focusCol) {
		if(focusRow < 3) {
			if (focusCol < 3) 
				_subGrid = generateSubGrid(grid,0,0);
			else 
				if (focusCol >=3 && focusCol<6) 
					_subGrid = generateSubGrid(grid,0,1);
				else
					if (focusCol >=6) 
						_subGrid = generateSubGrid(grid,0,2);
		}
				
		else  
			if(focusRow >= 3 && focusRow <6) {
				if (focusCol < 3) 
					_subGrid = generateSubGrid(grid,1,0);
				else 
					if (focusCol >=3 && focusCol<6) 
						_subGrid = generateSubGrid(grid,1,1);
					else
						if (focusCol >=6) 
							_subGrid = generateSubGrid(grid,1,2);
			}
			
			else {
				if(focusRow >=6) 
					if (focusCol < 3) 
						_subGrid = generateSubGrid(grid,2,0);
					else 
						if (focusCol >=3 && focusCol<6)
							_subGrid = generateSubGrid(grid,2,1);
						else
							if (focusCol >=6) 
								_subGrid = generateSubGrid(grid,2,2);
				}
	}

	private int[][] generateSubGrid(int[][] grid, int baseRow, int baseCol) {
		int[][] subGrid = new int[3][3];
		baseRow = baseRow*3;
		baseCol = baseCol*3;
		for (int row = baseRow; row < baseRow+3; row++) {
			for (int col = baseCol; col < baseCol+3; col++) {
				subGrid[row-baseRow][col-baseCol] = grid[row][col];
			}
		}
		return subGrid;
	}
	
	public Set<Integer> getDefinedValues() {
		HashSet<Integer> values = new HashSet<Integer>();
		for (int row = 0; row < _subGrid.length; row++) {
			for (int col = 0; col < _subGrid[row].length; col++) {
				if (_subGrid[row][col] != 0) 
					values.add(_subGrid[row][col]);
			}
		}
		return values;
	}
}
