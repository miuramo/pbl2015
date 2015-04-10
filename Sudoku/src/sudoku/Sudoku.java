package sudoku;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Sudoku extends JFrame {
	private static final long serialVersionUID = -8995261455025975788L;

	SudokuPanel mainP;
	
	JPanel buttonP;
	
	public Sudoku(){
		super("Sudoku");
		getContentPane().add(mainP = new SudokuPanel(this));
		
		buttonP = new JPanel();
		
		JButton jb = new JButton("Shuffle");
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainP.shuffle();
//				mainP.repaint();
			}
		});
		buttonP.add(jb);
		JButton jb2 = new JButton("2");
		jb2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//mainP.test();
				mainP.check();
			}
		});
		buttonP.add(jb2);
		
		
		getContentPane().add(buttonP, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		
		
	}
	
	public static Sudoku theapp; 

	public static void main(String[] args){
		theapp = new Sudoku();
	}
	

}
