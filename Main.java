// Sagi Belmash and Yair Vinshststocks

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String[] sellers = new String[0], buyers = new String[0];
        int sCount = 0, bCount = 0, input;
        String seller, buyer;

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
                    System.out.println("Please enter the name of the seller: ");
                    do {
                        String sName = reader.next();
                        if (!inList(sellers, sName)) {
                            sellers = addToList(sellers, sName, sCount);
                            sCount++;
                            break;
                        }
                        else {
                            System.out.println("Seller is already in the list, please enter another seller: ");
                        }
                    } while (true);
                    break;
                case 2: // Add buyer
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
                    break;
                case 3: // Add product to seller
                    seller = chooseSeller(sellers);
                    System.out.println("Please enter the name of the product: ");
                    String product = reader.next();
                    System.out.println("Please enter the price of the product: ");
                    float price = reader.nextFloat();
                    System.out.println("Please enter the category of the product: ");
                    String category = reader.next();
                    break;
                case 4: // Add product to buyer
                    buyer = chooseBuyer(buyers);
                    seller = chooseSeller(sellers);
                    break;
                case 5: // Order payment for buyer
                    buyer = chooseBuyer(buyers);
                    break;
                case 6: // Show details of all buyers
                    for (String b: buyers) {
                        System.out.println(b);
                    }
                    break;
                case 7: // Show details of all sellers
                    for (String s: sellers) {
                        System.out.println(s);
                    }
                    break;
            }
        } while (input != 0);
        reader.close();
    }

    private static String[] addToList(String[] ls, String item, int count) {
        if (count == 0) {
            ls = Arrays.copyOf(ls, 1);
        }
        else {
            ls = Arrays.copyOf(ls, ls.length * 2);
        }
        ls[count] = item;
        return ls;
    }

    private static boolean inList(String[] ls, String item) {
        for (String l : ls) {
            if (l.equals(item)) {
                return true;
            }
        }
        return false;
    }

    private static String chooseSeller(String[] sellers) {
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
        reader.close();
        return seller;
    }

    private static String chooseBuyer(String[] buyers) {
        Scanner reader = new Scanner(System.in);
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
        reader.close();
        return buyer;
    }

}
