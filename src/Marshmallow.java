@SuppressWarnings("unused")
public class Marshmallow extends SweetBase {
    private final String color;

    public Marshmallow(String name, double weight, double sugarContent, double price, String color) {
        super(name, weight, sugarContent, price);
        this.color = color;
    }

    @Override
    public String getDescription() {
        return "Marshmallow of color: " + color;
    }

    @Override
    public String toString() {
        return super.toString() + ", color: " + color;
    }
}

