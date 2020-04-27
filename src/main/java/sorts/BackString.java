package sorts;

public class BackString {

//    输入字符串“abcdefg”和数字2，该函数将返回左旋两位得到的结果“cdefgab”。
    public static void main(String[] args) {
        char[] str = "abcdefg".toCharArray();
        int index = 2;

        reverseStr(str, 0, str.length - 1);
        reverseStr(str, 0, str.length - index - 1);
        reverseStr(str, str.length - index, str.length - 1);

        System.out.println(str);
    }

    public static void reverseStr(char[] strArray, int start, int end) {
        if (start == end) {
            return;
        }
        while (start < end) {
            exchange(strArray, start, end);
            start++;
            end--;
        }
    }

    public static void exchange(char[] strArray, int pos1, int pos2) {
        char tmp = strArray[pos1];
        strArray[pos1] = strArray[pos2];
        strArray[pos2] = tmp;
    }
}
