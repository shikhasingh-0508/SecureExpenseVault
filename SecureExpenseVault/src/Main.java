import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== INITIALIZING SECURE EXPENSE VAULT ===");
        
        // Security Check
        if (!Security.authenticate()) {
            System.out.println("Access Denied! Intruder alert.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();
        System.out.println("\nAuthentication Successful. Welcome.");

        // Main Application Loop
       // Main Application Loop
        while (true) {
            System.out.println("\n1. Add Expense | 2. View All | 3. Category Summary | 4. Delete Expense | 5. Lock & Exit");
            System.out.print("Command: ");
            
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); 
                continue;
            }
            
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            if (choice == 1) {
                System.out.print("Date (DD-MM): "); String date = sc.nextLine();
                System.out.print("Category (e.g., Food, Travel): "); String cat = sc.nextLine();
                System.out.print("Amount: "); 
                while (!sc.hasNextDouble()) {
                    System.out.print("Please enter a valid amount: ");
                    sc.next();
                }
                double amt = sc.nextDouble();
                sc.nextLine();
                System.out.print("Brief Description: "); String desc = sc.nextLine();
                manager.addExpense(date, cat, amt, desc);
                
            } else if (choice == 2) {
                manager.viewAll();
                
            } else if (choice == 3) {
                manager.showCategoryTotals();
                
            } else if (choice == 4) {
                // --- NEW DELETE LOGIC ---
                manager.viewAll(); // Show the list first so they know what to delete
                System.out.print("\nEnter the ID number to delete (or 0 to cancel): ");
                if (sc.hasNextInt()) {
                    int deleteId = sc.nextInt();
                    sc.nextLine(); // consume newline
                    if (deleteId != 0) {
                        manager.deleteExpense(deleteId);
                    } else {
                        System.out.println("Deletion cancelled.");
                    }
                } else {
                    System.out.println("Invalid input.");
                    sc.next(); // clear bad input
                }
                
            } else if (choice == 5) {
                System.out.println("Locking vault... Goodbye!");
                break;
                
            } else {
                System.out.println("Invalid command. Choose between 1 and 5.");
            }
        }
        sc.close();
    }
}