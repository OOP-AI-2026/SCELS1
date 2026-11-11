package model;

public class CampusMember {
    private String memberId;
    private String fullName;
    private String email;
    private String department;
    private int activeLoans;
    private int borrowingLimit;

    public CampusMember(String memberId, String fullName, String email,
                        String department, int borrowingLimit) {
        this.memberId = memberId;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.borrowingLimit = borrowingLimit;
        this.activeLoans = 0;
    }

    public String getMemberId()    { return memberId; }
    public String getFullName()    { return fullName; }
    public String getEmail()       { return email; }
    public String getDepartment()  { return department; }
    public int getActiveLoans()    { return activeLoans; }
    public int getBorrowingLimit() { return borrowingLimit; }
    public String getLoanStatus()  { return activeLoans + "/" + borrowingLimit; }

    public boolean canBorrow() { return activeLoans < borrowingLimit; }

    public void recordBorrowing() { 
        if (canBorrow()) activeLoans++; 
    }
    
    public void recordReturn()    { 
        if (activeLoans > 0) activeLoans--; 
    }

    public void displayProfile() {
        System.out.println("Member ID   : " + memberId);
        System.out.println("Full Name   : " + fullName);
        System.out.println("Email       : " + email);
        System.out.println("Department  : " + department);
        System.out.println("Active loans: " + activeLoans + "/" + borrowingLimit);
    }
}