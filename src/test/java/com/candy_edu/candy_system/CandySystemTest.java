package com.candy_edu.candy_system;

import com.candy_edu.candy.*;
import com.candy_edu.candy_system.CandySystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CandySystemTest {

    private CandySystem system;
    private List<Candy> candies;

    @BeforeEach
    void setUp() {
        system = new CandySystem();
        candies = new ArrayList<>();
        candies.add(new Candy("Roshen", 100, 20.0, 60, CandyType.CHOCOLATE));
        candies.add(new Candy("Korivka", 50, 10.0, 30, CandyType.IRIS));
        candies.add(new Candy("Jelly Bears", 80, 25.0, 70, CandyType.JELLY));
    }

    @Test
    void testFindBySugarRange() {
        List<Candy> result = system.findBySugarRange(candies,40, 65);
        assertEquals(1, result.size());
        assertEquals("Roshen", result.get(0).getName());
    }

    @Test
    void testTotalWeight() {
        int totalWeight = system.getTotalWeight(candies);
        assertEquals(230, totalWeight);
    }

    @Test
    void testTotalPrice() {
        double totalPrice = system.getTotalPrice(candies);
        assertEquals(55.0, totalPrice);
    }
}
