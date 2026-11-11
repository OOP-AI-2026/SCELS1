
package model;

public class Loan {

    private String loanId;
    private CampusMember member;
    private EquipmentItem item;
    private int numberOfDays;
    private double totalFee;
    private boolean active;

    public Loan(String loanId, CampusMember member,
                EquipmentItem item, int numberOfDays) {

        this.loanId = loanId;
        this.member = member;
        this.item = item;
        this.numberOfDays = numberOfDays;

        this.totalFee = item.calculateFee(numberOfDays);
        this.active = true;
    }

    public String getLoanId() {
        return loanId;
    }

    public CampusMember getMember() {
        return member;
    }

    public EquipmentItem getItem() {
        return item;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public boolean isActive() {
        return active;
    }

    public void closeLoan() {
        active = false;
    }

    public boolean isForItem(EquipmentItem targetItem) {
        return this.item == targetItem;
    }

    public boolean belongsTo(CampusMember targetMember) {
        return this.member == targetMember;
    }

    public String summaryLine() {
        return loanId + " | " + member + " | " + item + " | "
                + numberOfDays + " days | Fee: " + totalFee
                + " | Status: " + (active ? "Active" : "Closed");
    }
}