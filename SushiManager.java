import java.util.*;
import java.io.*;

// This class manages a conveyor belt sushi restaurant. Users can add, insert, eat,
// and view plates on the conveyor, print the customer's receipt with its total cost,
// and pay off plates.

public class SushiManager {
    public static final String RED = "red";
    public static final String GREEN = "green";
    public static final String GOLD = "gold";
    public static final double RED_PRICE = 1.5;
    public static final double GREEN_PRICE = 3.0;
    public static final double GOLD_PRICE = 5.25;

    public static void main(String[] args) {
        System.out.println("Welcome to the Cornbear's Sushi Restaurant Manager!");
        runMenu();
    }

    private static void runMenu() {
        boolean running = true;
        Scanner console = new Scanner(System.in);
        Queue<String> conveyorBelt = new LinkedList<>();
        Stack<String> platesEaten = new Stack<>();
        while (running) {
            System.out.println("Menu: (A)dd sushi, (I)nsert sushi, (E)at sushi, (V)iew conveyor, " +
                "Print customer (R)eceipt, (P)ay for plates, (Q)uit? ");
            System.out.print("Enter your choice: ");
            String choice = console.nextLine();
            if (choice.equalsIgnoreCase("a")) {
                addingSushi(conveyorBelt, console);
            } else if (choice.equalsIgnoreCase("i")) {
                insertingSushi(conveyorBelt, console);
            } else if (choice.equalsIgnoreCase("e")) {
                eatingSushi(conveyorBelt, platesEaten); 
            } else if (choice.equalsIgnoreCase("v")) {
                viewConveyorBelt(conveyorBelt);
            } else if (choice.equalsIgnoreCase("r")) {
                printReceipt(platesEaten);
            } else if (choice.equalsIgnoreCase("p")) {
                payForPlates(platesEaten, console);
            } else if (choice.equalsIgnoreCase("q")) {
                running = false;
            } else {
                System.out.println("Invalid choice: " + choice);
                System.out.println("Please try again");
            }
            System.out.println();
        }
    }

    private static void payForPlates(Stack<String> platesEaten, Scanner console) {
        System.out.print("Is customer paying from the (T)op or (B)ottom of receipt? ");
        String startPosition = console.nextLine();
        System.out.print("Enter number of plates to pay: ");
        int numPlates = Integer.parseInt(console.nextLine());
        if (numPlates < 0 || numPlates > platesEaten.size()) {
            throw new IllegalArgumentException();
        }
        if (numPlates != 0) {
            if (startPosition.equalsIgnoreCase("t")) {
                for (int i = 0; i < numPlates; i++) {
                    platesEaten.pop();
                }
            } else {
                Stack<String> aux = new Stack<>();
                while (!platesEaten.isEmpty()) {
                    aux.push(platesEaten.pop());
                }
                for (int i = 0; i < numPlates; i++) {
                    aux.pop();
                }
                while (!aux.isEmpty()) {
                    platesEaten.push(aux.pop());
                }
            }
        }
    }

    private static void printReceipt(Stack<String> platesEaten) {
        if (platesEaten.isEmpty()) {
            System.out.println("All plates are paid for!");
        } else {
            Stack<String> aux = new Stack<>();
            double total = 0;
            System.out.println("Current receipt:");
            while(!platesEaten.isEmpty()) {
                String plate = platesEaten.peek();
                Scanner plateScan = new Scanner(plate);
                String color = plateScan.next();
                if (color.equalsIgnoreCase(RED)) {
                    total += RED_PRICE;
                } else if (color.equalsIgnoreCase(GREEN)) {
                    total += GREEN_PRICE;
                } else {
                    total += GOLD_PRICE;
                }
                System.out.println("    " + plate);
                aux.push(platesEaten.pop());
            }
            while (!aux.isEmpty()) {
                platesEaten.push(aux.pop());
            }
            System.out.println("Total cost: $" + total);
        }
    }

    private static void eatingSushi(Queue<String> conveyorBelt, Stack<String> platesEaten) {
        if (conveyorBelt.isEmpty()) {
            throw new IllegalStateException();
        }
        String sushi = conveyorBelt.remove();
        platesEaten.push(sushi);
        System.out.println("Grabbing plate: " + sushi);
    }

    private static void viewConveyorBelt(Queue<String> conveyorBelt) {
        if (conveyorBelt.isEmpty()) {
            System.out.println("No sushi available!");
        } else {
            int length = conveyorBelt.size();
            for (int i = 0; i < length; i++) {
                String sushi = conveyorBelt.remove();
                System.out.println("    " + sushi);
                conveyorBelt.add(sushi);
            }
        }
    }

    private static void insertingSushi(Queue<String> conveyorBelt, Scanner console) {
        System.out.print("Enter sushi plate to insert: ");
        String newSushi = console.nextLine();
        System.out.print("Enter color to insert after: ");
        String color = console.nextLine();
        boolean found = false;
        int length = conveyorBelt.size();
        for (int i = 0; i < length; i++) {
            String sushi = conveyorBelt.peek();
            Scanner sushiScan = new Scanner(sushi);
            String sushiColor = sushiScan.next();
            conveyorBelt.remove();
            conveyorBelt.add(sushi);
            if (sushiColor.equalsIgnoreCase(color) && !found) {
                conveyorBelt.add(newSushi);
                found = true;
            }
        }
        if (!found){
            conveyorBelt.add(newSushi);
        }
        System.out.println("Successfully added " + newSushi + " after " + color + " plate");
    }

    private static void addingSushi(Queue<String> conveyorBelt, Scanner console) {
        System.out.print("Enter sushi plate: ");
        String sushi = console.nextLine();
        conveyorBelt.add(sushi);
        System.out.println("Successfully added " + sushi);
    }
}