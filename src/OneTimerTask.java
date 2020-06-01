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

public class OneTimerTask {
	
	static int exitCode = 0;
	static String state = null;
	
	final int cycleTime = 300000;//同步定时器循环时间ms
	final int timeOutTime = 7200000;//检测同步定时器的定时器循环时间ms
	
	class MyTimerTask extends TimerTask{
		@Override
		public void run() {
			if(exitCode != 1){
//				exitCode = 1;
//				count = 0;
				synchronization();
//			}else{
//				count++;
//				System.out.println(count);
//				restart(count);
			}
		}
	}
	public TimerTask myTimerTask(){
		return new MyTimerTask();
	}
	
	class MyTimerTask2 extends TimerTask{
		@Override
		public void run() {
			restart();
		}
	}
	public TimerTask myTimerTask2(){
		return new MyTimerTask2();
	}
	
	//同步方法
	public void synchronization(){
		String command ="rsync -vzrtopg --port=873 --progress --delete longwel@111.230.52.206::test /home/useregi/tools/office --password-file=/home/useregi/tools/password.txt";
		List<String> params = new ArrayList<String>();
		for(String s : command.split(" "))
			params.add(s);
//        params.add("rsync -vzrtopg --port=873 --progress --delete longwel@111.230.52.206::tomcat  /home/wel/t6/webapps/OrderDinner --password-file=/home/useregi/tools/password.txt");
        
        ProcessBuilder processBuilder = new ProcessBuilder(params);
        processBuilder.redirectErrorStream(true);
        try {
            Process process = processBuilder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            exitCode = process.waitFor();
            System.out.println("exitCode = "+exitCode);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(df.format(new Date()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	//线程卡住时关闭线程
	public void restart(){
		String id = checkThread();
		if(state != null && id != null && id.equals(state)){
			killThread(id);
			try {
				Thread.sleep(10000);//等待线程关闭
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        exitCode = 0;
		}else{
			state = id;
		}
	}
	
	//检查线程状态
	public String checkThread(){
		String[] params = {"/bin/sh", "-c", "ps -ef|grep \"rsync -vzrtopg --port=873 --progress --delete longwel@111.230.52.206::test\""};//会将后面的命令当一个完整的字符串运行（这样才能运行管道符"|"）
        ProcessBuilder processBuilder = new ProcessBuilder(params);
        processBuilder.redirectErrorStream(true);
        try {
            Process process = processBuilder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            List<String> list = new ArrayList<String>();
            while ((line = br.readLine()) != null) {
            	regEx(line, list);
            }
            int code = process.waitFor();
            System.out.println("checkExitCode = "+code);
            System.out.println("线程检查完毕");
            if(list.size() > 0){
            	return(list.get(0));
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		return null;
	}
	
	//关闭线程
	public void killThread(String s){
		String[] params = {"/bin/sh", "-c", "kill " + s};
        ProcessBuilder processBuilder = new ProcessBuilder(params);
        processBuilder.redirectErrorStream(true);
        try {
            Process process = processBuilder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            int code = process.waitFor();
            System.out.println("killExitCode = "+code);
            System.out.println("线程" + s + "已经被关闭");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	//正则获取进程ID
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

	//定时器
	public static void main(String[] args){
		OneTimerTask oneTimerTask = new OneTimerTask();
		//同步定时器
		Timer timer = new Timer();
		TimerTask timerTask = oneTimerTask.myTimerTask();
		timer.schedule(timerTask, 0, oneTimerTask.cycleTime);
		//检查同步是否正常运行定时器
		Timer timer2 = new Timer();
		TimerTask timerTask2 = oneTimerTask.myTimerTask2();
		timer2.schedule(timerTask2, 0, oneTimerTask.timeOutTime);
	}
}