package sudoku;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

public class Model3x3 {
	public int[][] m3x3;
	public Model3x3(){
		reset();
		setIA(iagen());		
	}
	public void paintComponent(Graphics2D g2d){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				g2d.drawString(String.valueOf(m3x3[i][j]), (i+1)*20, (j+1)*20);				
			}
		}

	}
	public void reset(){
		m3x3 = new int[3][3];
	}
	public int[] col(int c){
		int col[] = new int[3];
		for(int i=0;i<3;i++){
			col[i] = m3x3[c][i];
		}
		return col;
	}
	public int[] row(int r){
		int row[] = new int[3];
		for(int i=0;i<3;i++){
			row[i] = m3x3[i][r];
		}
		return row;
	}
	public void setIA(ArrayList<Integer> ia){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				m3x3[i][j] = ia.get(i*3+j);
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
		printIA(col(0));
		printIA(row(1));
		
		
	}

}
