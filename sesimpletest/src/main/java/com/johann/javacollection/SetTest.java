package main.java.com.johann.javacollection;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class SetTest {

    public static void main(String [] args) {
        Set<Student> hashSet = new HashSet<Student>();
        hashSet.add(new Student(111,"hello"));
        hashSet.add(new Student(111,"java"));
        hashSet.add(new Student(112,"java"));
        hashSet.add(new Student(113,"java"));
        System.out.println(hashSet);
    }

}