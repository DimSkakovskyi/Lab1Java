package com.candy_edu.container;

import com.candy_edu.candy.*;
import com.candy_edu.candy_system.CandySystem;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class CandyBox {
    private final List<Candy> candies = new ArrayList<>();
    private final CandySystem system = new CandySystem();

    public CandyBox() {
        candies.add(new Candy("Roshen Black Chocolate", 100, 35.5, CandyType.CHOCOLATE));
        candies.add(new Candy("Korivka", 50, 12.0, CandyType.IRIS));
        candies.add(new Candy("Barbaris", 40, 10.0, CandyType.CARAMEL));
        candies.add(new Candy("Jelly Bears", 80, 25.0, CandyType.JELLY));
        candies.add(new Candy("Zefir Kyivsky", 120, 40.0, CandyType.MARSHMALLOW));

        system.getCandies(candies);
        system.getTotalWeightAndPrice(candies);
        system.sortingByType(candies, CandyType.CHOCOLATE);
        system.findByWeightRange(50, 100, candies);
    }
}

