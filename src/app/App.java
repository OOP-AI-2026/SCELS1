package app;

import exception.*;
import model.*;
import service.LendingService;

public class App {

    public static void main(String[] args) {

        // --- 1. Initialisation des données ---
        CampusMember member1 = new CampusMember("M001", "Amin", "amina@univ.ma", "AI", 2);
        CampusMember[] members = { member1 };

        EquipmentItem camera = new Camera("EQ001", "Canon Camera", "Media", 50.0, 24, true);
        EquipmentItem laptop = new Laptop("EQ002", "Dell Laptop", "Computing", 80.0, 16, true);
        EquipmentItem[] items = { camera, laptop };

        LendingService service = new LendingService(members, items);

        System.out.println("===== EXERCISE 6 : SCENARIOS =====");

        // --- Scénario 1 : Valid borrow ---
        System.out.println("\n-- Scenario 1: Valid borrow --");
        try {
            Loan l1 = service.borrowItem("M001", "EQ001", 3);
            System.out.println("Success: " + l1.summaryLine());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        printState(service, camera);

        // --- Scénario 2 : Unknown member ---
        System.out.println("\n-- Scenario 2: Unknown member --");
        try {
            service.borrowItem("M999", "EQ002", 2);
        } catch (UnknownMemberException | UnknownItemException | ItemUnavailableException | InvalidLoanException e) {
            System.out.println("Borrowing rejected: " + e.getMessage());
        }
        printState(service, camera);

        // --- Scénario 3 : Unknown item ---
        System.out.println("\n-- Scenario 3: Unknown item --");
        try {
            service.borrowItem("M001", "EQ999", 2);
        } catch (UnknownMemberException | UnknownItemException | ItemUnavailableException | InvalidLoanException e) {
            System.out.println("Borrowing rejected: " + e.getMessage());
        }
        printState(service, camera);

        // --- Scénario 4 : Unavailable item (Camera déjà empruntée au scénario 1) ---
        System.out.println("\n-- Scenario 4: Unavailable item --");
        try {
            service.borrowItem("M001", "EQ001", 1);
        } catch (UnknownMemberException | UnknownItemException | ItemUnavailableException | InvalidLoanException e) {
            System.out.println("Borrowing rejected: " + e.getMessage());
        }
        printState(service, camera);

        // --- Scénario 5 : Invalid duration ---
        System.out.println("\n-- Scenario 5: Invalid duration --");
        try {
            service.borrowItem("M001", "EQ002", 0);
        } catch (UnknownMemberException | UnknownItemException | ItemUnavailableException | InvalidLoanException e) {
            System.out.println("Borrowing rejected: " + e.getMessage());
        }
        printState(service, camera);

        // --- Scénario 6 : Valid return ---
        System.out.println("\n-- Scenario 6: Valid return --");
        try {
            service.returnItem("L001"); // On retourne la caméra empruntée au scénario 1
            System.out.println("Success: Loan L001 returned.");
        } catch (UnknownLoanException | InvalidLoanException e) {
            System.out.println("Return rejected: " + e.getMessage());
        }
        printState(service, camera);

        // --- Scénario 7 : Unknown loan ---
        System.out.println("\n-- Scenario 7: Unknown loan --");
        try {
            service.returnItem("L999");
        } catch (UnknownLoanException | InvalidLoanException e) {
            System.out.println("Return rejected: " + e.getMessage());
        }
        printState(service, camera);

        // --- Scénario 8 : Return same loan again ---
        System.out.println("\n-- Scenario 8: Return same loan again --");
        try {
            service.returnItem("L001"); // Déjà retourné au scénario 6
        } catch (UnknownLoanException | InvalidLoanException e) {
            System.out.println("Return rejected: " + e.getMessage());
        }
        printState(service, camera);

        // --- EXERCICE 5 : finally ---
        System.out.println("\n-- Exercise 5: finally block demonstration --");
        try {
            service.borrowItem("M001", "EQ999", 1); // Va échouer (UnknownItemException)
        } catch (Exception e) {
            System.out.println("Borrowing rejected: " + e.getMessage());
        } finally {
            System.out.println(">> Borrowing scenario finished (finally block executed).");
        }
    }

    // Méthode utilitaire pour afficher l'état
    private static void printState(LendingService service, EquipmentItem item) {
        System.out.println("   [State] Loans stored: " + service.getLoanCount() 
                + " | Item EQ001 available: " + item.isAvailable());
    }
}