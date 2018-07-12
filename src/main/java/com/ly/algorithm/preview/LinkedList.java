package com.ly.algorithm.preview;

/**
 * @Description:链表面试总结学习
 * @Date 2018-06-19 11:27
 * @Author ly
 */
public class LinkedList {

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head;
    private Node current;

    //添加节点
    public void add(int data) {
        if (head == null) {
            Node node = new Node(data);
            head = node;
            current = node;
        } else {
            current.next = new Node(data);
            current = current.next;
        }
    }

    //遍历链表
    public void allLinked(Node node) {
        if (node == null) {
            System.out.println("null...");
            return;
        } else {
            current = node;
            while (current != null) {
                System.out.println("node=" + current.data);
                current = current.next;
            }
        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        //向LinkList中添加数据
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        // 从head节点开始遍历输出
        list.allLinked(list.head);
    }

}
