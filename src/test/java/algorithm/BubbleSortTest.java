package algorithm;

import static org.junit.Assert.*;

public class BubbleSortTest {

    @org.junit.Test
    public void bubbleSort() {
        int[] arr = {3, 4, 1, 2, 10, 6, 8, 5, 9, 7};
        BubbleSort.bubbleSort(arr);
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertArrayEquals(expected, arr);
    }
}