package realworld.java;

import java.util.Scanner;

public class InstituteManagementSystem {

    public static void main(String[] args) {

        Institute institute = new Institute();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Institute Management ---");
            System.out.println("1. Admission");
            System.out.println("2. Discontinue");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    institute.admission();
                    break;
                case 2:
                    institute.discontinue();
                    break;
                case 3:
                    System.out.println("Thank you!");
                    return;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }
}
