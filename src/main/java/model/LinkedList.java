package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.EficienciaEspacial;
import util.PerformanceMonitor;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
    private static final Logger performanceLogger = LogManager.getLogger("tiempos");
    private Nodo<T> cabeza;
    private int size;

    public LinkedList() {
        cabeza = null;
        size = 0;
    }

    public void add(T valor) {
        Nodo<T> nodoNuevo = new Nodo<>(valor);

        if (cabeza == null) {
            // LinkedList vacio
            cabeza = nodoNuevo;
        } else {
            // Se recorre el LinkedList hasta encontrar la cola
            Nodo<T> temp = cabeza;
            while (temp.getReferencia() != null) {
                temp = temp.getReferencia();
            }
            temp.setReferencia(nodoNuevo);
        }
        size++;
    }

    public void intercambiarReferencias() {
        long start = System.nanoTime();
        if (cabeza == null || cabeza.getReferencia() == null) {
            long end = System.nanoTime();
            performanceLogger.info("intercambiarReferencias duration: {} ms", (end - start) / 1_000_000);
            return;
        }

        Nodo<T> anterior = null;
        Nodo<T> actual = cabeza;

        while (actual != null && actual.getReferencia() != null) {
            Nodo<T> next = actual.getReferencia();
            Nodo<T> nextNext = next.getReferencia();

            if (anterior == null) {
                cabeza = next;
            } else {
                anterior.setReferencia(next);
            }

            next.setReferencia(actual);
            actual.setReferencia(nextNext);

            anterior = actual;
            actual = nextNext;
        }
        long end = System.nanoTime();
        performanceLogger.info("Duracion del proceso intercambiarReferencias: {} ns", (end - start));
    }

    private int indexOf(Nodo<T> target) {
        Nodo<T> temp = cabeza;
        int idx = 0;
        while (temp != null) {
            if (temp == target) {
                return idx;
            }
            temp = temp.getReferencia();
            idx++;
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Nodo<T> current = cabeza;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T value = current.getValor();
            current = current.getReferencia();
            return value;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Nodo<T> temp = cabeza;
        int idx = 0;

        sb.append("LinkedList(size=").append(size).append(")").append(System.lineSeparator());

        while (temp != null) {
            sb.append("Nodo ").append(idx).append(": ");
            sb.append("valor=").append(temp.getValor() == null ? "null" : temp.getValor().toString());
            Nodo<T> ref = temp.getReferencia();
            if (ref == null) {
                sb.append(", referencia=null");
            } else {
                int refIdx = indexOf(ref);
                sb.append(", referencia=").append(refIdx);
                sb.append(" (valor=").append(ref.getValor() == null ? "null" : ref.getValor().toString()).append(")");
            }
            sb.append(System.lineSeparator());
            temp = temp.getReferencia();
            idx++;
        }

        return sb.toString();
    }
}