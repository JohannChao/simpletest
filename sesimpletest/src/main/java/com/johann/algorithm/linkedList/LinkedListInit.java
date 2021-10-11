package com.johann.algorithm.linkedList;

/**
 * @ClassName: LinkedListInit
 * @Description: 链表初始化
 * @Author: Johann
 * @Date: 2021-06-21 14:28
 *
 * 时间复杂度
 *
 *  	最坏情况	平均情况
 * 搜索	O(n)	O(n)
 * 插入	O(1)	O(1)
 * 删除	O(1)	O(1)
 * 空间复杂度：O(n)
 *
 *
 **/
public class LinkedListInit {

    // Creating a node
    Node head;

    static class Node {
        int value;
        Node next;

        Node(int d) {
            value = d;
            next = null;
        }
    }

    public static void main(String[] args) {
        LinkedListInit linkedList = new LinkedListInit();

        // Assign value values
        linkedList.head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);

        // Connect nodess
        linkedList.head.next = second;
        second.next = third;

        // printing node-value
        while (linkedList.head != null) {
            System.out.print(linkedList.head.value + " ");
            linkedList.head = linkedList.head.next;
        }
    }
}
