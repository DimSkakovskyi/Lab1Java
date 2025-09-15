@SuppressWarnings("unused")
public class Chocolate extends SweetBase {
    private final String type;

    public Chocolate(String name, double weight, double sugarContent, double price, String type) {
        super(name, weight, sugarContent, price);
        this.type = type;
    }

    @Override
    public String getDescription() {
        return "Chocolate type: " + type;
    }

    @Override
    public String toString() {
        return super.toString() + ", type: " + type;
    }
}
