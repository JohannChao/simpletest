package com.johann.javaProblem;

import java.io.*;

/**
 * @ClassName: serializeTest
 * @Description: Java序列化和反序列化
 * @Author: Johann
 * @Date: 2021-09-13 14:04
 **/
public class serializeTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /**------------------------------【1】序列化和反序列测试--------------------------------*/
        // 序列化
//        Person person = new Person();
//        person.setId(1);
//        person.setName("Johann");
//        person.setAge(28);
//        serializeObj(person,"E:/Person.txt");

        // 反序列化
        // transient 关键字修饰的字段，不会被序列化
//        Object o = deSerializeObj("E:/Person.txt");
//        if( o != null && o instanceof Person){
//            Person p = (Person)o;
//            System.out.println(p.toString()+"CLASS_TYPE: "+p.CLASS_TYPE);
//        }
        /**------------------------------【1】序列化和反序列测试--------------------------------*/


        /**------------------------------【2】不加序列化版本号--------------------------------*/
        // 序列化
//        PersonNoUid pn = new PersonNoUid();
//        pn.setId(2);
//        pn.setSex(1);
//        serializeObj(pn,"E:/PersonNoUid.txt");

        //反序列化
//        Object o = deSerializeObj("E:/PersonNoUid.txt");
//        if( o != null && o instanceof PersonNoUid){
//            PersonNoUid pn = (PersonNoUid)o;
//            System.out.println(pn.toString());
//        }
        /**------------------------------【2】不加序列化版本号--------------------------------*/


        /**------------------------------【3】序列化存储规则--------------------------------*/
        // 序列化，写入多个对象
//        ObjectOutputStream oo_multi = new ObjectOutputStream(new FileOutputStream(new File("E:/multi_Person.txt")));
//        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("E:/Person.txt")));
//        Person person = new Person();
//        person.setId(1);
//        person.setName("Johann");
//        person.setAge(28);
//        oo_multi.writeObject(person);
//        oo.writeObject(person);

        // 再写入一个
        // case1：写入的是之前的相同的对象
//        oo_multi.writeObject(person);

        // case2：写入的是一个新的对象
//        Person person2 = new Person();
//        person2.setId(2);
//        person2.setName("Jessie");
//        person2.setAge(27);
//        oo_multi.writeObject(person2);




        // 反序列化
//        ObjectInputStream  oi_muti = new ObjectInputStream(new FileInputStream(new File("E:/multi_Person.txt")));
//        Person p1 = (Person) oi_muti.readObject();
//        Person p2 = (Person) oi_muti.readObject();
//        System.out.println("p1 \n"+p1.toString());
//        System.out.println("p2 \n"+p2.toString());
//        System.out.println("p1 == p2 :"+ (p1==p2));
//        ObjectInputStream  oi = new ObjectInputStream(new FileInputStream(new File("E:/Person.txt")));
//        Person p = (Person) oi.readObject();
//        System.out.println("p \n"+p.toString());
//        System.out.println("p2 == p :"+ (p2==p));
//        System.out.println("p1 == p :"+ (p1==p));
        /**------------------------------【3】序列化存储规则--------------------------------*/



        /**------------------------------【4】通过编写私有方法writeObject和readObject，完成部分属性的序列化--------------------------------*/
        // 序列化
//        PersonOverride po = new PersonOverride();
//        po.setId(1);
//        po.setAge(28);
//        po.setName("Johann");
//
//        FileOutputStream fos = new FileOutputStream("E:/PersonOverride.txt");
//        BufferedOutputStream bos = new BufferedOutputStream(fos);
//        ObjectOutputStream oos = new ObjectOutputStream(bos);
//        oos.writeObject(po)
//        System.out.println("PersonOverride 序列化成功！");
//        oos.close();

        // 反序列化
