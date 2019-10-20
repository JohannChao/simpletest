 ## java集合框架
 
 集合类存放于java.util包下，主要有三种：Set（集），List（列表包含Queue），Map（映射）。
 ```text
差异点：

Collections 这个类是集合的一个工具类（Arrays也是集合下的工具类），用于存放多个静态方法，供我们使用集合的时候直接调用。
```
 
 ### collection接口：
 
 colletion是集合List，Set，Queqe的最基本的接口。
 collection接口依赖Iterator接口
 ---
 #### 1 List
 
 List接口继承Collection接口
 
 ##### 1.1,ArrayList
 ```java
    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param  initialCapacity  the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *         is negative
     */
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        }
    }
    
    /**
     * Appends the specified element to the end of this list.
     *
     * @param e element to be appended to this list
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     */
    public boolean add(E e) {
        //判断是否需要扩容
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        //添加新的元素
        elementData[size++] = e;
        return true;
    }
```

继承关系：
    
继承自AbstractList抽象类；实现List，RandomAccess（可随机访问），Cloneable，Serializable接口。

特征：

1，排列有序，可重复；

2，底层使用数组实现；

3，查询速度快，增删速度慢；

4，线程不安全；

5，当容量不足时，ArrayList是当前容量*1.5+1。Size为0的List的底层数组，在初始add元素的时候设置初始容量是10。

 ```text
为什么线程不安全？

查看源码，在add添加元素的时候，其实是分两步执行的
    1，调用ensureCapacityInternal()方法，来判断当前的数组是否需要扩容
    2，新增元素
在多线程的程序中，多个线程同时操作某个数组，就可能会出现数组越界的情况。
```
##### 1.2 Vector
```java
    public Vector(int initialCapacity, int capacityIncrement) {
        super();
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        this.elementData = new Object[initialCapacity];
        this.capacityIncrement = capacityIncrement;
    }
    
    /**
     * Appends the specified element to the end of this Vector.
     *
     * @param e element to be appended to this Vector
     * @return {@code true} (as specified by {@link Collection#add})
     * @since 1.2
     */
    public synchronized boolean add(E e) {
        modCount++;
        ensureCapacityHelper(elementCount + 1);
        elementData[elementCount++] = e;
        return true;
    }
```

继承关系：

继承自AbstractList抽象类；实现List，RandomAccess（可随机访问），Cloneable，Serializable接口。
    
特征：
    
1，排列有序，可重复；
    
2，底层使用数组实现；
    
3，查询速度快，增删速度慢；
    
4，线程安全，效率低；
    
5，当容量不足时，Vector是当前容量*2。Size为0的List的底层数组，在初始add元素的时候设置初始容量是10。   

```text
为什么Vector的线程是安全的？

通过分析源码，我们看到Vector在add新元素的时候，也是分两步 1，判断是否需要扩容；2，新增元素。只不过，它所有add元素,remove元素的的方法上都有 synchronized 关键字
```

##### 1.3 LinkedList
```java
    public boolean add(E e) {
        linkLast(e);
        return true;
    }
    /**
     * Links e as last element.
     */
    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }
    
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }
    /**
     * Returns the (non-null) Node at the specified element index.
     */
    Node<E> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
```    
继承关系：

继承自AbstractSequentialList抽象类；实现List，Deque（提供了操作队列的方法），Cloneable，Serializable接口。

特征：

1，排列有序，可重复；

2，基于双向链表实现（JDK1.7/8 之后取消了循环，修改为双向链表）；

3，查询速度慢，增删快；

4，线程不安全。

---
#### 2 SET

Set继承Collection接口

##### 2.1 HashSet

```java
    public HashSet() {
        map = new HashMap<>();
    }
    
    public boolean add(E e) {
        return map.put(e, PRESENT)==null;
    }
    
    public boolean remove(Object o) {
        return map.remove(o)==PRESENT;
    }
    
    /**
     * Constructs a new, empty linked hash set 构造新的空链接哈希集
     * This package private constructor is only used by LinkedHashSet. 此包私有构造函数仅由LinkedHashSet使用
     **/
    HashSet(int initialCapacity, float loadFactor, boolean dummy) {
        map = new LinkedHashMap<>(initialCapacity, loadFactor);
    }
```

