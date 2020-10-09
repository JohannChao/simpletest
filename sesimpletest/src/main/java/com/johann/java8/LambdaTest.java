package main.java.com.johann.java8;

import java.awt.event.ActionListener;
import java.util.*;
import java.util.function.*;

/**
 * @ClassName: LambdaTest
 * @Description: TODO
 * @Author: Johann
 * @Date: 2020-09-24
 * @reference: https://github.com/CarpenterLee/JavaLambdaInternals
 **/
public class LambdaTest {

    public static void main(String[] args) {

        /* <p> */
        // Lambda and Anonymous Classes(I)
        // 下文将介绍如何使用Lambda表达式简化匿名内部类的书写，但Lambda表达式并不能取代所有的匿名内部类，只能用来取代函数接口（Functional Interface）的简写。
        // ### 匿名内部类的对比
        // 1.7及以前
        new Thread(new Runnable() { //匿名类 接口名
            @Override
            public void run() {// 方法名
                System.out.println("hello");
            }
        }
        ).start();

        // 1.8 Lymbda表达式. 省略了匿名类的 接口名和函数名
        new Thread(
                //() -> System.out.println("hello Lymbda")
                () -> {
                    System.out.println("hello Lymbda"); //多行的话，写在代码块中
                    System.out.println("hello Lymbda2");
                }
        ).start();

        // ### 函数带参数的匿名类
        // 1.7
        List<String> list = Arrays.asList("Love","I","China");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1 == null)
                    return -1;
                else if (o2 == null)
                    return 1;
                else
                    return o1.length() - o2.length();
            }
        });
        System.out.println(list.toString());

        /**
        *  1.8 Lymbda 表达式
         * 不只省略了 接口名和函数名，连同函数参数的参数类型也一同省略了
         * 这得益于javac的类型推断机制，编译器能够根据上下文信息推断出参数的类型，当然也有推断失败的时候，这时就需要手动指明参数类型了
        */
        List<String> listLymbda = Arrays.asList("Love","I","China");
        Collections.sort(listLymbda,(o1, o2) -> {
        //Collections.sort(listLymbda,(String o1, String o2) -> {
            if(o1 == null)
                return -1;
            else if (o2 == null)
                return 1;
            else
                return o1.length() - o2.length();
        });
        System.out.println(listLymbda.toString());

        /**
         * Lymbda表达式 简写的依据
         *
         * 能够使用Lambda的依据是必须有相应的函数接口（函数接口，是指内部只有一个抽象方法的接口）
         * 这一点跟Java是强类型语言吻合，也就是说你并不能在代码的任何地方任性的写Lambda表达式
         * 实际上Lambda的类型就是对应函数接口的类型。
         *
         * Lambda表达式另一个依据是类型推断机制，在上下文信息足够的情况下，编译器可以推断出参数表的类型，而不需要显式指名
        */
        ActionListener listener = event -> System.out.println("button clicked");

        BinaryOperator<Long> add = (Long x, Long y) -> x + y;//
        BinaryOperator<Long> addImplicit = (x, y) -> x + y;//  类型推断


        // 自定义函数接口
        MyInterface<String> mingzi = str -> System.out.println(str);

        // 还可以这么写
        MyStream<String> myStream = new MyStream<String>();
        myStream.setList(listLymbda);
        myStream.myForEach(anyThing -> System.out.println(anyThing));
        /* </p> */


        /* <p> */
        // Lambda and Anonymous Classes(II)
        // 下文将进一步区分Lambda表达式和匿名内部类在JVM层面的区别
        // 经过前文介绍，我们看到Lambda表达式似乎只是为了简化匿名内部类书写，这看起来仅仅通过语法糖在编译阶段把所有的Lambda表达式替换成匿名内部类就可以了。
        // 但实时并非如此。在 JVM 层面，Lambda表达式和匿名内部类有着明显的差别。


        // 匿名内部类本质上仍是一个类，只是无需我们自己命名，编译器会自动为这个匿名内部类命名。
        // 因此，有一个匿名内部类的类，经过编译，会生成两个 class 文件。

        // Lambda表达式通过invokedynamic指令实现，书写Lambda表达式不会产生新的类。
        // 反编译之后我们发现Lambda表达式被封装成了主类的一个私有方法，并通过invokedynamic指令进行调用。

        // ### 推论，this引用的意义
        // 既然Lambda表达式不是内部类的简写，那么Lambda内部的this引用也就跟内部类对象没什么关系了。在Lambda表达式中this的意义跟在表达式外部完全一样。
        new  LambdaTest().r1.run();
        new  LambdaTest().r2.run();
        //输出的结果显示，打印了两遍 "Lymbda表达式中的 this "，而不是两个引用地址【注：调用的是 外部类（姑且这么称之）的 已重写的toString()方法】
        /* </p> */


        /* <p> */
        // Lambda and Collections
        // 为引入Lambda表达式，Java8新增了java.util.function包，里面包含常用的函数接口，这是Lambda表达式的基础，Java集合框架也新增部分接口，以便与Lambda表达式对接。



        //  ### Collection接口中的新方法

        /**
        *  1，forEach {@link java.util.Collection#forEach(Consumer)}
         *  这个方法，其实是继承自 Iterable 接口
         * 对容器中的每个元素执行action指定的动作，其中Consumer是个函数接口，里面只有一个待实现方法void accept(T t)
        */

        // 需求： 打印 list 中，长度超过3的字符串
        // 1.7 实现
        for (String str : list) {
            if(str.length() > 3){
                System.out.print(str+" 1.7_forEach ");
            }
        }
        System.out.println();
        // 1.8 forEach()函数实现
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                if (s.length() > 3){
                    System.out.print(s+" 1.8_forEach ");
                }
            }
        });
        System.out.println();
        // 1.8 Lymbda表达式
        list.forEach(str ->{
            if (str.length() > 3){
                System.out.print(str+" 1.8_forEach_Lymbda ");
            }
        });
        System.out.println();
        // 上述代码给forEach()方法传入一个Lambda表达式，我们不需要知道accept()方法，也不需要知道Consumer接口，类型推导帮我们做了一切。


        /**
         *  2,removeIf {@link java.util.Collection#removeIf(Predicate)}
         *  删除容器中所有满足filter指定条件的元素，其中Predicate是一个函数接口，里面只有一个待实现方法boolean test(T t)
        */

        // 需求：删除列表中，长度大于 3 的字符串

        // 1.7实现
