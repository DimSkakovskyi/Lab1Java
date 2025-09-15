abstract public class SweetBase {
    private final String name;
    private final double weight;
    private final double sugarContent;
    private final double price;

    @SuppressWarnings("unused")
    public SweetBase(String name, double weight, double sugarContent, double price) {
        this.name = name;
        this.weight = weight;
        this.sugarContent = sugarContent;
        this.price = price;
    }

    @SuppressWarnings("unused")
    public double getWeight() { return weight; }
    @SuppressWarnings("unused")
    public double getSugarContent() { return sugarContent; }
    @SuppressWarnings("unused")
    public double getPrice() { return price; }
    @SuppressWarnings("unused")
    public String getName() { return name; }

    @SuppressWarnings("unused")
    public abstract String getDescription();

    @Override
    public String toString() {
        return name + " (" + weight + "g, " + sugarContent + "% sugar, " + price + " UAH)";
    }
}
