package sorts;

public class HeapSort {

    // 下沉操作 让小元素下沉到最下面的位置：让堆顶元素大于左右叶子节点
    public static void sink(int[] array, int k, int length) {
        int N = length;

        // 下沉直至无子节点
        while (k * 2 <= N) {
            int pos = k * 2;

            // 找到左右节点中的最大值
            if (pos < N && array[pos] < array[pos + 1]) {
                pos = pos + 1;
            }

            // 如果要必要的节点的值比最大值还要大 则直接跳过
            if (array[k] > array[pos]) {
                break;
            }

            // 下沉小元素
            SortMain.exchange(array, k, pos);

            // 继续下沉
            k = pos;
        }
    }

    /***
     * 堆排序
     * 能够最优的利用空间和时间
     * 适合空间开销是否紧张的时候，比如嵌入式或者低成本移动设备。能够用几行机器码实现。
     * 但现在计算很少使用它，因为它无法利用缓存，元素很少和相邻元素进行比较，因此缓存命中的次数远远小于使用和相邻元素比较的算法
     * 比如：快速排序，归并排序，甚至是希尔排序。
     *
     * 反而用堆实现的优先队列在现代应用程序中越来越重要。
     *
     * */
    public static void sort(int[] array) {
        // 建立大根堆 (从边缘的节点开始，循环递进建立大根堆), 注意第0个元素是不使用的。
        for (int i = (array.length - 1) / 2; i >= 1; i--) {
            sink(array, i, array.length - 1);
        }

        // 依次将堆顶元素与最后一个叶子节点交换，堆剩下的堆再进行堆有序操作
        int k = array.length - 1;
        while(k > 1) {
            SortMain.exchange(array, 1, k--);
            sink(array, 1, k);
        }
    }

}
