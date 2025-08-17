package gripsystem.grip.service;

import gripsystem.grip.model.Customer;
import gripsystem.grip.model.Expense;
import gripsystem.grip.model.Inventory;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataService {
    private static final String CUSTOMERS_FILE = "customers.csv";
    private static final String EXPENSES_FILE = "expenses.csv";
    private static final String INVENTORY_FILE = "inventory.csv";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Get the absolute path for CSV files
    private static String getFilePath(String filename) {
        // Get the current working directory and create the file path
        String workingDir = System.getProperty("user.dir");
        return workingDir + File.separator + filename;
    }

    // Customer operations
    public static List<Customer> loadCustomers() {
        List<Customer> customers = new ArrayList<>();
        String filePath = getFilePath(CUSTOMERS_FILE);
        System.out.println("Loading customers from: " + filePath);
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                Customer customer = Customer.fromString(line);
                if (customer != null) {
                    customers.add(customer);
                    count++;
                }
            }
            System.out.println("Successfully loaded " + count + " customers");
        } catch (IOException e) {
            System.out.println("No existing customers file found. Starting fresh.");
        }
        return customers;
    }

    public static void saveCustomers(List<Customer> customers) {
        String filePath = getFilePath(CUSTOMERS_FILE);
        System.out.println("Saving customers to: " + filePath);
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Customer customer : customers) {
                writer.println(customer.toString());
            }
            System.out.println("Successfully saved " + customers.size() + " customers");
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Expense operations
    public static List<Expense> loadExpenses() {
        List<Expense> expenses = new ArrayList<>();
        String filePath = getFilePath(EXPENSES_FILE);
        System.out.println("Loading expenses from: " + filePath);
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                Expense expense = Expense.fromString(line);
                if (expense != null) {
                    expenses.add(expense);
                    count++;
                }
            }
            System.out.println("Successfully loaded " + count + " expenses");
        } catch (IOException e) {
            System.out.println("No existing expenses file found. Starting fresh.");
        }
        return expenses;
    }

    public static void saveExpenses(List<Expense> expenses) {
        String filePath = getFilePath(EXPENSES_FILE);
        System.out.println("Saving expenses to: " + filePath);
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Expense expense : expenses) {
                writer.println(expense.toString());
            }
            System.out.println("Successfully saved " + expenses.size() + " expenses");
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Inventory operations
    public static List<Inventory> loadInventory() {
        List<Inventory> inventory = new ArrayList<>();
        String filePath = getFilePath(INVENTORY_FILE);
        System.out.println("Loading inventory from: " + filePath);
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                Inventory item = Inventory.fromString(line);
                if (item != null) {
                    inventory.add(item);
                    count++;
                }
            }
            System.out.println("Successfully loaded " + count + " inventory items");
        } catch (IOException e) {
            System.out.println("No existing inventory file found. Starting fresh.");
            e.printStackTrace();
        }
        return inventory;
    }

    public static void saveInventory(List<Inventory> inventory) {
        String filePath = getFilePath(INVENTORY_FILE);
        System.out.println("Saving inventory to: " + filePath);
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Inventory item : inventory) {
                writer.println(item.toString());
            }
            System.out.println("Successfully saved " + inventory.size() + " inventory items");
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Initialize default inventory
    public static void initializeDefaultInventory() {
        System.out.println("Initializing default inventory...");
        List<Inventory> inventory = new ArrayList<>();
        
        // Joggers
        String[] joggerColors = {"Black", "Ash", "White", "Pink"};
        String[] sizes = {"Small", "Medium", "Large"};
        
        for (String color : joggerColors) {
            for (String size : sizes) {
                inventory.add(new Inventory("Jogger", color, size, 50, 3628.0));
            }
        }
        
        // Shorts
        String[] shortColors = {"Black", "Off White"};
        for (String color : shortColors) {
            for (String size : sizes) {
                inventory.add(new Inventory("Short", color, size, 50, 2750.0));
            }
        }
        
        saveInventory(inventory);
        System.out.println("Default inventory initialized with " + inventory.size() + " items");
    }

    // Check if inventory file exists and has content
    public static boolean shouldInitializeDefaultInventory() {
        String filePath = getFilePath(INVENTORY_FILE);
        File file = new File(filePath);
        
        if (!file.exists()) {
            System.out.println("Inventory file does not exist, will initialize default inventory");
            return true;
        }
        
        if (file.length() == 0) {
            System.out.println("Inventory file is empty, will initialize default inventory");
            return true;
        }
        
        // Try to load existing inventory to see if it's valid
        List<Inventory> existingInventory = loadInventory();
        if (existingInventory.isEmpty()) {
            System.out.println("Existing inventory file is invalid or empty, will initialize default inventory");
            return true;
        }
        
        System.out.println("Valid inventory file found with " + existingInventory.size() + " items, no need to initialize");
        return false;
    }

    public static String getCurrentDate() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    // Test method to verify file operations
    public static void testFileOperations() {
        System.out.println("=== Testing File Operations ===");
        System.out.println("Current working directory: " + System.getProperty("user.dir"));
        System.out.println("Inventory file path: " + getFilePath(INVENTORY_FILE));
        
        File inventoryFile = new File(getFilePath(INVENTORY_FILE));
        System.out.println("Inventory file exists: " + inventoryFile.exists());
        if (inventoryFile.exists()) {
            System.out.println("Inventory file size: " + inventoryFile.length() + " bytes");
            System.out.println("Inventory file absolute path: " + inventoryFile.getAbsolutePath());
        }
        
        // Test loading inventory
        List<Inventory> testInventory = loadInventory();
        System.out.println("Test load result: " + testInventory.size() + " items");
        
        System.out.println("=== End Test ===");
    }
} 