package fifteen;

interface Coffe{
	void drink();
}

class OneCoffe implements Coffe{

	@Override
	public void drink() {
		System.out.println("OneCoffe");
	}
	
}

//抽象装饰器类
abstract class Decorator implements Coffe{
	private Coffe coffe;
	public Decorator(Coffe c) {
		this.coffe = c;
	}
	
	public void drink(){
		coffe.drink();
	}
}

//实体装饰器类
class DecoratorImpl extends Decorator{

	public DecoratorImpl(Coffe c) {
		super(c);
	}
	
	public void milk(){
		System.out.println("加入牛奶");
	}
	public void foam(){
		System.out.println("加入泡沫");
	}
	public void chocolates(){
		System.out.println("加入巧克力");
	}
	public void caramel(){
		System.out.println("加入焦糖");
	}
	public void cream(){
		System.out.println("加入生奶油");
	}
	
}

public class Exercise38 {

	public static void main(String[] args){
		DecoratorImpl di = new DecoratorImpl(new OneCoffe());
		di.drink();
		di.milk();
	}
}
