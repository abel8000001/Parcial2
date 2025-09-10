package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

class EncriptadorTest {

    @Test
    @DisplayName("Encriptar y luego desencriptar devuelve frase original")
    void testRoundTrip() {
        Frase original = new Frase("hola mundo");
        Deque<LinkedList<Integer>> enc = Encriptador.encriptarFrase(original);
        assertNotNull(enc);
        assertFalse(enc.isEmpty(), "La encriptación no debe dar deque vacío");
        Frase result = Encriptador.desencriptarFrase(enc);
        assertEquals(original.getQ(), result.getQ());
    }

    @Test
    @DisplayName("Encriptar frase vacía devuelve deque vacío")
    void testEmptyPhrase() {
        Frase vacia = new Frase("");
        Deque<LinkedList<Integer>> enc = Encriptador.encriptarFrase(vacia);
        assertNotNull(enc);
        assertTrue(enc.isEmpty());
    }

    @Test
    @DisplayName("Encriptar frase null devuelve deque vacío")
    void testNullPhrase() {
        Deque<LinkedList<Integer>> enc = Encriptador.encriptarFrase(null);
        assertNotNull(enc);
        assertTrue(enc.isEmpty());
    }

    @Test
    @DisplayName("Encriptación aplica swap de primeros dos caracteres")
    void testSwapApplied() {
        Frase original = new Frase("hola");
        Deque<LinkedList<Integer>> enc = Encriptador.encriptarFrase(original);
        assertEquals(1, enc.size());
        LinkedList<Integer> palabra = enc.peekFirst();
        assertNotNull(palabra);
        // Construimos los valores esperados manualmente: h+1, o+3, l+5, a+7 => swap -> o+3, h+1, a+7, l+5
        int h = 'h' + 1; // 105
        int o = 'o' + 3; // 114
        int l = 'l' + 5; // 113
        int a = 'a' + 7; // 104
        int[] esperado = new int[]{o, h, a, l};
        int idx = 0;
        for (Integer valor : palabra) {
            assertEquals(esperado[idx], valor);
            idx++;
        }
        assertEquals(esperado.length, idx);
    }
}
