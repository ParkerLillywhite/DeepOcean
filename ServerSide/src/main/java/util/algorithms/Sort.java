package util.algorithms;

public class Sort {

    public Sort() {

    }

    //iteratively swaps each element in the arr with the one next to it until the array is sorted.
    //bigO(n^2)
    public void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean isSwapped;
        for(int i = 0; i < n - 1; i++) {
            isSwapped = false;
            for(int j = 0; j < n - 1 - i; j++) {
                if(arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSwapped = true;
                }
            }
            if(isSwapped == false) break;
        }
    }


    //creates a line dividing the sorted array starting and index 0, and unsorted array.
    //if the selected index is smaller than it's preceding one, it is swapped with that one
    public void insertionSort(int[] arr) {
        int n = arr.length;
        for(int i = 1; i < n; i++) {
            int j = i - 1;
            int temp = arr[i];
            while(j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = temp;
        }
    }


    //for loop creates a divider separating the unsorted and sorted array.
    //then searches the unsorted for the minimum and swaps the first index of the unsorted with the minimum.
    public void selectionSort(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[j] < arr[min]) {
                    min = arr[j];
                }
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }
}
