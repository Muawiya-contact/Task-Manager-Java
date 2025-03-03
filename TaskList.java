import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private String fileName;

    public TaskList(String fileName) {
        this.fileName = fileName;
    }

    public void addTask(String name, String description, String deadline) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(name + " | " + description + " | " + deadline);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving task: " + e.getMessage());
        }
    }

    public void viewTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean hasTasks = false;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                hasTasks = true;
            }
            if (!hasTasks) {
                System.out.println("No tasks found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading tasks: " + e.getMessage());
        }
    }

    public void deleteTask(String taskName) {
        List<String> tasks = new ArrayList<>();
        boolean taskFound = false;

        // Read all tasks except the one to delete
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(taskName + " |")) {
                    tasks.add(line);
                } else {
                    taskFound = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading tasks: " + e.getMessage());
        }

        // Write updated list back to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating tasks: " + e.getMessage());
        }

        if (taskFound) {
            System.out.println("Task deleted successfully!");
        } else {
            System.out.println("Task not found.");
        }
    }
}
