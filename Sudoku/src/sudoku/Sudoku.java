package sudoku;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Sudoku extends JFrame {
	private static final long serialVersionUID = -8995261455025975788L;

	SudokuPanel mainP;
	
	public Sudoku(){
		super("Sudoku");
		getContentPane().add(mainP = new SudokuPanel(this));
		
		JButton jb = new JButton("Shuffle");
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
//				mainP.shuffle();
				mainP.repaint();
			}
		});
		getContentPane().add(jb, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		
		
	}
	
	public static Sudoku theapp; 

	public static void main(String[] args){
		theapp = new Sudoku();
	}
	

}
