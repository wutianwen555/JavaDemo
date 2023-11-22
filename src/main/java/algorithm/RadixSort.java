package algorithm;

public class RadixSort {
    public static void radixSort(int[] arr) {
    if (arr == null || arr.length <= 1) {
        return;
    }

    // 找到数组中的最大值，确定最大位数
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++) {
        max = Math.max(max, arr[i]);
    }

    // 根据个位进行排序
    int exp = 1;
    while (max / exp > 0) {
        countingSort(arr, exp);
        exp *= 10;
    }
}
private static void countingSort(int[] arr, int exp) {
    int n = arr.length;
    int[] count = new int[10];
    int[] output = new int[n];

    // 统计桶中每个数字的个数
    for (int i = 0; i < n; i++) {
        count[(arr[i] / exp) % 10]++;
    }

    // 计算每个数字的个数累加
    for (int i = 1; i < 10; i++) {
        count[i] += count[i - 1];
    }

    // 将元素按照顺序放入输出数组
    for (int i = n - 1; i >= 0; i--) {
        output[count[(arr[i] / exp) % 10] - 1] = arr[i];
        count[(arr[i] / exp) % 10]--;
    }

    // 将输出数组中的元素复制回原始数组
    for (int i = 0; i < n; i++) {
        arr[i] = output[i];
    }
}

}
