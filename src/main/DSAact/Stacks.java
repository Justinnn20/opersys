package main.DSAact;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Stacks {
    private int[] _stackArray;
    private int _top;
    private int _maxSize;

    public Stacks(int size) {
        _maxSize = size;
        _stackArray = new int[_maxSize];
        _top = -1;
    }

    public void Push(int item) {
        if (IsFull()) {
            System.out.println("Stack Overflow! Cannot push " + item);
            return;
        }
        _stackArray[++_top] = item;
        System.out.println("Pushed: " + item);
    }

    public int Pop() {
        if (IsEmpty()) {
            System.out.println("Stack Underflow! Stack is empty.");
            return -1;
        }
        int item = _stackArray[_top--];
        System.out.println("Popped: " + item);
        return item;
    }

    public int Peek() {
        if (IsEmpty()) {
            System.out.println("Stack is empty. Nothing to peek.");
            return -1;
        }
        return _stackArray[_top];
    }

    public boolean IsEmpty() {
        return _top == -1;
    }

    public boolean IsFull() {
        return _top == _maxSize - 1;
    }

    public void Display() {
        if (IsEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.println("Stack (top to bottom):");
        for (int i = _top; i >= 0; i--) {
            System.out.println("| " + _stackArray[i] + " |");
        }
        System.out.println("-----");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = readInt(scanner, "Enter stack size: ");
        while (size <= 0) {
            System.out.println("Size must be greater than 0.");
            size = readInt(scanner, "Enter stack size: ");
        }

        Stacks stack = new Stacks(size);
        boolean running = true;

        while (running) {
            System.out.println("\n===== STACK MENU =====");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Is Empty?");
            System.out.println("5. Is Full?");
            System.out.println("6. Display");
            System.out.println("0. Exit");

            int choice = readInt(scanner, "Choose an option: ");

            switch (choice) {
                case 1:
                    int item = readInt(scanner, "Enter a number to push: ");
                    stack.Push(item);
                    break;
                case 2:
                    stack.Pop();
                    break;
                case 3:
                    if (!stack.IsEmpty()) {
                        System.out.println("Top element: " + stack.Peek());
                    } else {
                        System.out.println("Stack is empty. Nothing to peek.");
                    }
                    break;
                case 4:
                    System.out.println("Is empty? " + stack.IsEmpty());
                    break;
                case 5:
                    System.out.println("Is full? " + stack.IsFull());
                    break;
                case 6:
                    stack.Display();
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }

    // Keeps asking until the user types a valid whole number
    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid whole number.");
                scanner.next(); // discard the bad input
            }
        }
    }
}