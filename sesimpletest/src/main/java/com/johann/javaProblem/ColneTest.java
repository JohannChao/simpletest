package com.johann.javaProblem;

/**
 * @ClassName: ColneTest
 * @Description: TODO
 * @Author: Johann
 * @Date: 2021-10-19
 **/
public class ColneTest {
    /**
    * Java的clone()方法.【继承自java.lang.Object类的clone()方法是浅复制】
     *
     * （1）clone方法将对象复制了一份并返回给调用者。一般而言，clone()方法满足：
     *      ①对任何的对象x，都有x.clone() !=x//克隆对象与原对象不是同一个对象；
     *      ②对任何的对象x，都有x.clone().getClass()= =x.getClass()//克隆对象与原对象的类型一样；
     *      ③如果对象x的equals()方法定义恰当，那么x.clone().equals(x)应该成立。
     *
     *  （2）Java中对象的克隆
     *      ①为了获取对象的一份拷贝，我们可以利用Object类的clone()方法。
     *      ②在派生类中覆盖基类的clone()方法，并声明为public。
     *      ③在派生类的clone()方法中，调用super.clone()。
     *      ④在派生类中实现Cloneable接口。
     *
     *   浅复制：
     *   被复制对象的所有变量都含有与原来的对象相同的值，而所有的对其他对象的引用仍然指向原来的对象。换言之，浅复制仅仅复制所考虑的对象，而不复制它所引用的对象。
     *   深复制：
     *   被复制对象的所有变量都含有与原来的对象相同的值，除去那些引用其他对象的变量。那些引用其他对象的变量将指向被复制过的新对象，而不再是原有的那些被引用的对象。
     *   换言之，深复制把要复制的对象所引用的对象都复制了一遍。
     *
    */

    public static void main(String[] args) throws CloneNotSupportedException {
        Subjects sb_orig = new Subjects();
        sb_orig.setSub("001");
        sb_orig.setMath(80);
        sb_orig.setChinese(70);
        sb_orig.setEnglish(60);

        Subjects sb_copy = sb_orig.clone();

        System.out.println(sb_orig);
        //sb_orig.setSub("003");
        System.out.println(sb_copy);

//        System.out.println(sb_orig == sb_copy);
//        System.out.println(sb_orig.equals(sb_copy));
//        System.out.println(sb_orig.getClass() == sb_copy.getClass());

        CopyPerson cp = CopyPerson.builder()
                .id(1).name("Johann")
                .subject(sb_orig)
                .build();
        CopyPerson cp_copy = cp.clone();
        sb_orig.setSub("003");

        System.out.println(cp);
        System.out.println(cp_copy);
    }

}
