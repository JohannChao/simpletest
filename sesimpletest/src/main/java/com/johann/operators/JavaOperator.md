Java运算符用于执行数学运算，操作变量，可以大致分为以下几类：
- 算术运算符
- 关系运算符
- 位运算符
- 逻辑运算符
- 赋值运算符
- 其他运算符

### 算术运算符

```text
+   加法 
-   减法 
*   乘法 
/   除法 【c = a/b （整数商  -7/4 = -1）】
%   取余 【r = a-b*c (余数 -7%4 = -3）】
++  自增 【a++和++a，两种写法，对变量a产生的影响都是，最终结果为a+1】 
--  自减 【a--和--a，两种写法，对变量a产生的影响都是，最终结果为a-1】 
```
```java
public class JavaOperatorTest {
    public static void main(String[] args) {
        //除法和取余运算
        int a = -7;
        int b = 4;
        int c = a/b; // c1 = -1
        int r = a%b; // r1 = -3
        System.out.println(c);
        System.out.println(r);

        //取模运算的商和结果
        int c1 = Math.floorDiv(a,b); // c2 = -2
        int r1 = Math.floorMod(a,b); // r2 = 1 
        System.out.println(c1);
        System.out.println(r1);

        // ++ --
        int m1 = 10;
        int m2 = 10;
        int n = m1++; //  m1 = 11 ; n = 10 先赋值，再自增
        //int n = ++m1; // m1 = 11 ; n = 11 先自增，再赋值
        System.out.println(m1);
        System.out.println(n);
        int l = m2--; // m2 = 9 ; l = 10 先赋值，再自减
        //int l = --m2; // m2 = 9 ; l = 9 先自减，再赋值
        System.out.println(m2);
        System.out.println(l);
    }
}
```

#### java中取模运算和取余运算的区别
```text
#### java中取模运算和取余运算的区别 ####

1，如果被除数（a）和除数（b）正负号相同时，此时取模运算和取余运算是等同的；

2，当被除数（a）和除数（b）正负号不同时，取余的结果与被除数（a）同号，取模的结果与除数（b）同号；

3，取模运算，在获取整数商的时候，商的取值向无穷小方向舍入。取余运算，在获取整数商的时候，商的取值向 0 方向舍入。
```

### 关系运算符

```text
'=='    是否相等
'!='    是否不相等
'>'     大于号比较
'<'     小于号比较          
'>='    大于等于号比较    
'<='    小于等于号比较    
```

### 位运算符

Java定义了位运算符，应用于整数类型(int)，长整型(long)，短整型(short)，字符型(char)，和字节型(byte)等类型。

位运算符是将数值转换为二进制格式的数值进行操作的。
```text

```

