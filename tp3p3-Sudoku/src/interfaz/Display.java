package interfaz;

import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import system.BruteForce;
import system.SValidator;

public class Display extends JFrame implements ActionListener, KeyListener{
	
	public JToggleButton[][] buttons;
	private final int dimension = 9;
	private int[][] sudoku;
	private BruteForce bruteForce;
	private final int sudokuWidth = 560, sudokuHeight = 560;
	private JPanel sudokuPanel;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem solve, clear;
	private SValidator validator = new SValidator();
	
	public Display(){
		this.setTitle("Sudoku AutoCheck");
		this.setSize(sudokuWidth, sudokuHeight);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new FlowLayout());
		
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		menu = new JMenu("Brute Force");
		menuBar.add(menu);
		solve = new JMenuItem("Solve");
		clear = new JMenuItem("Clear");
		clear.addActionListener(this);
		solve.addActionListener(this);
		menu.add(clear);
		menu.add(solve);
		sudokuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		sudokuPanel.setPreferredSize(new Dimension(sudokuWidth, sudokuHeight));
		
		sudoku = new int[dimension][dimension];
		
		buttons = new JToggleButton[dimension][dimension];
		
		for(int row = 0; row < dimension; row++){
			for(int col = 0; col < dimension; col++){			
				buttons[row][col] = new JToggleButton();
				buttons[row][col].addActionListener(this);
				buttons[row][col].addKeyListener(this);
				buttons[row][col].setPreferredSize(new Dimension(50, 50));
				this.add(buttons[row][col]);
			}
		}
		
		this.add(sudokuPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Clear")){
			for(int row = 0; row < dimension; row++){
				for(int col = 0; col < dimension; col++){
					buttons[row][col].setText("");
				}
			}
			return;
		}else if(e.getActionCommand().equals("Solve")){
			for(int row = 0; row < dimension; row++){
				for(int col = 0; col < dimension; col++){
					if(buttons[row][col].getText().equals("")){
						sudoku[row][col] = 0;
						continue;
					}
					int number = Integer.parseInt(buttons[row][col].getText());
					
					sudoku[row][col] = number;
				}
			}
			solve();
			for(int row = 0; row < dimension; row++){
				for(int col = 0; col < dimension; col++){
					buttons[row][col].setText(""+sudoku[row][col]);
					buttons[row][col].validate();
				}
			}
			
			return;
		}
		for(int row = 0; row < dimension; row++){
			for(int col = 0; col < dimension; col++){
				buttons[row][col].setSelected(false);
			}
		}
		((JToggleButton) e.getSource()).setSelected(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_1){
			typeNumber(1);
		}else if(e.getKeyCode() == KeyEvent.VK_2){
			typeNumber(2);
		}else if(e.getKeyCode() == KeyEvent.VK_3){
			typeNumber(3);
		}else if(e.getKeyCode() == KeyEvent.VK_4){
			typeNumber(4);
		}else if(e.getKeyCode() == KeyEvent.VK_5){
			typeNumber(5);
		}else if(e.getKeyCode() == KeyEvent.VK_6){
			typeNumber(6);
		}else if(e.getKeyCode() == KeyEvent.VK_7){
			typeNumber(7);
		}else if(e.getKeyCode() == KeyEvent.VK_8){
			typeNumber(8);
		}else if(e.getKeyCode() == KeyEvent.VK_9){
			typeNumber(9);
		}else if(e.getKeyCode() == KeyEvent.VK_0){
			typeNumber(0);
		}
	}

	private void typeNumber(int number){
		for(int row = 0; row < dimension; row++){
			for(int col = 0; col < dimension; col++){
				if(buttons[row][col].isSelected()){
					if(number == 0){
						buttons[row][col].setText("");
						return;
					}
					buttons[row][col].setText(""+number);
					buttons[row][col].validate();
					return;
				}
			}
		}
	}

	private void solve() {
		bruteForce = new BruteForce(sudoku);
		if (validateSudoku())
			sudoku = bruteForce.solve();
	}
	
	private boolean validateSudoku() {
		if (validator.validateSudoku(sudoku))
			return true;
		else
			JOptionPane.showMessageDialog(null, "Sudoku no valido para resolucion");
		return false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}