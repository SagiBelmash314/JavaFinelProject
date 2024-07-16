// Sagi Belmash 324227271
// Yair Vinshststocks 214616781
// Instructor: Pini Shlomi

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static Scanner reader;
    private static Manager manager;




    public static void main(String[] args) {
        run();
        reader.close();
    }

    public static void run() {
        reader = new Scanner(System.in);
        manager = new Manager();

//        TESTING
        manager.addBuyer(new Buyer("Bob", "123", new Address("Here", 1, "There", "Somewhere")));
        manager.addSeller(new Seller("Sagi", "123"));
        manager.addSeller(new Seller("Yair", "123"));
        manager.getSeller("Sagi").addProduct(new Product("Soap", 10, Category.ELECTRIC, 25));
        manager.getSeller("Yair").addProduct(new Product("Shampoo", 5, Category.CHILD, 0));
        manager.getBuyer("Bob").addItemToCart(manager.getSeller("Sagi").getProductByName("Soap"));

//        END OF TESTING

        menu();
    }
    public static void menu() throws InputMismatchException{
        int input = 0;

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
                    8: Show every product of a specific category
                    9: Create new cart for buyer from previous cart
                    """);
            boolean acceptableAnswer = false;
            while (!acceptableAnswer) {
                try {
                    input = reader.nextInt();
                    acceptableAnswer = true;
                }
                catch (InputMismatchException e) {
                    System.out.println("Input must be an integer, please enter an answer with the correct type:");
                    reader.nextLine();
                }
            }
            switch (input) {
                case 0:
                    System.out.println("Goodbye!");
                    break;
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
                case 8:
                    printByCategory();
                    break;
                case 9:
                    createCartFromHistory();
                    break;
                default:
                    System.out.println("Invalid option, please try again");
            }
        } while (input != 0);
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
        System.out.println("Please enter buyer's street name: ");
        String street = reader.next();
        System.out.println("Please enter buyer's building number: ");
        boolean acceptableAnswer = false;
        while (!acceptableAnswer) {
            try {
                int building = reader.nextInt();
                System.out.println("Please enter buyer's city: ");
                acceptableAnswer = true;
                String city = reader.next();
                System.out.println("Please enter buyer's country: ");
                String country = reader.next();
                manager.addBuyer(new Buyer(name, password, new Address(street, building, city, country)));
                System.out.println(name + " got added to the system as a buyer.");
                manager.sort();
            } catch (InputMismatchException e) {
                System.out.println("Input must be an integer, please enter an answer with the correct type:");
                reader.nextLine();
            }
        }
    }

    private static void addProductToSeller() {
        Seller seller = chooseSeller();
        System.out.println("Please enter the name of the product: ");
        String productName = reader.next();
        while (manager.productExists(seller, productName)) {
            System.out.println("This product is already in the sellers list. Please choose another:");
            productName = reader.next();
        }
        System.out.println("Please enter the price of the product: ");
        boolean acceptableAnswer = false;
        float price = 0;
        while (!acceptableAnswer) {
            try {
                price = reader.nextFloat();
                acceptableAnswer = true;
            } catch (InputMismatchException e) {
                System.out.println("Input must be a number, please enter an answer with the correct type:");
                reader.nextLine();
            }
        }
        Category category = selectCategory();
        System.out.println("Please enter the price of the package (if there is no special package enter 0):");
        acceptableAnswer = false;
        while (!acceptableAnswer) {
            try {
                float packagePrice = reader.nextFloat();
                seller.addProduct(new Product(productName, price, category, packagePrice));
                manager.sort();
                acceptableAnswer = true;
            } catch (InputMismatchException e) {
                System.out.println("Input must be a number, please enter an answer with the correct type:");
                reader.nextLine();
            }
        }
    }


    private static void addProductBuyer() {
        Buyer buyer = chooseBuyer();
        Seller seller = chooseSeller();
        seller.printProducts();
        System.out.println("Please enter the name of the desired product:");
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
        buyer.purchase();
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
            if (manager.sellerExists(name)) {
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

    public static Category selectCategory() {
        System.out.println("Select a category:");
        for (Category category : Category.values()) {
            System.out.println((category.ordinal() + 1) + ") " + category);
        }
        int categoryIndex = 0;
        boolean acceptableAnswer = false;
        while (!acceptableAnswer) {
            try {
                categoryIndex = reader.nextInt();
                while (categoryIndex < 1 || categoryIndex > 4) {
                    System.out.println("The input should be between 1-4, please select again:");
                    categoryIndex = reader.nextInt();
                }
                acceptableAnswer = true;
            }
            catch (InputMismatchException e) {
                System.out.println("Input must be an integer, please enter an answer with the correct type:");
                reader.nextLine();
            }
        }
        return Category.values()[categoryIndex - 1];
    }

    private static void printByCategory() {
        Product[] products = manager.getItemsByCategory(selectCategory());
        for (int i = 1; i < products.length + 1; i++) {
            System.out.println(i + ") " + products[i - 1].getName());
        }
    }

    private static void createCartFromHistory() {
        Buyer buyer;
        buyer = chooseBuyer();
        while (!buyer.hasPrevOrders()) {
            System.out.println("This buyer doesn't have previous orders, do you want to choose another buyer? (Y/N)");
            String answer = reader.next();
            while (!answer.equals("Y") && !answer.equals("N")) {
                System.out.println("Please enter \"Y\" or \"N\":");
                answer = reader.next();
            }
            if (answer.equals("N")) {
                return;
            }
            buyer = chooseBuyer();
        }
        if (!buyer.getShoppingCart().isEmpty()) {
            System.out.println("The selected buyer already has a cart, do you wish to replace it with a previous order? (Y/N)");
            String answer = reader.next();
            while (!answer.equals("Y") && !answer.equals("N")) {
                System.out.println("Please enter \"Y\" or \"N\":");
                answer = reader.next();
            }
            if (answer.equals("N")) {
                return;
            }
        }
        int numOfOrders = buyer.printOrderHistory();
        System.out.println("Please select an order to be the buyer's new cart, if you wish to cancel enter \"0\":");
        boolean acceptableAnswer = false;
        while (!acceptableAnswer) {
            try {
                int orderNum = reader.nextInt();
                while (orderNum < 0 || orderNum > numOfOrders) {
                    System.out.println("This option is not available, please choose from the options above:");
                    orderNum = reader.nextInt();
                }
                if (orderNum == 0) {
                    return;
                }
                buyer.setCart(buyer.getPrevOrder(orderNum - 1));
                System.out.println("Buyer's cart has been updated");
                acceptableAnswer = true;
            }
            catch (InputMismatchException e) {
                System.out.println("Input must be an integer, please enter an answer with the correct type:");
                reader.nextLine();
            }
        }
    }
}



