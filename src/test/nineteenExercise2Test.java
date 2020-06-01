package test;

import java.util.ArrayList;
import java.util.List;

import nineteen.Exercise2;

import org.junit.Assert;
import org.junit.Test;

public class nineteenExercise2Test extends Exercise2{
	int[] height;
	@Test
    public void trap() {
		this.height = new int[]{1,2,3};
        if(height.length < 2){
            System.out.println(0);
        }
        height = creat(height);
        int result = 0;
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i < height.length; i++){
			if(height[i-1] > height[i]){
				//add
				list.add(i-1);
				//find low
				for(int x = i+1; x < height.length; x++){
					i = x-1;
					if(height[x-1] < height[x]){
						break;
					}
				}
			}
		}
        for(int i = 0; i < list.size()-1; i++){
            for(int x = i+1; x < list.size(); x++){
                if(height[list.get(i)] <= height[list.get(x)]){
                    result += sum(list.get(i), list.get(x));
                    i = x-1;
                    break;
                }
                if(x == list.size() - 1){
                    int a = max(list, i+1);
                    result += sum(list.get(i),list.get(a));
                    i = a-1;
                }
            }
        }
        System.out.println(result);
    }

    public int sum(int start, int end){
        int result;
        int low;
        if(start == end){
            return 0;
        }
        if(height[start] > height[end]){
            low = end;
        }else{
            low = start;
        }
        result = (end - start -1 ) * height[low];
        for(int i = start+1; i < end; i++){
            if(height[i] <= height[low]){
                result -= height[i];
            }else{
                result -= height[low];
            }
        }
        return result;
    }
    public int[] creat(int[] height){
		int[] res = new int[height.length+1];
		for(int i = 0; i < height.length; i++){
			res[i] = height[i];
		}
		res[height.length] = 0;
		return res;
	}
    public int max(List<Integer> list, int i){
    	int res = i;
    	for(;i < list.size(); i++){
    		if(height[list.get(i)] > height[list.get(res)]){
    			res = i;
    		}
    	}
    	return res;
    }
//	public void next(){
//		Assert.assertEquals("BOB", returnNext());
//		System.out.println("BOB".equals(returnNext()));
//	}
}
