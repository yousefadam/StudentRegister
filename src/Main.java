import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMainMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addStudentData(scanner);
                    break;
                case 2:
                    selectBatchFile();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice == 1 || choice == 2);
    }

    private static void displayMainMenu() {
        System.out.println("Main Menu");
        System.out.println("1- Add student data");
        System.out.println("2- Add batch students data");
    }

    private static void addStudentData(Scanner scanner) {
        System.out.println("Add Student Data");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter student courses (comma-separated): ");
        String courses = scanner.nextLine();

        // Process the entered data
        System.out.println("Student Name: " + name);
        System.out.println("Student ID: " + id);
        System.out.println("Student Courses: " + courses);
    }

    private static void selectBatchFile() {
        String folderPath = "/app/data/batch";
        List<String> eligibleFiles = new ArrayList<>();

        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().contains("verified")) {
                    eligibleFiles.add(file.getName());
                }
            }
        }
    }
}
