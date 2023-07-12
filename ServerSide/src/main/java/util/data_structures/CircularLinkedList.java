package util.data_structures;

import java.util.NoSuchElementException;

public class CircularLinkedList {
    private ListNode last;
    private int length;

    public class ListNode {
        private int data;
        private ListNode next;

        public ListNode(int data) {
            this.data = data;
        }
    }

    public CircularLinkedList() {
        last = null;
        length = 0;
    }

    public int length() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public void print(){
        if(last == null){
            return;
        }

        ListNode first = last.next;

        while(first != last) {
            System.out.print(first.data + " --> ");
            first = first.next;
        }
        System.out.print(first.data);
    }

    public void insertFirst(int data) {
        ListNode newNode = new ListNode(data);
        if(last == null){
            last = newNode;
        } else {
            newNode.next = last.next;
        }
        last.next = newNode;
        length++;
    }

    public void insertLast(int data) {
        ListNode newNode = new ListNode(data);
        if(last == null){
            last = newNode;
            last.next = last;
        } else {
            newNode.next = last.next;
            last.next = newNode;
            last = newNode;
            length++;
        }
    }

    public void deleteFirst(){
        if(isEmpty()){
            throw new NoSuchElementException("you're a silly, silly man: list is empty");
        }

        ListNode pointer = last.next;

        if(last.next == last){
            last = null;
        } else {
            last.next = pointer.next;
        }
        pointer.next = null;
        length--;
        return;
    }

    public void createCircularLinkedList() {
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);

        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = first;
    }
}
