package main.java.com.johann;

import java.util.HashMap;

/**
 * @ClassName: Test0514
 * @Description: TODO
 * @Author: Johann
 * @Date: 2020-05-14 16:40
 **/
public class Test0514 {
    
    /** 
    * @Description: 两数之和
    * @Param: [array, target] 
    * @return: void 
    * @Author: Johann 
    * @Date: 2020/5/14 
    */ 
    public static void twoSum(int [] array,int target){
        int size = array.length;
        for(int i =0;i<size;i++){
            for(int j=0;j<size;j++){
                if(i!=j && array[i]+array[j]==target){
                    System.out.println("符合条件的数组索引为：array["+i+"] + array["+j+"] = "+target);
                    return;
                }
            }
        }
    }
    public static int[] twoSum2(int[]array,int target){
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<array.length;i++){
            if(map.containsKey(target-array[i])){
                return new int[]{i,map.get(target-array[i])};
            }
            map.put(array[i],i);
        }
        return null;
    }

    public static void main(String[] args) {
        int [] a = {0,1,2,3,4,5,6};
        int[] b = twoSum2(a,10);
        System.out.println(b[0]+"==="+b[1]);
    }
}
