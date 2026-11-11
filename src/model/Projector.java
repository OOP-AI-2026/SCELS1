
package model;

public class Projector extends EquipmentItem {

    private int lumens;
    private boolean hdmiSupported;

    public Projector(String itemId, String name, String category, double dailyFee, int lumens, boolean hdmiSupported) {
        super(itemId, name, category, dailyFee);
        this.lumens = lumens;
        this.hdmiSupported = hdmiSupported;
    }

    public int getLumens() {
        return lumens;
    }

    public boolean isHdmiSupported() {
        return hdmiSupported;
    }

    @Override
    public String getTechnicalDescription() {
        return "Projector with " + lumens + " lumens, HDMI: " + hdmiSupported;
    }

    @Override
    public double calculateFee(int numberOfDays) {
        double fee = getDailyFee() * numberOfDays;
        if (lumens > 3000) {
            fee += 15.0 * numberOfDays;
        }
        return fee;
    }
}