 ## java集合框架
 
 集合类存放于java.util包下，主要有三种：Set（集），List（列表包含Queue），Map（映射）。
 ```text
差异点：

Collections 这个类是集合的一个工具类（Arrays也是集合下的工具类），用于存放多个静态方法，供我们使用集合的时候直接调用。
```
 
 ### collection接口：
 
 colletion是集合List，Set，Queqe的最基本的接口。
 collection接口依赖Iterator接口
 
 #### 1,List
 
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


#### 2,SET

Set继承Collection接口

