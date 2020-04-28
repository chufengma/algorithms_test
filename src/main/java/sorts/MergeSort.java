package sorts;

/**
 * Create at  : 2020/4/28
 * Description:
 */
public class MergeSort {

    public static void mergeSort(int[] array) {
        sort(array, 0, array.length - 1);
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
            } else if (secondPos > end) {  // 第一堆用完，直接放置第一堆的数据
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
        mergeSort(array);
        SortMain.printComparable(array);
    }

}
