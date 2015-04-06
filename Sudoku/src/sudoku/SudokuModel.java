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
}
