package sudoku;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

public class SudokuModel {
	public Model3x3[][] model3x3;

	public SudokuModel(){
		model3x3 = new Model3x3[3][3];
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				model3x3[i][j] = new Model3x3();
			}
		}

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

		ArrayList<Integer> ia = new ArrayList<Integer>();
		for(int i=1;i<=9;i++){
			ia.add(i);
		}
		Collections.shuffle(ia);

		for(int i=0;i<9;i++){
			ta[i].setValues(ia.get(i),model3x3);
		}
	}
	//	public SudokuModel(){
	//		model3x3 = new Model3x3[3][3];
	//		for(int i=0;i<3;i++){
	//			for(int j=0;j<3;j++){
	//				model3x3[i][j] = new Model3x3();
	//			}
	//		}
	//	}
	/**
	 * Create Same Model Converted
	 * @param sdModel9x9
	 */
	public SudokuModel(SDModel9x9 sdModel9x9) {
		model3x3 = new Model3x3[3][3];
		for(int c=0;c<3;c++){
			for(int r=0;r<3;r++){
				model3x3[c][r] = sdModel9x9.get3x3Model(c, r);
			}
		}
	}
	public SDModel9x9 toSDModel9x9(){
		SDModel9x9 ninemodel = new SDModel9x9(this);
		return ninemodel;
	}
	public void paintComponent(Graphics2D g2d){
		int tx = 70;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				g2d.translate(i*tx, j*tx);
				model3x3[i][j].paintComponent(g2d);
				g2d.translate(i*-tx, j*-tx);
			}
		}

	}
	public void debugprint(int px, int py) {
		model3x3[px][py].debugprint();		
	}
	public int getInt(int c, int r) {
		int mc = c/3;
		int mr = r/3;
		return model3x3[mc][mr].m3x3[c%3][r%3];
	}
	public boolean getError(int c, int r) {
		int mc = c/3;
		int mr = r/3;
		return model3x3[mc][mr].error3x3[c%3][r%3];
	}
}
