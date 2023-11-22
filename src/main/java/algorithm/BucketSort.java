package algorithm;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.sort;

public class BucketSort {
    public static void bucketSort(int[] arr) {
        //确定数组中的最大值和最小值
        int max = arr[0];
        int min = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        //确定每个桶的大小和数量
        int bucketSize = 10;
        int bucketCount = (max - min) / bucketSize + 1;
        //创建桶数组，每个元素是一个列表
        List<Integer>[] buckets = new List[bucketCount];
        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new ArrayList<>();
        }
        //将原始数组中的元素按照映射规则放入对应的桶中
        for (int num : arr) {
            int index = (num - min) / bucketSize;
            buckets[index].add(num);
        }
        //对每个非空的桶进行排序，并将结果放回原始数组中
        int k = 0;
        for (List<Integer> bucket : buckets) {
            if (!bucket.isEmpty()) {
                //将列表转换为数组，方便使用插入排序
                int[] bucketArr = new int[bucket.size()];
                for (int i = 0; i < bucket.size(); i++) {
                    bucketArr[i] = bucket.get(i);
                }
                //对每个桶进行排序
                sort(bucketArr);
                //将每个桶中的元素放回原始数组中
                for (int num : bucketArr) {
                    arr[k++] = num;
                }
            }
        }
    }
}
