package Lec6_Arrays;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Assignment7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Find max and min in an array");
            System.out.println("2. Reverse an array");
            System.out.println("3. Count occurrences of a number in an array");
            System.out.println("4. To-Do List (add, view, remove)");
            System.out.println("5. Get even numbers from an array");
            System.out.println("6. Enter numbers until -1 and get the sum");
            System.out.println("7. Remove duplicates from ArrayList");
            System.out.println("8. Sort an array and binary search");
            System.out.println("9. Swap two elements in an array");
            System.out.println("10. Concatenate two arrays");
            System.out.println("11. Find largest and second largest number in an array");
            System.out.println("12. Count character frequency in a string");
            System.out.println("13. Merge two sorted arrays");
            System.out.println("14. Remove elements > 50 from ArrayList");
            System.out.println("15. Convert between Array and ArrayList");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1: findMaxMin(sc); break;
                case 2: reverseArray(sc); break;
                case 3: countOccurrences(sc); break;
                case 4: todoList(); break;
                case 5: evenNumbers(); break;
                case 6: sumUntilMinusOne(sc); break;
                case 7: removeDuplicatesList(); break;
                case 8: sortAndBinarySearch(sc); break;
                case 9: swapArrayElements(); break;
                case 10: concatenateArrays(sc); break;
                case 11: secondLargest(sc); break;
                case 12: charFrequency(sc); break;
                case 13: mergeSortedArrays(sc); break;
                case 14: removeGreaterThan50(sc); break;
                case 15: convertArrayAndArrayList(sc); break;
                case 0: System.out.println("Exiting program. Goodbye!"); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 0);

        sc.close();
    }
 
    public static void findMaxMin(Scanner sc) {
        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " integers: ");
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int max = arr[0], min = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
            if (num < min) min = num;
        }
        System.out.println("Max value: " + max);
        System.out.println("Min value: " + min);
    }
 
    public static void reverseArray(Scanner sc) {
        System.out.print("Enter array length: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter elements: ");
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        for (int i = 0; i < n / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = temp;
        }
        System.out.println("Reversed array: " + Arrays.toString(arr));
    }

    public static void countOccurrences(Scanner sc) {
        int[] arr = {2,5,6,5,2,5};
        System.out.print("Enter number to find: ");
        int x = sc.nextInt();
        int count = 0;
        for (int num : arr) if (num == x) count++;
        if (count > 0) System.out.println(x + " occurs " + count + " times.");
        else System.out.println(x + " does not appear in the array.");
    }
    
    public static void todoList() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        int c;
        do {
            System.out.println("\n===== To-Do List Menu =====");
            System.out.println("1. Add a task");
            System.out.println("2. View tasks");
            System.out.println("3. Remove task by index");
            System.out.println("4. Exit");
            System.out.print("Your choice: ");
            c = scanner.nextInt();
            scanner.nextLine();
            switch (c) {
                case 1: 
                    System.out.print("Enter a new task: "); 
                    tasks.add(scanner.nextLine()); 
                    System.out.println("Task added!"); 
                    break;
                case 2: 
                    if (tasks.isEmpty()) System.out.println("No tasks yet!"); 
                    else for (int i = 0; i < tasks.size(); i++) System.out.println(i + ". " + tasks.get(i)); 
                    break;
                case 3: 
                    System.out.print("Enter index to remove: "); 
                    int index = scanner.nextInt(); 
                    if (index >=0 && index < tasks.size()) System.out.println("Removed: " + tasks.remove(index)); 
                    else System.out.println("Invalid index!"); 
                    break;
                case 4: System.out.println("Exiting To-Do List..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (c != 4);
    }

    public static void evenNumbers() {
        int[] arr = {1,2,3,4,5,6,7,8};
        ArrayList<Integer> evens = new ArrayList<>();
        for (int num : arr) if (num % 2 == 0) evens.add(num);
        System.out.println("Even numbers: " + evens);
    }

    public static void sumUntilMinusOne(Scanner sc) {
        ArrayList<Integer> list = new ArrayList<>();
        int sum = 0;
        System.out.println("Enter numbers (-1 to stop): ");
        while (true) {
            int num = sc.nextInt();
            if (num == -1) break;
            list.add(num);
            sum += num;
        }
        System.out.println("Numbers entered: " + list);
        System.out.println("Sum: " + sum);
    }

    public static void removeDuplicatesList() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,3,2,1,4,3,5));
        System.out.println("Original list: " + list);
        for (int i = 0; i < list.size(); i++) {
            int current = list.get(i);
            for (int j = i+1; j < list.size(); j++) {
                if (list.get(j).equals(current)) {
                    list.remove(j);
                    j--;
                }
            }
        }
        System.out.println("List without duplicates: " + list);
    }

    public static void sortAndBinarySearch(Scanner sc) {
        int[] arr = new int[5];
        System.out.print("Enter 5 numbers: ");
        for (int i = 0; i < 5; i++) arr[i] = sc.nextInt();
        Arrays.sort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
        System.out.print("Enter number to find: ");
        int k = sc.nextInt();
        int index = Arrays.binarySearch(arr,k);
        if(index>=0) System.out.println(k + " found at index " + index);
        else System.out.println(k + " not found");
    }

    public static void swapArrayElements() {
        int[] arr = {10,20,30};
        System.out.println("Original array: " + Arrays.toString(arr));
        int temp = arr[0]; arr[0]=arr[2]; arr[2]=temp;
        System.out.println("After swap: " + Arrays.toString(arr));
    }

    public static void concatenateArrays(Scanner sc) {
        System.out.print("Enter size of array 1: ");
        int n1 = sc.nextInt();
        int[] a1 = new int[n1];
        System.out.print("Enter array 1: ");
        for (int i = 0; i < n1; i++) a1[i] = sc.nextInt();
        System.out.print("Enter size of array 2: ");
        int n2 = sc.nextInt();
        int[] a2 = new int[n2];
        System.out.print("Enter array 2: ");
        for (int i = 0; i < n2; i++) a2[i] = sc.nextInt();
        int[] result = new int[n1+n2];
        System.arraycopy(a1,0,result,0,n1);
        System.arraycopy(a2,0,result,n1,n2);
        System.out.println("Concatenated array: " + Arrays.toString(result));
    }

    public static void secondLargest(Scanner sc) {
        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int max1 = arr[0], max2 = arr[0];
        for (int num : arr) if(num>max1) max1=num;
        for (int num : arr) if(num<max1 && num>max2) max2=num;
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Largest: " + max1);
        System.out.println("Second largest: " + max2);
    }

    public static void charFrequency(Scanner sc) {
        System.out.print("Enter a string: ");
        String str = sc.nextLine();
        int[] freq = new int[26];
        for(char c : str.toCharArray()) {
            if(c>='a' && c<='z') freq[c-'a']++;
        }
        System.out.println("Character frequency:");
        for(int i=0;i<26;i++) if(freq[i]>0) System.out.println((char)(i+'a')+": "+freq[i]);
    }

    public static void mergeSortedArrays(Scanner sc) {
        System.out.print("Enter size of array 1: ");
        int n1 = sc.nextInt();
        int[] a1 = new int[n1];
        System.out.print("Enter sorted array 1: ");
        for (int i=0;i<n1;i++) a1[i] = sc.nextInt();
        System.out.print("Enter size of array 2: ");
        int n2 = sc.nextInt();
        int[] a2 = new int[n2];
        System.out.print("Enter sorted array 2: ");
        for (int i=0;i<n2;i++) a2[i] = sc.nextInt();
        int[] merged = new int[n1+n2];
        int i=0,j=0,k=0;
        while(i<n1 && j<n2) {
            if(a1[i]<=a2[j]) merged[k++] = a1[i++];
            else merged[k++] = a2[j++];
        }
        while(i<n1) merged[k++] = a1[i++];
        while(j<n2) merged[k++] = a2[j++];
        System.out.println("Merged array: " + Arrays.toString(merged));
    }

    public static void removeGreaterThan50(Scanner sc) {
        System.out.print("Enter list size: ");
        int n = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        System.out.print("Enter elements: ");
        for(int i=0;i<n;i++) list.add(sc.nextInt());
        for(int i=list.size()-1;i>=0;i--) if(list.get(i)>50) list.remove(i);
        System.out.println("List after removal: " + list);
    }

    public static void convertArrayAndArrayList(Scanner sc) {
   
        System.out.print("Enter number of elements in array: ");
        int n = sc.nextInt();
        sc.nextLine();
        String[] arr = new String[n];
        System.out.println("Enter array elements: ");
        for(int i=0;i<n;i++) arr[i]=sc.nextLine();
        ArrayList<String> list = arrayToArrayList(arr);
        System.out.println("Converted ArrayList: " + list);

        ArrayList<String> newList = new ArrayList<>(Arrays.asList("Charles","David"));
        String[] newArr = arrayListToArray(newList);
        System.out.println("Array converted from ArrayList: " + Arrays.toString(newArr));
    }

    public static ArrayList<String> arrayToArrayList(String[] arr) {
        ArrayList<String> list = new ArrayList<>();
        for(String s : arr) list.add(s);
        return list;
    }

    public static String[] arrayListToArray(ArrayList<String> list) {
        String[] arr = new String[list.size()];
        return list.toArray(arr);
    }
}
