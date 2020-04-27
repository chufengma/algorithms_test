package sorts;

public class Selection {

    /***
     * 选择排序：每一轮选择选一个最小的值放到每一轮起始位置
     *
     * 交换次数和数组的大小是线性关系，其它算法都是线性对数或是平方级别
     * 运行时间和输入无关
     *
     * 时间复杂度：n^2
     * 空间复杂度：1
     */
    public static void sort(int[] array) {
        if (array.length <= 1) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            int min = array[i];
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                SortMain.exchange(array, minIndex, i);
            }
        }
    }
}
