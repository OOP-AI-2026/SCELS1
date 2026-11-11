package service;

import exception.*;
import model.*;

public class LendingService {
    private CampusMember[] members;
    private EquipmentItem[] items;
    private Loan[] loans;
    private int loanCount;

    public LendingService(CampusMember[] members, EquipmentItem[] items) {
        this.members = members;
        this.items = items;
        this.loans = new Loan[50];
        this.loanCount = 0;
    }

    public Loan[] getLoans() {
        return loans;
    }

    public int getLoanCount() {
        return loanCount;
    }

    public Loan borrowItem(String memberId, String itemId, int numberOfDays)
            throws UnknownMemberException, UnknownItemException, ItemUnavailableException, InvalidLoanException {
        
        CampusMember member = null;
        for (CampusMember m : members) {
            if (m != null && m.getMemberId().equals(memberId)) {
                member = m;
                break;
            }
        }
        if (member == null) throw new UnknownMemberException("Unknown member id: " + memberId);

        EquipmentItem item = null;
        for (EquipmentItem i : items) {
            if (i != null && i.getItemId().equals(itemId)) {
                item = i;
                break;
            }
        }
        if (item == null) throw new UnknownItemException("Unknown item id: " + itemId);

        if (numberOfDays < 1 || numberOfDays > 30) {
            throw new InvalidLoanException("Invalid duration: " + numberOfDays + " days. Must be between 1 and 30.");
        }

        if (!item.isAvailable()) {
            throw new ItemUnavailableException("Item is already borrowed: " + itemId);
        }


        String loanId = "L" + String.format("%03d", loanCount + 1);
        Loan newLoan = new Loan(loanId, member, item, numberOfDays);
        
        item.markBorrowed();
        member.recordBorrowing();
        loans[loanCount] = newLoan;
        loanCount++;

        return newLoan;
    }


    public void returnItem(String loanId) throws UnknownLoanException, InvalidLoanException {

        Loan loan = null;
        for (int i = 0; i < loanCount; i++) {
            if (loans[i] != null && loans[i].getLoanId().equals(loanId)) {
                loan = loans[i];
                break;
            }
        }
        if (loan == null) throw new UnknownLoanException("Unknown loan id: " + loanId);

        if (!loan.isActive()) {
            throw new InvalidLoanException("Loan is already closed: " + loanId);
        }


        loan.closeLoan();
        loan.getItem().markReturned();
        loan.getMember().recordReturn();
    }
}