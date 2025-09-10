package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FraseTest {
    @Test
    void testNullConstructor() {
        Frase f = new Frase(null);
        assertEquals("", f.getQ());
    }

    @Test
    void testGetQ() {
        Frase f = new Frase("Texto");
        assertEquals("Texto", f.getQ());
    }
}
