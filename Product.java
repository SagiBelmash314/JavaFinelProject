public class Product {
    private final String name;
    private final float price;

    public Product(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public Product(Product other) {
        this.name = other.name;
        this.price = other.price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " ---> " + price + " ILS";
    }
}
