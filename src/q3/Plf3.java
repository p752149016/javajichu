package q3;

import q.Plf;
import q2.Plf2;

public class Plf3 extends Plf2{

	Plf backImplPlf1(){
		return new ImplPlf1();
	}
	public static void main(String[] args){
		Plf3 plf3 = new Plf3();
		plf3.backImplPlf1().def();
	}
}
