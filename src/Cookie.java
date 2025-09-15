@SuppressWarnings("unused")
public class Cookie extends SweetBase {
    private final boolean hasFilling;

    public Cookie(String name, double weight, double sugarContent, double price, boolean hasFilling) {
        super(name, weight, sugarContent, price);
        this.hasFilling = hasFilling;
    }

    @Override
    public String getDescription() {
        return "Cookie " + (hasFilling ? "with filling" : "without filling");
    }

    @Override
    public String toString() {
        return super.toString() + (hasFilling ? ", with filling" : ", no filling");
    }
}

