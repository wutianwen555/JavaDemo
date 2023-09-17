package algorithm;

import static org.junit.Assert.*;

public class BubbleSortTest {

    @org.junit.Test
    public void bubbleSort() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.bubbleSort(arr);
        assertArrayEquals(arr, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }
}