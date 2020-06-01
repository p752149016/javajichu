package eighteen;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

class DirFilter implements FilenameFilter{
	private Pattern pattern;
	public DirFilter(String regex){
		pattern = Pattern.compile(regex);
	}
	@Override
	public boolean accept(File arg0, String arg1) {
		return pattern.matcher(arg1).matches();
	}
}

public class Exercise3 {
	public static void main(String[] args){
		String path = "F:/PLF";
		File file = new File(path);
		long i = 0;
		String[] list;
//		if(args.length == 0){
//			list = file.list();
//		}else{
			list = file.list(new DirFilter(".*\\.txt"));
//		}
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		
		for(String dirItem : list){
			i = i + new File(path, dirItem).length();
			System.out.println(dirItem);
		}
		System.out.println(i);
	}
}
