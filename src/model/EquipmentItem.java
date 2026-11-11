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

    public String getItemId()      { return itemId; }
    public String getName()        { return name; }
    public String getCategory()    { return category; }
    public boolean isAvailable()   { return available; }
    protected double getDailyFee() { return dailyFee; }

    public String getAvailabilityText() {
        return available ? "Available" : "Borrowed";
    }

    public void markBorrowed() { if (available) available = false; }
    public void markReturned() { if (!available) available = true; }

    public void displaySummary() {
        System.out.println(getDisplayLabel(true));
    }

    // --- EXERCICE 2 ---
    public String getDisplayLabel() {
        return getItemId() + " - " + getName();
    }

    // --- EXERCICE 3 ---
    public String getDisplayLabel(boolean includeAvailability) {
        if (!includeAvailability) {
            return getDisplayLabel();
        } else {
            return getDisplayLabel() + " / available=" + isAvailable();
        }
    }

    public abstract String getTechnicalDescription();
}