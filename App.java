import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            // Load JDBC driver and connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rental", "root", "");
            System.out.println("Connected to the database");

            Scanner scanner = new Scanner(System.in);
            boolean isLoggedIn = false;

            while (!isLoggedIn) {
                // Main menu for registration or login with validation
                int option = 0;
                while (option != 1 && option != 2) {
                    System.out.print("1. Register\n2. Login\nChoose an option: ");
                    if (scanner.hasNextInt()) {
                        option = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        if (option != 1 && option != 2) {
                            System.out.println("Invalid option. Please choose either 1 for Register or 2 for Login.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a number (1 for Register, 2 for Login).");
                        scanner.nextLine();  // Clear invalid input
                    }
                }

                if (option == 1) {
                    // Register a new user
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    User.registerUser(con, username, password);

                    System.out.println("Registration successful. Please log in.");
                    option = 2; // Automatically go to login
                }

                if (option == 2) {
                    // Login with retry
                    while (!isLoggedIn) {
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();

                        if (User.loginUser(con, username, password)) {
                            System.out.println("Login successful!");
                            isLoggedIn = true;

                            // Start the item selection and purchasing loop
                            boolean continueShopping = true;
                            while (continueShopping) {
                                // Prompt user to select item type
                                String itemType = "";
                                while (!itemType.equals("book") && !itemType.equals("movie")) {
                                    System.out.print("Do you want to rent a book or a movie? ");
                                    itemType = scanner.nextLine().toLowerCase();
                                    if (!itemType.equals("book") && !itemType.equals("movie")) {
                                        System.out.println("Invalid choice. Please enter 'book' or 'movie'.");
                                    }
                                }

                                // Prompt user to enter genre
                                System.out.print("Enter the genre you want to search (e.g., romance, scary): ");
                                String genre = scanner.nextLine();

                                // Display items by genre
                                RentalSystem.displayItemsByGenre(con, itemType, genre);

                                // Allow multiple item selections within the chosen genre/type
                                String addMore = "yes";
                                while (addMore.equalsIgnoreCase("yes")) {
                                    System.out.print("Enter the ID of the item you want to select: ");
                                    int itemId = scanner.nextInt();
                                    scanner.nextLine();  // Consume newline

                                    RentalSystem.selectItemById(con, itemId);

                                    System.out.print("Do you want to select another item in this genre? (yes/no): ");
                                    addMore = scanner.nextLine().toLowerCase();
                                    if (!addMore.equals("yes") && !addMore.equals("no")) {
                                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                                    }
                                }

                                // Ask if the user wants to continue shopping across different genres or types
                                System.out.print("Would you like to switch to another genre or type? (yes to continue shopping / no to checkout): ");
                                String continueShoppingChoice = scanner.nextLine().toLowerCase();
                                if (!continueShoppingChoice.equals("yes")) {
                                    continueShopping = false;
                                }
                            }

                            // Display total selected items and total budget, then offer to split the cost
                            RentalSystem.calculateTotalPriceAndSplit();
                        } else {
                            System.out.println("Login failed. Incorrect username or password. Try again.");
                        }
                    }
                }
            }

            scanner.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
