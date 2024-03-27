package newBaru;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<Dress> dresses;
    private Scanner sc;

    public Main() {
        this.dresses = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }
    
    public void insertNewDress() {
    	System.out.println("\nPick which Type of dress to insert:");
        System.out.println("1. Regular Dress");
        System.out.println("2. Limited Edition Dress");
        System.out.println("3. Back");
        System.out.print(">> ");
        int typeChoice = sc.nextInt();
        sc.nextLine(); // consume the leftover newline
        if (typeChoice == 3) {
            return;
        } else if (typeChoice < 1 || typeChoice > 2) {
            System.out.println("Invalid choice. Please enter a valid option.");
            return;
        }

        System.out.print("Input dress name [5 - 20 characters]: ");
        String name = sc.nextLine();
        while (name.length() < 5 || name.length() > 20) {
            System.out.print("Invalid name length. Input dress name [5 - 20 characters]: ");
            name = sc.nextLine();
        }

        int fabricPrice;
        if (typeChoice == 1) {
            // Regular Dress
            System.out.print("Input fabric price [Rp. 10000 - Rp. 100000]: Rp. ");
            fabricPrice = sc.nextInt();
            while (fabricPrice < 10000 || fabricPrice > 100000) {
                System.out.print("Invalid price. Input fabric price [Rp. 10000 - Rp. 100000]: Rp. ");
                fabricPrice = sc.nextInt();
            }

            sc.nextLine(); 
            System.out.print("Input fabric type [Cotton | Wool] (case sensitive): ");
            String fabricType = sc.nextLine();
            while (!(fabricType.equals("Cotton") || fabricType.equals("Wool"))) {
                System.out.print("Invalid fabric type. Input fabric type [Cotton | Wool] (case sensitive): ");
                fabricType = sc.nextLine();
            }

            System.out.print("Input discount for this dress [1..100]: ");
            int discount = sc.nextInt();
            while (discount < 1 || discount > 100) {
                System.out.print("Invalid discount. Input discount for this dress [1..100]: ");
                discount = sc.nextInt();
            }

            RegularDress newRegularDress = new RegularDress(fabricType, discount, fabricType, discount);
            newRegularDress.name = name;
            newRegularDress.fabricType = fabricType; 
            newRegularDress.discount = discount;
            dresses.add(newRegularDress);
            System.out.println("Successfully added a new Regular Dress!");

        } else {
            // Limited Edition Dress
            System.out.print("Input fabric price [Rp. 100000 - Rp. 300000]: Rp. ");
            fabricPrice = sc.nextInt();
            while (fabricPrice < 100000 || fabricPrice > 300000) {
                System.out.print("Invalid price. Input fabric price [Rp. 100000 - Rp. 300000]: Rp. ");
                fabricPrice = sc.nextInt();
            }

            sc.nextLine();
            System.out.print("Input fabric type [Satin | Chiffon | Crepe] (case sensitive): ");
            String fabricType = sc.nextLine();
            while (!(fabricType.equals("Satin") || fabricType.equals("Chiffon") || fabricType.equals("Crepe"))) {
                System.out.print("Invalid fabric type. Input fabric type [Satin | Chiffon | Crepe] (case sensitive): ");
                fabricType = sc.nextLine();
            }

            System.out.print("Input stock for this dress [1..10]: ");
            int stock = sc.nextInt();
            while (stock < 1 || stock > 10) {
                System.out.print("Invalid stock. Input stock for this dress [1..10]: ");
                stock = sc.nextInt();
            }

            LimitedEditionDress newLimitedEditionDress = new LimitedEditionDress(fabricType, stock, fabricType, stock);
            newLimitedEditionDress.name = name;
            newLimitedEditionDress.fabricType = fabricType;
            newLimitedEditionDress.stock = stock;
            dresses.add(newLimitedEditionDress);
            System.out.println("Successfully added a new Limited Edition Dress!");
        }
        sc.nextLine(); 
        System.out.println("Press Enter to continue...");
        sc.nextLine();
    }

    // Displays all dresses in the store
    public void viewAllDresses() {
        // Logic to display all dresses
    	if (dresses.isEmpty()) {
            System.out.println("No dresses available.");
            return; 
        }
        System.out.println("==============================================================================================");
        System.out.println("|  ID   |    Name        | Fabric Price | Fabric Type | Discount| Stock | Total Price |");
        for (Dress dress : dresses) {
            String id = dress.getId(); 
            String name = dress.getName();
            int fabricPrice = dress.getFabricPrice();
            String fabricType = dress.getFabricType();
            String discount = (dress instanceof RegularDress) ? ((RegularDress) dress).getDiscount() + "%" : "-";
            String stock = (dress instanceof LimitedEditionDress) ? Integer.toString(((LimitedEditionDress) dress).getStock()) : "-";
            double totalPrice = dress.calculateTotalPrice();
            System.out.printf("| %-5s | %-15s | Rp. %-10d | %-10s | %-8s | %-5s | Rp. %.2f |\n", id, name, fabricPrice, fabricType, discount, stock, totalPrice);
        }
        System.out.println("==============================================================================================");
        promptEnterToContinue();
    }

    // Deletes a dress from the store based on ID
    public void deleteDress() {
    	if (dresses.isEmpty()) {
            System.out.println("No dresses available.");
            return; 
        }
        viewAllDresses();
        System.out.print("Input dress ID to delete: ");
        String dressIdToDelete = sc.next();
        sc.nextLine(); // Consume the rest of the line
        Dress dressToDelete = null;
        for (Dress dress : dresses) {
            if (dress.getId().equalsIgnoreCase(dressIdToDelete)) {
                dressToDelete = dress;
                break;
            }
        }
        if (dressToDelete != null) {
            dresses.remove(dressToDelete);
            System.out.println("Successfully removed dress!");
        } else {
            System.out.println("Dress ID is invalid!");
        }
        promptEnterToContinue();
     }

    // Displays the main menu and handles user interaction
    public void displayMainMenu() {
        int choice;
        do {
            System.out.println("================\n  Dress Store  \n================");
            System.out.println("1. Insert new dress");
            System.out.println("2. View all dresses");
            System.out.println("3. Delete a dress");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();

	            if (choice == 1) {
	               insertNewDress();
	            } else if (choice == 2) {
	            	viewAllDresses();
	            } else if (choice == 3) {
	            	  deleteDress();
	            }  
            } while (choice != 4);
        
        	System.exit(0);
        	
    }
    
    // Prompts user to hit Enter to continue
    private void promptEnterToContinue() {
        System.out.println("Press Enter to continue...");
        sc.nextLine();
    }
    
    // Closes the scanner before exiting the application
    public void closeScanner() {
        sc.close();
    }

    // Entry point of the application
    public static void main(String[] args) {
        Main mainApp = new Main();
        mainApp.displayMainMenu();
        mainApp.closeScanner();
    }

}
