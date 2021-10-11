package com.johann.javacollection;

import java.util.*;

/**
 * @ClassName TraversalTest
 * @Description 测试for循环，for each，iterator 循环遍历效率问题
 * @Author Johann
 * @Date 2019-10-22 11:38
 **/
public class TraversalTest {

    /**
     * @Author Johann
     * @Description for 循环
     * @Date 11:50 2019-10-22
     * @Param [list]
     * @return long
     **/
    public static long traverseByForLoop(List list){
        long startTime = System.currentTimeMillis();
        for (int i = 0;i < list.size();i++){
            list.get(i);
        }
        long endTime = System.currentTimeMillis();
        return endTime-startTime;
    }

    /**
     * @Author Johann
     * @Description for each循环
     * @Date 11:50 2019-10-22
     * @Param [list]
     * @return long
     **/
    public static long traverseByForEach(List<String> list){
        long startTime = System.currentTimeMillis();
        for (String str : list){

        }
        long endTime = System.currentTimeMillis();
        return endTime-startTime;
    }

    /**
     * @Author Johann
     * @Description //TODO for each循环
     * @Date 13:51 2019-10-22
     * @Param [set]
     * @return long
     **/
    public static long traverseByForEach(Set<String> set){
        long startTime = System.currentTimeMillis();
        for (String str : set){

        }
        long endTime = System.currentTimeMillis();
        return endTime-startTime;
    }

    /**
     * @Author Johann
     * @Description iterator迭代器遍历
     * @Date 11:50 2019-10-22
     * @Param [list]
     * @return long
     **/
    public static long traverseByIterator(List list){
        Iterator iterator = list.iterator();
        long startTime = System.currentTimeMillis();
        while (iterator.hasNext()){
            iterator.next();
        }
        long endTime = System.currentTimeMillis();
        return endTime-startTime;
    }

    /**
     * @Author Johann
     * @Description //TODO iterator迭代器遍历
     * @Date 13:52 2019-10-22
     * @Param [set]
     * @return long
     **/
    public static long traverseByIterator(Set set){
        Iterator iterator = set.iterator();
        long startTime = System.currentTimeMillis();
        while (iterator.hasNext()){
            iterator.next();
        }
        long endTime = System.currentTimeMillis();
        return endTime-startTime;
    }


    public static void main(String[] args) {
        // ArrayList
//        List<String> arrayList = new ArrayList<>();
//        for (int i = 0;i < 3000000;i++){
//            arrayList.add("l " + i);
//        }
//        long arraylistForTime = traverseByForLoop(arrayList);
//        long arraylistForeachTime = traverseByForEach(arrayList);
//        long arraylistIteratorTime = traverseByIterator(arrayList);
//        System.out.println("ArrayList for 循环时间 ： "+arraylistForTime);
//        System.out.println("ArrayList for each 循环时间 ： "+arraylistForeachTime);
//        System.out.println("ArrayList iterator 遍历循环时间 ： "+arraylistIteratorTime);

        // LinkedList
//        List<String> linkedList = new LinkedList<>();
//        for (int i = 0;i < 30000;i++){
//            linkedList.add("l " + i);
//        }
//        long linkedlistForTime = traverseByForLoop(linkedList);
//        long linkedlistForeachTime = traverseByForEach(linkedList);
//        long linkedlistIteratorTime = traverseByIterator(linkedList);
//        System.out.println("LinkedList for 循环时间 ： "+linkedlistForTime);
//        System.out.println("LinkedList for each 循环时间 ： "+linkedlistForeachTime);
//        System.out.println("LinkedList iterator 遍历循环时间 ： "+linkedlistIteratorTime);

        // HashSet
//        Set<String> hashSet = new HashSet<>();
//        long addhashsetstart = System.currentTimeMillis();
//        for(int i = 0;i < 300000;i++){
//            hashSet.add("l "+i);
//        }
//        long addhashsetend = System.currentTimeMillis();
//        long timedifference =addhashsetend - addhashsetstart;
//        System.out.println("没有设定初始容量的Set，添加元素耗时 ： "+timedifference);
//
//        Set<String> hashSet1 = new HashSet<>(524288);
//        long addhashsetstart1 = System.currentTimeMillis();
//        for(int i = 0;i < 300000;i++){
//            hashSet1.add("l "+i);
//        }
//        long addhashsetend1 = System.currentTimeMillis();
//        long timedifference1 =addhashsetend1 - addhashsetstart1;
//        System.out.println("设定初始容量的Set，添加元素耗时 ： "+timedifference1);

//        long hashsetForeachTime = traverseByForEach(hashSet);
//        long hashsetIteratorTime = traverseByIterator(hashSet);
//        System.out.println("HashSet for each 循环时间 ： "+hashsetForeachTime);
//        System.out.println("HashSet iterator 遍历循环时间 ： "+hashsetIteratorTime);


        // 遍历Map的几种方式
        Map<String,String> hashMap = new HashMap<>();
        hashMap.put("CN", "中国");
        hashMap.put("JP", "日本");
        hashMap.put("US", "美国");

        // 1 使用entrySet，for each遍历
        for(Map.Entry<String, String> entry : hashMap.entrySet()){
            System.out.println("Key : "+entry.getKey()+" -- Value : "+entry.getValue());
        }

        // 2 使用entrySet，Iterator遍历
        Iterator<Map.Entry < String, String>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> entry = iterator.next();
            System.out.println(entry.getKey()+" -- "+entry.getValue());
        }

        // 3 使用KeySet，values方法
        for (String key : hashMap.keySet()){
            System.out.println("Key : "+key);
        }

        for (String value : hashMap.values()){
            System.out.println("Value : "+value);
        }

        // 4 使用java9之后提供的 forEach 方法
        hashMap.forEach(
                (key,value) ->
                        System.out.println(key+" ** "+value)
        );
    }
}
