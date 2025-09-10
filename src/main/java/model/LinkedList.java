package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
    private static final Logger performanceLogger = LogManager.getLogger("tiempos");
    private static final Logger mainLogger = LogManager.getLogger("main");

    private Nodo<T> cabeza;
    private int size;

    public LinkedList() {
        cabeza = null;
        size = 0;
        mainLogger.info("Nueva LinkedList creada. Tamaño inicial: {}", size);
    }

    // Agrega un nuevo nodo al final de la lista
    public void add(T valor) {
        try {
            if (valor == null) {
                performanceLogger.warn("Intento de insertar un valor nulo en LinkedList.");
                mainLogger.warn("Intento de insertar un valor nulo en LinkedList.");
                return;
            }

            Nodo<T> nodoNuevo = new Nodo<>(valor);

            if (cabeza == null) {
                cabeza = nodoNuevo;
            } else {
                Nodo<T> temp = cabeza;
                while (temp.getReferencia() != null) {
                    temp = temp.getReferencia();
                }
                temp.setReferencia(nodoNuevo);
            }
            size++;
            mainLogger.info("Elemento agregado a LinkedList. Nuevo tamaño: {}", size);
        } catch (Exception e) {
            performanceLogger.error("Error en add(): ", e);
            mainLogger.error("Error en add(): {}", e.getMessage());
        }
    }

    // Invierte referencias de la lista de a pares
    public void intercambiarReferencias() {
        long start = System.nanoTime();
        try {
            if (cabeza == null || cabeza.getReferencia() == null) {
                long end = System.nanoTime();
                performanceLogger.info("intercambiarReferencias duration: {} ms", (end - start) / 1_000_000);
                mainLogger.info("intercambiarReferencias no realizado: lista vacía o con un solo elemento.");
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
            mainLogger.info("intercambiarReferencias ejecutado correctamente.");
        } catch (Exception e) {
            performanceLogger.error("Error en intercambiarReferencias(): ", e);
            mainLogger.error("Error en intercambiarReferencias(): {}", e.getMessage());
        } finally {
            long end = System.nanoTime();
            performanceLogger.info("Duracion del proceso intercambiarReferencias: {} ns", (end - start));
        }
    }

    // Busca el índice de un nodo en la lista
    private int indexOf(Nodo<T> target) {
        try {
            Nodo<T> temp = cabeza;
            int idx = 0;
            while (temp != null) {
                if (temp == target) {
                    return idx;
                }
                temp = temp.getReferencia();
                idx++;
            }
        } catch (Exception e) {
            performanceLogger.error("Error en indexOf(): ", e);
            mainLogger.error("Error en indexOf(): {}", e.getMessage());
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
            try {
                if (current == null) {
                    throw new IllegalStateException("No hay más elementos en la lista.");
                }
                T value = current.getValor();
                current = current.getReferencia();
                return value;
            } catch (Exception e) {
                performanceLogger.error("Error en LinkedListIterator.next(): ", e);
                mainLogger.error("Error en LinkedListIterator.next(): {}", e.getMessage());
                return null;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        try {
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
            mainLogger.info("toString ejecutado correctamente para LinkedList de tamaño {}", size);
        } catch (Exception e) {
            performanceLogger.error("Error en toString(): ", e);
            mainLogger.error("Error en toString(): {}", e.getMessage());
        }
        return sb.toString();
    }
}
