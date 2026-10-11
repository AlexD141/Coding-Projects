import java.util.*;
import java.io.*;

//This class is a TODO list manager. Through a menu, user can add, complete, load or save from a 
//file, display, and filter incomplete tasks.
public class TodoListManager {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to your TODO List Manager!");
        runMenu();
    }

    //Displays the menu and calls methods based on choice taken by user until they quit. Prints
    //   error message if invalid choice. Prints incomplete tasks after every choice.
    private static void runMenu() throws FileNotFoundException{
        boolean running = true;
        Scanner console = new Scanner(System.in);
        List<String> incompleteTasks = new ArrayList<>();
        List<String> completeTasks = new ArrayList<>();
        while (running) {
            System.out.println("Menu: (A)dd task, (C)omplete task, (L)oad tasks, (S)ave tasks, " + 
                "(D)isplay Complete Tasks, (F)ilter tasks, (Q)uit?");
            System.out.print("Enter your choice: ");
            String choice = console.nextLine();
            if (choice.equalsIgnoreCase("a")) {
                addTask(console, incompleteTasks);
            } else if (choice.equalsIgnoreCase("l")) {
                loadFile(console, incompleteTasks, completeTasks);
            } else if (choice.equalsIgnoreCase("c")) {
                completeTask(console, incompleteTasks, completeTasks);
            } else if (choice.equalsIgnoreCase("s")) {
                saveFile(console, incompleteTasks, completeTasks);
            } else if (choice.equalsIgnoreCase("d")) {
                printTasks("Complete Tasks:", "  You have yet to complete any tasks :(",
                    completeTasks);
            } else if (choice.equalsIgnoreCase("f")) {
                filterTasks(console, incompleteTasks);
            } else if (choice.equalsIgnoreCase("q")) {
                running = false;
            } else {
                System.out.println("Invalid choice: " + choice);
                System.out.println("Please try again");
            }
            System.out.println();
            printTasks("Incomplete Tasks:", "  You have nothing else to do today! Relax!", 
                incompleteTasks);
            System.out.println();
        }
    }

    //Asks user for a word, then prints any incomplete tasks that includes the exact word 
    //   (case-insensitive). If non found, prints corresponding message.
    private static void filterTasks(Scanner console, List<String> incompleteTasks) {
        System.out.print("Enter a word (token) to filter: ");
        String word = console.nextLine();
        boolean foundAtLeastOneTask = false;
        for (int i = 0; i < incompleteTasks.size(); i++) {
            String task = incompleteTasks.get(i);
            Scanner taskScanner = new Scanner(task);
            boolean foundTask = false;
            while (!foundTask && taskScanner.hasNext()) {
                foundTask = taskScanner.next().equalsIgnoreCase(word);
            }
            if (foundTask) {
                if (!foundAtLeastOneTask) {
                    System.out.println("Here's some relevant tasks:");
                    foundAtLeastOneTask = true;
                }
                System.out.println("  " + task);
            }
        }
        if(!foundAtLeastOneTask){
            System.out.println("No incomplete tasks found with word " + word + ".");
        }
    }

    //Asks user for file name and replaces incomplete and complete task list with
    //   corresponding task lists from the file.
    private static void loadFile(Scanner console, List<String> incompleteTasks,  
            List<String> completeTasks) throws FileNotFoundException{
        System.out.print("File name? ");
        String fileName = console.nextLine();
        Scanner file = new Scanner(new File(fileName));
        incompleteTasks.clear();
        completeTasks.clear();
        file.nextLine();
        boolean incomplete = true;
        boolean notTask = false;
        while (file.hasNextLine()){
            String task = file.nextLine();
            if (task.equalsIgnoreCase("Complete:")){
                incomplete = false;
                notTask = true;
            }
            if (incomplete) {
                incompleteTasks.add(task);
            } else if (!notTask) {
                completeTasks.add(task);
            }
            notTask = false;
        }
        file.close();
    }

    //Asks user for file name and writes incomplete and complete task lists to the file with
    //   each group having its own header.
    private static void saveFile(Scanner console, List<String> incompleteTasks, 
            List<String> completeTasks) throws FileNotFoundException{
        System.out.print("File name? ");
        String fileName = console.nextLine();
        PrintStream file = new PrintStream(new File(fileName));
        file.println("Incomplete:");
        for (int i = 0; i < incompleteTasks.size(); i++) {
            file.println(incompleteTasks.get(i));
        }
        file.println("Complete:");
        for (int i = 0; i < completeTasks.size(); i++) {
            file.println(completeTasks.get(i));
        }
        file.close();
    }

    //If there are no incomplete tasks, prints an message. Otherwise, asks user for
    //   the number (1 - ...)  of the task completed. Then removing the task from 
    //   incomplete task list and placing it in the complete task list. 
    private static void completeTask(Scanner console, List<String> incompleteTasks, 
            List<String> completeTasks) {
        if (incompleteTasks.size() != 0) {
            System.out.print("Which task did you complete (1-" + (incompleteTasks.size()) +")? ");
            int taskIndex = Integer.parseInt(console.nextLine()) - 1;            
            completeTasks.add(incompleteTasks.remove(taskIndex));
        } else {
            System.out.println("All done! No tasks left to complete!");
        }
    }

    //Adds task in incomplete list after asking the user for a task and where to 
    //   put it in the list (1 - ...). User can also enter nothing if they want to add 
    //   to end of list.
    private static void addTask(Scanner console, List<String> incompleteTasks) {
        System.out.print("What would you like to add? ");
        String task = console.nextLine();
        String position = "";
        if (incompleteTasks.size() != 0) {
            System.out.print("Where in the list should it be (1-" + (incompleteTasks.size() + 1) + 
                ")? (Enter for end): ");
            position = console.nextLine();
        }
        if (position.equals("")){
            incompleteTasks.add(task);
        } else {
            incompleteTasks.add(Integer.parseInt(position) - 1, task);
        }
    }

    //Prints a header, then each task numbered starting at 1. If there
    //   are no tasks in list then prints a given empty message instead.
    private static void printTasks(String header, String emptyMessage, List<String> tasks) {
        System.out.println(header);
        if (tasks.size() == 0) {
            System.out.println(emptyMessage);
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("  " + (i + 1) + ": " + tasks.get(i));
        }
    }
}