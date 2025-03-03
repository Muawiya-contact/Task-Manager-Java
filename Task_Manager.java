import java.io.*;
import java.util.Scanner;

public class Task_Manager {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList("tasks.txt"); // Pass file name to TaskList

        while (true) {
            System.out.println("\n1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Delete Task");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice;
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); // Consume the newline
            } else {
                System.out.println("Invalid input! Please enter a number.");
                sc.next(); // Clear invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Task Name: ");
                    String taskName = sc.nextLine();
                    System.out.print("Enter Task Description: ");
                    String taskDescription = sc.nextLine();
                    System.out.print("Enter Task Deadline: ");
                    String taskDeadline = sc.nextLine();

                    taskList.addTask(taskName, taskDescription, taskDeadline);
                    System.out.println("Task added successfully!");
                    break;

                case 2:
                    System.out.println("\n--- Task List ---");
                    taskList.viewTasks();
                    break;

                case 3:
                    System.out.print("Enter Task Name to delete: ");
                    String taskNameToDelete = sc.nextLine();
                    taskList.deleteTask(taskNameToDelete);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Choice! Please try again.");
            }
        }
    }
}
