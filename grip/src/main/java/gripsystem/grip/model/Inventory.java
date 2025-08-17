package gripsystem.grip.model;

public class Inventory {
    private String productType; // "Jogger" or "Short"
    private String color;
    private String size;
    private int quantity;
    private double price;

    public Inventory(String productType, String color, String size, int quantity, double price) {
        this.productType = productType;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public String getProductType() { return productType; }
    public void setProductType(String productType) { this.productType = productType; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return productType + "," + color + "," + size + "," + quantity + "," + price;
    }

    public static Inventory fromString(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length == 5) {
            return new Inventory(parts[0], parts[1], parts[2], 
                               Integer.parseInt(parts[3]), Double.parseDouble(parts[4]));
        }
        return null;
    }
} 