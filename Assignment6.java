package Lec5_Loop;
import java.util.Random;
import java.util.Scanner;
public class Assignment6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- Exercise 1: Calculate the Sum of Numbers from 1 to N ---");
        System.out.print("Enter a positive integer N: ");
        int N = sc.nextInt();
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += i;
        }
        System.out.println("The sum of numbers from 1 to " + N + " is: " + sum);

        System.out.println("\n--- Exercise 2: Multiplication Table ---");
        System.out.print("Enter an integer from 2 to 9: ");
        int n = sc.nextInt();
        if (n >= 2 && n <= 9) {
            for (int i = 1; i <= 10; i++) {
                System.out.println(n + " x " + i + " = " + (n * i));
            }
        } else {
            System.out.println("Invalid input! Please enter between 2 and 9.");
        }

        System.out.println("\n--- Exercise 3: Draw a Rectangle with Asterisks (*) ---");
        System.out.print("Enter height: ");
        int height = sc.nextInt();
        System.out.print("Enter width: ");
        int width = sc.nextInt();
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        System.out.println("\n--- Exercise 4: Draw a Right-Angled Triangle ---");
        System.out.print("Enter height h: ");
        int h = sc.nextInt();
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }

        System.out.println("\n--- Exercise 5: Calculate Factorial ---");
        System.out.print("Enter a non-negative integer: ");
        int f = sc.nextInt();
        long factorial = 1;
        for (int i = f; i >= 1; i--) {
            factorial *= i;
        }
        System.out.println("The factorial of " + f + " is: " + factorial);

        System.out.println("\n=== Part 2: Guess the Number Game ===");
        Random random = new Random();
        int secret = random.nextInt(100) + 1;
        int guess;
        do {
            System.out.print("Enter your guess (1-100): ");
            guess = sc.nextInt();
            if (guess > secret) {
                System.out.println("Your guess is higher than the secret number!");
            } else if (guess < secret) {
                System.out.println("Your guess is lower than the secret number!");
            } else {
                System.out.println("Congratulations! You guessed correctly!");
            }
        } while (guess != secret);

        System.out.println("\n=== Part 3: Sum of Digits ===");
        System.out.print("Enter a positive integer: ");
        int number = sc.nextInt();
        int digitSum = 0;
        if (number < 0) {
            System.out.println("ERROR: Please enter a positive integer!");
        } else {
            while (number > 0) {
                int digit = number % 10;
                digitSum += digit;
                number /= 10;
            }
            System.out.println("The sum of digits is: " + digitSum);
        }

        System.out.println("\n=== Part 4: Exercise 8 - Input Validation ===");
        int validNumber;
        do {
            System.out.print("Enter a number from 1 to 10: ");
            validNumber = sc.nextInt();
            if (validNumber < 1 || validNumber > 10) {
                System.out.println("Invalid number, please try again.");
            }
        } while (validNumber < 1 || validNumber > 10);
        System.out.println("You entered the number: " + validNumber);

        System.out.println("\n=== Part 5: Exercise 9 - Calculate Average Until 0 is Entered ===");
        int input;
        int total = 0;
        int count = 0;
        System.out.print("Enter a number (enter 0 to stop): ");
        input = sc.nextInt();
        while (input != 0) {
            total += input;
            count++;
            System.out.print("Enter a number (enter 0 to stop): ");
            input = sc.nextInt();
        }
        if (count > 0) {
            double average = (double) total / count;
            System.out.println("The average is: " + average);
        } else {
            System.out.println("No numbers were entered.");
        }

        sc.close();
        System.out.println("\n=== End of Program ===");
    }
}
