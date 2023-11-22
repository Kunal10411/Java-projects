import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {

    private static final String FILE_NAME = "todo_list.txt";
    private static ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasksFromFile();

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Todo List Application");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Complete");
            System.out.println("3. Remove Task");
            System.out.println("4. View Tasks");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addTask(scanner);
                    break;
                case 2:
                    markTaskAsComplete(scanner);
                    break;
                case 3:
                    removeTask(scanner);
                    break;
                case 4:
                    viewTasks();
                    break;
                case 0:
                    saveTasksToFile();
                    System.out.println("Exiting Todo List Application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void addTask(Scanner scanner) {
        System.out.print("Enter the task: ");
        String task = scanner.nextLine();
        tasks.add(task);
        System.out.println("Task added: " + task);
    }

    private static void markTaskAsComplete(Scanner scanner) {
        viewTasks();
        System.out.print("Enter the task number to mark as complete: ");
        int taskNumber = scanner.nextInt();
        if (isValidTaskNumber(taskNumber)) {
            String task = tasks.get(taskNumber - 1);
            System.out.println("Marking task as complete: " + task);
            // You can add additional logic here, e.g., move the task to a completed tasks list.
        } else {
            System.out.println("Invalid task number. Please try again.");
        }
    }

    private static void removeTask(Scanner scanner) {
        viewTasks();
        System.out.print("Enter the task number to remove: ");
        int taskNumber = scanner.nextInt();
        if (isValidTaskNumber(taskNumber)) {
            String removedTask = tasks.remove(taskNumber - 1);
            System.out.println("Removed task: " + removedTask);
        } else {
            System.out.println("Invalid task number. Please try again.");
        }
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list.");
        } else {
            System.out.println("Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 1 && taskNumber <= tasks.size();
    }

    private static void loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
            System.out.println("Tasks loaded from file.");
        } catch (IOException e) {
            // File may not exist yet. That's fine.
            System.out.println("No existing tasks file found. Starting with an empty list.");
        }
    }

    private static void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
            System.out.println("Tasks saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
            e.printStackTrace();
        }
    }
}
