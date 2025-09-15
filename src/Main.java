import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<SweetBase> sweets = new ArrayList<>();
        sweets.add(new Candy("ChocoCandy", 25, 40, 12, "chocolate"));
        sweets.add(new Chocolate("Dark Choco", 100, 30, 50, "dark"));
        sweets.add(new Cookie("Oreo", 50, 35, 20, true));
        sweets.add(new Marshmallow("Zephyr", 30, 60, 15, "white"));

        for (SweetBase sweet : sweets) {
            System.out.println(sweet + " → " + sweet.getDescription());
        }
    }
}
