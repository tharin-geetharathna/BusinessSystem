package gripsystem.grip.model;

public class Customer {
    private String name;
    private String contactNumber;
    private String productType; // "Jogger" or "Short"
    private String color;
    private String size;
    private int quantity;
    private double totalPrice;
    private String orderDate;

    public Customer(String name, String contactNumber, String productType, String color, String size, int quantity, double totalPrice, String orderDate) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.productType = productType;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getProductType() { return productType; }
    public void setProductType(String productType) { this.productType = productType; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }

    @Override
    public String toString() {
        return name + "," + contactNumber + "," + productType + "," + color + "," + size + "," + quantity + "," + totalPrice + "," + orderDate;
    }

    public static Customer fromString(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length == 8) {
            return new Customer(parts[0], parts[1], parts[2], parts[3], parts[4], 
                             Integer.parseInt(parts[5]), Double.parseDouble(parts[6]), parts[7]);
        }
        return null;
    }
} 