//        FileInputStream fis = new FileInputStream("E:/PersonOverride.txt");
//        BufferedInputStream bis = new BufferedInputStream(fis);
//        ObjectInputStream ois = new ObjectInputStream(bis);
//        PersonOverride po_read =   (PersonOverride)ois.readObject();
//        System.out.println("PersonOverride 反序列化成功！");
//        System.out.println(po_read.toString());
//        ois.close();
        /**------------------------------【4】通过编写私有方法writeObject和readObject，完成部分属性的序列化--------------------------------*/


        /**------------------------------【5】通过实现 Externalizable  接口，自定义属性字段的序列化--------------------------------*/
        // 序列化
        PersonExternalize pe = new PersonExternalize();
        pe.setId(1);
        pe.setName("Johann");
        pe.setAge(28);
        pe.setAddress("BeiJing");

        FileOutputStream fos = new FileOutputStream("E:/PersonExternalize.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(pe);
        System.out.println("PersonExternalize 序列化成功！");
        oos.close();

        // 反序列化
        FileInputStream fis = new FileInputStream("E:/PersonExternalize.txt");
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        PersonExternalize pe_read =   (PersonExternalize)ois.readObject();
        System.out.println("PersonExternalize 反序列化成功！");
        System.out.println(pe_read.toString());
        ois.close();
        /**------------------------------【5】通过实现 Externalizable  接口，自定义属性字段的序列化--------------------------------*/
    }

    /** 
    * @Description: 将Java对象序列化为，字节序列
    * @Param: [obj] 
    * @return: void 
    * @Author: Johann 
    * @Date: 2021/9/13 
    */ 
    private static void serializeObj(Object obj,String filePathName) throws FileNotFoundException,IOException {
        // ObjectOutputStream 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File(filePathName)));
        oo.writeObject(obj);
        System.out.println(obj.getClass().getName()+" 序列化成功");
        oo.flush();
        oo.close();
    }

    
    /** 
    * @Description: 将文件中的字节序列，反序列化为Java对象 
    * @Param: [] 
    * @return: void 
    * @Author: Johann 
    * @Date: 2021/9/13 
    */ 
    private static Object deSerializeObj(String filePathName) throws FileNotFoundException, IOException, ClassNotFoundException {
        // 对象输入流，从文件中读取字节序列
        ObjectInputStream  oi = new ObjectInputStream(new FileInputStream(new File(filePathName)));
        Object obj = oi.readObject();
        System.out.println("Object 反序列化成功");
        return  obj;
    }

}

class Person implements Serializable {

    // 序列化版本号
    private static final long serialVersionUID = 4628720067993202116L;
    private Integer id;
    private transient String name;
    private Integer age;

    public static Integer CLASS_TYPE = 1;


    //  通过 ObjectStreamField 数组来声明类需要序列的字段
    // ※数组名 serialPersistentFields 不允许修改※
    private static final ObjectStreamField[] serialPersistentFields
            = {new ObjectStreamField("name",String.class),
            new ObjectStreamField("id",Integer.class)};


    // 由于指定了 serialVersionUID，即使新增字段，之前序列化生成的字节序列，仍旧可以反序列化。
    // 若是 缺少一个字段呢？ 少一个字段仍旧可以反序列化成功
    //private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\''
                + ", age=" + age
                + '}';
    }
}

class PersonNoUid implements Serializable {

    // 此时，没有serialVersionUID 序列化版本号
    // private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer sex;

    // 新增一个成员变量，反序列化失败
    //private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "PersonNoUid{" +
                "id=" + id +
                ", sex=" + sex +
                '}';
    }
}


class PersonOverride implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer age;
    private String name;

    /**
     * @Description: 通过编写私有方法writeObject和readObject，完成部分属性的序列化。这里的writeObject和readObject是private的且是void的~
     * @Param: [oos]
     * @return: void
     * @Author: Johann
     * @Date: 2021/9/15
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        // oos.defaultWriteObject();
        oos.writeObject(id);
        oos.writeObject(name);
    }

    /**
     * @Description:  通过编写私有方法writeObject和readObject，完成部分属性的序列化。这里的writeObject和readObject是private的且是void的~
     * @Param: [ois]
     * @return: void
     * @Author: Johann
     * @Date: 2021/9/15
     */
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        // ois.defaultReadObject();
        id = (Integer) ois.readObject();
        name = (String) ois.readObject();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonOverride{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

class PersonExternalize implements Externalizable{


    private static final long serialVersionUID = 9122837820093257445L;

    private Integer id;
    private String name;
    private Integer age;
    private String address;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PersonExternalize{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(name);
        out.writeObject(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (Integer) in.readObject();
        name = (String) in.readObject();
        age = (Integer) in.readObject();
    }
}

