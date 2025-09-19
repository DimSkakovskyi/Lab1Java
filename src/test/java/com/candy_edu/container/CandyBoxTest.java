package com.candy_edu.container;

import com.candy_edu.candy.*;
import com.candy_edu.container.CandyBox;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CandyBoxTest {

    @Test
    void testCandyBoxInitialization() {
        CandyBox box = new CandyBox();
        assertNotNull(box.getCandies());
        assertTrue(box.getCandies().size() > 0);
    }
}

