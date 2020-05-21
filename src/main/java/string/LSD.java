package string;

public class LSD {

    public static void lsdString(String[] dataArray, int w) {
        int[] count = new int[256 + 1];
        int position = 0;

        // 频率采集
        for (int i = 0; i < dataArray.length; i++) { // 下标加一是为了第二步计算起始位置：因为每一组的起始位置总是前面元素的个数和
            count[dataArray[i].charAt(position) + 1]++;
        }

        // 频率转换成索引
        for (int i = 0; i < count.length - 1; i++) { // 依次将前面的元素和本位置的元素相加，得到本位置元素的起始位置
            count[i + 1] += count[i];
        }

        // 元素分类, 需要使用辅助数组，因为要使用位置替换
        String[] aux = new String[dataArray.length];
        for (int i = 0; i < dataArray.length; i++) {
            aux[count[dataArray[i].charAt(position)]++] = dataArray[i];
        }

        // 数据回写
        for (int i = 0; i < dataArray.length; i++) {
            dataArray[i] = aux[i];
        }
    }

    public static void main(String[] args) {
        String[] dataArray = new String[]{"a", "3", "4", "a", "d", "b", "d", "b", "f", "c", "d", "7"};
        lsdString(dataArray, 9);
        for (String item : dataArray) {
            System.out.println(item + "，");
        }
    }

}
