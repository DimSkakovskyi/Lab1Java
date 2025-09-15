@SuppressWarnings("unused")
public class Candy extends SweetBase {
    private final String flavor;

    public Candy(String name, double weight, double sugarContent, double price, String flavor) {
        super(name, weight, sugarContent, price);
        this.flavor = flavor;
    }

    @Override
    public String getDescription() {
        return "Candy with flavor: " + flavor;
    }

    @Override
    public String toString() {
        return super.toString() + ", flavor: " + flavor;
    }
}
