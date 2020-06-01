package seventeen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

//实现将一个列表隔一个位置插入另一个列表
public class Exercise7 {
	public static void main(String[] args){
		ArrayList<String> al = new ArrayList<>(Arrays.asList("a1", "b1", "c1", "d1", "e1", "f1", "h1", "g1"));
		LinkedList<String> ll = new LinkedList<>(Arrays.asList("a2", "b2", "c2", "d2", "e2", "f2", "h2", "g2"));
		Iterator<String> ali = al.iterator();
		Iterator<String> lli = ll.iterator();
		System.out.println("ali: ");
		while(ali.hasNext()){
			System.out.println(ali.next());
		}
		System.out.println("lli:  ");
		while(lli.hasNext()){
			System.out.println(lli.next());
		}
		ListIterator<String> all = al.listIterator(al.size());
		ListIterator<String> lll = ll.listIterator(al.size());
		while(lll.hasPrevious()){
			lll.previous();
			if(all.hasPrevious()){
				lll.add(all.previous());
				lll.previous();
			}
		}
		while(lll.hasNext()){
			System.out.print(lll.next());
		}
	}
}
