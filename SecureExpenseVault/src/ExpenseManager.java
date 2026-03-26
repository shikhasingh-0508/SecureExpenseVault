import java.io.*;
import java.util.*;

public class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();
    // FIX 1: Simplified path to avoid VS Code folder confusion
    private static final String FILE_PATH = "vault.txt"; 

    public ExpenseManager() {
        loadVault();
    }

    public void addExpense(String date, String cat, double amt, String desc) {
        expenses.add(new Expense(date, cat, amt, desc));
        saveVault(); 
        System.out.println("Expense locked securely in the vault.");
    }

    public void viewAll() {
        if (expenses.isEmpty()) {
            System.out.println("The vault is empty.");
            return;
        }
        System.out.println("\n--- Decrypted Vault Records ---");
        // We use a traditional for-loop here so we can get the index number
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println((i + 1) + ". " + expenses.get(i));
        }
    }

    public void deleteExpense(int id) {
        // Check if the ID is valid (greater than 0 and not bigger than our list)
        if (id > 0 && id <= expenses.size()) {
            // Arrays are 0-indexed, so ID 1 is at index 0
            Expense removed = expenses.remove(id - 1); 
            saveVault(); // Save the new list to the file immediately
            System.out.println("Successfully deleted: " + removed.getCategory() + " (Rs." + removed.getAmount() + ")");
        } else {
            System.out.println("Invalid ID. No expense deleted.");
        }
    }

    public void showCategoryTotals() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to summarize.");
            return;
        }
        Map<String, Double> totals = new HashMap<>();
        for (Expense e : expenses) {
            totals.put(e.getCategory(), totals.getOrDefault(e.getCategory(), 0.0) + e.getAmount());
        }
        System.out.println("\n--- Spending by Category ---");
        totals.forEach((k, v) -> System.out.printf("%s: Rs.%.2f%n", k, v));
    }

    // --- BULLETPROOF FILE I/O ---

    private void saveVault() {
        File file = new File(FILE_PATH);
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (Expense e : expenses) {
                String rawCSV = e.toCSV();
                String encryptedData = Security.encryptDecrypt(rawCSV);
                writer.println(encryptedData);
            }
            // FIX 2: Print exactly where the file is being saved
            System.out.println("[SYSTEM DEBUG] Data saved to: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error saving to vault: " + e.getMessage());
        }
    }

    private void loadVault() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("[SYSTEM DEBUG] Starting fresh. No vault found at: " + file.getAbsolutePath());
            return; 
        }

        System.out.println("[SYSTEM DEBUG] Loading previous vault from: " + file.getAbsolutePath());

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String decryptedData = Security.encryptDecrypt(line);
                
                // FIX: Split using our new double-semicolon delimiter
                String[] parts = decryptedData.split(";;"); 
                
                if (parts.length == 4) {
                    expenses.add(new Expense(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading from vault. Data might be corrupted.");
        }
    }
}