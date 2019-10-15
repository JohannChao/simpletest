package main.java.com.johann.javacollection;

import java.util.*;

public class ListTest {

    List arrayList = new ArrayList();

    //List list = new LinkedList();

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(6,7,5,8,4,3,9,2,0,1,12,34,56,78,94);
        System.out.println(list);
        //Collections.sort(list);
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1.compareTo(o2)>0){
                    return 1;
                }else if(o1.compareTo(o2)<0){
                    return -1;
                }else {
                    return 0;
                }
            }
        });
//        Collections.sort(list, new Comparator<Integer>() {
//
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                if(o1.compareTo(o2) > 0) {
//                    return 1;
//                } else if(o1.compareTo(o2) < 0){
//                    return -1;
//                } else {
//                    return 0;
//                }
//            }
//        });
        System.out.println(list);
        int index = Collections.binarySearch(list, 0);
        System.out.println(index);
    }
}
