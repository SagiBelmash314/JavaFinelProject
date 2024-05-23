// Sagi Belmash and Yair Vinshststocks

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static Scanner reader;
    private static int sCount;
    private static int bCount;
    private static String[] sellers;
    private static String[] buyers;

    public static void main(String[] args) {
        sellers = new String[0];
        buyers = new String[0];
        sCount = 0;
        bCount = 0;
        int input;

        reader = new Scanner(System.in);

        do {
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
                    sellers = addSeller();
                    sCount++;
                    break;
                case 2: // Add buyer
                    buyers = addBuyer();
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
                    printDetails(buyers);
                    break;
                case 7: // Show details of all sellers
                    printDetails(sellers);
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

    private static String[] addSeller() {
        System.out.println("Please enter the name of the seller: ");
        do {
            String sName = reader.next();
            if (!inList(sellers, sName)) {
                sellers = addToList(sellers, sName, sCount);
                break;
            }
            else {
                System.out.println("Seller is already in the list, please enter another seller: ");
            }
        } while (true);
        return sellers;
    }

    private static String[] addBuyer() {
        System.out.println("Please enter the name of the buyer: ");
        do {
            String bName = reader.next();
            if (inList(buyers, bName)) {
                System.out.println("Buyer is already in the list, please enter another buyer: ");
            }
            else {
                buyers = addToList(buyers, bName, bCount);
                bCount++;
                break;
            }
        } while (true);
        return buyers;
    }

    private static void addProductSeller() {
        String seller = chooseSeller();
        System.out.println("Please enter the name of the product: ");
        String product = reader.next();
        System.out.println("Please enter the price of the product: ");
        float price = reader.nextFloat();
        System.out.println("Please enter the category of the product: ");
        String category = reader.next();
    }

    private static void printDetails(String[] ls) {
        for (String i: ls) {
            System.out.println(i);
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
                System.out.println("This seller is not in the list, please choose another: ");
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
                System.out.println("This buyer is not in the list, please choose another: ");
            }
        } while (true);
        return buyer;
    }

}
