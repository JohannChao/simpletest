# 序列化和反序列化

### 序列化和反序列化概念
* 把对象转换为字节序列的过程称为对象的序列化。
* 把字节序列恢复为对象的过程称为对象的反序列化。

### 序列化目的
* 便于存储，可以将对象持久化到介质中，就像实现对象直接存储。
* 便于传输，在网络上传送对象的字节序列。

在很多应用中，需要对某些对象进行序列化，让它们离开内存空间，入住物理硬盘，以便长期保存。比如最常见的是Web服务器中的Session对象，当有 10万用户并发访问，
就有可能出现10万个Session对象，内存可能吃不消，于是Web容器就会把一些seesion先序列化到硬盘中，等要用了，再把保存在硬盘中的对象还原到内存中。

当两个进程在进行远程通信时，彼此可以发送各种类型的数据。无论是何种类型的数据，都会以二进制序列的形式在网络上传送。
发送方需要把这个Java对象转换为字节序列，才能在网络上传送；接收方则需要把字节序列再恢复为Java对象。

   
### 序列化方式   
* 实现 Serializable 接口：采用默认的序列化方式 ，也可以自定义 writeObject、readObject、writeReplace、readResolve 方法，会通过反射调用。
* 实现 Externalizable 接口：需要实现 writeExternal 和 readExternal 方法。 

### 部分字段序列化定义方式

* 默认方式，实现 Serializable 接口的类，它的实例化对象中非 static 静态和非 transient 修饰的字段都会被序列化。
* 在需要序列化的类中，通过 ObjectStreamField[] serialPersistentFields 数组来声明类需要序列化的字段。
* 在需要序列化的类中，添加writeObject和readObject方法，完成指定属性字段的序列化。
* 实现 Externalizable 完成指定属性字段的序列化。

# JDK类库中的序列化API

java.io.ObjectOutputStream代表对象输出流，它的writeObject(Object obj)方法可对参数指定的obj对象进行序列化，把得到的字节序列写到一个目标输出流中。

java.io.ObjectInputStream代表对象输入流，它的readObject()方法从一个源输入流中读取字节序列，再把它们反序列化为一个对象，并将其返回。

只有实现了Serializable和Externalizable接口的类的对象才能被序列化。

Externalizable接口继承自 Serializable接口，实现Externalizable接口的类完全由自身来控制序列化的行为，而仅实现Serializable接口的类可以 采用默认的序列化方式 。

* 对象的序列化步骤为：
 创建一个对象输出流，它可以包装一个其他类型的目标输出流，如文件输出流；通过对象输出流的writeObject()方法写对象。
 
 * 对象的反序列化步骤为：
 创建一个对象输入流，它可以包装一个其他类型的源输入流，如文件输入流；通过对象输入流的readObject()方法读取对象。
 
 ### 序列化与反序列化实例
 ```java
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
    }

    /** 
    * @Description: 将Java对象序列化为，字节序列
    * @Param: [obj] 
    * @return: void 
    * @Author: Johann 
    * @Date: 2021/9/13 
    */ 
    private static void serializeObj(Object obj) throws FileNotFoundException,IOException {
        // ObjectOutputStream 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("E:/Person.txt")));
        oo.writeObject(obj);
        System.out.println("Person 序列化成功");
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
    private static Person deSerializeObj() throws FileNotFoundException, IOException, ClassNotFoundException {
        // 对象输入流，从文件中读取字节序列
        ObjectInputStream  oi = new ObjectInputStream(new FileInputStream(new File("E:/Person.txt")));
        Person p = (Person)oi.readObject();
        System.out.println("Person 反序列化成功");
        return  p;
    }

}

class Person implements Serializable {

    // 序列化版本号
    private static final long serialVersionUID = 4628720067993202116L;
    private Integer id;
    // transient 关键字修饰的字段，不会被序列化
    private transient String name;
    private Integer sex;

    public static Integer CLASS_TYPE = 1;

    // 由于指定了 serialVersionUID，即使新增字段，之前序列化生成的字节序列，仍旧可以反序列化。
    // 若是 缺少一个字段呢？ 少一个字段仍旧可以反序列化成功
    //private String address;
    
    //  通过 ObjectStreamField[] serialPersistentFields 数组来声明类需要序列的字段
    // ※数组名 serialPersistentFields 不允许修改※
    private static final ObjectStreamField[] serialPersistentFields
            = {new ObjectStreamField("name",String.class),
            new ObjectStreamField("id",Integer.class)};
        
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }
}
```
 
 ### 部分属性进行序列化的方式
