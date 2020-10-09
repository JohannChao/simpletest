package main.java.com.johann.java8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Spliterator;

/**
 * @ClassName: SpliteratorTest
 * @Description: java8 Spliterator 源码解析
 * @Author: Johann
 * @Date: 2020-09-27 
 */
public class SpliteratorTest {
    
    
    /**
     *  Collection 在 1.8 加入了一个新的方法 {@link Collection#spliterator()} ，这个方法调用后，会生成一个可拆分的迭代器{@link java.util.Spliterator}
     *
     *  1，Spliterator 接口特征集
     *
     *  a. {@link java.util.Spliterator#ORDERED}
     *      这个特征标识，集合中的元素是有序的，可以理解为每个元素有指定的index.
     *
     *  b.{@link java.util.Spliterator#SORTED}
     *      这个特征标识，迭代器中的元素是按照某种指定的排序方式排过序，即有指定的比较器.
     *
     *  c.{@link java.util.Spliterator#DISTINCT}
     *      这个特征标识，迭代器中的元素是不重复的.
     *
     *  d.{@link java.util.Spliterator#SIZED}
     *      这个特征标识，迭代器中的元素的个数是有界的.
     *
     *  e.{@link java.util.Spliterator#NONNULL}
     *      这个特征标识，迭代器中的元素是非 null 的.
     *
     *  f.{@link java.util.Spliterator#IMMUTABLE}
     *      这个特征标识，迭代器中的元素是不可变的，即元素不可被替换，不可增加，不可删除.
     *
     *  g.{@link java.util.Spliterator#CONCURRENT}
     *      这个特征标识，迭代器的数据源是线程安全的，可多线程操作.
     *
     *  h.{@link java.util.Spliterator#SUBSIZED}
     *      这个特征标识，当前迭代器的所有的子迭代器都具有 'SORTED'和'SUBSIZED' 特征.
    */

    public static void main(String[] args) {

        // 迭代器当前索引
        int index;

        // 迭代器边界位置
        int fence;

        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        arrayList.add(10);
        arrayList.add(11);
        arrayList.add(12);
        arrayList.add(13);
        arrayList.add(14);

        Spliterator spliterator = arrayList.spliterator();

        System.out.println(" = = = = = spliterator tryAdvance = = = = = ");
        /**
        *  tryAdvance方法 {@link java.util.Spliterator#tryAdvance}
        *  迭代器中如果有剩余的元素存在，则对下一个元素执行给定参数的操作，并返回true，否则返回false
        *  如果Spliterator对象具有ORDERED属性，那么tryAdvance也会按照相应的顺序去执行。
        */
        // 初始时，迭代器 index = 0
        spliterator.tryAdvance(iint ->{
            System.out.println("消费当前元素 ： "+iint);
        });
        // 执行一次 tryAdvance方法， 此时，index = 1

        /**
        *  getExactSizeIfKnown方法  {@link java.util.Spliterator#getExactSizeIfKnown}
        *  返回Spliterator对象确切的大小，如果存在SIZED属性，则返回estimateSize()方法的返回值，否则返回-1
        */

        /**
        *  estimateSize方法  {@link java.util.Spliterator#estimateSize}
        *  返回一个预估的值，等于执行forEachRemaining方法时调用tryAdvance的次数。
        *  如果这个值过大，或者需要太复杂的计算过程，那么直接回返回Long.MAX_VALUE
        */
        System.out.println("迭代器中剩余的元素个数 ：  "+spliterator.getExactSizeIfKnown());// 13

        // 父迭代器 spliterator 中的剩余元素对应的索引为 [1 - 13]
        /**
        *  trySplit方法 {@link java.util.Spliterator#trySplit}
        *  如果这个Spliterator是可以被分割的，那么这个方法会返回一个索引靠前的子迭代器Spliterator
        *  子迭代器中元素的个数 =  父迭代器元素个数/2
        *  如果Spliterator不能再分割，那么会返回null
        */
        Spliterator s1 = spliterator.trySplit();

        // 执行一次 trySplit方法，此时，返回的子迭代器 s1 中元素对应的索引为 [1 - 6]，父迭代器中元素对应的索引为 [7 - 13]

        // 此时，index = 7
        spliterator.tryAdvance(iint ->{
            System.out.println("消费当前元素 ： "+iint);
        });
        // 再对 父迭代器执行一次 tryAdvance方法，此时 index = 8，即父迭代器中元素的索引范围为 [8 - 13]

        Spliterator s2 = spliterator.trySplit();
        // 再对父迭代器执行一次 trySplit方法，返回的子迭代器中元素的索引范围是 [8 - 10]，此时父迭代器剩余元素的索引为 [11 - 13]

        /**
         *  forEachRemaining方法  {@link java.util.Spliterator#forEachRemaining}
         *  对Spliterator的剩余的每一个对象执行tryAdvance操作
        */
        System.out.println(" = = = = = spliterator forEachRemaining = = = = = ");
        spliterator.forEachRemaining(iint ->{
            System.out.println(iint);
        });

        System.out.println(" = = = = = s1 forEachRemaining = = = = = ");
        s1.forEachRemaining(iint ->{
            System.out.println(iint);
        });

        System.out.println(" = = = = = s2 forEachRemaining = = = = = ");
        s2.forEachRemaining(iint ->{
            System.out.println(iint);
        });
    }
}
