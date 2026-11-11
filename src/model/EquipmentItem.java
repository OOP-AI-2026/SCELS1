
package model;  
public abstract class EquipmentItem implements Chargeable {

    private String itemId;
    private String name;
    private String category;
    private double dailyFee;
    private boolean available;

    protected EquipmentItem(String itemId, String name, String category, double dailyFee) {
        this.itemId = itemId;
        this.name = name;
        this.category = category;
        this.dailyFee = dailyFee;
        this.available = true;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    protected double getDailyFee() {
        return dailyFee;
    }

    public boolean isAvailable() {
        return available;
    }

    public void markBorrowed() {
        available = false;
    }

    public void markReturned() {
        available = true;
    }

    public String getAvailabilityText() {
        return available ? "Available" : "Borrowed";
    }

    public void displaySummary() {
        System.out.println(itemId + " | " + name + " | " + category + " | " + getAvailabilityText());
    }

    public abstract String getTechnicalDescription();

    @Override
    public abstract double calculateFee(int numberOfDays);

    @Override
    public String toString() {
        return name + " (" + itemId + ")";
    }
}