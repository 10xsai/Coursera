public class MergeSort {
    public static void sort(int[] a) {
        int N = a.length;
        int low = 0;
        int high = N - 1;
        int[] aux = new int[N];
        sort(a, aux, low, high);
    }

    public static void sort(int[] a, int[] aux, int low, int high) {
        if (high <= low) return;
        int middle = (low + high) / 2;
        sort(a, aux, low, middle);
        sort(a, aux, middle + 1, high);
        merge(a, aux, low, middle, high);
    }

    public static void merge(int[] a, int[] aux, int low, int middle, int high) {
        for (int k = low; k <= high; k++) {
            aux[k] = a[k];
        }
        int i = low, j = middle + 1;
        for (int k = low; k <= high; k++) {
            if (i > middle) a[k] = aux[j++];
            else if (j > high) a[k] = aux[i++];
            else if (isLess(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static <T extends Comparable<T>> boolean isLess(T v, T w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        // Test with Integer array
        int[] numbers = {5, 1, 12, 4, 8};
        sort(numbers);
        for (Integer num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println(); // Prints: 1 4 5 8 12
    }
}
