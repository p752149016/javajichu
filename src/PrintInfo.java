import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrintInfo {

	static int exitCode = 0;
	public void synchronization(){
//		String command ="ps -ef";
//		List<String> params = new ArrayList<String>();
//		for(String s : command.split(" "))
//			params.add(s);
//        params.add("rsync -vzrtopg --port=873 --progress --delete longwel@111.230.52.206::tomcat  /home/wel/t6/webapps/OrderDinner --password-file=/home/useregi/tools/password.txt");
        String[] params = {"/bin/sh", "-c", "ps -ef|grep rsync"};
        ProcessBuilder processBuilder = new ProcessBuilder(params);
        processBuilder.redirectErrorStream(true);
        try {
            Process process = processBuilder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            List<String> list = new ArrayList<String>();
            while ((line = br.readLine()) != null) {
            	regEx(line, list);
                System.out.println(line);
            }
            exitCode = process.waitFor();
            System.out.println("exitCode = "+exitCode);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(df.format(new Date()));
            for(String s : list){
            	System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	public void regEx(String str, List<String> list){
		int state = 0;
		Matcher m1 = Pattern.compile("grep").matcher(str);
		while(m1.find()){
			state = 1;
		}
		Matcher m2 = Pattern.compile("\\b(\\d+)\\b").matcher(str.substring(0, 21));
		if(state == 0){
			while(m2.find()){
				list.add(m2.group());
			}
		}
	}
	
	class MyTimerTask extends TimerTask{
		@Override
		public void run() {
			System.out.println(new Date());
			while(true){
				
			}
		}
	}
	
	public TimerTask myTimerTask(){
		return new MyTimerTask();
	}
	
	//定时器
	public static void main(String[] args){
		PrintInfo oneTimerTask = new PrintInfo();
		Timer timer = new Timer();
		TimerTask timerTask = oneTimerTask.myTimerTask();
		timer.schedule(timerTask, 0, 1000);
	}
}
