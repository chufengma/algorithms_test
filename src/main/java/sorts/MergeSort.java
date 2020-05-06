package sorts;

/**
 * Create at  : 2020/4/28
 * Description:
 */
public class MergeSort {

    /***
     * 以空间换时间。时间复杂度平均表现比较好，但需要额外的空间。适合大数据。
     * 优点：排序所需时间和NlogN成正比，快
     * 缺点：需要N的辅助空间
     *
     * 最欢的情况下复杂度O(~NlogN)。
     * 自顶向下：递归方式基于分治思想。
     * 自底向上：规定步长，层层外扩
     */
    public static void mergeSort(int[] array) {
        // 自顶向下 递归解决
        sort(array, 0, array.length - 1);
    }

    public static void mergeSortFromBottom(int[] array) {
        // 自底向上 循环解决
        for (int i = 1; i < array.length / 2 + 1; i = i * 2) {
            for (int j = 0; j < array.length - 1; j = j + i * 2) {
                int min = j;
                int max = j + i * 2 - 1;
                if (max > array.length - 1) {
                    max = array.length - 1;
                }
                int middle = (min + max) / 2;
                merge(array, j, middle, max);
            }
        }
    }

    private static void sort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int middle = start + (end - start) / 2;
        sort(array, start, middle);
        sort(array, middle + 1, end);
        merge(array, start, middle, end);
    }

    private static void merge(int[] array, int start, int middle, int end) {
        int[] tmpArray = new int[array.length];
        // 数据复制一份待操作
        for (int i = start; i < tmpArray.length; i++) {
            tmpArray[i] = array[i];
        }

        int firstPos = start;
        int secondPos = middle + 1;

        // "依次抓牌"
        for (int i = start; i < array.length; i++) {
            if (firstPos > middle) { // 第一堆用完，直接放置第二堆的数据
                array[i] = tmpArray[secondPos++];
            } else if (secondPos > end) {  // 第二堆用完，直接放置第一堆的数据
                array[i] = tmpArray[firstPos++];
            } else if (tmpArray[firstPos] < tmpArray[secondPos]) { // 2堆都没有用完，比较大小
                array[i] = tmpArray[firstPos++];
            } else { // 2堆都没有用完，比较大小
                array[i] = tmpArray[secondPos++];
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 5, 7, 9, 2, 4, 6, 8, 10};
//        merge(array, 0, 4, array.length - 1);
        mergeSortFromBottom(array);
        SortMain.printComparable(array);
    }

}
