import java.io.File;//imports the file class from the java.io package 
import java.io.FileNotFoundException;//imports the FileNotFoundException class from the java.io package
import java.io.FileWriter;//imports the FileWriter class from the java.io package
import java.io.IOException;//imports the IOException class fromm the java.io package
import java.util.*;// imports all classes from the java.util package

public class Theatre {
    public static ArrayList<Ticket> tickets_array = new ArrayList<>();
    private static final int[][] seats = new int[3][];// Initialize a 2D integer array called seats with 3 rows
    static {
        seats[0] = new int[12]; // initialize the first row with 12 columns
        seats[1] = new int[16]; // initialize the second row with 16 columns
        seats[2] = new int[20]; // initialize the third row with 20 columns
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the New Theatre");
        while (true) {// Infinite loop that keeps displaying the menu until the user chooses to quit
            Scanner input = new Scanner(System.in);// Creates a Scanner object to read user input
            try {
                while (true) {// Infinite loop that keeps displaying the menu until the user inputs correct
                              // input
                    System.out.print("-".repeat(50));// Prints the menu options
                    System.out.println();
                    System.out.println("Please select an option: ");
                    System.out.println("1) Buy a ticket");
                    System.out.println("2) Print seating area");
                    System.out.println("3) Cancel ticket");
                    System.out.println("4) List available seats ");
                    System.out.println("5) Save to file");
                    System.out.println("6) Load from file");
                    System.out.println("7) Print ticket information and total price");
                    System.out.println("8) Sort tickets by price");
                    System.out.println("    0) Quit");
                    System.out.println("-".repeat(50));
                    System.out.print("Enter option: ");

                    int option = input.nextInt(); // Reads the user's input

                    switch (option) { // Switch statement to execute different options based on user input
                        case 1:
                            buy_ticket();
                            break;
                        case 2:
                            print_seating_area();
                            break;
                        case 3:
                            cancel_ticket();
                            break;
                        case 4:
                            show_available();
                            break;
                        case 5:
                            save();
                            break;
                        case 6:
                            load();
                            break;
                        case 7:
                            show_tickets_info();
                            break;
                        case 8:
                            sort_tickets();
                            break;
                        case 0:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid option selected. Please choose a valid option from the menu."); // Prints
                                                                                                                        // an
                                                                                                                        // error
                                                                                                                        // message
                                                                                                                        // if
                                                                                                                        // the
                                                                                                                        // user
                                                                                                                        // inputs
                                                                                                                        // an
                                                                                                                        // invalid
                                                                                                                        // option
                    }
                }
            } catch (NumberFormatException | InputMismatchException e) { // Prints an error message if the user inputs
                                                                         // non-numeric data
                System.out.println("Invalid input. Please enter an integer value.");// referred from w3 schools
            }
        }
    }

    public static void buy_ticket() {
        Scanner input = new Scanner(System.in);/*
                                                * This method allows the user to buy a ticket by entering their name,
                                                * surname and email address, and choosing a seat from the seating area.
                                                * The user is prompted to enter the row and seat number they want to
                                                * book, and the method checks if the seat is available.
                                                * If the seat is available, the user is prompted to enter the price of
                                                * the ticket, which depends on the row number.
                                                * The method then creates a new Ticket object with the given details,
                                                * adds it to the list of tickets, and marks the seat as sold on the
                                                * seating area.
                                                * If the input is invalid or the seat is already sold, an error message
                                                * is displayed and the method returns.
                                                */
        System.out.print("Enter name: ");
        String name = input.nextLine();
        System.out.print("Enter surname: ");
        String surname = input.nextLine();
        System.out.print("Enter email: ");
        String email = input.nextLine();
        Person person = new Person(name, surname, email);
        int row;
        int seat;
        System.out.print("Enter row number: ");

        row = input.nextInt() - 1;
        if (row < 0 || row >= seats.length) {// Check if the row number is valid
            System.out.println(
                    "Sorry, the row number you entered is invalid. Please enter a number between 1 and 3 to select a row and try again.");
            return;
        }
        System.out.print("Enter seat number: ");
        seat = input.nextInt() - 1;
        if (seat < 0 || seat >= seats[row].length) {// Check if the seat number is valid
            System.out.println(
                    "Sorry, the seat number you entered is invalid. Please enter a valid seat number and try again.");
            return;
        }
        if (seats[row][seat] == 1) {// Check if the seat is already sold
            System.out.println("Sorry, the seat you selected has already been sold. Please select another seat.");
            return;
        }
        double ticket_price;
        while (true) { // Run an infinite loop until the user enters a valid price
            System.out.println("Row 1 = £15"); // Display the prices for each row
            System.out.println("Row 2 = £10");
            System.out.println("Row 3 = £5");
            System.out.print("Enter price : £");
            ticket_price = input.nextDouble(); // Read the user's input as a double value
            if ((row == 0 && ticket_price == 15) || (row == 1 && ticket_price == 10)
                    || (row == 2 && ticket_price == 5)) {// Check if the entered price matches the price of the selected
                                                         // row
                break;
            } else { // If the entered price is invalid, display an error message and continue the
                     // loop
                System.out
                        .println("Sorry, the price you entered is invalid. Please enter a valid price and try again.");
            }
        }
        Ticket ticket = new Ticket(row, seat, ticket_price, person);// Create a new Ticket object with the given details
                                                                    // and add it to the list of tickets
        // Mark the seat as sold on the seating area
        seats[row][seat] = 1;
        tickets_array.add(ticket);
        System.out.println("Your seat has been successfully booked.");
    }

