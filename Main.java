// Sagi Belmash 324227271
// Yair Vinshststocks 214616781
// Instructor: Pini Shlomi

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static Scanner reader;
    private static Manager manager;


    public static void main(String[] args) {
        run();
    }

    public static void run() {
        reader = new Scanner(System.in);
        manager = new Manager();

        menu();
    }
    public static void menu() {
        int input;

        do {
            System.out.println();
            System.out.println("""
                    Choose one of the following options:
                    0: Exit
                    1: Add seller
                    2: Add buyer
                    3: Add product to seller
                    4: Add product to buyer
                    5: Order payment for buyer
                    6: Show details of all buyers
                    7: Show details of all sellers
                    """);
            input = reader.nextInt();
            switch (input) {
                case 1: // Add seller
                    addSeller();
                    break;
                case 2: // Add buyer
                    addBuyer();
                    break;
                case 3: // Add product to seller
                    addProductToSeller();
                    break;
                case 4: // Add product to buyer
                    addProductBuyer();
                    break;
                case 5: // Order payment for buyer
                    orderPayment();
                    break;
                case 6: // Show details of all buyers
                    printBuyers();
                    break;
                case 7: // Show details of all sellers
                    printSellers();
                    break;
                default:
                    System.out.println("Invalid option, please try again");
            }
        } while (input != 0);
        reader.close();
    }

    private static void addSeller() {
        System.out.println("Please enter the name of the seller or enter \".\" to return to the main menu: ");
        String name;
        do {
            name = reader.next();
            if (name.equals(".")) {
                return;
            }
            if (!manager.isExisting(name)) {
                break;
            }
            System.out.println("Seller is already in the system.\n" +
                    "Please enter a different seller or enter \".\" to return to the main menu:");
        } while (true);
        System.out.println("Please enter a password: ");
        String password = reader.next();
        manager.addSeller(new Seller(name, password));
        System.out.println(name + " got added to the system as a seller.");
    }

    private static void addBuyer() {
        System.out.println("Please enter the name of the buyer or enter \".\" to return to the main menu: ");
        String name;
        do {
            name = reader.next();
            if (name.equals(".")) {
                return;
            }
            if (!manager.isExisting(name)) {
                break;
            }
            System.out.println("Buyer is already in the system.\n" +
                    "Please enter a different buyer or enter \".\" to return to the main menu:");
        } while (true);
        System.out.println("Please enter a password: ");
        String password = reader.next();
        System.out.println("Please enter an address: ");
        String address = reader.next();
        manager.addBuyer(new Buyer(name, password, address));
        System.out.println(name + " got added to the system as a buyer.");
    }

    private static void addProductToSeller() {
        Seller seller = chooseSeller();
        System.out.println("Please enter the name of the product: ");
        String productName = reader.next();
        System.out.println("Please enter the price of the product: ");
        float price = reader.nextFloat();
        seller.addProduct(new Product(productName, price));
    }

    private static void addProductBuyer() {
        Buyer buyer = chooseBuyer();
        Seller seller = chooseSeller();
        seller.printProducts();
        System.out.println("Choose one of the products:");
        String productName;
        do {
            productName = reader.next();
            if (productName.equals(".")) {
                return;
            }
            if (chooseProduct(seller, productName)) {
                break;
            }
            System.out.println("You chose a product that this seller does not offer.\n" +
                    "Please choose another or enter \".\" to return to the main menu.");
        } while (true);
        buyer.addItemToCart(seller.getProductByName(productName));
        System.out.println("The item was added to the buyer's cart.");
    }

    private static void orderPayment() {
        Buyer buyer = chooseBuyer();
        buyer.checkout();
    }

    private static void printBuyers() {
        manager.printBuyers();
    }

    private static void printSellers() {
        manager.printSellers();
    }

    private static Seller chooseSeller() {
        System.out.println("Please enter the name of the seller: ");
        String name;
        do {
            name = reader.next();
            if (manager.sellerExits(name)) {
                break;
            }
            else {
                System.out.println("This seller doesn't exist, please choose another: ");
            }
        } while (true);
        return manager.getSeller(name);
    }

    private static Buyer chooseBuyer() {
        System.out.println("Please enter the name of the buyer: ");
        String name;
        do {
            name = reader.next();
            if (manager.buyerExists(name)) {
                break;
            }
            else {
                System.out.println("This buyer doesn't exist, please choose another: ");
            }
        } while (true);
        return manager.getBuyer(name);
    }

    private static boolean chooseProduct(Seller seller, String productName) {
        for (Product p : seller.getProducts()) {
            if (p.getName().equals(productName)) {
                return true;
            }
        }
        return false;
    }

}
