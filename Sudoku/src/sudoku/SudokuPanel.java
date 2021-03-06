package sudoku;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;


public class SudokuPanel extends JPanel implements MouseListener {
	private static final long serialVersionUID = -1611925332039214586L;

	Sudoku sudoku;

//	int[][] numAry = new int[10][10];
	SudokuModel model;
	
	public SudokuPanel(Sudoku _sudoku){
		sudoku = _sudoku;
		model = new SudokuModel();
		addMouseListener(this);
	}

	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g.clearRect(0,0,getWidth(),getHeight());
		model.paintComponent(g2d);
	}
	public Dimension getPreferredSize(){
		return new Dimension(230,230);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int px = e.getX()/70;
		int py = e.getY()/70;
//		System.out.println(px+" "+py);
		model.debugprint(px,py);
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}

	public void test() {
		SDModel9x9 m2 = model.toSDModel9x9();
		m2.debugprint();
	}

	public void shuffle() {
		model = new SudokuModel();
		repaint();
	}
	public void check(){
		SDModel9x9 m2 = model.toSDModel9x9();
		m2.errorcheck();
		model = m2.toSudokuModel();
		repaint();
	}

	public void save() {
		ArrayList<SudokuModel> sdkary = new ArrayList<SudokuModel>();
		sdkary.add(model);
		AbstractDataStore.saveToFile(null, sdkary, ".sdk");

	}

	public void load() {
		ArrayList<SudokuModel> assary = AbstractDataStore.loadFromFile(null, ".sdk");

		model = assary.get(0);
		repaint();
	}
}
