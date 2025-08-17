package gripsystem.grip.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import gripsystem.grip.model.Customer;
import gripsystem.grip.model.Expense;
import gripsystem.grip.model.Inventory;
import gripsystem.grip.service.DataService;

import java.util.List;

public class MainInterfaceController {
    
    // Customer Order Tab
    @FXML private TextField customerNameField;
    @FXML private TextField contactNumberField;
    @FXML private ComboBox<String> productTypeCombo;
    @FXML private ComboBox<String> colorCombo;
    @FXML private ComboBox<String> sizeCombo;
    @FXML private Spinner<Integer> quantitySpinner;
    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, String> nameCol;
    @FXML private TableColumn<Customer, String> contactCol;
    @FXML private TableColumn<Customer, String> productCol;
    @FXML private TableColumn<Customer, String> colorCol;
    @FXML private TableColumn<Customer, String> sizeCol;
    @FXML private TableColumn<Customer, Integer> quantityCol;
    @FXML private TableColumn<Customer, Double> priceCol;
    @FXML private TableColumn<Customer, String> dateCol;

    // Expense Tab
    @FXML private TextField expenseDescriptionField;
    @FXML private TextField expenseAmountField;
    @FXML private TableView<Expense> expenseTable;
    @FXML private TableColumn<Expense, String> descriptionCol;
    @FXML private TableColumn<Expense, Double> amountCol;
    @FXML private TableColumn<Expense, String> expenseDateCol;

    // Inventory Tab
    @FXML private ComboBox<String> invProductTypeCombo;
    @FXML private ComboBox<String> invColorCombo;
    @FXML private ComboBox<String> invSizeCombo;
    @FXML private Spinner<Integer> invQuantitySpinner;
    @FXML private TextField invPriceField;
    @FXML private TableView<Inventory> inventoryTable;
    @FXML private TableColumn<Inventory, String> invProductCol;
    @FXML private TableColumn<Inventory, String> invColorCol;
    @FXML private TableColumn<Inventory, String> invSizeCol;
    @FXML private TableColumn<Inventory, Integer> invQuantityCol;
    @FXML private TableColumn<Inventory, Double> invPriceCol;

    // Profit Tab
    @FXML private Label totalSalesLabel;
    @FXML private Label totalExpensesLabel;
    @FXML private Label profitLabel;

    private ObservableList<Customer> customers = FXCollections.observableArrayList();
    private ObservableList<Expense> expenses = FXCollections.observableArrayList();
    private ObservableList<Inventory> inventory = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupComboBoxes();
        setupSpinners();
        setupTables();
        loadData();
        setupEventHandlers();
        
