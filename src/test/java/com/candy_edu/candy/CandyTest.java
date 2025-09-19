package com.candy_edu.candy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CandyTest {

    @Test
    void testCandyCreation() {
        Candy candy = new Candy("Roshen", 100, 20.0, 50, CandyType.CHOCOLATE);

        assertEquals("Roshen", candy.getName());
        assertEquals(100, candy.getWeight().getGrams());
        assertEquals(20.0, candy.getPrice());
        assertEquals(50, candy.getSugar());
        assertEquals(CandyType.CHOCOLATE, candy.getType());
    }

    @Test
    void testSetSugar() {
        Candy candy = new Candy("Korivka", 50, 10.0, 30, CandyType.IRIS);
        candy.setSugar(40);
        assertEquals(40, candy.getSugar());
    }
}
