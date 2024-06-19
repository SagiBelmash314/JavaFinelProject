import java.util.Arrays;

public class Manager {
    Seller[] sellers;
    int numOfSellers;
    Buyer[] buyers;
    int numOfBuyers;

    public Manager() {
        sellers = new Seller[0];
        numOfSellers = 0;
        buyers = new Buyer[0];
        numOfBuyers = 0;
    }
    
    public boolean sellerExits(String name) {
        for (int i = 0; i < numOfSellers; i++) {
            if (sellers[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean buyerExists(String name) {
        for (int i = 0; i < numOfBuyers; i++) {
            if (buyers[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isExisting(String name) {
        return (sellerExits(name) || buyerExists(name));
    }

    public void addSeller(Seller seller) {
        expandSellers();
        sellers[numOfSellers++] = seller;
    }

    public Seller getSeller(String name) {
        for (int i = 0; i < numOfSellers; i++) {
            if (sellers[i].getName().equals(name)) {
                return sellers[i];
            }
        }
        return null;
    }

    public void addBuyer(Buyer buyer) {
        expandBuyers();
        buyers[numOfBuyers++] = buyer;
    }

    public Buyer getBuyer(String name) {
        for (int i = 0; i < numOfBuyers; i++) {
            if (buyers[i].getName().equals(name)) {
                return buyers[i];
            }
        }
        return null;
    }

    public void printBuyers() {
        for (int i = 0; i < numOfBuyers; i++) {
            System.out.println(buyers[i] + "\n");
        }
    }

    public void printSellers() {
        for (int i = 0; i < numOfSellers; i++) {
            System.out.println(sellers[i]);
            sellers[i].printProducts();
            System.out.println();
        }
    }

    public void expandSellers(){
        if (sellers.length == 0) {
            sellers = new Seller[1];
        } else if (sellers.length == numOfSellers) {
            sellers = Arrays.copyOf(sellers, sellers.length * 2);
        }
    }

    public void expandBuyers(){
        if (buyers.length == 0) {
            buyers = new Buyer[1];
        } else if (buyers.length == numOfBuyers) {
            buyers = Arrays.copyOf(buyers, buyers.length * 2);
        }
    }
}
