package main.java.com.johann.algorithm.jianzhi;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Jz03_DuplicationInArray {

    /***
     * 题目描述：找出数组中重复的数字
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，
     * 也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     * 示例 1：
     * 输入：
     * [2, 3, 1, 0, 2, 5, 3]
     * 输出：2 或 3
     * 限制：
     *  2 <= n <= 100000
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * https://github.com/apachecn/apachecn-algo-zh/blob/master/docs/jianzhioffer/java/03_01_DuplicationInArray.md
     */

    public static int[] newArray(int n){
        int[] arr = new int[n];
        Random random = new Random();
        for (int i=0;i<arr.length;i++){
            arr[i] = random.nextInt(n);
        }
        return arr;
    }

    /**
     *  1， 使用双重循环进行遍历对比进行查找重复元素。这种方法的时间复杂度是 O(n^2)
     *  Ο最坏情况复杂度
     *  Theta(Θ) 平均用例复杂度
     *  Omega(Ω) 最佳情况复杂度
     * @return
     */

    /**
     * 2,利用哈希表不可重复的特性，遍历数组将数组中的元素放入到哈希表（Set）中，如果某个元素无法放入，则该元素重复
     *  时间复杂度 O(n) 空间复杂度 O(n)
     * @return
     */
    public static int solution(int [] arr){
        int repeat = -1;
        Set<Integer> set = new HashSet<>(arr.length);
        for (int i=0;i<arr.length;i++){
            if (!set.add(arr[i])){
                repeat = arr[i];
                break;
            }
        }
        return repeat;
    }

    /**
    *  长度为 n，元素的数值范围也为 n，如果没有重复元素，那么数组每个下标对应的值与下标相等。
     *  从头到尾遍历数组，当扫描到下标 i 的数字 nums[i]：
     *      如果等于 i，继续向下扫描；
     *      如果不等于 i，拿它与第 nums[i] 个数进行比较，如果相等，说明有重复值，返回 nums[i]。如果不相等，就把第 i 个数 和第 nums[i] 个数交换。
     *      重复这个比较交换的过程,直到索引 i 上的元素值也为i（如果数组中有 元素 i 的话）。
     * 此算法时间复杂度为 O(n)，因为每个元素最多只要两次交换，就能确定位置。空间复杂度为 O(1)。
     *
    * @Author: Johann
    * @Date: 2021/4/2
    */
    public static int soluion2False(int [] arr){
        int repeat = -1;
        for(int i=0;i<arr.length;i++){
            //如果数组索引 与 当前索引的数组元素相等，则遍历下一个索引

            // ※此处不用用 if，而应该改用 while。※
            // 当数组元素为 {1 , 2 , 3 , 2},使用 if 将返回 -1
            // 如果数组中有与当前索引 i 相同的元素值，while是为了确保这个元素一定会被交换到 索引 i 上
            if(arr[i] != i){
                // 如果数组索引 与 当前索引的数组元素 不相等，则比较当前索引(i)的数组元素 与 索引为arr[i]的数组元素
                // 若相等，即存在重复元素，返回当前数组元素
                if(arr[i] == arr[arr[i]]){
                    repeat = arr[i];
                    break;
                }else{ // 否则，交换索引为 i,arr[i]的元素
                    swap(arr,i,arr[i]);
                }
            }
        }
        return repeat;
    }

    public static int soluion2(int[] arr) {
        for(int i=0;i<arr.length;i++){
            //如果数组索引 与 当前索引的数组元素相等，则遍历下一个索引
            while (arr[i]!=i){
                // 如果数组索引 与 当前索引的数组元素 不相等，则比较当前索引(i)的数组元素 与 索引为arr[i]的数组元素
                // 若相等，即存在重复元素，返回当前数组元素
                if(arr[i]==arr[arr[i]]){
                    return arr[i];
                }
                swap(arr,i,arr[i]);
            }
        }
        return -1;
    }

    /** 
    * @Description: 数组元素交换
    * @Param: [arr, i, j] 
    * @return: void 
    * @Author: Johann 
    * @Date: 2021/4/2 
    */ 
    public static void swap(int[] arr,int i,int j){
        int m = arr[i];
        arr[i] = arr[j];
        arr[j] = m;
    }

    // java 不借用中间数字，交换数值
    public static  void swap2(int m,int n){
        m = m - n;
        n = n + m;
        m = n - m;
        System.out.println("交换完，m : "+m+",n : "+n);
    }



    public static void main(String[] args) {
        //int[] arr = newArray(100000);
        //int [] arr = {2, 3, 1, 0, 2, 5, 3};
        int [] arr = {1 , 2 , 3 , 2 };
        long start = System.currentTimeMillis();
        //int repeat = solution(arr);
        //int repeat = soluion2(arr);
        int repeat = soluion2False(arr);
        long end = System.currentTimeMillis();
        System.out.println("用时："+ (end - start));
        System.out.println(repeat);


    }

}
