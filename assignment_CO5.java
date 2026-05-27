import java.util.Arrays;

public class assignment_CO5 {

    // LSD Radix Sort
    static int[] radixSort(int[] arr, int d) {

        int[] current = arr.clone();

        int divisor = 1;

        for (int pass = 0; pass < d; pass++) {

            current = countingSortByDigit(
                    current,
                    divisor);

            System.out.println(
                    "\nAfter Pass "
                            + (pass + 1)
                            + " : "
                            + Arrays.toString(current));

            divisor *= 10;
        }

        return current;
    }

    // Stable Counting Sort
    static int[] countingSortByDigit(
            int[] in,
            int divisor) {

        int[] out = new int[in.length];

        int[] count = new int[10];

        // Count occurrences

        for (int num : in) {

            int digit = digitAt(num, divisor);

            count[digit]++;
        }

        // Convert to prefix sums

        for (int i = 1; i < 10; i++) {

            count[i] += count[i - 1];
        }

        // Build output array
        // Reverse traversal preserves stability

        for (int i = in.length - 1; i >= 0; i--) {

            int digit = digitAt(in[i], divisor);

            out[count[digit] - 1] = in[i];

            count[digit]--;
        }

        return out;
    }

    // Extract digit
    static int digitAt(
            int n,
            int divisor) {

        return (n / divisor) % 10;
    }

    public static void main(String[] args) {

        int[] arr = {
                473,
                152,
                681,
                247,
                539,
                826,
                715,
                304
        };

        System.out.println(
                "Original Array:");

        System.out.println(
                Arrays.toString(arr));

        int[] sorted = radixSort(arr, 3);

        System.out.println(
                "\nFinal Sorted Array:");

        System.out.println(
                Arrays.toString(sorted));
    }
}