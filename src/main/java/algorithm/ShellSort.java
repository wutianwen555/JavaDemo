package algorithm;

public class ShellSort {
    public static void shellSort(int[] arr) {
        int d = arr.length;
        while (d > 1) {
            d = d / 2;
            for (int i = d; i < arr.length; i++) {
                int temp = arr[i];
                int j = i - d;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + d] = arr[j];
                    j = j - d;
                }
                arr[j + d] = temp;
            }
        }
    }
}
