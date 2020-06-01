import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateDemo {
	public static void main(String args[]){
		Date date = new Date();
		System.out.println(date.toString());
		System.out.println(date.getTime());
		System.out.println(new java.sql.Date(new java.util.Date().getTime()));//可存入数据库的当前时间（年月日）
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
	    System.out.println("当前时间为: " + ft.format(date));
	    System.out.printf("全部日期和时间信息：%tF%n",date);
	    Calendar c = Calendar.getInstance();//默认是当前日期
	    System.out.println(c.get(Calendar.MONTH) + 1);
	    c.add(Calendar.YEAR, +10);
	    System.out.println(c.getTime());//当前日期后十年
	}
}
