package util.data_structures;

public class MaxPriorityQueue {
    private Integer[] heap;
    private int n; // size of max heap

    public MaxPriorityQueue(int capacity) {
        heap = new Integer[capacity + 1]; //index zero is kept as empty
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(int data)  {
        if(n == heap.length - 1) {
            resize(2*heap.length);
        }
        n++;
        heap[n] = data;
        swim(n);
    }

    public void swim(int k) {
        while(k > 1 && heap[k/2] < heap[k]) {
            int temp = heap[k];
            heap[k] = heap[k/2];
            heap[k/2] = temp;
            k = k/2; //because we need to keep traversing and check against parent node
        }
    }

    public void resize(int capacity) {
        Integer[] temp = new Integer[capacity];
        for(int i = 0; i < heap.length; i++) {
            temp[i] = heap[i];
        }
        heap = temp;
    }
}