1.  默认方式，实现 Serializable 接口的类，它的实例化对象中非 static 静态和非 transient 修饰的字段都会被序列化。
2.  在需要序列化的类中，通过 ObjectStreamField[] serialPersistentFields 数组来声明类需要序列化的字段。
3.  在需要序列化的类中，添加writeObject和readObject方法，完成指定属性字段的序列化。
4.  实现 Externalizable 完成指定属性字段的序列化。

---

通过编写writeObject和readObject方法，控制类需要序列化的字段

```java
public class serializeTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /**------------------------------【4】通过编写私有方法writeObject和readObject，完成部分属性的序列化--------------------------------*/
        // 序列化
        PersonOverride po = new PersonOverride();
        po.setId(1);
        po.setAge(28);
        po.setName("Johann");

        FileOutputStream fos = new FileOutputStream("E:/PersonOverride.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(po);
        oos.close();

        // 反序列化
        FileInputStream fis = new FileInputStream("E:/PersonOverride.txt");
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        PersonOverride po_read =   (PersonOverride)ois.readObject();
        System.out.println(po_read.toString());
        ois.close();
        /**------------------------------【4】通过编写私有方法writeObject和readObject，完成部分属性的序列化--------------------------------*/
    }
}    

class PersonOverride implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer age;
    private String name;

    /**
     * @Description: 通过编写私有方法writeObject和readObject，完成部分属性的序列化
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
     * @Description:  通过编写私有方法writeObject和readObject，完成部分属性的序列化
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
```

 ※ 为什么可以通过，重写 "writeObject" 和 "readObject" 方法实现自定义序列化字段？

```java
// ※源码分析※ 
// ObjectStreamClass 类源码

public class ObjectStreamClass implements Serializable {
    /**
     * Creates local class descriptor representing given class.
     */
    private ObjectStreamClass(final Class<?> cl) {
            
        // ...
    
        if (externalizable) {
            cons = getExternalizableConstructor(cl);
        } else {
            cons = getSerializableConstructor(cl);
            // 检查其是否有私有的，无返回值的writeObject方法，如果有，其会委托该方法进行对象序列化。
            writeObjectMethod = getPrivateMethod(cl, "writeObject",
                new Class<?>[] { ObjectOutputStream.class },
                Void.TYPE);
            // 检查其是否有私有的，无返回值的writeObject方法，如果有，其会委托该方法进行对象序列化。
            readObjectMethod = getPrivateMethod(cl, "readObject",
                new Class<?>[] { ObjectInputStream.class },
                Void.TYPE);
            readObjectNoDataMethod = getPrivateMethod(
                cl, "readObjectNoData", null, Void.TYPE);
            hasWriteObjectData = (writeObjectMethod != null);
        }
        domains = getProtectionDomains(cons, cl);
        writeReplaceMethod = getInheritableMethod(
            cl, "writeReplace", null, Object.class);
        readResolveMethod = getInheritableMethod(
            cl, "readResolve", null, Object.class);
        return null;
    }
        // ...
}
```

>※ Java调用ObjectOutputStream类检查其是否有私有的，无返回值 void 的 writeObject 方法，如果有，其会委托该方法进行对象序列化。
>
>※  因为如此，所以我们在序列化的类中重写 "writeObject" 和 "readObject" 方法时，这里的writeObject和readObject是private的且是void的。

---
 
通过实现 Externalizable  接口，自定义属性字段的序列化
 
 ```java
public class serializeTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
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
```

执行过程中，序列化步骤执行成功，反序列的时候，程序报错。
```text
PersonExternalize 序列化成功！
Exception in thread "main" java.io.InvalidClassException: main.java.com.johann.javaProblem.PersonExternalize; no valid constructor
	at java.io.ObjectStreamClass$ExceptionInfo.newInvalidClassException(ObjectStreamClass.java:169)
	at java.io.ObjectStreamClass.checkDeserialize(ObjectStreamClass.java:874)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:2043)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1573)
	at java.io.ObjectInputStream.readObject(ObjectInputStream.java:431)
	at main.java.com.johann.javaProblem.serializeTest.main(serializeTest.java:133)
```