        // Add shutdown hook to save data
        Runtime.getRuntime().addShutdownHook(new Thread(this::saveAllData));
    }

    private void saveAllData() {
        System.out.println("Saving all data before shutdown...");
        DataService.saveCustomers(customers);
        DataService.saveExpenses(expenses);
        DataService.saveInventory(inventory);
        System.out.println("All data saved successfully");
    }

    private void saveDataOnChange() {
        System.out.println("Saving data due to changes...");
        DataService.saveCustomers(customers);
        DataService.saveExpenses(expenses);
        DataService.saveInventory(inventory);
    }

    private void setupComboBoxes() {
        // Customer order combos
        productTypeCombo.setItems(FXCollections.observableArrayList("Jogger", "Short"));
        colorCombo.setItems(FXCollections.observableArrayList("Black", "Ash", "White", "Pink"));
        sizeCombo.setItems(FXCollections.observableArrayList("Small", "Medium", "Large"));
        
        // Inventory combos
        invProductTypeCombo.setItems(FXCollections.observableArrayList("Jogger", "Short"));
        invColorCombo.setItems(FXCollections.observableArrayList("Black", "Ash", "White", "Pink"));
        invSizeCombo.setItems(FXCollections.observableArrayList("Small", "Medium", "Large"));
        
        // Set default values
        productTypeCombo.setValue("Jogger");
        colorCombo.setValue("Black");
        sizeCombo.setValue("Small");
        invProductTypeCombo.setValue("Jogger");
        invColorCombo.setValue("Black");
        invSizeCombo.setValue("Small");
    }

    private void setupSpinners() {
        // Setup quantity spinner for customer orders
        SpinnerValueFactory<Integer> quantityFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        quantitySpinner.setValueFactory(quantityFactory);
        quantitySpinner.setEditable(true);
        
        // Setup quantity spinner for inventory
        SpinnerValueFactory<Integer> invQuantityFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0);
        invQuantitySpinner.setValueFactory(invQuantityFactory);
        invQuantitySpinner.setEditable(true);
    }

    private void setupTables() {
        // Customer table
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        productCol.setCellValueFactory(new PropertyValueFactory<>("productType"));
        colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        customerTable.setItems(customers);

        // Expense table
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        expenseDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        expenseTable.setItems(expenses);

        // Inventory table
        invProductCol.setCellValueFactory(new PropertyValueFactory<>("productType"));
        invColorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
        invSizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        invQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        invPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        inventoryTable.setItems(inventory);
    }

    private void loadData() {
        System.out.println("MainInterfaceController: Starting to load data...");
        
        System.out.println("Loading customers...");
        customers.setAll(DataService.loadCustomers());
        System.out.println("Loaded " + customers.size() + " customers");
        
        System.out.println("Loading expenses...");
        expenses.setAll(DataService.loadExpenses());
        System.out.println("Loaded " + expenses.size() + " expenses");
        
        System.out.println("Loading inventory...");
        inventory.setAll(DataService.loadInventory());
        System.out.println("Loaded " + inventory.size() + " inventory items");
        
        updateProfitCalculation();
        System.out.println("Data loading completed");
    }

    private void setupEventHandlers() {
        // Update color combo based on product type
        productTypeCombo.setOnAction(e -> updateColorCombo());
        invProductTypeCombo.setOnAction(e -> updateInvColorCombo());
    }

    private void updateColorCombo() {
        String productType = productTypeCombo.getValue();
        if ("Jogger".equals(productType)) {
            colorCombo.setItems(FXCollections.observableArrayList("Black", "Ash", "White", "Pink"));
        } else if ("Short".equals(productType)) {
            colorCombo.setItems(FXCollections.observableArrayList("Black", "Off White"));
        }
        colorCombo.setValue(colorCombo.getItems().get(0));
    }

    private void updateInvColorCombo() {
        String productType = invProductTypeCombo.getValue();
        if ("Jogger".equals(productType)) {
            invColorCombo.setItems(FXCollections.observableArrayList("Black", "Ash", "White", "Pink"));
        } else if ("Short".equals(productType)) {
            invColorCombo.setItems(FXCollections.observableArrayList("Black", "Off White"));
        }
        invColorCombo.setValue(invColorCombo.getItems().get(0));
    }

    // Customer Order Methods
    @FXML
    private void addCustomerOrder() {
        try {
            String name = customerNameField.getText();
            String contact = contactNumberField.getText();
            String productType = productTypeCombo.getValue();
            String color = colorCombo.getValue();
            String size = sizeCombo.getValue();
            int quantity = quantitySpinner.getValue();

            if (name.isEmpty() || contact.isEmpty()) {
                showAlert("Error", "Please fill in all fields.");
                return;
            }

            // Check inventory availability
            if (!updateInventoryForOrder(productType, color, size, quantity)) {
                showAlert("Error", "Insufficient inventory for this order.");
                return;
            }

            // Calculate total price
            double price = getProductPrice(productType, color, size) * quantity;
            
            Customer customer = new Customer(name, contact, productType, color, size, quantity, price, DataService.getCurrentDate());
            customers.add(customer);
            
            clearCustomerFields();
            updateProfitCalculation();
            saveDataOnChange();
            showAlert("Success", "Order added successfully!");
            
        } catch (Exception e) {
            showAlert("Error", "Error adding order: " + e.getMessage());
        }
    }

    @FXML
    private void deleteCustomerOrder() {
        Customer selected = customerTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // Restore inventory
            restoreInventoryForOrder(selected.getProductType(), selected.getColor(), selected.getSize(), selected.getQuantity());
            
            customers.remove(selected);
            updateProfitCalculation();
            saveDataOnChange();
            showAlert("Success", "Order deleted successfully!");
        } else {
            showAlert("Error", "Please select an order to delete.");
        }
    }

    @FXML
    private void updateCustomerOrder() {
        Customer selected = customerTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // Restore old inventory
            restoreInventoryForOrder(selected.getProductType(), selected.getColor(), selected.getSize(), selected.getQuantity());
            
            // Update with new values
            String name = customerNameField.getText();
            String contact = contactNumberField.getText();
            String productType = productTypeCombo.getValue();
            String color = colorCombo.getValue();
            String size = sizeCombo.getValue();
            int quantity = quantitySpinner.getValue();

            if (name.isEmpty() || contact.isEmpty()) {
                showAlert("Error", "Please fill in all fields.");
                return;
            }

            // Check new inventory availability
            if (!updateInventoryForOrder(productType, color, size, quantity)) {
                showAlert("Error", "Insufficient inventory for this order.");
                return;
            }

            selected.setName(name);
            selected.setContactNumber(contact);
            selected.setProductType(productType);
            selected.setColor(color);
            selected.setSize(size);
            selected.setQuantity(quantity);
            selected.setTotalPrice(getProductPrice(productType, color, size) * quantity);

            customerTable.refresh();
            clearCustomerFields();
            updateProfitCalculation();
            saveDataOnChange();
            showAlert("Success", "Order updated successfully!");
        } else {
            showAlert("Error", "Please select an order to update.");
        }
    }

    // Expense Methods
    @FXML
    private void addExpense() {
        try {
            String description = expenseDescriptionField.getText();
            double amount = Double.parseDouble(expenseAmountField.getText());

            if (description.isEmpty()) {
                showAlert("Error", "Please enter expense description.");
                return;
            }

            Expense expense = new Expense(description, amount, DataService.getCurrentDate());
            expenses.add(expense);
            
            expenseDescriptionField.clear();
            expenseAmountField.clear();
            updateProfitCalculation();
            saveDataOnChange();
            showAlert("Success", "Expense added successfully!");
            
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid amount.");
        } catch (Exception e) {
            showAlert("Error", "Error adding expense: " + e.getMessage());
        }
    }

    @FXML
    private void deleteExpense() {
        Expense selected = expenseTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            expenses.remove(selected);
            updateProfitCalculation();
            saveDataOnChange();
            showAlert("Success", "Expense deleted successfully!");
        } else {
            showAlert("Error", "Please select an expense to delete.");
        }
    }

    @FXML
    private void updateExpense() {
        Expense selected = expenseTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String description = expenseDescriptionField.getText();
            double amount = Double.parseDouble(expenseAmountField.getText());

            if (description.isEmpty()) {
                showAlert("Error", "Please enter expense description.");
                return;
            }

            selected.setDescription(description);
            selected.setAmount(amount);

            expenseTable.refresh();
            expenseDescriptionField.clear();
            expenseAmountField.clear();
            updateProfitCalculation();
            saveDataOnChange();
            showAlert("Success", "Expense updated successfully!");
        } else {
            showAlert("Error", "Please select an expense to update.");
        }
    }

    // Inventory Methods
    @FXML
    private void addInventory() {
        try {
            String productType = invProductTypeCombo.getValue();
            String color = invColorCombo.getValue();
            String size = invSizeCombo.getValue();
            int quantity = invQuantitySpinner.getValue();
            double price = Double.parseDouble(invPriceField.getText());

            // Check if item already exists
            Inventory existingItem = findInventoryItem(productType, color, size);
            if (existingItem != null) {
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
                existingItem.setPrice(price);
            } else {
                Inventory newItem = new Inventory(productType, color, size, quantity, price);
                inventory.add(newItem);
            }

            invQuantitySpinner.getValueFactory().setValue(0);
            invPriceField.clear();
            saveDataOnChange();
            showAlert("Success", "Inventory updated successfully!");
            
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid price.");
        } catch (Exception e) {
            showAlert("Error", "Error updating inventory: " + e.getMessage());
        }
    }

    @FXML
    private void deleteInventory() {
        Inventory selected = inventoryTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            inventory.remove(selected);
            saveDataOnChange();
            showAlert("Success", "Inventory item deleted successfully!");
        } else {
            showAlert("Error", "Please select an inventory item to delete.");
        }
    }

    @FXML
    private void updateInventory() {
        Inventory selected = inventoryTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            int quantity = invQuantitySpinner.getValue();
            double price = Double.parseDouble(invPriceField.getText());

            selected.setQuantity(quantity);
            selected.setPrice(price);

            inventoryTable.refresh();
            invQuantitySpinner.getValueFactory().setValue(0);
            invPriceField.clear();
            saveDataOnChange();
            showAlert("Success", "Inventory updated successfully!");
        } else {
            showAlert("Error", "Please select an inventory item to update.");
        }
    }

    // Helper Methods
    private boolean updateInventoryForOrder(String productType, String color, String size, int quantity) {
        Inventory item = findInventoryItem(productType, color, size);
        if (item != null && item.getQuantity() >= quantity) {
            item.setQuantity(item.getQuantity() - quantity);
            return true;
        }
        return false;
    }

    private void restoreInventoryForOrder(String productType, String color, String size, int quantity) {
        Inventory item = findInventoryItem(productType, color, size);
        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
        }
    }

    private Inventory findInventoryItem(String productType, String color, String size) {
        return inventory.stream()
                .filter(item -> item.getProductType().equals(productType) && 
                               item.getColor().equals(color) && 
                               item.getSize().equals(size))
                .findFirst()
                .orElse(null);
    }

    private double getProductPrice(String productType, String color, String size) {
        Inventory item = findInventoryItem(productType, color, size);
        return item != null ? item.getPrice() : 0.0;
    }

    private void updateProfitCalculation() {
        double totalSales = customers.stream().mapToDouble(Customer::getTotalPrice).sum();
        double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
        double profit = totalSales - totalExpenses;
        totalSalesLabel.setText(String.format("Rs. %.2f", totalSales));
        totalExpensesLabel.setText(String.format("Rs. %.2f", totalExpenses));
        profitLabel.setText(String.format("Rs. %.2f", profit));
    }

    private void clearCustomerFields() {
        customerNameField.clear();
        contactNumberField.clear();
        productTypeCombo.setValue("Jogger");
        colorCombo.setValue("Black");
        sizeCombo.setValue("Small");
        quantitySpinner.getValueFactory().setValue(1);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
} 