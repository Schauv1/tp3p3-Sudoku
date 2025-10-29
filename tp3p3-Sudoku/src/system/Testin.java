package system;

public class Testin {

	public static void main(String[] args) {
		int[][] grd = new int[][] {
			{6,0,0,8,7,4,0,1,0},
			{0,0,9,0,3,6,0,0,0},
			{0,0,0,1,9,0,8,0,0},
			{7,9,4,6,0,0,0,0,0},
			{0,0,1,0,8,9,4,0,0},
			{0,0,0,4,1,0,0,6,9},
			{0,7,0,0,5,0,0,9,0},
			{0,5,3,9,0,7,6,0,0},
			{9,0,2,0,6,1,0,4,7}
		};
		Grilla grid = new Grilla(grd);
		Solver solver = new Solver();
		int[][] solved = solver.bruteForceIt(grid);
		grid = null;
		grid = new Grilla(36);
		for (int row = 0;row < solved.length; row++) {
			System.out.println(" ");
			for (int col = 0;col < solved[row].length; col++) {
				System.out.print(solved[row][col] + " ");
			}
		}
		/*
		solved = solver.bruteForceIt(grid);
		
		for (int row = 0;row < solved.length; row++) {
			System.out.println(" ");
			for (int col = 0;col < solved[row].length; col++) {
				System.out.print(solved[row][col] + " ");
			}
		}
		*/
	}

}
