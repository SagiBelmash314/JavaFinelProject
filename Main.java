// Sagi Belmash 324227271
// Yair Vinshststocks 214616781
// Instructor: Pini Shlomi

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static Scanner reader;
    private static int sCount;
    private static int bCount;
    private static String[] sellers;
    private static String[] buyers;


    public static void main(String[] args) {
        run();
    }

    public static void run() {
        sellers = new String[0];
        buyers = new String[0];
        sCount = 0;
        bCount = 0;
        reader = new Scanner(System.in);

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
                case 0:
                    break;
                case 1: // Add seller
                    addSeller();
                    sCount++;
                    break;
                case 2: // Add buyer
                    addBuyer();
                    bCount++;
                    break;
                case 3: // Add product to seller
                    addProductSeller();
                    break;
                case 4: // Add product to buyer
                    addProductBuyer();
                    break;
                case 5: // Order payment for buyer
                    orderPayment();
                    break;
                case 6: // Show details of all buyers
                    printDetails(buyers, bCount);
                    break;
                case 7: // Show details of all sellers
                    printDetails(sellers, sCount);
                    break;
                default:
                    System.out.println("Invalid option, please try again");
                    break;
            }
        } while (input != 0);
        reader.close();
    }

    private static void addProductBuyer() {
        String buyer = chooseBuyer();
        String seller = chooseSeller();
    }

    private static void orderPayment() {
        String buyer = chooseBuyer();
    }

    private static void addSeller() {
        System.out.println("Please enter the name of the seller: ");
        do {
            String sName = reader.next();
            if (sName.equals(".")) {
                return;
            }
            if (inList(sellers, sName) || inList(buyers, sName)) {
                System.out.println("Name is already in the system, please enter another seller or enter \".\" to cancel:");
            }
            else {
                sellers = addToList(sellers, sName, sCount);
                break;
            }
        } while (true);
    }

    private static void addBuyer() {
        System.out.println("Please enter the name of the buyer: ");
        do {
            String bName = reader.next();
            if (bName.equals(".")) {
                return;
            }
            else if (inList(sellers, bName) || inList(buyers, bName)) {
                System.out.println("Name is already in the system, please enter another buyer or enter \".\" to cancel:");
            }
            else {
                buyers = addToList(buyers, bName, bCount);
                break;
            }
        } while (true);
    }

    private static void addProductSeller() {
        String seller = chooseSeller();
        if (seller.equals(".")) {
            return;
        }
        System.out.println("Please enter the name of the product: ");
        String product = reader.next();
        System.out.println("Please enter the price of the product: ");
        float price = reader.nextFloat();
        System.out.println("Please enter the category of the product: ");
        String category = reader.next();
    }

    private static void printDetails(String[] ls, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(ls[i]);
        }

    }

    private static String[] addToList(String[] ls, String name, int count) {
        if (count == 0) {
            ls = Arrays.copyOf(ls, 1);
        }
        else if (count == ls.length){
            ls = Arrays.copyOf(ls, ls.length * 2);
        }
        ls[count] = name;
        return ls;
    }

    private static boolean inList(String[] ls, String name) {
        for (String l : ls) {
            if (l != null && l.equals(name)) {
                return true;
            }
        }
        return false;
    }

    private static String chooseSeller() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Please enter the name of the seller: ");
        String seller;
        do {
            seller = reader.next();
            if (inList(sellers, seller)) {
                break;
            }
            else {
                System.out.println("This seller is not in the list, please choose another or enter \".\" to cancel:");
            }
        } while (true);
        return seller;
    }

    private static String chooseBuyer() {
        System.out.println("Please enter the name of the buyer: ");
        String buyer;
        do {
            buyer = reader.next();
            if (inList(buyers, buyer)) {
                break;
            }
            else {
                System.out.println("This buyer is not in the list, please choose another or enter \".\" to cancel:");
            }
        } while (true);
        return buyer;
    }

}
