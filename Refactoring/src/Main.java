import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	 private List<Dress> dresses;
	 private Scanner scanner;
	 
	public Main() {
		// TODO Auto-generated constructor stub
		this.dresses = new ArrayList<>();
        this.scanner = new Scanner(System.in);
	}
	
	  public void runApplication() {
		  boolean exit = false;
		  while(!exit) {
			  	System.out.println("================\n  Dress Store  \n================");
	            System.out.println("1. Insert new dress");
	            System.out.println("2. View all dresses");
	            System.out.println("3. Delete a dress");
	            System.out.println("4. Exit");
	            System.out.print(">> ");
	            int choice = scanner.nextInt();
	            scanner.nextLine();
	            
	            Operation operation = Operation.fromInt(choice);
	            switch (operation) {
	                case INSERT:
	                    insertDress();
	                    break;
	                case VIEW:
	                    viewAllDress();
	                    break;
	                case DELETE:
	                    deleteDress();
	                    break;
	                case EXIT:
	                    exit = true;
	                    break;
	                default:
	                    System.out.println("Invalid option. Please try again.");
	                    break;
	            }
		  	}
	    }
	  
	  	private void deleteDress() {
		// TODO Auto-generated method stub
			if (dresses.isEmpty()) {
	            System.out.println("No dresses available.");
	            return; // Directly return to the calling method if no dresses are available.
	        }
	        System.out.println("==============================================================================================");
	        System.out.println("|  ID   |    Name        | Fabric Price | Fabric Type | Discount| Stock | Total Price\t\t|");
	        for (Dress dress : dresses) {
	            String id = dress.getId(); // Changed to getId() to get the already-stored ID.
	            String name = dress.getName();
	            int fabricPrice = dress.getFabricPrice();
	            String fabricType = dress.getFabricType();
	            String discount = (dress instanceof RegularDress) ? ((RegularDress) dress).getDiscount() + "%" : "-";
	            String stock = (dress instanceof LimitedEditionDress) ? Integer.toString(((LimitedEditionDress) dress).getStock()) : "-";
	            double totalPrice = dress.calculateTotalPrice();
	            System.out.printf("| %-5s | %-15s| Rp. %-10d | %-10s | %-8s | %-5s | Rp. %.2f\t|\n", id, name, fabricPrice, fabricType, discount, stock, totalPrice);
	        }
	        System.out.println("==============================================================================================");
	}

		private void viewAllDress() {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			if (dresses.isEmpty()) {
	            System.out.println("No dresses available.");
	            return; // Directly return to the calling method if no dresses are available.
	        }
	        System.out.println("==============================================================================================");
	        System.out.println("|  ID   |    Name        | Fabric Price | Fabric Type | Discount| Stock | Total Price\t\t|");
	        for (Dress dress : dresses) {
	            String id = dress.getId(); // Changed to getId() to get the already-stored ID.
	            String name = dress.getName();
	            int fabricPrice = dress.getFabricPrice();
	            String fabricType = dress.getFabricType();
	            String discount = (dress instanceof RegularDress) ? ((RegularDress) dress).getDiscount() + "%" : "-";
	            String stock = (dress instanceof LimitedEditionDress) ? Integer.toString(((LimitedEditionDress) dress).getStock()) : "-";
	            double totalPrice = dress.calculateTotalPrice();
	            System.out.printf("| %-5s | %-15s| Rp. %-10d | %-10s | %-8s | %-5s | Rp. %.2f\t|\n", id, name, fabricPrice, fabricType, discount, stock, totalPrice);
	        }
	        System.out.println("==============================================================================================");
		}
	
		private void insertDress() {
	  		DressType typeChoice = promptForDressType();

	        if (typeChoice == null || typeChoice == DressType.BACK) { // Invalid choice
	            return;
	        }
	        
	        String name = getStringInputWithValidationTypeChoice(
	                "Input dress name [5 - 20 characters]: ", 
	                Constants.MIN_NAME_LENGTH, 
	                Constants.MAX_NAME_LENGTH
	            );

	            int fabricPrice = (typeChoice == DressType.REGULAR_DRESS)
	                ? getIntInputWithValidation(
	                    "Input fabric price [10000 - 100000]: Rp.",
	                    Constants.MIN_REGULAR_PRICE,
	                    Constants.MAX_REGULAR_PRICE
	                )
	                : getIntInputWithValidation(
	                    "Input fabric price [100000 - 300000]: Rp.",
	                    Constants.MIN_LIMITED_PRICE,
	                    Constants.MAX_LIMITED_PRICE
	                );

	            String fabricType = getFabricTypeInput(typeChoice);

	        switch (typeChoice) {
	            case REGULAR_DRESS:
	                // Logic to insert a regular dress
	            	int discount = getIntInputWithValidation(
	                        "Input discount for this dress [1 - 100]:",
	                        Constants.MIN_DISCOUNT,
	                        Constants.MAX_DISCOUNT
	                        );
	            	dresses.add(new RegularDress(name, fabricPrice, fabricType, discount));
	                break;
	            case LIMITED_EDITION_DRESS:
	                // Logic to insert a limited edition dress
	            	 int stock = getIntInputWithValidation(
	                         "Input stock for this dress [1 - 10]:",
	                         Constants.MIN_STOCK,
	                         Constants.MAX_STOCK
	                     );
	                     dresses.add(new LimitedEditionDress(name, fabricPrice, fabricType, stock));
	                break;
	            default:
	            	break;
	        }

	        System.out.println("Successfully added a new " + typeChoice.name().replace("_", " ") + "!");
	 	
	  	}
	  	
	  	public enum DressType {
	        REGULAR_DRESS,
	        LIMITED_EDITION_DRESS,
	        BACK
	    }
	  	
	  	private DressType promptForDressType() {
	        System.out.println("\nPick which Type of dress to insert:");
	        System.out.println("1. Regular Dress");
	        System.out.println("2. Limited Edition Dress");
	        System.out.println("3. Back");
	        System.out.print(">> ");

	        int typeChoice = getIntInputWithValidation("Choose option [1-3]: ", 1, 3);

	        if (typeChoice == 1) {
	            return DressType.REGULAR_DRESS;
	        } else if (typeChoice == 2) {
	            return DressType.LIMITED_EDITION_DRESS;
	        } else if (typeChoice == 3) {
	            return DressType.BACK;
	        } else {
	            System.out.println("Invalid choice. Please enter a valid option.");
	            return null; // Indicates an invalid choice
	        }
	    }
	  	
	  	private String getFabricTypeInput(DressType typeChoice) {
	        if (typeChoice == DressType.REGULAR_DRESS) {
	            return getStringInputWithValidationDressType(
	                    "Input fabric type [Cotton | Wool] (case sensitive): ",
	                    new String[]{"Cotton", "Wool"}
	            );
	        } else {
	            return getStringInputWithValidationDressType(
	                    "Input fabric type [Satin | Chiffon | Crepe] (case sensitive): ",
	                    new String[]{"Satin", "Chiffon", "Crepe"}
	            );
	        }
	        
	    }
	    
	  	
	  	private int getIntInputWithValidation(String prompt, int minValue, int maxValue) {
	        int userInput;
	        while (true) {
	            System.out.print(prompt);
	            userInput = scanner.nextInt();
	            scanner.nextLine(); // Consume the newline
	            if (userInput >= minValue && userInput <= maxValue) {
	                return userInput;
	            }
	            System.out.println("Invalid input. Please enter a number between " + minValue + " and " + maxValue + ".");
	        }
	    }

	    private String getStringInputWithValidationTypeChoice(String prompt, int minLength, int maxLength) {
	        String userInput;
	        while (true) {
	            System.out.print(prompt);
	            userInput = scanner.nextLine();
	            if (userInput.length() >= minLength && userInput.length() <= maxLength) {
	                return userInput;
	            }
	            System.out.println("Invalid input. Please enter a string between " + minLength + " and " + maxLength + " characters long.");
	        }
	    }
	    
	    private String getStringInputWithValidationDressType(String prompt, String[] validOptions) {
	        String userInput;
	        while (true) {
	            System.out.print(prompt);
	            userInput = scanner.nextLine();
	            for (String option : validOptions) {
	                if (userInput.equalsIgnoreCase(option)) {
	                    return userInput;
	                }
	            }
	            System.out.println("Invalid input. Please enter one of the following: " + String.join(", ", validOptions));
	        }
	    }

		private enum Operation {
	        INSERT(1), VIEW(2), DELETE(3), EXIT(4), INVALID(-1);

	        private final int choiceValue;

	        Operation(int choiceValue) {
	            this.choiceValue = choiceValue;
	        }

	        public static Operation fromInt(int value) {
	            for (Operation operation : values()) {
	                if (operation.choiceValue == value) {
	                    return operation;
	                }
	            }
	            return INVALID;
	        }
	    }
	  	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  Main main = new Main();
	        try {
	            main.runApplication();
	        } finally {
	            if(main.scanner != null) {
	                main.scanner.close();
	            }
	        }
	}

}
