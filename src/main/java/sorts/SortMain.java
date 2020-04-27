package sorts;

public class SortMain {

    public static void exchange(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{12, 323, 223, 2, 5, 2, 34, 123};

//        Insertion.sort(array);
//        Selection.sort(array);
        ShellSort.sort(array);

        for(int result : array) {
            System.out.println(result + ",");
        }
    }

}
