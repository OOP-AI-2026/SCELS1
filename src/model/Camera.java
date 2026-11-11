package model;

public class Camera extends EquipmentItem {
    private int megapixels;
    private boolean lensKitIncluded;

    public Camera(String itemId, String name, String category, double dailyFee,
                  int megapixels, boolean lensKitIncluded) {
        super(itemId, name, category, dailyFee);
        this.megapixels = megapixels;
        this.lensKitIncluded = lensKitIncluded;
    }

    public int getMegapixels() { return megapixels; }

    @Override
    public String getTechnicalDescription() {
        return "Camera with " + megapixels + " MP";
    }

    @Override
    public double calculateFee(int numberOfDays) {
        if (numberOfDays <= 0) return 0.0;
        return getDailyFee() * numberOfDays + 20.0;
    }

    @Override
    public String getDisplayLabel() {
        return "Camera / " + super.getDisplayLabel();
    }
}