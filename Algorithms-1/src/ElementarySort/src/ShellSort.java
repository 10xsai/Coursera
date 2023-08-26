public class ShellSort {
    public static <T extends Comparable<T>> void sort(T[] a) {
        int N = a.length;

        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && isLess(a[j], a[j - h]); j -= h) {
                    swap(a, j, j - h);
                }
            }
            h /= 3;
        }
    }

    public static <T extends Comparable<T>> boolean isLess(T v, T w) {
        return v.compareTo(w) < 0;
    }

    public static <T> void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        // Test with Integer array
        Integer[] numbers = {5, 1, 12, 4, 8};
        sort(numbers);
        for (Integer num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println(); // Prints: 1 4 5 8 12

        // Test with String array
        String[] words = {"banana", "apple", "cherry", "date"};
        sort(words);
        for (String word : words) {
            System.out.print(word + " ");
        }
        // Prints: apple banana cherry date
    }
}
