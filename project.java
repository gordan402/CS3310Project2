import java.util.*;

public class project {
    public static void main(String[] args) {
        int sum = (int) (Math.random() * 200.0);
        int random = (int) (Math.random() * 10.0);
        if (random > 5)
            sum = sum * -1;
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            int num = (int) (Math.random() * 100.0);
            random = (int) (Math.random() * 10.0);
            if (random > 5)
                num = num * -1;
            arr[i] = num;
        }
        //sort(arr);
        printArray(arr);
        System.out.println("The sum is " + sum);
        long startTime = System.nanoTime();
        System.out.println(unSortedSum2(arr, sum));
        long endTime = System.nanoTime() - startTime;
        System.out.println(endTime);
        //System.out.println(unSortedSum2(arr, sum));
        //System.out.println(sortedSum1(arr, sum));
        //System.out.println(sortedSum2(arr, sum));
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //sorted
    public static String sortedSum1(int arr[], int sum) {
        boolean negative = true;
        int counter = 0;
        while (negative == true && counter<arr.length) {
            if (arr[counter] >= 0)
                negative = false;
            else
                counter++;
        }
        for (int i = 0; i < arr.length; i++) {
            int temp = sum + -arr[i];
            if (temp < 0 && temp>=100) {
                for (int j = 0; j < counter; j++) {
                    if (arr[i] + arr[j] == sum && i != j)
                        return "The numbers " + Integer.toString(arr[i]) + " and " + Integer.toString(arr[j]) + " add up to " + Integer.toString(sum);
                }
            } else if(temp >= 0 && temp<=100){
                for (int j = counter; j < arr.length; j++) {
                    if (arr[i] + arr[j] == sum && i != j)
                        return "The numbers " + Integer.toString(arr[i]) + " and " + Integer.toString(arr[j]) + " add up to " + Integer.toString(sum);
                }
            }
        }
        return "No 2 numbers add together";
    }

    public static String sortedSum2(int[] arr, int sum) {
        int index;
        for (int i = 0; i < arr.length; i++) {
            index = i;
            int temp = sum + -arr[i];
            if (temp < -100 || temp > 100)
                continue;
            if (binarySearch(arr, index, temp) == -1 || binarySearch(arr, index, temp) == i)
                continue;
            else
                return "The numbers " + Integer.toString(arr[i]) + " and " + Integer.toString(arr[binarySearch(arr, index, temp)]) + " add up to " + Integer.toString(sum);
        }
        return "No 2 numbers add together";
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //unsorted
    public static String unSortedSum1(int[] arr, int sum) { //brute force method
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == sum)
                    return "The numbers " + Integer.toString(arr[i]) + " and " + Integer.toString(arr[j]) + " add up to " + Integer.toString(sum);
            }
        }
        return "No 2 numbers add together";
    }

    public static String unSortedSum2(int[] arr, int sum) {
        HashSet<Integer> values = new HashSet<Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (values.contains(sum-arr[i]))
                return "The numbers " + Integer.toString(arr[i]) + " and " + Integer.toString(sum + -arr[i]) + " add up to " + Integer.toString(sum);
            values.add(arr[i]);
        }
        return "No 2 numbers add together";
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //helpers
    public static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
            if (i % 20 == 0 && i>0)
                System.out.println();
        }
        System.out.println();
    }

    public static int binarySearch(int arr[], int left, int x) {
        int l = left, r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == x)
                return mid;
            if (arr[mid] < x)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return -1;
    }

    public static void sort(int arr[]) {
        int n = arr.length;
        for (int i = n / 2; i > 0; i /= 2) {
            for (int j = i; j < n; j++) {
                int temp = arr[j];
                int k;
                for (k = j; k >= i && arr[k - i] > temp; k -= i) {
                    arr[k] = arr[k - i];
                }
                arr[k] = temp;
            }
        }
    }
}
