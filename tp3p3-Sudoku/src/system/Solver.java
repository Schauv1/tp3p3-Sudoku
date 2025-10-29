package system;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Solver {
	Grilla _grid;
	Stack<Integer> _lastGuess = new Stack<Integer>();
	Stack<Integer[]> _lastGuessPosition = new Stack<Integer[]>();
	
	public int[][] bruteForceIt(Grilla grid) {
		int[][] valid_solution;
		_grid = grid;
		valid_solution = solveSingleNumbersAndCopyGrid();
		HashSet<Integer> existantValues = new HashSet<Integer>();
		Subgrilla sub;
		for (int y = 0; y < grid.length(); y++) {
			for (int x = 0; x < grid.length(); x++) {
				if (grid.getValue(y, x) == 0) {
					sub = new Subgrilla(valid_solution,y,x);
					existantValues.clear();
					existantValues.addAll(getAllRowValuesFor(y, valid_solution));
					existantValues.addAll(getAllColumnValuesFor(x, valid_solution));
					existantValues.addAll(sub.getDefinedValues());
					_lastGuess.add(generatePossibleValue(existantValues));
					valid_solution[y][x] = _lastGuess.peek();
					_lastGuessPosition.add(new Integer[] {y,x});
					return bruteForceIt(valid_solution);
				}
			}
		}
		return valid_solution;
	}

	public int[][] bruteForceIt(int[][] current_grid) {
		int[][] valid_solution = solveSingleNumbers(current_grid);
		HashSet<Integer> existantValues = new HashSet<Integer>();
		Subgrilla sub;
		for (int y = 0; y < _grid.length(); y++) {
			for (int x = 0; x < _grid.length(); x++) {
				if (valid_solution[y][x] == 0) {
					sub = new Subgrilla(valid_solution,y,x);
					existantValues.clear();
					existantValues.addAll(getAllRowValuesFor(y, valid_solution));
					existantValues.addAll(getAllColumnValuesFor(x, valid_solution));
					existantValues.addAll(sub.getDefinedValues());
					if (existantValues.size() == 9)
						return mistake(valid_solution, _lastGuessPosition.pop());
					else {
						_lastGuess.add(generatePossibleValue(existantValues));
						valid_solution[y][x] = _lastGuess.peek();
						_lastGuessPosition.add(new Integer[] {y,x});
						return bruteForceIt(valid_solution);
					}
				}
			}
		}
		return valid_solution;
	}
	
	private int[][] mistake(int[][] current_grid, Integer[] errors) {
		HashSet<Integer> existantValues = new HashSet<Integer>();
		int[][] valid_solution = current_grid;
		valid_solution[errors[0]][errors[1]] = 0;
		Subgrilla sub = new Subgrilla(valid_solution,errors[0],errors[1]);
		existantValues.addAll(getAllRowValuesFor(errors[0], valid_solution));
		existantValues.addAll(getAllColumnValuesFor(errors[1], valid_solution));
		existantValues.addAll(sub.getDefinedValues());
		if (existantValues.size() == 8)
			return(mistake(valid_solution, _lastGuessPosition.pop()));
		_lastGuess.add(generatePossibleValue(existantValues, _lastGuess.pop()));
		valid_solution[errors[0]][errors[1]] = _lastGuess.peek();
		
		return bruteForceIt(valid_solution);
	}

	private int[][] solveSingleNumbersAndCopyGrid() {
		int[][] valid_solution = copyGrid();
		HashSet<Integer> existantValues = new HashSet<Integer>();
		Subgrilla sub;
		for (int y = 0; y < _grid.length(); y++) {
			for (int x = 0; x < _grid.length(); x++) {
				if (valid_solution[y][x] == 0) {
					sub = new Subgrilla(valid_solution,y,x);
					existantValues.clear();
					existantValues.addAll(getAllColumnValuesFor(x, valid_solution));
					existantValues.addAll(getAllRowValuesFor(y, valid_solution));
					existantValues.addAll(sub.getDefinedValues());
					if (existantValues.size() == 8) {
						_lastGuess.add(generatePossibleValue(existantValues));
						valid_solution[y][x] = _lastGuess.peek();
						_lastGuessPosition.add(new Integer[] {y,x});
					}
				}
			}
		}
		return valid_solution;
	}
	
	private int[][] copyGrid() {
		int[][] grid = new int[9][9];
		for (int y = 0; y < _grid.length(); y++) {
			for (int x = 0; x < _grid.length(); x++) {
				if (_grid.getValue(y, x) != 0) {
					grid[y][x] = _grid.getValue(y, x);
				}
			}
		}
		return grid;
	}

	private int[][] solveSingleNumbers(int[][] current_grid) {
		int[][] valid_solution = current_grid;
		HashSet<Integer> existantValues = new HashSet<Integer>();
		Subgrilla sub;
		for (int y = 0; y < _grid.length(); y++) {
			for (int x = 0; x < _grid.length(); x++) {
				if (valid_solution[y][x] == 0) {
					existantValues.clear();
					sub = new Subgrilla(valid_solution,y,x);
					existantValues.addAll(getAllRowValuesFor(y, valid_solution));
					existantValues.addAll(getAllColumnValuesFor(x, valid_solution));
					existantValues.addAll(sub.getDefinedValues());
					if (existantValues.size() == 8) {
						_lastGuess.add(generatePossibleValue(existantValues));
						valid_solution[y][x] = _lastGuess.peek();
						_lastGuessPosition.add(new Integer[] {y,x});
					}
				}
			}
		}
		return valid_solution;
	}
	
	private Set<Integer> getAllColumnValuesFor(int column, int[][] grid) {
		HashSet<Integer> values = new HashSet<Integer>();
		for (int row = 0; row < grid.length; row++) {
			values.add(grid[row][column]);
		}
		values.remove(0);
		return values;
	}
	
	private Set<Integer> getAllRowValuesFor(int row, int[][] grid) {
		HashSet<Integer> values = new HashSet<Integer>();
		for (int col = 0; col < grid[0].length; col++) {
			values.add(grid[row][col]);
		}
		values.remove(0);
		return values;
	}

	private Integer generatePossibleValue(HashSet<Integer> existantValues) {
		for (Integer i = 1; i <= 9; i++) {
			if (!existantValues.contains(i))
				return i;
		}
		return 0;
	}

	private Integer generatePossibleValue(HashSet<Integer> existantValues, int startValue) {
		if (startValue >9 || startValue <0)
			throw new IllegalArgumentException("valor inicial fuera de rango");
		boolean firstPass = true;
		for (Integer i = startValue; i <= 9; i++) {
			if (!existantValues.contains(i))
				return i;
			if (i == 9 && firstPass) {
				i = 1;
				firstPass = false;
			}
		}
		return 0;
	}

}
