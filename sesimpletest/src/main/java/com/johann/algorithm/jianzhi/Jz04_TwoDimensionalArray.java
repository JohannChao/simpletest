package com.johann.algorithm.jianzhi;

/**
 * @ClassName: Jz04_TwoDimensionalArray
 * @Description: TODO
 * @Author: Johann
 * @Date: 2021-04-28 10:22
 **/
public class Jz04_TwoDimensionalArray {

    /**
     * 题目描述：二维数组中的查找
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * 示例:
     * 现有矩阵 matrix 如下：
     * [
     *   [1,   4,  7, 11, 15],
     *   [2,   5,  8, 12, 19],
     *   [3,   6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     * 给定 target = 20，返回 false。
     * 限制：
     * 0 <= n <= 1000
     * 0 <= m <= 1000
     *
     * 注意：本题与主站 240 题相同：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
    */

    public static int[][] newArray(){
        //int[][] matrix = new int[1][];
        //int[][] matrix = {{1,2,3},{4,5,6}};
        int[][] matrix = {};
        return matrix;
    }

    /**
    * @Description:
     *      从右上角开始轮询这个二维数组;
     *      如果目标数等于 第一行最后一列的数字，直接返回 true;
     *      如果目标数字小于 第一行最后一列的数字，则砍掉最后一列（因为这一列以下的数字都大于第一个数字），即 n = n-1;
     *      如果目标数字大于 第一行最后一列的数字，则砍掉第一行（因为这一行之前的数字都小于最后一列数字），即 m = m+1;
     *      循环遍历;
    * @Param: [matrix, target]
    * @return: boolean
    * @Author: Johann
    * @Date: 2021/4/28
    */
    public static boolean solution(int[][] matrix,int target){
        if(matrix == null){
            return false;
        }
        if(matrix.length == 0){
            return false;
        }
        if(matrix[0] == null){
            return false;
        }
        boolean flag = false;
        // 行坐标最大值
        int i = matrix.length-1;
        // 列坐标最大值
        int j = matrix[0].length-1;
        System.out.println("二维数组数组原始坐标是: "+i+"--"+j);

        // 从右上角开始轮询这个二维数组
        int m = 0;
        int n = j;

        while(m <= i && n >= 0){
            // 如果目标数等于 第一行最后一列的数字，直接返回 true;
            if(target == matrix[m][n]){
                flag = true;
                break;
                // 如果目标数字小于 第一行最后一列的数字，则砍掉最后一列（因为这一列以下的数字都大于第一个数字），即 n = n-1;
            }else if(target < matrix[m][n]){
                --n;
                // 如果目标数字大于 第一行最后一列的数字，则砍掉第一行（因为这一行之前的数字都小于最后一列数字），即 m = m+1;
            }else if(target > matrix[m][n]){
                ++m;
            }
        }
        if(flag){
            System.out.println("对应的二维数组数组坐标是: "+m+"--"+n);
        }
        return flag;
    }

    public static void main(String[] args) {
        System.out.println(solution(newArray(),5));
    }
}
