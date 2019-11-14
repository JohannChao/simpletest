### 字符串概述;

- 字符串（String类）是java常用类之一，位于java.util包下。
- 字符串是字符序列，它不是基本数据类型，而是对象。
- String字符串是不可变的，一旦创建变无法更改。
- 字符串可以通过使用双引号来快捷创建其对象，这些字符串对象也被成为字符串常量。
- 字符串是唯一支持运算符重载的Java类，我们可以使用“ +”运算符来拼接两个字符串。
- Java将字符串对象存储在特定的预定义区域中，字符串池是Java堆空间中存储字符串的一部分。
- 字符串本质上是可序列化的，并实现可序列化接口。

String对象两种不同的创建方式

1，使用双引号创建。这是最简单也是首选的创建字符串对象的方式，它将在字符串常量池中创建一个字符串对象。

2，使用new关键字创建。使用new创建的字符串对象，存储在堆内存中，对象指向的也是堆内存中的对象，而不是字符串池中的同值对象。

```java
1, String s1 = "hello，java"; 
2, String s2 = new String("hello，java");
```
### 字符串字面量

一个字符串字面量是由双引号（“”）括起来的零个或多个字符。


**字符串字面量示例**
（JDK1.8的环境下）
```java
package testPackage;
class Test {
    public static void main(String[] args) {
        String hello = "Hello", lo = "lo";
        System.out.print((hello == "Hello") + " ");             //true
        System.out.print((Other.hello == hello) + " ");         //true
        System.out.print((other.Other.hello == hello) + " ");   //true
        System.out.print((hello == ("Hel"+"lo")) + " ");        //true
        System.out.print((hello == ("Hel"+lo)) + " ");          //false
        System.out.println(hello == ("Hel"+lo).intern());       //true
    }
}
class Other { static String hello = "Hello"; }
```
```java
package other;
public class Other { public static String hello = "Hello"; }
```
以上程序运行结果为：
```text
true true true true false true
```


### 字符串常量池

又叫全局字符串常量池，存在于Java Heap中（jdk1.6以及之前的版本，String Pool存在于PermGen中）。

String的String Pool的底层实现是一个Hashtable，默认值大小长度是1009，如果放进String Pool的String非常多，就会造成Hash冲突严重，从而导致链表会很长，
而链表长了后直接会造成的影响就是当调用String.intern时性能会大幅下降（因为要一个一个找）。

### 字符串池工作方式

1，当我们创建一个字符串时，它存储在字符串池中。

2，如果字符串池中已经有一个具有相同值的字符串，则不会创建新的字符串对象。返回对现有字符串对象的引用。

3，Java字符串池是字符串对象的缓存。因为字符串是不可变的使得这一实现成为可能。

4，如果我们使用new关键字创建一个string对象，它将在堆内存中创建。如果要将它移动到字符串池，可以使用intern()方法。

5，字符串池是Flyweight设计模式的一个实现例子。


### 探讨String#intern

它的大体实现结构就是: JAVA 使用 jni 调用c++实现的StringTable的intern方法, StringTable的intern方法跟Java中的HashMap的实现是差不多的, 只是不能自动扩容。默认大小是1009。

在 jdk6中StringTable是固定的，就是1009的长度，所以如果常量池中的字符串过多就会导致效率下降很快。在jdk7中，StringTable的长度可以通过一个参数指定：
```text
-XX:StringTableSize=99991
```

源码中对intern方法的解释（JDK1.8）
```text
    /**
     * When the intern method is invoked, if the pool already contains a
     * string equal to this {@code String} object as determined by
     * the {@link #equals(Object)} method, then the string from the pool is
     * returned. Otherwise, this {@code String} object is added to the
     * pool and a reference to this {@code String} object is returned.
     *
     * 当intern方法被调用时候，如果字符串池中已经包含了一个与当前 string(A) 值相等的 string(B)，
     * 那么，会返回这个池中的 string(B)。若不包含，则当前的这个 string(A)  会被添加到字符串池中，
     * 并且返回这个 string(A) 的引用。
     */
    public native String intern();
```
以下我们会通过几组不同的示例（JDK1.8下），来探讨String#intern方法以及String Pool。

#### 示例一 new String("hello")
```java
public class StringInternTest {

    public static void main(String[] args) {
        // 1，创建了两个对象，一个是存储在常量池中的“hello”字符串，另一个是存储在Heap中的String对象。s1指向Heap中的对象
        String s1 = new String("hello");
        // 2，s2指向字符串池中“hello”字符串
        String s2 = "hello";
        // 3，字符串池中已有“hello”字符串，所以这一句没有产生任何影响。
        s1.intern();
        System.out.println("s1==s2 : "+(s1==s2)); //false

    }
}
```

