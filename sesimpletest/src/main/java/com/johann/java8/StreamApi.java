package com.johann.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @ClassName: StreamApi
 * @Description: 初识 Stream
 * @Author: Johann
 * @Date: 2020-10-09
 **/
public class StreamApi {

    /**
     *  java8 为何要引入函数式编程，原因有：
     *  a. 代码简洁，函数式编程写出的代码简洁且意图明确，使用stream {@link java.util.stream.Stream} 接口让你从此告别for循环。
     *  b. 多核友好，Java函数式编程使得编写并行程序从未如此简单，你需要的全部就是调用一下parallel()方法。
     *
     *  stream，Java函数式编程的主角，它并不是某种数据结构，它只是数据源的一种视图。这里的数据源可以是一个数组，Java容器或I/O channel等。
     *  正因如此要得到一个stream通常不会手动创建，而是调用对应的工具方法，比如：
     *    > 调用Collection.stream()或者Collection.parallelStream()方法
     *    > 调用Arrays.stream(T[] array)方法
     *
     *  Stream，IntStream, LongStream, DoubleStream 都继承自 BaseStream. 【IntStream, LongStream, DoubleStream 不是 Stream 的子接口】
     *  其中IntStream, LongStream, DoubleStream对应三种基本类型（int, long, double，注意不是包装类型），Stream对应所有剩余类型的stream视图。
     *  为不同数据类型设置不同stream接口，可以1.提高性能，2.增加特定接口函数。
     *
     *  > 为什么不把IntStream等设计成Stream的子接口？毕竟这接口中的方法名大部分是一样的。
     *  > 答案是：这些方法的名字虽然相同，但是返回类型不同，如果设计成父子接口关系，这些方法将不能共存，因为Java不允许只有返回类型不同的方法重载。
     *
     *
     *  虽然大部分情况下stream是容器调用 Collection.stream() 方法得到的，但 stream 和 collections 有以下不同：
     *    a. 无存储。stream不是一种数据结构，它只是某种数据源的一个视图，数据源可以是一个数组，Java容器或I/O channel等。
     *    b. 为函数式编程而生。对stream的任何修改都不会修改背后的数据源，比如对stream执行过滤操作并不会删除被过滤的元素，而是会产生一个不包含被过滤元素的新stream。
     *    c. 惰式执行。stream上的操作并不会立即执行，只有等到用户真正需要结果的时候才会执行。
     *    d. 可消费性。stream只能被“消费”一次，一旦遍历过就会失效，就像容器的迭代器那样，想要再次遍历必须重新生成。
     *
     *
     *  对stream的操作分为为两类，中间操作(intermediate operations)和结束操作(terminal operations)，二者特点是：
     *    a. 中间操作总是会惰式执行，调用中间操作只会生成一个标记了该操作的新stream，仅此而已。
     *    b. 结束操作会触发实际计算，计算发生时会把所有中间操作积攒的操作以 pipeline 的方式执行，这样可以减少迭代次数。计算完成之后stream就会失效。
     *
     *    > Stream接口中间操作的方法 ： concat() distinct() filter() flatMap() limit() map() peek()
     *                                  skip() sorted() parallel() sequential() unordered()
     *    > Stream接口结束操作的方法 ： allMatch() anyMatch() collect() count() findAny() findFirst()
     *                                  forEach() forEachOrdered() max() min() noneMatch() reduce() toArray()
     *
     *
     *  ###############################################################
     *
     *  Stream方法使用
     *  stream跟函数接口关系非常紧密，没有函数接口stream就无法工作。函数接口是指内部只有一个抽象方法的接口。
    */


    public static void main(String[] args) {
        List<String> streamList = new ArrayList<>(Arrays.asList("I", "love", "China","love"));
        /**
        *  1,forEach() {@link java.util.stream.Stream#forEach(Consumer)}
         *  遍历元素
        */
        System.out.println(" = = = forEach = = = = = ");
        Stream<String> forEachstream = streamList.stream();
        forEachstream.forEach(s -> System.out.println(s));

        /**
        *  2,filter() {@link java.util.stream.Stream#filter(Predicate)}
         *  {@link java.util.Collection#removeIf(Predicate)} 也包含 Predicate
         *  返回一个只包含满足 predicate 条件元素的Stream。
        */
        System.out.println(" = = = filter = = = = = ");
        Stream<String> filterstream = streamList.stream();
        filterstream.filter(s -> s.length()>3)
                .forEach(s -> System.out.println(s));

        /**
        *  3,distinct() {@link java.util.stream.Stream#distinct()}
         *  返回一个去除重复元素之后的Stream。
        */
        System.out.println(" = = = distinct = = = = = ");
        Stream<String> distinctstream = streamList.stream();
        distinctstream.distinct()
                .forEach(s -> System.out.println(s));

        /**
        *  4,sorted() {@link Stream#sorted()} {@link Stream#sorted(Comparator)}
         *  排序函数有两个，一个是用自然顺序排序，一个是使用自定义比较器排序
        */
        System.out.println(" = = = sorted = = = = = ");
        Stream<String> sortedstream = streamList.stream();
        // 自然排序
//        sortedstream.sorted()
//                .forEach(s -> System.out.println(s));
        // 自定义比较器排序
        sortedstream.sorted((s1,s2) -> s1.length()-s2.length())
                .forEach(s -> System.out.println(s));

        /**
        *  5,map()  {@link java.util.stream.Stream#map(Function)}
         *  返回一个对当前所有元素执行mapper之后的结果组成的Stream。
         *  直观的说，就是对每个元素按照某种操作进行转换，转换前后Stream中元素的个数不会改变，但元素的类型取决于转换之后的类型。
        */
        System.out.println(" = = = map = = = = = ");
        Stream<String> mapstream = streamList.stream();
        mapstream.map(s -> s.toUpperCase())
                .forEach(s -> System.out.println(s));

        /**
        *  6,flatMap()  {@link java.util.stream.Stream#flatMap(Function)}
         *  作用是对每个元素执行mapper指定的操作，并用所有mapper返回的Stream中的元素组成一个新的Stream作为最终返回结果。
         *  说起来太拗口，通俗的讲flatMap()的作用就相当于把原stream中的所有元素都"摊平"之后组成的Stream，转换前后元素的个数和类型都可能会改变。
        */
        System.out.println(" = = = flatMap = = = = = ");
        List<Integer> intList1 = new ArrayList<>(Arrays.asList(100,200,300));
        List<Integer> intList2 = new ArrayList<>(Arrays.asList(400,500,600,700));
        List<List<Integer>> list = new ArrayList<>();
        list.add(intList1);
        list.add(intList2);
        Stream<List<Integer>> listStream = list.stream();
        //listStream.forEach(li -> System.out.println(li));
        listStream.flatMap(li -> li.stream())
                .forEach(i -> System.out.println(i));

    }


}
