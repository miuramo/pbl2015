package sudoku;

import java.util.ArrayList;
import java.util.Collections;

public class Util {
	public static void print(ArrayList<Integer> numlist){
		for(int i: numlist) System.out.print(i+" ");
		System.out.println("");
	}
	
	public static ArrayList<Integer> numShuffle(ArrayList<Integer> numlist){
		Collections.shuffle(numlist);
		return numlist;
	}

	public static ArrayList<Integer> generate(){
		ArrayList<Integer> numlist = new ArrayList<Integer>();
		for(int i=1;i<=9;i++){
			numlist.add(i);
		}
		return numlist;
	}
	// pos‚Í0,1,2
	public static ArrayList<Integer> three(ArrayList<Integer> list, int pos){
		ArrayList<Integer> s = new ArrayList<Integer>();
		s.add(list.get(pos*3));
		s.add(list.get(pos*3+1));
		s.add(list.get(pos*3+2));
		return s;
 	}
	public static void avoidOverlap(ArrayList<Integer> l1, ArrayList<Integer> l2, int block3){
		ArrayList<Integer> l1s = three(l1, block3);
		for(int i=block3*3;i<block3*3+3;i++){
			int l2i = l2.get(i);
			if (l1s.contains(l2i)){
				l2.remove(l2i);
				l2.add(l2i);
			}
		}
	}
}
