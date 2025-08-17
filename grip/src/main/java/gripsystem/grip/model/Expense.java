package gripsystem.grip.model;

public class Expense {
    private String description;
    private double amount;
    private String date;

    public Expense(String description, double amount, String date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    // Getters and Setters
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    @Override
    public String toString() {
        return description + "," + amount + "," + date;
    }

    public static Expense fromString(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length == 3) {
            return new Expense(parts[0], Double.parseDouble(parts[1]), parts[2]);
        }
        return null;
    }
} 