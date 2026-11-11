package model;

public class Projector extends EquipmentItem {
    private int lumens;
    private boolean hdmiSupported;

    public Projector(String itemId, String name, String category, double dailyFee,
                     int lumens, boolean hdmiSupported) {
        super(itemId, name, category, dailyFee);
        this.lumens = lumens;
        this.hdmiSupported = hdmiSupported;
    }

    public int getLumens() { return lumens; }

    @Override
    public String getTechnicalDescription() {
        return "Projector with " + lumens + " lumens";
    }

    @Override
    public double calculateFee(int numberOfDays) {
        if (numberOfDays <= 0) return 0.0;
        return getDailyFee() * numberOfDays + 10.0;
    }

    @Override
    public String getDisplayLabel() {
        return "Projector / " + super.getDisplayLabel();
    }
}