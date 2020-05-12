package others;

import sorts.SortMain;

import java.util.ArrayList;
import java.util.List;

public class PriorityCache {

    private List<Integer> dataArray = new ArrayList<>();

    public PriorityCache() {
        dataArray.add(0);
    }

    // 上浮，重新堆有序
    public void swim(int position) {
        if (position / 2 <= 1) {
            return;
        }
        while(position / 2 > 0) {
            if (dataArray.get(position) > dataArray.get(position / 2)) {
                SortMain.exchange(dataArray, position, position / 2);
            }
            position = position / 2;
        }
    }

    // 下沉，重新建立堆有序
    public void sink(int position) {
        while(position * 2 <= dataArray.size() - 1) {
            int pos = position * 2;
            if (pos + 1 <= dataArray.size() - 1 &&
                    (dataArray.get(pos) < dataArray.get(pos + 1))) {
                pos++;
            }

            if (dataArray.get(pos) < dataArray.get(position)) {
                break;
            }

            SortMain.exchange(dataArray, pos, position);
            position = pos;
        }
    }

    /**
     * 插入依次执行下沉操作，每次都是建立堆有序的过程
     */
    public void insert(int value) {
        dataArray.add(value);
        swim(dataArray.size() - 1);
    }

    /**
     * 删除最高优先级的数据。
     * 1、将最后一个元素和顶部元素交换。
     * 2、删除最后一个元素，也就是最大优先级
     * 3、对堆顶元素进行下沉操作，重新
     */
    public int removeTop() {
        if (dataArray.size() <= 1) {
            return -1;
        }

        SortMain.exchange(dataArray, 1, dataArray.size() - 1);
        int value = dataArray.remove(dataArray.size() - 1);
        sink(1);
        return value;
    }

    public static void main(String[] args) {
        PriorityCache priorityCache = new PriorityCache();
        int[] array = new int[]{0, 12, 323, 223, 2, 5, 2, 34, 123};
        for(int item : array) {
            priorityCache.insert(item);
        }

        /**
         *         323
         *     123     223
         *   34   0   5    2
         * 12  2
         */
        // SortMain.printList(priorityCache.dataArray);

        priorityCache.removeTop();
        priorityCache.removeTop();

        /**
         *         123
         *     34      5
         *   12   0   2    2
         */
        SortMain.printList(priorityCache.dataArray);
    }

}