    public static void print_seating_area() {
        System.out.println("    ***********");
        System.out.println("    *  STAGE  *");
        System.out.println("    ***********");

        for (int i = 0; i < seats.length; i++) {// iterate through the rows of the seating array and prints the seats in
                                                // each row using the print_seating method.

            if (i == 0) { // to print the first row of seats
                System.out.print("    ");
                print_seating(0);
                System.out.println();
            }

            if (i == 1) { // to print the second row of seats
                System.out.print("  ");
                print_seating(1);
                System.out.println();
            }

            if (i == 2) { // to print the third row of seats
                print_seating(2);
                System.out.println();
            }
        }
    }

    public static void cancel_ticket() {/*
                                         * Allows the user to cancel a previously booked seat by specifying the row and
                                         * seat number.
                                         * Prompts the user to enter the row and seat number.
                                         * If the row and seat numbers are valid, and the seat is already booked, the
                                         * seat booking is cancelled and the seat is made available again.
                                         * The corresponding ticket object is also removed from the tickets list.
                                         * If the row and seat numbers are invalid or the seat is already available,
                                         * appropriate error messages are displayed.
                                         */
        Scanner input = new Scanner(System.in);
        int row = 0;
        int seat = 0;
        System.out.print("Enter row number: "); // Get the row and seat number from the user
        row = input.nextInt() - 1;
        if (row < 0 || row >= seats.length) {
            System.out.println(
                    "Sorry, the row number you entered is invalid. Please enter a number between 1 and 3 to select a row and try again.");
            return;
        }
        System.out.print("Enter seat number: ");

        seat = input.nextInt() - 1;
        if (seat < 0 || seat >= seats[row].length) {
            System.out.println(
                    "Sorry, the seat number you entered is invalid. Please enter a valid seat number and try again.");
            return;
        }
        if (seats[row][seat] == 0) {// Check if the seat is already available
            System.out.println("Sorry, the seat you selected is already available. Please choose a different seat.");
            return;
        }
        seats[row][seat] = 0;// Mark the seat as available and remove the corresponding ticket
        for (int i = 0; i < tickets_array.size(); i++) {
            Ticket ticket = tickets_array.get(i);
            if (ticket.row == row && ticket.seat == seat) {
                tickets_array.remove(i);
                System.out.println("Your seat booking has been cancelled successfully.");
                return;
            }
        }
    }

    public static void show_available() {// This method displays the available seats for each row in the seating area
        for (int j = 0; j < 3; j++) {
            System.out.print("Seats available in row " + (j + 1) + ": ");
            for (int i = 0; i < seats[j].length; i++) {
                if (seats[j][i] == 1) {
                    System.out.print("");
                } else {
                    System.out.print(i + 1);
                    if (i == seats[j].length - 1) {
                        System.out.print(".");
                    } else {
                        System.out.print(",");
                    }
                }
            }
            System.out.println();
        }
    }

