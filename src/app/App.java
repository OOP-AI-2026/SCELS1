package app;

import exception.*;
import model.*;
import service.LendingService;

public class App {

    public static void main(String[] args) {

        CampusMember member1 = new CampusMember("M001", "Amin", "amina@univ.ma", "AI", 2);
        CampusMember[] members = { member1 };

        System.out.println("--- EXERCISE 1 : Constructor Chaining ---");
        EquipmentItem laptop = new Laptop("EQ002", "Dell Laptop", "Computing", 80.0, 16, true);
        EquipmentItem camera = new Camera("EQ001", "Canon Camera", "Media", 50.0, 24, true);
        EquipmentItem projector = new Projector("EQ003", "Epson Projector", "Presentation", 60.0, 3000, true);
        EquipmentItem tablet = new Tablet("EQ004", "iPad Pro", "Mobile", 40.0, 256);

        EquipmentItem[] items = { laptop, camera, projector, tablet };
        LendingService service = new LendingService(members, items);

        System.out.println("\n--- EXERCISE 3 : Overloaded Display ---");
        System.out.println(laptop.getDisplayLabel());
        System.out.println(camera.getDisplayLabel(true));

        System.out.println("\n--- EXERCISE 4 : Polymorphic Processing ---");
        EquipmentItem[] inventory = { laptop, camera, projector, tablet };
        for (EquipmentItem item : inventory) {
            printEquipment(item);
        }

        System.out.println("\n--- EXERCISE 5 : Safe Downcasting ---");
        for (EquipmentItem item : inventory) {
            if (item instanceof Laptop) {
                Laptop l = (Laptop) item;
                System.out.println(" -> " + l.getName() + " has " + l.getRamGb() + "GB RAM.");
            } else if (item instanceof Camera) {
                Camera c = (Camera) item;
                System.out.println(" -> " + c.getName() + " is " + c.getMegapixels() + "MP.");
            } else if (item instanceof Projector) {
                Projector p = (Projector) item;
                System.out.println(" -> " + p.getName() + " produces " + p.getLumens() + " lumens.");
            } else if (item instanceof Tablet) {
                Tablet t = (Tablet) item;
                System.out.println(" -> " + t.getName() + " has " + t.getStorageGb() + "GB storage.");
            }
        }

        // Décommente ce bloc pour tester le ClassCastException demandé à l'exercice 5
        /*
        System.out.println("\n-- TEST UNSAFE CAST --");
        try {
            Laptop fakeLaptop = (Laptop) camera;
        } catch (ClassCastException e) {
            System.out.println("Attrapé : " + e.getMessage());
        }
        */

        System.out.println("\n--- EXERCISE 6 : Lending Transactions ---");
        System.out.println("Before transaction:");
        for (EquipmentItem item : inventory) {
            System.out.println("  " + item.getDisplayLabel(true));
        }

        try {
            System.out.println("\nBorrowing Camera (EQ001) for 3 days...");
            Loan validLoan = service.borrowItem("M001", "EQ001", 3);
            System.out.println("Success: " + validLoan.getLoanId());

            System.out.println("\nDisplay after borrow:");
            System.out.println("  " + camera.getDisplayLabel(true));

            System.out.println("\nAttempting invalid borrow (EQ003 for 0 days)...");
            service.borrowItem("M001", "EQ003", 0);

            System.out.println("\nReturning Camera...");
            service.returnItem(validLoan.getLoanId());

        } catch (UnknownMemberException | UnknownItemException | ItemUnavailableException
               | InvalidLoanException | UnknownLoanException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        System.out.println("\nAfter transactions:");
        for (EquipmentItem item : inventory) {
            System.out.println("  " + item.getDisplayLabel(true));
        }
    }

    private static void printEquipment(EquipmentItem item) {
        System.out.println(item.getDisplayLabel() + " | " + item.getDisplayLabel(true));
    }
}