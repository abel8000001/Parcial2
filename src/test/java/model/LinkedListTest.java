package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Test
    @DisplayName("intercambiarReferencias debe intercambiar pares (lista impar)")
    void testIntercambiarReferenciasOdd() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= 5; i++) list.add(i); // 1 2 3 4 5
        list.intercambiarReferencias();
        List<Integer> result = new ArrayList<>();
        for (Integer v : list) result.add(v);
        assertEquals(List.of(2,1,4,3,5), result, "La lista no coincide tras intercambio de referencias");
    }

    @Test
    @DisplayName("intercambiarReferencias debe intercambiar pares (lista par)")
    void testIntercambiarReferenciasEven() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= 4; i++) list.add(i); // 1 2 3 4
        list.intercambiarReferencias();
        List<Integer> result = new ArrayList<>();
        for (Integer v : list) result.add(v);
        assertEquals(List.of(2,1,4,3), result);
    }

    @Test
    @DisplayName("intercambiarReferencias lista vacía no explota")
    void testIntercambiarReferenciasEmpty() {
        LinkedList<Integer> list = new LinkedList<>();
        assertDoesNotThrow(list::intercambiarReferencias);
        assertFalse(list.iterator().hasNext());
    }

    @Test
    @DisplayName("intercambiarReferencias lista de un elemento permanece igual")
    void testIntercambiarReferenciasSingle() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.intercambiarReferencias();
        List<Integer> result = new ArrayList<>();
        for (Integer v : list) result.add(v);
        assertEquals(List.of(10), result);
    }
}
