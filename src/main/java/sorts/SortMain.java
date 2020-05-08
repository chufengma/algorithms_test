package sorts;

public class SortMain {

    public static void exchange(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
//        Integer[] array = new Integer[]{12, 323, 223, 2, 5, 2, 34, 123};
        int[] array = new int[]{0, 12, 323, 223, 2, 5, 2, 34, 123};
//        Insertion.sort(array);
//        Selection.sort(array);
//        ShellSort.sort(array);
//        QuickSort.sort(array);
        HeapSort.sort(array);
        printComparable(array);
    }

    public static void printComparable(int[] array) {
        for(Object result : array) {
            System.out.println(result + ",");
        }
    }

    public static void printComparable(Comparable[] array) {
        for(Object result : array) {
            System.out.println(result + ",");
        }
    }
}
