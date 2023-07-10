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

    public ListNode insertWithSortedList(int value) {
        ListNode newNode = new ListNode(value);
        if(head == null){
            return newNode;
        }

        ListNode current = head;
        ListNode temp = null;

        while(current != null && current.data < newNode.data) {
            temp = current;
            current = current.next;
        }
        newNode.next = current;
        temp.next = newNode;
        return head;
    }

    public void deleteSorted(int position) {
        ListNode temp = null;
        ListNode current = head;

        if(current != null && current.data == position){
            head = current.next;
            return;
        }

        while(current != null && current.data != position){
            temp = current;
            current = current.next;
        }
        if(current == null) return;
        temp.next = current.next;
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

    public boolean checkIfExists(ListNode head, int searchKey){
        if(head == null){
            return false;
        }

        ListNode current = head;

        while(current != null){
            if(current.data == searchKey) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public ListNode reverse(){

        if(head == null){
            return head;
        }

        ListNode current = head;
        ListNode previous = null;
        ListNode next = null;

        while(current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        return previous;
    }

    public ListNode findMiddle() {
        if(head == null){
            return null;
        }

        ListNode fastPointer = head;
        ListNode slowPointer = head;

        while(fastPointer != null && fastPointer.next != null){
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }
        return slowPointer;
    }

    public ListNode getNthNodeFromEnd(int n) {
        if(head == null){
            return null;
        }

        if(n <= 0) {
            throw new IllegalArgumentException("Invalid Value: " + n);
        }

        ListNode mainPointer = head;
        ListNode referencePointer = head;
        int count = 0;

        while(count < n){
            if(referencePointer == null){
                throw new IllegalArgumentException(n + " is greater than the number of nodes in list.");
            }
            referencePointer = referencePointer.next;
            count++;
        }

        while(referencePointer != null){
            referencePointer = referencePointer.next;
            mainPointer = mainPointer.next;
        }
        return mainPointer;
    }

    public void removeDuplicates(){

        if(head == null){
            return;
        }
        ListNode current = head;
        while(current != null && current.next != null){
            if(current.data == current.next.data){
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
    }

    public boolean containsLoop(){
        ListNode fastPointer = head;
        ListNode slowPointer = head;

        while(fastPointer != null && fastPointer.next != null){
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
        }
        if(slowPointer == fastPointer){
            return true;
        }

        return false;
    }

    public ListNode getStartOfLoop(){
        ListNode fastPointer = head;
        ListNode slowPointer = head;

        while(fastPointer != null && fastPointer.next != null){
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
        }
        if(slowPointer == fastPointer){
            return getStartOfLoop(slowPointer);
        }
        return null;
    }

    public ListNode getStartOfLoop(ListNode pointer){
        ListNode temp = head;
        while(temp != pointer){
            temp = temp.next;
            pointer = pointer.next;
        }
        return temp;
    }

    public void deleteLoop(){
        ListNode slowPointer = head;
        ListNode fastPointer = head;

        while(fastPointer != null && fastPointer.next != null){
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
        }
        if(slowPointer == fastPointer){
            changeNextToNull(slowPointer);
            return;
        }
    }

    private void changeNextToNull(ListNode pointer){
        ListNode temp = head;
        while(temp.next != pointer.next){
            temp = temp.next;
            pointer = pointer.next;
        }
        pointer.next = null;
        return;
    }

    public ListNode merge(ListNode a, ListNode b){
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while(a != null && b != null){
            if(a.data <= b.data){
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }

        if(a == null){
            tail.next = b;
        } else {
            tail.next = a;
        }
        return dummy.next;
    }
}