> ※ 这是因为缺少无参构造器导致,报 no valid constructor 异常
>
> ※ Externalizable接口的实现方式一定要有默认的无参构造函数~
>
> ※ Serializable接口实现，其采用反射机制完成内容恢复，没有一定要有无参构造函数的限制~

 
 ---
 
 ### 序列化存储规则
 ```java
public class serializeTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /**------------------------------【3】序列化存储规则--------------------------------*/
        // 序列化，写入多个对象
        ObjectOutputStream oo_multi = new ObjectOutputStream(new FileOutputStream(new File("E:/multi_Person.txt")));
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("E:/Person.txt")));
        Person person = new Person();
        person.setId(1);
        person.setName("Johann");
        person.setAge(28);
        oo_multi.writeObject(person);
        oo.writeObject(person);

        // 再写入一个
        // case1：写入的是之前的相同的对象
        oo_multi.writeObject(person);

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
    }
}    
```
---
运行结果如下：

case1：第二次写入的对象，与第一次相同。 

序列化后得到的文件，multi_Person.txt 只比 Person.txt 文件多了两三个字节，而非我们想象的那样，前者是后者的两倍。

反序列化时，multi_Person.txt 文件中的两个对象，指向的是同一个。

---
##### 结论：
 >  Java 序列化机制为了节省磁盘空间，具有特定的存储规则，当写入文件的为同一对象时，并不会再将对象的内容进行存储，而只是再次存储一份引用。
 反序列化时，恢复引用关系，生成的两个对象引用指向唯一的对象，二者相等，输出 true。
 该存储规则极大的节省了存储空间。
 
 
 
 
 # serialVersionUID 字段的作用
 
 s​e​r​i​a​l​V​e​r​s​i​o​n​U​I​D​:​ ​字​面​意​思​上​是​序​列​化​的​版​本​号​，凡是实现 Serializable 接口的类都有一个表示序列化版本标识符的静态变量。
 
 它的作用是：序列化时为了保持版本的兼容性，即在版本升级时反序列化仍保持对象的唯一性。
 
 新建一个实体类 PersonNoUid，实现了 Serializable 接口，但没有定义序​列​化​版​本​号 serialVersionUID
 
 如果不对这个类 PersonNoUid 进行修改，那么序列化后的字节序列，仍旧可以反序列化为 Java 对象。现在，对 PersonNoUid 类进行修改（新增一个成员变量），再使用之前生成的字节序列进行反序列化。
 
 ### 无序列化版本化的序列化与反序列化
 ```java
public class serializeTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
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
        System.out.println("Object 序列化成功");
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

class PersonNoUid implements Serializable {

    // 此时，没有serialVersionUID 序列化版本号
    // private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer sex;

    // 新增一个成员变量，反序列化失败
    private String name;

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
```
 
 此时，报异常：
 ```text
Exception in thread "main" java.io.InvalidClassException: main.java.com.johann.javaProblem.PersonNoUid; 
local class incompatible: stream classdesc serialVersionUID = 1763842315926755690, local class serialVersionUID = -1203584509319705339	
```
这个异常其实是因为，文件流 stream 中的 class 和 本地的 class 不兼容（incompatible），出于安全机制考虑，程序抛出了错误，并且拒绝载入。

如果我们想要在序列化后修改类（新增/删除 成员变量，新增/删除 函数），并能正常反序列化，此时需要指定一个 s​e​r​i​a​l​V​e​r​s​i​o​n​U​I​D​。

Java中，当一个类实现了Serializable 接口，却没有指定s​e​r​i​a​l​V​e​r​s​i​o​n​U​I​D​，那么java编译器会自动给这个class进行一个摘要算法，类似于指纹算法，只要这个文件 多一个空格，得到的UID就会截然不同的，可以保证在这么多类中，这个编号是唯一的。
所以，添加了一个字段后，由于没有显指定 serialVersionUID，编译器又为我们生成了一个UID，当然和前面保存在文件中的那个不会一样了，于是就出现了2个序列化版本号不一致的错误。

