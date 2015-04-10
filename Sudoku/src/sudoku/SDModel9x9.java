package sudoku;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SDModel9x9 {
	public int[][] m9x9;
	public boolean[][] error9x9;
	public SDModel9x9(){
		reset();
		setIA(iagen());		
	}
	public SDModel9x9(SudokuModel sudokuModel) {
		reset();
		for(int c=0;c<9;c++){
			for(int r=0;r<9;r++){
				m9x9[c][r] = sudokuModel.getInt(c,r);
				error9x9[c][r] = sudokuModel.getError(c,r);
			}
		}
	}
	public SudokuModel toSudokuModel(){
		SudokuModel sm = new SudokuModel(this);
		return sm;
	}
	/**
	 *  •”•ªƒ‚ƒfƒ‹‚ð•Ô‚·
	 * @param c column (0,1,2)
	 * @param r row (0,1,2)
	 * @return •”•ªƒ‚ƒfƒ‹3x3
	 */
	public Model3x3 get3x3Model(int c, int r){
		Model3x3 subm = new Model3x3();
		int sc = c*3;
		int sr = r*3;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				subm.m3x3[i][j] = m9x9[i+sc][j+sr];
				subm.error3x3[i][j] = error9x9[i+sc][j+sr];
			}
		}
		return subm;
	}
	public void paintComponent(Graphics2D g2d){
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if (error9x9[i][j]) g2d.setColor(Color.red);
				else g2d.setColor(Color.black);
				g2d.drawString(String.valueOf(m9x9[i][j]), (i+1)*20, (j+1)*20);				
			}
		}
	}
	public void reset(){
		m9x9 = new int[9][9];
		error9x9 = new boolean[9][9];
	}
	public int[] col(int c){
		int col[] = new int[9];
		for(int i=0;i<9;i++){
			col[i] = m9x9[c][i];
		}
		return col;
	}
	public int[] row(int r){
		int row[] = new int[9];
		for(int i=0;i<9;i++){
			row[i] = m9x9[i][r];
		}
		return row;
	}
	public void setIA(ArrayList<Integer> ia){
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				m9x9[i][j] = ia.get(i*9+j);
			}			
		}
	}
	public ArrayList<Integer> iagen(){
		ArrayList<Integer> ia = new ArrayList<Integer>();
		for(int i=1;i<=9;i++){
			ia.add(i);
		}
		Collections.shuffle(ia);
		return ia;
	}
	public static void printIA(int[] a){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
		System.out.println("");
	}
	public void debugprint() {
		for(int r=0;r<9;r++){
			for(int c=0;c<9;c++){
				System.out.print(m9x9[c][r]+" ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	public void errorcheck(){
		for(int r=0;r<9;r++){
			errorcheck_row(r);
		}
		for(int c=0;c<9;c++){
			errorcheck_col(c);
		}
	}
	private void errorcheck_col(int c) {
		int col[] = col(c);
		Arrays.sort(col);
		int count[] = new int[10];
		for(int i=0;i<9;i++) count[col[i]]++;
		for(int i=1;i<10;i++) if (count[i]>1) markErrorCol_Val(c,i);
	}
	private void errorcheck_row(int r) {
		int row[] = row(r);
		Arrays.sort(row);
		int count[] = new int[10];
		for(int i=0;i<9;i++) count[row[i]]++;
		for(int i=1;i<10;i++) if (count[i]>1) markErrorRow_Val(r,i);
	}
	private void markErrorCol_Val(int c, int v){
		for(int i=0;i<9;i++){
			if (m9x9[c][i]==v) error9x9[c][i]=true;
		}
	}
	private void markErrorRow_Val(int r, int v){
		for(int i=0;i<9;i++){
			if (m9x9[i][r]==v) error9x9[i][r]=true;
		}
	}
}
