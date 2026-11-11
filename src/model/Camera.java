
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

    public int getMegapixels() {
        return megapixels;
    }

    public boolean isLensKitIncluded() {
        return lensKitIncluded;
    }

    @Override
    public String getTechnicalDescription() {
        return "Camera with " + megapixels + "MP, Lens Kit: " + lensKitIncluded;
    }

    @Override
    public double calculateFee(int numberOfDays) {
        double fee = getDailyFee() * numberOfDays;
        if (lensKitIncluded) {
            fee += 5.0 * numberOfDays;
        }
        return fee;
    }
}