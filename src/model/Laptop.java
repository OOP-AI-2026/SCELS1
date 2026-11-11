
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

    public int getRamGb() {
        return ramGb;
    }

    public boolean isHasDedicatedGpu() {
        return hasDedicatedGpu;
    }

    @Override
    public String getTechnicalDescription() {
        return "Laptop with " + ramGb + "GB RAM, Dedicated GPU: " + hasDedicatedGpu;
    }

    @Override
    public double calculateFee(int numberOfDays) {
        double fee = getDailyFee() * numberOfDays;
        if (hasDedicatedGpu) {
            fee += 10.0 * numberOfDays;
        }
        return fee;
    }
}