#### 示例二 new String("hel") + new String("lo")
```java
public class StringInternTest {

    public static void main(String[] args) {
        // 1，在堆中创建了“hel”和“lo”对象，并将“hel”，“lo”字符串加入到了字符串常量池中。
        //    在堆中创建一个“hello”对象,字面量为“hello”的字符串没有加入到全局字符串常量池中。s1指向的是堆中的“hello”对象
        String s1 = new String("hel") + new String("lo");
        // 2，字符串常量池中没有“hello”，在字符串常量池中生成一个“hello”字符串，s2指向字符串常量池中的“hello”
        String s2 = "hello";
        // 3，字符串常量池中已经有一个“hello”字符串。返回字符串常量池中的“hello”字符串，无影响。
        s1.intern();
        System.out.println("s1 == s2 : "+(s1 == s2)); //false

        System.out.println("======================");

        // 1，在堆中创建了“go”和“od”对象，并将“go”，“od”字符串加入到了字符串常量池中。
        //    在堆中创建一个“good”对象,字面量为“good”的字符串没有加入到全局字符串常量池中。s1指向的是堆中的“good”对象
        String s3 = new String("go") +new String("od");

        // 2，字符串常量池中不存在“good”字符串，字符串常量池中直接保存字面量为“good”的堆中对象的引用，即此时常量池中的引用指向s1引用的对象。
        // ### 如果此时为JDK1.6，JDK1.6中，字符串常量池保存在PermGen中，此时会在字符串常量池中生成一个字面量为“good”的字符串。###
        s3.intern();

        // 3，s4的引用指向字符串常量池中的s1引用对象的引用
        // ### JDK1.6及以前，s4指向PermGen中字符串常量池里的“good”字符串。###
        String s4 = "good";
        System.out.println("s3 == s4 : "+(s3 == s4)); //JDK1.7、JDK1.8结果为 true；JDK1.6及以前，结果为 false
    }
}
```

#### 示例三 new String("lo"+"ve")
```java
public class StringInternTest {

    public static void main(String[] args) {
        // 1，这一句我们只知道在堆中创建了“love”对象，并不知道在常量池中是否加入了“love”字符串
        String s15 = new String("lo"+"ve");

        //如果上一句执行完，如果在StringTable中放入了"love"，那么下面的执行结果就是false；如果在StringTable中没有放入了"love"，执行该句，会把堆中“love”对象的引用放入到StringTable中，即下面的结果是 true
        // 2，上一句执行完，如果在字符串常量池中没有加入“love”字符串，那么执行完 s15.intern(); 会在字符串常量池中加入字面量为“love”的堆中对象的引用。
        //    如果字符串中已经加入了“love”字符串，此时无影响。
        s15.intern();
        // 3, s16为字符串常量池中的对象引用
        String s16 = "love";

        System.out.println("s15 == s16 : "+(s15==s16));// fasle 说明执行 new String("lo"+"ve"); 时，在字符串常量池中已经加入了“love”，即在StringTable中已经放入了“love”

        System.out.println("=================================");

        // 1，通过javap分析，我们可以知道这一句代码在编译的时候，会把“mi”，“ne”合并为一个对象“mine”保存在常量池中。
        //   即这一句，只在堆中创建了“mine”对象，在字符串常量池中创建了“mine”字符串。没有“mi”，“ne”对象
        String s17 = new String("mi"+"ne");

        // 2，只会在堆中创建"mi",在StringTable不放入“mi”
        String s18 = new String("m")+new String("i");
        // 2，只会在堆中创建"mi",在StringTable不放入“mi”
        //String s18 = new StringBuilder().append("m").append("i").toString();

        System.out.println(s18.intern()==s18); // true

        System.out.println("=================================");

        // 1，在堆中创建了“yo”对象，在字符串常量池中创建了字面量为“yo”的字符串
        String s19 = new String("yo");

        // 2，只会在堆中创建"yo",在StringTable不放入“yo”
        String s20 = new String("y")+new String("o");
        // 2，只会在堆中创建"yo",在StringTable不放入“yo”
        //String s20 = new StringBuilder().append("y").append("o").toString();

        System.out.println(s20.intern()==s20); //false
    }
}
```



参考：

https://docs.oracle.com/javase/specs/jls/se8/html/jls-3.html#jls-CharacterLiteral

https://tech.meituan.com/2014/03/06/in-depth-understanding-string-intern.html

https://www.zhihu.com/question/55994121/answer/147296098
