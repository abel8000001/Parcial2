package service;

import model.Frase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApiManagerTest {

    @Test
    @DisplayName("Consumir API no retorna null (puede ser lista vacía)")
    void testConsumirApiNotNull() {
        List<Frase> frases = ApiManager.consumirApi();
        assertNotNull(frases);
    }
}
