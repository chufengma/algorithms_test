package sorts;

/**
 * Create at  : 2020/5/7
 * Description:
 */
public class QuickSort {

    public static void sort(int[] array) {
        oneSort(array, 0, array.length - 1);
    }

    public static void oneSort(int[] array, int low, int high) {
        if (high <= low) {
            return;
        }
        int pos = partition(array, low, high);
        oneSort(array, low, pos - 1);
        oneSort(array, pos + 1, high);
    }

    public static int partition(int[] array, int low, int high) {
        int theOne = array[low];
        int i = low;
        int j = high + 1;
        while(true) {
            // 从左往右找比theOne大的
            while(array[++i] < theOne) if(i == high) break;
            // 从右往左找比theOne小的
            while(array[--j] > theOne) if(j == low) break;
            // 当任意一边都没找到说明已经分割完毕
            if (i >= j) {
                break;
            }
            // 交换位置，使小的在左边，大的在右边
            SortMain.exchange(array, i, j);
        }

        // 将此次对比的数字交换到"中间位置"，该位置左边都比它小，右边都比它大
        SortMain.exchange(array, low, j);
        return j;
    }

}
