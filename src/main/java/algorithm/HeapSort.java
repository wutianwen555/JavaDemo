package algorithm;

public class HeapSort {
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // 构建最大堆
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // 堆排序
        for (int i = n - 1; i > 0; i--) {
            // 将堆顶元素（最大值）与堆末尾元素交换位置
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // 调整堆
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i; // 初始化最大值为当前节点
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // 如果左子节点比最大值大，则更新最大值
        if (left < n && arr[left] > arr[largest])
            largest = left;

        // 如果右子节点比最大值大，则更新最大值
        if (right < n && arr[right] > arr[largest])
            largest = right;

        // 如果最大值不是当前节点，将最大值与当前节点交换位置，并继续调整交换后的子树
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }
}
