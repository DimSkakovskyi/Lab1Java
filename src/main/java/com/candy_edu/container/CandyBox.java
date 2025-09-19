package com.candy_edu.container;

import com.candy_edu.candy.Candy;
import com.candy_edu.candy.CandyType;
import com.candy_edu.candy_system.CandySystem;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class CandyBox {
    private final List<Candy> candies = new ArrayList<>();
    private final CandySystem system = new CandySystem();

    public CandyBox() {
        candies.add(new Candy("Roshen Black Chocolate", 100, 35.5, 60, CandyType.CHOCOLATE));
        candies.add(new Candy("Korivka", 50, 12.0, 30, CandyType.IRIS));
        candies.add(new Candy("Barbaris", 40, 10.0, 25, CandyType.CARAMEL));
        candies.add(new Candy("Jelly Bears", 80, 25.0, 70, CandyType.JELLY));
        candies.add(new Candy("Zefir Kyivsky", 120, 40.0, 50, CandyType.MARSHMALLOW));

        system.getCandies(candies);
        system.getTotalWeight(candies);
        system.getTotalPrice(candies);
        system.sortingByType(candies, CandyType.CHOCOLATE);
        system.findBySugarRange(candies, 30, 60);
    }

    public List<Candy> getCandies() {
        return candies;
    }
}

