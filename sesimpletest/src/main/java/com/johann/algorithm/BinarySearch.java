package com.johann.algorithm;

/**
 * @ClassName: BinarySearch
 * @Description: TODO
 * @Author: Johann
 * @Date: 2020-05-11 11:17
 **/
public class BinarySearch {

    /**
    * @Description: 基本的二分查找法
    * @Param: [array, x] 
    * @return: int 
    * @Author: Johann 
    * @Date: 2020/5/12 
    */ 
    public static int bs(int[] array,int x){
        int l = 0;
        int r = array.length-1;
        int mid;
        while (l <= r){
            //mid = l + (r - l) / 2;
            mid = (l+r)>>>1;
            if(array[mid] == x){
                return mid;
            }
            else if (array[mid] < x){
                l = mid + 1;
            }
            else if (array[mid] > x){
                r = mid - 1;
            }
        }
        return -1;
    }

    /** 
    * @Description: 二分查找法。不断收紧右边界，以便锁定左边界 {0,1,2,2,2,3,3,3,4,4,4,4,4,5,6,7,8,9};
    * @Param: [array, x] 
    * @return: int 
    * @Author: Johann 
    * @Date: 2020/5/12 
    */ 
    public static int bsFindLeft(int[] array,int x){
        int l = 0;
        int r = array.length - 1;
        int mid;
        while (l <= r){
            mid = l + (r - l) / 2;
            if(array[mid] == x){
                r = mid - 1;
            }
            else if(array[mid] > x){
                r = mid -1;
            }
            else if(array[mid] < x){
                l = mid +1;
            }
        }
        //如果 x 不在数组中，则返回 -1
        if(array[l] != x){
            l = -1;
        }
        return l;
    }

    /** 
    * @Description: 二分查找法。不断收紧左边界，以便锁定右边界 {0,1,2,2,2,3,3,3,4,4,4,4,4,5,6,7,8,9};
    * @Param: [array, x] 
    * @return: int 
    * @Author: Johann 
    * @Date: 2020/5/12 
    */ 
    public static int bsFindRight(int[] array,int x){
        int l = 0;
        int r = array.length - 1;
        int mid;
        while(l <= r){
            mid = l + (r - l) / 2;
            if(array[mid]==x){
                l = mid + 1;
            }
            else if(array[mid] < x){
                l = mid + 1;
            }
            else if(array[mid] > x){
                r = mid - 1;
            }
        }
        //如果 x 不在数组中，则返回 -1
        if(array[r] != x){
            r = -1;
        }
        return r;
    }

    
    public static void main(String[] args) {
        //int [] array = {0,1,2,2,2,3,3,3,4,4,4,4,4,5,6,7,8,9,9};
        //int index = bs(array,4);
        //int index = bsFindLeft(array,4);
        //int index = bsFindRight(array,4);
        //System.out.println("数组对应的索引是 ： " + index);

    }
}
