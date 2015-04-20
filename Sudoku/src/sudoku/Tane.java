package sudoku;

import java.util.Random;

public class Tane {

	int[][] a = new int[][]{{1,9,5},{8,4,3},{6,2,7}};
	public Tane(){
	}
	public Tane(boolean isrand){
		if (isrand){
			Random r = new Random();
			int c;
			c = r.nextInt(3);
			for(int i=0;i<c;i++) colrot();
			c = r.nextInt(3);
			for(int i=0;i<c;i++) tenchi();
			c = r.nextInt(3);
			for(int i=0;i<c;i++) rowrot();
		}
	}
	public Tane(Tane o){
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				a[i][j] = o.a[i][j];
	}
	public Tane clone(){
		return new Tane(this);
	}
	public Tane colrotT(){
		Tane t = new Tane();
		t.setCol(0, this.getCol(1));
		t.setCol(1, this.getCol(2));
		t.setCol(2, this.getCol(0));
		return t;
	}
	public void colrot(){
		Tane t = colrotT();
		a = t.a;
	}
	public Tane rowrotT(){
		Tane t = new Tane();
		t.setRow(0, this.getRow(1));
		t.setRow(1, this.getRow(2));
		t.setRow(2, this.getRow(0));
		return t;
	}
	public void rowrot(){
		Tane t = rowrotT();
		a = t.a;
	}
	public Tane tenchiT(){
		Tane t = new Tane();
		t.a[1][0] = a[0][1];
		t.a[2][0] = a[0][2];
		t.a[2][1] = a[1][2];
		t.a[0][1] = a[1][0];
		t.a[0][2] = a[2][0];
		t.a[1][2] = a[2][1];
		for(int i=0;i<3;i++) t.a[i][i] = a[i][i];
		return t;
	}
	public void tenchi(){
		Tane t = tenchiT();
		a = t.a;
	}
	public int[] getCol(int c){
		int[] p = new int[3];
		for(int i=0;i<3;i++) p[i] = a[c][i];
		return p;
	}
	public void setCol(int c, int[] o){
		for(int i=0;i<3;i++) a[c][i] = o[i];
	}
	public int[] getRow(int r){
		int[] p = new int[3];
		for(int i=0;i<3;i++) p[i] = a[i][r];
		return p;
	}
	public void setRow(int r, int[] o){
		for(int i=0;i<3;i++) a[i][r] = o[i];
	}
	public void print(){
		for(int r=0;r<3;r++){
			for(int c=0;c<3;c++)
				System.out.print(a[c][r]+" ");
			System.out.println("");
		}
		System.out.println("");
	}
	public static void main(String[] args) {
		Tane t = new Tane(true);
		Tane[] ta = new Tane[9];
		ta[0] = t;
		ta[1] = ta[0].colrotT();
		ta[2] = ta[1].colrotT();
		ta[3] = ta[0].rowrotT();
		ta[4] = ta[1].rowrotT();
		ta[5] = ta[2].rowrotT();
		ta[6] = ta[3].rowrotT();
		ta[7] = ta[4].rowrotT();
		ta[8] = ta[5].rowrotT();
		
		for(int i=0;i<9;i++) ta[i].print();
	}
	public void setValues(int n, Model3x3[][] model3x3) {
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				int c = (a[i][j]-1)%3;
				int r = (a[i][j]-1)/3;
				model3x3[i][j].m3x3[c][r] = n;
			}
		}
	}

}
