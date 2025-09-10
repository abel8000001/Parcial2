package util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EficienciaEspacialTest {
    @Test
    void testNullObject() {
        String result = EficienciaEspacial.medirPesoObjeto(null);
        assertTrue(result.contains("Objeto null"));
    }

    @Test
    void testSimpleObject() {
        String result = EficienciaEspacial.medirPesoObjeto("cadena");
        assertNotNull(result);
        assertTrue(result.contains("Layout Interno"));
        assertTrue(result.contains("Tamanio total"));
    }
}
