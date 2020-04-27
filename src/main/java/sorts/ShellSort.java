package sorts;

public class ShellSort {


    /**
     * 希尔排序：因为直接插入排序在数组有序的情况下表现良好，那么可以通过分隔逐步将数据变成有序。
     * 给定一个一个步长，每个步长构成一个数组，将这个步长数据进行直接插入排序。当步长位1的时候数组就已排好序
     *
     * 因为对于中等大小的数组它的运行时间是可以接受的，它的代码量很小，且不需要使用额外的内存空间。
     *
     * 希尔排序比冒泡排序快5倍，比插入排序大致快2倍
     * 平均时间复杂度：O(nlog2n)
     */
    public static void sort(int[] arrays) {
        for (int stepLength = arrays.length / 2; stepLength >= 1; stepLength = stepLength / 2) { // 逐步缩减步长
            for (int i = stepLength; i < arrays.length; i += stepLength) { // 直接插入排序
                for (int j = i; j > 0 && arrays[j] < arrays[j - stepLength]; j -= stepLength) {
                    SortMain.exchange(arrays, j, j - stepLength);
                }
            }
        }
    }
}