//        List<String> removeIf17 = Arrays.asList("I", "love", "China");
//        Iterator<String> it17 = removeIf17.iterator();
//        while (it17.hasNext()){
//            if(it17.next().length() > 3){
//                it17.remove();
//            }
//        }
        // 上述实现报错“Exception in thread "main" java.lang.UnsupportedOperationException”，
        // 原因是 Arrays.asList 方法生成的实体是 Arrays的内部类ArrayList（和我们常用的 ArrayList是两个不同的类），
        // 这个内部类继承 AbstractList类，但却没有重写 remove方法。因此报错，定位在 AbstractList中的remove方法
        List<String> removeIf17 = new ArrayList<>(Arrays.asList("I", "love", "China"));
        Iterator<String> it17 = removeIf17.iterator();
        while (it17.hasNext()){
            if(it17.next().length() > 3){
                it17.remove();
            }
        }
        removeIf17.forEach(str ->{
            System.out.println(str+" removeIf17 ");
        });

        // 1.8 实现
        List<String> removeIf18 = new ArrayList<>(Arrays.asList("I", "love", "China"));
        removeIf18.removeIf(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 3;
            }
        });
        removeIf18.forEach(str ->{
            System.out.println(str+" removeIf18 ");
        });
        // 1.8 Lymbda
        List<String> removeIfLymbda18 = new ArrayList<>(Arrays.asList("I", "love", "China"));
        removeIfLymbda18.removeIf(s -> s.length()>3);
        removeIfLymbda18.forEach(str ->{
            System.out.println(str+" removeIfLymbda18 ");
        });


        /**
        *  3,replaceAll {@link java.util.List#replaceAll(UnaryOperator)}
         *  这个方法是1.8以后，在List中新增的方法
         *  对列表中的每个元素，执行 operator 指定的操作，并使用操作结果替换原来的元素。
        */

        // 需求：对列表中长度小于 3 元素后面，拼接指定字符串 "_johann"

        // 1.7 不再累述

        // 1.8 replaceAll
        List<String> replaceAll18 = new ArrayList<>(Arrays.asList("I", "love", "China"));
        replaceAll18.replaceAll(new UnaryOperator<String>() {
            @Override
            public String apply(String s) {
                if(s.length() < 3)
                    return s+"_johann";
                else
                    return s;
            }
        });
        replaceAll18.forEach(str ->{
            System.out.println(str+" replaceAll18 ");
        });

        // 1.8 replaceAll Lymbda
        List<String> replaceAllLymbda18 = new ArrayList<>(Arrays.asList("I", "love", "China"));
        replaceAllLymbda18.replaceAll(s -> {
            if(s.length() < 3)
                return s + "_johann";
            else
                return s;
        });
        replaceAllLymbda18.forEach(str ->{
            System.out.println(str+" replaceAllLymbda18 ");
        });

        /**
         * 4, spliterator {@link java.util.Collection#spliterator()}
         * @see SpliteratorTest java8 Spliterator 源码解析
         *
         * 该方法返回容器的可拆分迭代器。
         * 从名字来看该方法跟iterator()方法有点像，我们知道Iterator是用来迭代容器的，Spliterator也有类似作用，但二者有如下不同：
         *      a. Spliterator既可以像Iterator那样逐个迭代，也可以批量迭代。批量迭代可以降低迭代的开销。
         *      b. Spliterator是可拆分的，一个Spliterator可以通过调用Spliterator<T> trySplit()方法来尝试分成两个。一个是this，另一个是新返回的那个，这两个迭代器代表的元素没有重叠。
         * 可通过（多次）调用Spliterator.trySplit()方法来分解负载，以便多线程处理。
         */

        List<String> spliteratorList = new ArrayList<>(Arrays.asList("I", "love", "China","HeBei","HanDan","YongNian","You","2020","10","01","PRC"));

        Spliterator spliterator = spliteratorList.spliterator();
        System.out.println("==========================================");
        // 此值用于表示为分隔符元素定义了遇到顺序
        System.out.println( spliterator.hasCharacteristics(Spliterator.ORDERED));
        // 此值表示元素遇到的每对元素是否相等。如果我们从集合创建分离器，它将始终是DISTINCT。
        System.out.println( spliterator.hasCharacteristics(Spliterator.DISTINCT));
        // 表示该元素总是有序
        System.out.println( spliterator.hasCharacteristics(Spliterator.SORTED));
        // 表示estimateSize()方法返回的值表示有限大小
        System.out.println( spliterator.hasCharacteristics(Spliterator.SIZED));
        // 此值表示遇到的元素不会为空。
        System.out.println( spliterator.hasCharacteristics(Spliterator.NONNULL));
        // 此值表示是否无法修改元素的来源，即我们不能添加，替换或删除任何元素。
        System.out.println( spliterator.hasCharacteristics(Spliterator.IMMUTABLE));
        // 表示可以同时修改源，即，我们可以使用多个线程并发地添加，删除或删除元素，而无需同步。
        System.out.println( spliterator.hasCharacteristics(Spliterator.CONCURRENT));
        // 表示由返回的所有分隔符trySplit()将为 SIZED和SUBSIZED。
        System.out.println( spliterator.hasCharacteristics(Spliterator.SUBSIZED));
        System.out.println(spliterator.estimateSize());
        System.out.println(spliterator.getExactSizeIfKnown());




        // ### Map接口中的新方法

        /**
        *  1,forEach {@link java.util.Map#forEach(BiConsumer)}
         *  对Map中的每个映射<K,V>执行指定的操作
         *  可简化Map的遍历
        */
        System.out.println(" = = = = = forEach = = = = = = ");
        Map<String,String> map = new HashMap<>();
        map.put("一","赵");
        map.put("二","钱");
        map.put("三","孙");
        map.put("四","李");
        map.forEach((k,v) ->{
            System.out.println("百家姓第 "+k+" 位是 ："+v);
        });

        /**
        *  2，getOrDefault {@link java.util.Map#getOrDefault(Object, Object)}
         *  按照给定的key查询Map中对应的value，如果没有找到则返回defaultValue
        */
        System.out.println(" = = = = = getOrDefault = = = = = = ");
        System.out.println(map.getOrDefault("五","周"));

        /**
        *  3,putIfAbsent  {@link java.util.Map#putIfAbsent(Object, Object)}
         *  只有在不存在key值的映射或映射值为null时，才将value指定的值放入到Map中，否则不对Map做更改
        */
        System.out.println(" = = = = = putIfAbsent = = = = = = ");
        map.putIfAbsent("一","袁");
        map.putIfAbsent("六","吴");
        map.forEach((k,v) ->{
            System.out.println("百家姓第 "+k+" 位是 ："+v);
        });

        /**
        *  4，remove {@link java.util.Map#remove(Object, Object)}
         *  Map中有一个remove(Object key)方法，来根据指定key值删除Map中的映射关系；
         *  Java8新增了remove(Object key, Object value)方法，只有在当前Map中**key正好映射到value时**才删除该映射，否则什么也不做．
        */
        System.out.println(" = = = = = remove = = = = = = ");
        map.remove("一","袁");
        map.remove("六","吴");
        map.forEach((k,v) ->{
            System.out.println("百家姓第 "+k+" 位是 ："+v);
        });

        /**
        *  5, replace
         *  再java7及其以前，要想替换某个映射的值，使用的是 put(k,v)的方法覆盖原来的值实现的。
         *  java8 引入了两个新的方法
         *  {@link java.util.Map#replace(Object, Object)}
         *  只有在当前Map中key的映射存在时才用value去替换原来的值，否则什么也不做．
         *
         *  {@link java.util.Map#replace(Object, Object, Object)}
         *  replace(K key, V oldValue, V newValue)，只有在当前Map中key的映射存在且等于oldValue时才用newValue去替换原来的值，否则什么也不做．
        */


        /**
        *  6，replaceAll {@link java.util.Map#replaceAll(BiFunction)}
         *  对Map中的每个映射执行function指定的操作，并用function的执行结果替换原来的value
        */
        System.out.println(" = = = = = replaceAll = = = = = = ");
        map.replaceAll((k,v) ->{
            return "百家姓第"+k+"位:"+v;
        });
        map.forEach((k,v) ->{
            System.out.println("百家姓第 "+k+" 位是 ："+v);
        });

        /**
        *  7,merge {@link java.util.Map#merge(Object, Object, BiFunction)}
         *         源码解析
         *
         *         // 接口函数需要实现
         *         Objects.requireNonNull(remappingFunction);
         *         // 传入的第二个参数 value 不能为 null
         *         Objects.requireNonNull(value);
         *
         *         // 获取key旧的映射值
         *         V oldValue = get(key);
         *
         *         // 如果key的映射旧值为 null，则用传入的第二个参数 value 代替这个映射旧值，和 key 一起进行指定的操作，并得到新的值
         *         V newValue = (oldValue == null) ? value :
         *                    remappingFunction.apply(oldValue, value);
         *
         *          // 如果得到的新值为 null，则移除当前 key
         *         if(newValue == null) {
         *             remove(key);
         *         // 得到的新值不为 null,则不管map是否存在当前 key，直接将 key和新值加入到map的映射中
         *         } else {
         *             put(key, newValue);
         *         }
         *
         *         // 返回新的值
         *         return newValue;
         *
        */
        System.out.println(" = = = = = merge = = = = = = ");
        String mergeNewValue1 = map.merge("五", "周", new BiFunction<String, String, String>() {
            @Override
            public String apply(String key, String s2) { //当前的 key，如果key的映射旧值不为 null，则 s2 = 映射旧值，否则 s2 = 传入的第二个参数值value
                return s2;
            }
        });
        System.out.println("mergeNewValue1 : "+mergeNewValue1);
        String mergeNewValue2 = map.merge("四", "李", new BiFunction<String, String, String>() {
            @Override
            public String apply(String key, String s2) { //当前的 key，如果key的映射旧值不为 null，则 s2 = 映射旧值，否则 s2 = 传入的第二个参数值value
                return null;
            }
        });
        System.out.println("mergeNewValue2 : "+mergeNewValue2);
        map.forEach((k,v) ->{
            System.out.println("百家姓第 "+k+" 位是 ："+v);
        });

        /**
        *  8,compute {@link java.util.Map#compute(Object, BiFunction)}
         *          源码解析
         *
         *          // 接口函数需要实现
         *          Objects.requireNonNull(remappingFunction);
         *         // 获取当前 key的映射旧值
         *         V oldValue = get(key);
         *
         *          // 用key和key对应的旧值，进行指定的操作，并得到新的值
         *         V newValue = remappingFunction.apply(key, oldValue);
         *
         *          // 得到的新值为 null
         *         if (newValue == null) {
         *             // delete mapping
         *             // 如果得到新值为 null，且map中存在当前 key，则移除当前映射，并返回 null
         *             if (oldValue != null || containsKey(key)) {
         *                 // something to remove
         *                 remove(key);
         *                 return null;
         *              //  如果得到新值为 null，而map不存在当前的 key，则只返回 null
         *             } else {
         *                 // nothing to do. Leave things as they were.
         *                 return null;
         *             }
         *          // 得到的新值不为 null,则不管map是否存在当前 key，直接将 key和新值关联加入到map的映射中，并返回新的值
         *         } else {
         *             // add or replace old mapping
         *             put(key, newValue);
         *             return newValue;
         *         }
         *
        */
        System.out.println(" = = = = = compute = = = = = = ");
        String computeNewValue = map.compute("七", new BiFunction<String, String, String>() {
            @Override
            public String apply(String key, String oldValue) { // 当前 key 和 key的映射值
                return key+oldValue;
            }
        });
        System.out.println("computeNewValue : "+computeNewValue);
        map.forEach((k,v) ->{
            System.out.println("百家姓第 "+k+" 位是 ："+v);
        });

        /**
        *  9,computeIfAbsent {@link java.util.Map#computeIfAbsent(Object, Function)}
         *          源码解析
         *         Objects.requireNonNull(mappingFunction);
         *         V v;
         *         // 如果 key对应的当前映射旧值 不为 null，则不做任何处理，直接返回旧值
         *
         *         // 如果 key对应的映射旧值为 null，用 key进行指定的操作，得到新值。
         *         // 如果新值不为null，将key与新值关联替换或加入 map 映射，并返回新的值
         *         if ((v = get(key)) == null) {
         *             V newValue;
         *             if ((newValue = mappingFunction.apply(key)) != null) {
         *                 put(key, newValue);
         *                 return newValue;
         *             }
         *         }
         *
         *         return v;
        */

        /**
        *  10,computeIfPresent  {@link java.util.Map#computeIfPresent(Object, BiFunction)}
         *          源码解析
         *          Objects.requireNonNull(remappingFunction);
         *         V oldValue;
         *         // 如果 key 对应的映射旧值 为 null，则不做处理，直接 返回null
         *
         *         // 如果 key 对应的映射旧值 不为 null，进行后续处理
         *         if ((oldValue = get(key)) != null) {
         *              // 用 key 和 映射旧值 进行指定操作，得到新值
         *             V newValue = remappingFunction.apply(key, oldValue);
         *             // 如果新值不为 null，将key与新值关联替换或加入 map 映射，并返回新的值
         *             if (newValue != null) {
         *                 put(key, newValue);
         *                 return newValue;
         *              // 如果新值为 null，移除当前 key，并返回 null
         *             } else {
         *                 remove(key);
         *                 return null;
         *             }
         *         } else {
         *             return null;
         *         }
         *
        */

        /* </p> */

    }

    Runnable r1 = () -> { System.out.println(this); }; // this 默认调用 toString()方法
    Runnable r2 = () -> { System.out.println(toString()); };

    public String toString() { return "Lymbda表达式中的 this "; }
}

// 注释@FunctionalInterface，是可选的，用于帮助检查当前的接口，是否符合 函数接口的定义，如果这个接口不止一个函数，则注释报错提示。
// Multiple non-overriding abstract methods found in interface main.java.com.johann.java8.myInterface
@FunctionalInterface
interface MyInterface<T>{
    void print(T t);
    //void do(T t);
}

class MyStream<T>{
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void myForEach(MyInterface myInterface){
        for(T t : list){
            myInterface.print(t);
        }
    }
}