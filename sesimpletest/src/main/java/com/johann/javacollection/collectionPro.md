 ## java集合框架
 
 集合类存放于java.util包下，主要有三种：Set（集），List（列表包含Queue），Map（映射）。
 ```text
差异点：
Collections类：这个类是集合的一个工具类，用于存放多个静态方法，供我们使用集合的时候直接调用。
```
 
 ### collection接口：
 
 colletion是集合List，Set，Queqe的最基本的接口。
 collection接口依赖Iterator接口
 
 #### 1,List
 
 List接口继承Collection接口
 
 1.1,ArrayList
 
 继承关系：继承自AbstractList抽象类，实现List，RandomAccess，Cloneable，Serializable接口
 
 特点：排列有序，可重复；底层数组实现