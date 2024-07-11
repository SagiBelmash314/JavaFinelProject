public class Product {
    private final String name;
    private final float price;
    private final int serialNum;
    private static int numOfProducts = 0;
    private final Category category;

    public Product(String name, float price, Category category) {
        this.name = name;
        this.price = price;
        this.serialNum = numOfProducts++;
        this.category = category;
    }

    public Product(Product other) {
        this.name = other.name;
        this.price = other.price;
        this.serialNum = other.serialNum;
        this.category = other.category;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price + packagePrice;
    }

    public int getSerialNum() {return serialNum;}

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name);
        sb.append(" (").append(category).append(") [")
                .append(serialNum).append("] ---> ").append(price).append(" ILS");
        return sb.toString();
    }
}
