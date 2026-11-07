package main;

import java.util.Scanner;

public class StackQueue {

    // Display the current character array as a string
    static void displayString(char[] arr, int size) {
        if (size == 0) {
            System.out.println("empty");
        } else {
            for (int i = 0; i < size; i++) {
                System.out.print(arr[i]);
            }
            System.out.println();
        }
    }

    // Pop: remove and return the character at the top (end) of the array
    static char[] pop(char[] arr, int[] sizeHolder) {
        if (sizeHolder[0] == 0) {
            System.out.println("empty");
        } else {
            sizeHolder[0]--;
            System.out.println("Character Popped.");
        }
        return arr;
    }

    // Top: display the character at the top (end) without removing it
    static void top(char[] arr, int size) {
        if (size == 0) {
            System.out.println("empty");
        } else {
            System.out.println("Top Character is " + arr[size - 1]);
        }
    }

    // Push/Enqueue: add a character to the end of the array
    static char[] push(char[] arr, int[] sizeHolder, char c) {
        if (sizeHolder[0] >= arr.length) {
            // Expand array if needed
            char[] newArr = new char[arr.length + 10];
            for (int i = 0; i < arr.length; i++) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
        arr[sizeHolder[0]] = c;
        sizeHolder[0]++;
        System.out.println("Character Pushed/Enqueued.");
        return arr;
    }

    // Dequeue: remove the character at the front (beginning) of the array
    static char[] dequeue(char[] arr, int[] sizeHolder) {
        if (sizeHolder[0] == 0) {
            System.out.println("empty");
        } else {
            // Shift all elements left by one
            for (int i = 0; i < sizeHolder[0] - 1; i++) {
                arr[i] = arr[i + 1];
            }
            sizeHolder[0]--;
            System.out.println("Character Dequeued.");
        }
        return arr;
    }

    // Front: display the character at the front (beginning) without removing it
    static void front(char[] arr, int size) {
        if (size == 0) {
            System.out.println("empty");
        } else {
            System.out.println("Front Character is " + arr[0]);
        }
    }

    // Display the menu
    static void displayMenu() {
        System.out.println();
        System.out.println("   A. Display the string.");
        System.out.println("   B. Pop");
        System.out.println("   C. Top");
        System.out.println("   D. Push/Enqueue");
        System.out.println("   E. Dequeue");
        System.out.println("   F. Front");
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter any text: ");
        String input = sc.nextLine();

        // Convert string to character array
        char[] arr = new char[input.length() + 20]; // extra space for push operations
        int[] sizeHolder = {input.length()};
        for (int i = 0; i < input.length(); i++) {
            arr[i] = input.charAt(i);
        }

        String choice;

        displayMenu(); // show menu once at the start

        do {
            System.out.print("Enter a choice from the menu [press X to exit program]: ");
            choice = sc.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    displayString(arr, sizeHolder[0]);
                    break;
                case "B":
                    arr = pop(arr, sizeHolder);
                    break;
                case "C":
                    top(arr, sizeHolder[0]);
                    break;
                case "D":
                    System.out.print("Enter a character: ");
                    String charInput = sc.nextLine();
                    if (charInput.length() > 0) {
                        arr = push(arr, sizeHolder, charInput.charAt(0));
                    } else {
                        System.out.println("No character entered.");
                    }
                    break;
                case "E":
                    arr = dequeue(arr, sizeHolder);
                    break;
                case "F":
                    front(arr, sizeHolder[0]);
                    break;
                case "X":
                    System.out.println("End");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (!choice.equals("X"));

        sc.close();
    }
}