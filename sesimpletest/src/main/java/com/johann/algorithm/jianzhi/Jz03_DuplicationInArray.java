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
     * @return
     */
    public static int solution(int [] arr){
        int repeat = -1;
        Set<Integer> set = new HashSet<>(arr.length);
        for (int i=0;i<arr.length;i++){
            if (set.add(arr[i])){

            }else{
                repeat = arr[i];
                break;
            }
        }
        return repeat;
    }

    public static void main(String[] args) {
        //int[] arr = newArray(100000);
        int [] arr = {2, 3, 1, 0, 2, 5, 3};
        long start = System.currentTimeMillis();
        int repeat = solution(arr);
        long end = System.currentTimeMillis();
        System.out.println("用时："+ (end - start));
        System.out.println(repeat);
    }

}
