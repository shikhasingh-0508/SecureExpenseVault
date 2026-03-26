public class Expense {
    private String date;
    private String category;
    private double amount;
    private String description;

    public Expense(String date, String category, double amount, String description) {
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    public String getCategory() { return category; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return String.format("[%s] %-12s: Rs.%.2f (%s)", date, category, amount, description);
    }

    // Formats the data so it can be cleanly saved to a text file
    public String toCSV() {
        return date + ";;" + category + ";;" + amount + ";;" + description;
    }
}