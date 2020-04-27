package sorts;

public class Insertion {


    /***
     * 插入排序：想象扑克牌抓牌阶段，手里的牌是已经排好序的，抓到的牌需要插入到手里的合适的位置
     * 非常适合已经是有序的或者接近有序的数据 对于部分有序和小规模的数组应该选择插入排序
     *
     * 时间复杂度：n ~ n^2
     * 空间复杂度：0
     */
    public static void sort(int[] array) {
        if (array.length <= 1) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            for(int j = i;j > 0 && array[j] < array[j - 1];j--) {
                SortMain.exchange(array, j, j - 1);
            }
        }
    }
}
