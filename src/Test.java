import java.util.ArrayList;
import java.util.List;


public class Test {
   public static void main(String args[]) {
	   Tank t1 = new Tank();
	   Tank t2 = new Tank();
	   t1.level = 9;
	   t2.level = 47;
	   t1 = t2;
	   t1.level = 27;
	   
	   System.out.println(t2.level+" "+t1.level);
   }
}

class Tank{
	int level;
}
