package model;

public class Laptop extends EquipmentItem {
    private int ramGb;
    private boolean hasDedicatedGpu;

    public Laptop(String itemId, String name, String category, double dailyFee,
                  int ramGb, boolean hasDedicatedGpu) {
        super(itemId, name, category, dailyFee);
        this.ramGb = ramGb;
        this.hasDedicatedGpu = hasDedicatedGpu;
    }

    public int getRamGb() { return ramGb; }

    @Override
    public String getTechnicalDescription() {
        return "Laptop with " + ramGb + " GB RAM";
    }

    @Override
    public double calculateFee(int numberOfDays) {
        if (numberOfDays <= 0) return 0.0;
        double base = getDailyFee() * numberOfDays;
        if (hasDedicatedGpu) base += 5.0 * numberOfDays;
        return base;
    }

    @Override
    public String getDisplayLabel() {
        return "Laptop / " + super.getDisplayLabel();
    }
}