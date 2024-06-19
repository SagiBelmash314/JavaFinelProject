import java.util.Arrays;

public class Buyer {
    private final String name;
    private final String password;
    private final String address ;
    private ShoppingCart shoppingCart;
    private ShoppingCart[] orders;
    private int numOfOrders;

    public Buyer(String name, String password, String address)
    {
        this.name = name;
        this.password = password;
        this.address = address;
        this.shoppingCart = new ShoppingCart(this);
        this.orders = new ShoppingCart[0];
        this.numOfOrders = 0;
    }

    public String getName()
    {
        return name;
    }

    public String getPassword()
    {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public void printItems(){
        for (int i = 1; i < shoppingCart.getProducts().length + 1; i++) {
            System.out.println(i + ") " + shoppingCart.getProducts()[i]);
        }
    }

    public void addItemToCart(Product product){
        shoppingCart.addProduct(product);
    }

    public void checkout() {
        System.out.println("Total price: " + shoppingCart.getTotal() + "ILS");
        shoppingCart.setDate();
        expandList();
        orders[numOfOrders++] = shoppingCart;
        shoppingCart = new ShoppingCart(this);
    }

    public void expandList(){
        if (orders.length == 0) {
            orders = new ShoppingCart[1];
        } else if (orders.length == numOfOrders) {
            orders = Arrays.copyOf(orders, orders.length * 2);
        }
    }

    @Override
     public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n")
                .append("Address: ").append(address).append("\n")
                .append("Current Shopping Cart:\n").append(shoppingCart).append("\n")
                .append("Order History:");

        for(int i = 1; i < numOfOrders + 1; i++){
            sb.append("\nOrder #").append(i).append(":\n").append(orders[i - 1].toString()).append("\n");
        }

        return sb.toString();
    }
}



