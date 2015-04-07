package sudoku;

import java.awt.Graphics2D;

public class SudokuModel {
	public Model3x3[][] model3x3;
	
	public SudokuModel(){
		model3x3 = new Model3x3[3][3];
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				model3x3[i][j] = new Model3x3();
			}
		}
	}
	/**
	 * “™‰¿‚Èƒ‚ƒfƒ‹‚ð‚Â‚­‚é
	 * @param sdModel9x9
	 */
	public SudokuModel(SDModel9x9 sdModel9x9) {
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
}
