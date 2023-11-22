package algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class SelectionSortTest {

    @Test
    public void selectionSort() {
        int[] arr = {3, 4, 1, 2, 10, 6, 8, 5, 9, 7};
        SelectionSort.selectionSort(arr);
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertArrayEquals(expected, arr);
    }
}