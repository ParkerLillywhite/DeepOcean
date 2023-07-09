package util.data_structures;

public class SinglyLinkedList {
    //ints only :(
    private ListNode head;

    private static class ListNode {
        private int data;
        private ListNode next;

        public ListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public void printList() {
        ListNode current = head;
        while(current != null){
            System.out.println(current.data + " --> ");
            current = current.next;
        }
    }

    public int size() {
        if(head == null){
            return 0;
        }
        int count = 0;
        ListNode current = head;
        while(current != null){
            count++;
            current = current.next;
        }
        return count;
    }

    public void insert(int position, int value) {
        ListNode newNode = new ListNode(value);

        if(position == 1) {
            newNode.next = head;
            head = newNode;
        } else {
            ListNode previous = head;
            int count = 1;

            while(count < position - 1){
                previous = previous.next;
                count++;
            }

            ListNode current = previous.next;
            previous.next = newNode;
            newNode.next = current;
        }
    }

    public void insertFirst(int value){
        ListNode newNode = new ListNode(value);

        //sets the head to the value of the new node's next, which is null.
        newNode.next = head;

        //sets the head to be equal to the 'position' of newNode
        head = newNode;
    }

    public void insertLast(int value) {
        ListNode newNode = new ListNode(value);
        if(head == null){
            head = newNode;
            return;
        }
        ListNode current = head;
        while(current.next != null){
            current = current.next;
        }
        current.next = newNode;
    }

    public void delete(int position) {
        if(position == 1){
            head = head.next;
        } else {
            ListNode previousNode = head;
            int nodeCount = 1;
            while(nodeCount < position - 1){
                previousNode = previousNode.next;
                nodeCount++;
            }
            ListNode currentNode = previousNode.next;
            previousNode.next = currentNode.next;
        }
    }

    public ListNode deleteFirst() {
        if(head == null){
            return  null;
        }

        ListNode temporaryNode = head;
        head = head.next;
        temporaryNode.next = null;
        return temporaryNode;
    }

    public ListNode deleteLast(){
        if(head == null || head.next == null){
            return head;
        }
        ListNode currentNode = head;
        ListNode previousNode = null;

        while(currentNode.next != null) {
            previousNode = currentNode;
            currentNode.next = currentNode;
        }
        previousNode.next = null; // break the chain "deleting" current
        return currentNode;
    }
}
