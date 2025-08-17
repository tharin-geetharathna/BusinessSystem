# Grip System - Inventory & Order Management

A comprehensive JavaFX application for managing customer orders, inventory, expenses, and profit tracking for a clothing business specializing in joggers and shorts.

## Features

### 🔐 Secure Login System
- **Username**: Tharin
- **Password**: THARINpro12345
- Modern gradient UI design with secure authentication

### 📦 Customer Order Management
- **Add Orders**: Create new customer orders with product details
- **Update Orders**: Modify existing orders (automatically handles inventory adjustments)
- **Delete Orders**: Remove orders and restore inventory
- **Order Details**: Customer name, contact number, product type, color, size, quantity, total price, and date

### 💰 Expense Tracking
- **Add Expenses**: Record business expenses with descriptions and amounts
- **Update Expenses**: Modify expense details
- **Delete Expenses**: Remove expense records
- **Automatic Date**: All expenses are automatically dated

### 📊 Inventory Management
- **Product Types**: Joggers and Shorts
- **Colors Available**:
  - Joggers: Black, Ash, White, Pink
  - Shorts: Black, Off White
- **Sizes**: Small, Medium, Large
- **Real-time Updates**: Inventory automatically adjusts with orders
- **Price Management**: Set and update product prices
- **Quantity Tracking**: Monitor stock levels

### 💵 Profit Analysis
- **Total Sales**: Sum of all customer orders
- **Total Expenses**: Sum of all recorded expenses
- **Net Profit**: Automatic calculation (Sales - Expenses)
- **Real-time Updates**: Profit updates automatically with every transaction

## System Architecture

### Data Persistence
- **CSV Files**: All data is stored in CSV format for easy backup and portability
- **Automatic Saving**: Data is saved automatically after every operation
- **No Data Loss**: All records are maintained between sessions

### Inventory Logic
- **Automatic Deduction**: When an order is placed, inventory is automatically reduced
- **Smart Updates**: Changing order details (e.g., size from Large to Medium) automatically adjusts inventory counts
- **Stock Validation**: Prevents orders when insufficient inventory is available

## File Structure

```
grip/
├── src/main/java/gripsystem/grip/
│   ├── GripApplication.java          # Main application class
│   ├── controller/
│   │   ├── LoginController.java      # Login screen controller
│   │   └── MainInterfaceController.java # Main system controller
│   ├── model/
│   │   ├── Customer.java             # Customer order model
│   │   ├── Expense.java              # Expense model
│   │   └── Inventory.java            # Inventory model
│   └── service/
│       └── DataService.java          # CSV data operations
├── src/main/resources/gripsystem/grip/
│   ├── gripLogin.fxml                # Login screen UI
│   ├── gripInterface.fxml            # Main interface UI
│   ├── login-styles.css              # Login screen styling
│   └── interface-styles.css          # Main interface styling
└── pom.xml                           # Maven configuration
```

## Data Files (Auto-generated)

- `customers.csv` - Customer order records
- `expenses.csv` - Expense records
- `inventory.csv` - Current inventory levels

## How to Run

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Running the Application

1. **Navigate to the project directory**:
   ```bash
   cd grip
   ```

2. **Compile and run**:
   ```bash
   mvn clean javafx:run
   ```

3. **Login with credentials**:
   - Username: Tharin
   - Password: THARINpro12345

## Usage Guide

### Adding a Customer Order
1. Go to "Customer Orders" tab
2. Fill in customer details (name, contact number)
3. Select product type (Jogger/Short)
4. Choose color and size
5. Set quantity
6. Click "Add Order"

### Managing Inventory
1. Go to "Inventory" tab
2. Select product type, color, and size
3. Enter quantity and price
4. Click "Add/Update Inventory"

### Recording Expenses
1. Go to "Expenses" tab
2. Enter expense description
3. Enter amount
4. Click "Add Expense"

### Viewing Profit
1. Go to "Profit Analysis" tab
2. View real-time calculations of sales, expenses, and profit

## Business Logic

### Inventory Management
- **Initial Setup**: System automatically creates default inventory with 50 units of each product variant
- **Price Structure**: 
  - Joggers: $25.00 per unit
  - Shorts: $20.00 per unit
- **Stock Control**: Prevents overselling and maintains accurate inventory counts

### Order Processing
- **Validation**: Ensures all required fields are completed
- **Inventory Check**: Verifies sufficient stock before processing orders
- **Price Calculation**: Automatically calculates total based on quantity and unit price
- **Date Stamping**: Records order date automatically

### Data Integrity
- **CSV Format**: Human-readable data storage
- **Automatic Backups**: Data is preserved between sessions
- **Error Handling**: Comprehensive error messages and validation

## Technical Features

- **Modern UI**: Clean, responsive interface with gradient backgrounds
- **Real-time Updates**: All calculations and displays update automatically
- **Data Validation**: Input validation and error handling
- **Responsive Design**: Adapts to different window sizes
- **Professional Styling**: Modern CSS styling with hover effects and shadows

## Support

For technical support or questions about the system, please refer to the code documentation or contact the development team.

---

**Grip System** - Streamlining your clothing business operations since 2024. 