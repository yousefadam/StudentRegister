import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
                    addBatchStudentsData(scanner);
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

        // Format the student data
        String studentData = name + ";" + id + ";" + courses;


        // Write student data to a TXT file (Database)
        try {
            FileWriter fileWriter = new FileWriter( "/app/data/Main-DB.txt", true);
            fileWriter.write(studentData + "\n");
            fileWriter.close();
            System.out.println("Student data added to file: Main-DB.txt");
        } catch (IOException e) {
            System.out.println("Error occurred while writing to the file: " + e.getMessage());
        }
    }

    private static void addBatchStudentsData(Scanner scanner) {
        String folderPath = "/app/data/batch";

        // Get a list of eligible batch files
        List<String> eligibleFiles = getEligibleBatchFiles(folderPath);

        // Display eligible batch files to the user
        System.out.println("Eligible Batch Files:");
        for (int i = 0; i < eligibleFiles.size(); i++) {
            System.out.println(eligibleFiles.get(i));
        }

        // Prompt the user to select a file
        System.out.print("Enter the Name of the batch file to use: ");
        String fileName = scanner.nextLine();

        // Get the selected file path
        String selectedFilePath = folderPath + '/' + fileName;

        try {
            Scanner fileScanner = new Scanner(new File(selectedFilePath));
            fileScanner.useDelimiter(",");

            FileWriter fileWriter = new FileWriter("/app/data/Main-DB.txt", true);

            while(fileScanner.hasNext()){
                String line = fileScanner.nextLine();

                fileWriter.write(line + "\n");

            }

            fileWriter.close();
            fileScanner.close();

            System.out.println("Student data added successfully");
        } catch (IOException e) {
            System.out.println("Error occurred while reading the files: " + e.getMessage());
        }
        /*
        // Read the content of the selected file
        try {
            List<String> fileContent = Files.readAllLines(Paths.get(selectedFilePath));

            // Write each line from the selected file to the target file
            FileWriter fileWriter = new FileWriter("/app/data/Main-DB.txt", true);
            for (String line : fileContent) {
                fileWriter.write(line + "\n");
            }
            fileWriter.close();

            System.out.println("Student data added successfully");
        } catch (IOException e) {
            System.out.println("Error occurred while reading or writing the files: " + e.getMessage());
        }

        */
    }

    private static List<String> getEligibleBatchFiles(String folderPath) {
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

        return eligibleFiles;
    }
}