继承关系：

继承自AbstractSet抽象类；实现Set，Cloneable，Serializable接口

特征：

1，排列无序，不可重复（对象是否重复，是自己定义的，重写equals和hashCode方法）；

2，底层使用的是Hash表实现，内部是HashMap；

3，存储速度快

##### 2.2 TreeSet
```java
    interface NavigableMap<K,V> extends SortedMap<K,V>

    private transient NavigableMap<E,Object> m;

    public TreeSet() {
        this(new TreeMap<E,Object>());
    }
    
    public boolean add(E e) {
        return m.put(e, PRESENT)==null;
    }
```

继承关系：

继承AbstractSet抽象类；实现NavigableSet，Cloneable，Serializable接口

特征：

1，底层使用二叉树实现，内部是TreeMap；

2，不可重复（对象是否重复，是自己定义的，重写equals和hashCode方法），排序存储；

3，要实现排序存储，需要保存的对象实现Comparable接口，重写compareTo方法；或者在对TreeSet进行实例化的时候，在构造函数中 通过匿名内部类 重写其中的compare方法。
```java
//TODO

```

##### 2.3 LinkedHashSet
```java
    public LinkedHashSet() {
        super(16, .75f, true);
    }
    
    /**
     * LinkedHashSet继承自HashSet，该方法即使HashSet中，LinkedHashSet的专用构造函数。
     * Constructs a new, empty linked hash set 构造新的空链接哈希集
     * This package private constructor is only used by LinkedHashSet. 此包私有构造函数仅由LinkedHashSet使用
     **/
    HashSet(int initialCapacity, float loadFactor, boolean dummy) {
        map = new LinkedHashMap<>(initialCapacity, loadFactor);
    }
```

继承关系：

继承自HashSet类；实现Set，Cloneable，Serializable接口

特征：

1，采用Hash表存储，不可重复。保证元素的唯一（哈希表），哈希表是真正存储数据的地方；

2，存储有序（底层有一个链接表），链表记录着存储数据的顺序；

3，线程不安全，效率高；

---
```text
理解区分TreeSet和LinkedHashSet的“有序”
//TODO

```

```java
for循环，for each，iterator循环合理使用
//TODO

```
---

#### 3 Queue

队列，与List和Set同级别，都继承自Collection接口。
```java
//TODO

```


### Map

映射表的基础接口；依赖Collection接口

#### 1 HashMap

继承关系：

继承AbstractMap类；实现Map，Cloneable，Serializable接口

```text
内部实现：
1，在JDK1.7中，HashMap的数据结构是，外层是一个数组，数组中每个元素是一个单项链表（Map.Entry）。
    capacity：当前数组的容量，始终保持2^n，可以扩容，扩容后数组大小为当前的2倍。初始默认是 16 (1>>4)
    loadFactor:负载因子，默认是 0.75f
    threshold:扩容的阈值，等于capacity*loadFactor（即扩容后是原来的1.5倍）
2，在JDK1.8中，Hash的内部结构是由 数组+链表+红黑树 组成。在JDK7中，查找的时候，根据hash值我们可以快速定位到数组的具体下标，但是之后，需要顺着链表一个个比较下去才能找到我们需要的，
时间复杂度取决于链表的长度为O(n)。为了降低这部分的开销，在java1.8中，当链表中的元素超过8个以后，会将链表转换为红黑树，在这些位置进行查找的时候，可以降低时间复杂度为O(logN)。
```
特征：

1，Key不可重复（只允许存在一个null）。Value允许重复；

2，底层为数组+链表+红黑树（红黑树1.8新增）；

3，线程不安全，具有很快的访问速度。

#### 2 HashTable


#### 3 TreeMap
