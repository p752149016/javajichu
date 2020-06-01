package nineteen;

import static nineteen.Sinnal.*;
//枚举类创建的小型状态机Demo
//还使用了无需enum类型修饰实例的方法（个人认为还是用上比较好）
public class Exercise1 {
	static int num = 3;
	Sinnal color = RED;
	public void change(){
		//状态机
		switch(color){
		case RED: color = GREEN;
		break;
		case GREEN: color = YELLOW;
		break;
		case YELLOW: color = RED;
		}
	}
	@Override
	public String toString(){
		return "The traffic light is " + color;
	}
	public static void main(String[] args){
		Exercise1 t = new Exercise1();
		for(int i = 0; i < 7; i++){
			System.out.println(t);
			t.change();
		}
	}
}
