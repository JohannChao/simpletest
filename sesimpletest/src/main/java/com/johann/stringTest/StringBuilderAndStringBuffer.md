### StringBuilder,StringBuffer简介

#### StringBuilder
1，Java StringBuilder类用于操作字符串对象。

2，StringBuilder是可变的字符序列。

3，StringBuilder是Java 1.5中引入的。

4，StringBuilder方法不是线程安全的，因此建议在单线程环境中使用。

5，StringBuilder提供的一些常见操作是–  append，insert，reverse和delete。

#### StringBuffer
1，Java StringBuffer类用于操作字符串对象。

2，StringBuffer是线程安全的可变字符序列。

3，StringBuffer是Java 1.0本身提供的。

4，StringBuffer方法是同步的，因此建议在多线程环境中将其用于字符串处理。

5，StringBuffer提供的一些常见操作是– append，insert，reverse和delete。

### StringBuilder

StringBuilder继承自 AbstractStringBuilder抽象类，实现Serializable, CharSequence接口。不可以被继承

#### StringBuilder的构造函数

```java
    //AbstractStringBuilder抽象类
    char[] value;
    //构造函数
    AbstractStringBuilder(int capacity) {
        value = new char[capacity];
    }
    
    //StringBuilder构造函数
    public StringBuilder() {
        super(16);
    }
    public StringBuilder(int capacity) {
        super(capacity);
    }
    public StringBuilder(String str) {
        super(str.length() + 16);
        append(str);
    }  
    public StringBuilder(CharSequence seq) {
        this(seq.length() + 16);
        append(seq);
    }    
```

StringBuilder类具有4个构造函数，每次初始化StringBuilder，底层创建了一个char数组，数组的初始容量可设定，默认是16。
如果使用的是参数是String或CharSequence的构造函数，那么数组的容量是 参数的长度+16。

当数组中的元素个数，超过数组的容量[capacity]时，数组会进行扩容，每次扩容遵循的公式是 
```text
StringBuilder底层数组扩容公式

new_capacity = old_capacity*2 + 2
```

#### StringBuilder常见方法

##### append方法

```java
public class StringBuilderTest {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append(1);//int
        sb.append(" ");
        sb.append(222l);//long
        sb.append(" ");
        sb.append(2.2f);//float
        sb.append(" ");
        sb.append(3.333d);//double
        sb.append(" ");
        sb.append(false);//boolean 添加布尔类型的数据，实际取值为 "true"或"false"字符串，而不是"0"或"1"
        sb.append(" ");
        sb.append('c');//char
        sb.append(" ");
        sb.append("JAVA");//String
        sb.append(" ");
        CharSequence seq = new StringBuilder("String");
        sb.append(seq,1,3);// append(CharSequence,开始位置[包含],结束位置[不包含])
        sb.append(" ");
        char [] chars = {'j','a','v','a','m','e'};
        sb.append(chars,2,3);// append(CharsArray,开始位置[包含],往后截取的长度)
        System.out.println(sb.toString());       
    }
}    
```
打印结果：
```text
1 222 2.2 3.333 false c JAVA tr vam
```

