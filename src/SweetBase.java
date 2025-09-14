public class SweetBase {
    private String name;
    private double weight;
    private double sugarContent;
    private double price;

    public SweetBase(String name, double weight, double sugarContent, double price) {
        this.name = name;
        this.weight = weight;
        this.sugarContent = sugarContent;
        this.price = price;
    }

    public double getWeight() { return weight; }
    public double getSugarContent() { return sugarContent; }
    public double getPrice() { return price; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return name + " (" + weight + "g, " + sugarContent + "% sugar, " + price + " UAH)";
    }
}