    public static void save() {
        try {
            FileWriter myWriter = new FileWriter("filename.txt");// referred from w3 schools
            for (int i = 0; i < seats.length; i++) {// Iterate over each row in the seating area
                for (int j = 0; j < seats[i].length; j++) {// Iterate over each seat in the row
                    if (seats[i][j] == 1) {// Write a "1" to the file if the seat is booked, or a "0" if the seat is
                                           // available
                        myWriter.write("1");
                    } else {
                        myWriter.write("0");
                    }
                }
                myWriter.write("\n");// After writing all the seats in the row, write a newline character to move to
                                     // the next line in the file
            }
            myWriter.close(); // Close the FileWriter object to save the changes to the file
            System.out.println("Successfully wrote to the file.");// Print a message to the console indicating that the
                                                                  // writing was successful
        } catch (IOException e) {// If an error occurs while writing to the file, catch the exception and print
                                 // an error message along with a stack trace to the console
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void load() {/*
                                * This method loads the seat reservations from a file named "filename.txt".
                                * The method reads the file and updates the "seats" array to restore the
                                * previous seat reservations.
                                * If the file is not found, the method prints an error message to the console.
                                */
        try {
            File textfile = new File("filename.txt");// Create a new File object that represents a file with the
                                                     // specified pathname ("filename.txt")
            Scanner reader = new Scanner(textfile);// referred from w3 schools
            for (int i = 0; i < seats.length; i++) {// Read the next line of the file
                String line = reader.nextLine();
                for (int j = 0; j < seats[i].length; j++) {// Convert the character at index j of the line to an integer
                                                           // value (0 or 1)
                    int F = Character.getNumericValue(line.codePointAt(j));
                    if (F == 1) {
                        seats[i][j] = 1;// If the integer value is 1, set the corresponding seat in the "seats" array to
                                        // 1 (reserved)
                    } else {
                        seats[i][j] = 0;// If the integer value is 0, set the corresponding seat in the "seats" array to
                                        // 0 (available)
                    }
                }
            }
            reader.close();
            System.out.println("Your data has been successfully restored.");
        } catch (FileNotFoundException e) { // If the file is not found, print an error message to the console
            System.out.println("Sorry, the file you're trying to access could not be found. Try again");
        }
    }

    public static void show_tickets_info() {
        double totalPrice = 0.0;
        System.out.println("_".repeat(50));
        for (Ticket ticket : tickets_array) {
            ticket.print();
            totalPrice += ticket.ticket_price;
            System.out.println();
        }
        System.out.println("_".repeat(50));
        System.out.println("Total price:  £" + totalPrice);

    }

    public static void sort_tickets() {
        double totalPrice = 0.0;
        System.out.println("_".repeat(50));
        List<Ticket> sorted = new ArrayList<>(tickets_array);// Create a new ArrayList and add all the tickets to it
        int no_of_tickets = sorted.size();
        for (int i = 0; i < no_of_tickets - 1; i++) {
            int minimum_index = i;
            for (int j = i + 1; j < no_of_tickets; j++) {
                if (sorted.get(j).getPrice() < sorted.get(minimum_index).getPrice()) {
                    minimum_index = j;
                }
            }
            if (minimum_index != i) {
                Ticket temporary = sorted.get(i);
                sorted.set(i, sorted.get(minimum_index));
                sorted.set(minimum_index, temporary);
            }
        }
        // Print the details of each ticket in the sorted ArrayList, calculate the total
        // price, and print it at the end
        for (Ticket currentticket : sorted) {
            currentticket.print();
            totalPrice += currentticket.ticket_price;
            System.out.println();
        }
        System.out.println("_".repeat(50));
        System.out.println("Total price:  £" + totalPrice);
    }

    public static void print_seating(int i) {// The output of the print_seating method is a visual representation of the
                                             // seating area, with empty seats represented by dots and sold seats
                                             // represented by X's.
        for (int j = 0; j < seats[i].length; j++) {// Loop through each seat in the specified row
            if (j == seats[i].length / 2) {// If the current seat is the middle seat, print "X" if it's sold, "O" if
                                           // it's available
                if (seats[i][j] == 1) {
                    System.out.print(" ");
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                    System.out.print("O");
                }
            } else {// If the current seat is not the middle seat, print "X" if it's sold, "O" if
                    // it's available
                if (seats[i][j] == 0) {
                    System.out.print("O");
                } else {
                    System.out.print("X");
                }
            }
        }
    }
}