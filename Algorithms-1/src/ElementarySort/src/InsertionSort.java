public class InsertionSort {
    public static <T extends Comparable<T>> void sort(T[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (isLess(a[j], a[j - 1]))
                    swap(a, j, j - 1);
                else break;
            }
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