### serialVersionUID 的用途

* 在某些场合，希望类的不同版本对序列化兼容，因此需要确保类的不同版本具有相同的serialVersionUID；
* 在某些场合，不希望类的不同版本对序列化兼容，因此需要确保类的不同版本具有不同的serialVersionUID。

由于类的serialVersionUID的默认值完全依赖于Java编译器的实现，对于同一个类，用不同的Java编译器编译，有可能会导致不同的 serialVersionUID，也有可能相同。
为了提高serialVersionUID的独立性和确定性，强烈建议在一个可序列化类中显示的定义serialVersionUID，为它赋予明确的值。
【除了根据编译器来自动生成一个64位的哈希字段以外，还可以直接简单粗暴的给它赋值 1L】

# 面试问题
### 1，序列化的概念，目的，及序列化方式
序列化和反序列化概念
  * 把对象转换为字节序列的过程称为对象的序列化。
  * 把字节序列恢复为对象的过程称为对象的反序列化。

序列化目的
* 便于存储，可以将对象持久化到介质中，就像实现对象直接存储。
* 便于传输，在网络上传送对象的字节序列。  
   
序列化方式   
* 实现 Serializable 接口：采用默认的序列化方式 ，也可以自定义 writeObject、readObject、writeReplace、readResolve 方法，会通过反射调用。
* 实现 Externalizable 接口：需要实现 writeExternal 和 readExternal 方法。   

### 2，serialVersionUID 字段的生成方式，及作用
生成方式：1，默认的1L；2，根据类名、接口名、成员方法及属性等来生成一个64位的哈希字段

作用：序列化时为了保持版本的兼容性，即在版本升级时反序列化仍保持对象的唯一性。


### 3，平常实体类没有实现Serializable接口，怎么也能存进数据库呢？
是因为在声明变量的时候，例如String、int、Boolean等时，数据类型已经实现了序列化（包装类或包装类的父类，已实现Serializable接口）


### 4，序列化存储规则
Java 序列化机制为了节省磁盘空间，具有特定的存储规则，当写入文件的为同一对象时，并不会再将对象的内容进行存储，而只是再次存储一份引用。

反序列化时，恢复引用关系，生成的两个对象引用指向唯一的对象，二者相等，输出 true。

该存储规则极大的节省了存储空间。


### 5，部分字段序列化定义方式
* 默认方式，实现 Serializable 接口的类，它的实例化对象中非 static 静态和非 transient 修饰的字段都会被序列化。
* 通过 ObjectStreamField[] serialPersistentFields 数组来声明类需要序列化的字段。 
* 在需要序列化的类中，添加writeObject和readObject方法，完成指定属性字段的序列化。
* 实现 Externalizable 完成指定属性字段的序列化。


### 6，Serializable和Externalizable的区别
Externalizable继承Serializable接口。只有实现了Serializable 或 Externalizable 接口的类的对象才能被序列化。
* Serializable 是标识接口，实现该接口，无需重写方法；实现 Externalizable 接口：需要重写 writeExternal 和 readExternal 方法。
* 实现 Serializable 接口,对象属性字段序列化方式有两种：采用默认的序列化方式 ；也可以自定义 writeObject、readObject、writeReplace、readResolve 方法，会通过反射调用。
* 实现 Externalizable 接口的类，一定要有默认的无参构造函数。若没有，则反序列会报错【no valid constructor】。
* 采用Externalizable无需产生序列化ID（serialVersionUID），而Serializable接口则需要。
* 相比较Serializable, Externalizable序列化、反序列更加快速，占用相比较小的内存。

---

### 参考
* [Java对象的序列化与反序列化](https://www.cnblogs.com/xdp-gacl/p/3777987.html)
* [Java 序列化和反序列化（一）Serializable 使用场景](https://www.cnblogs.com/binarylei/p/10987540.html)
* [Java 序列化和反序列化（二）Serializable 源码分析 - 1](https://www.cnblogs.com/binarylei/p/10987933.html)
* [Java 序列化和反序列化（三）Serializable 源码分析 - 2](https://www.cnblogs.com/binarylei/p/10989372.html)
* [Serializable和Externalizable浅析](https://my.oschina.net/wangmengjun/blog/1588096)