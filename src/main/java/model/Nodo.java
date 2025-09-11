package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Clase Nodo genérica que representa cada elemento de la lista enlazada
// Cada nodo guarda un valor y una referencia al siguiente nodo
public class Nodo<T> {
    private static final Logger mainLogger = LogManager.getLogger("main");

    private T valor;              // Dato almacenado en el nodo
    private Nodo<T> referencia;   // Apuntador al siguiente nodo

    // Constructor: crea un nodo con un valor
    // Valida que el valor no sea nulo (para evitar nodos vacíos inválidos)
    public Nodo(T valor) {
        try {
            if (valor == null) {
                throw new IllegalArgumentException("El valor de un nodo no puede ser nulo.");
            }
            this.valor = valor;
            this.referencia = null;
            mainLogger.info("Nodo creado correctamente con valor: {}", valor);
        } catch (Exception e) {
            System.err.println("Error al crear Nodo: " + e.getMessage());
            mainLogger.error("Error al crear Nodo: {}", e.getMessage());
            this.valor = null; // fallback seguro
            this.referencia = null;
        }
    }

    // Devuelve el valor almacenado en el nodo
    public T getValor() {
        return valor;
    }

    // Devuelve el siguiente nodo referenciado
    public Nodo<T> getReferencia() {
        return referencia;
    }

    // Asigna el siguiente nodo en la lista
    // Se previene que un nodo se apunte a sí mismo (ciclo infinito)
    public void setReferencia(Nodo<T> referencia) {
        try {
            if (referencia == this) {
                throw new IllegalArgumentException("Un nodo no puede referenciarse a sí mismo.");
            }
            this.referencia = referencia;
            mainLogger.info("Referencia establecida correctamente en Nodo con valor: {}", valor);
        } catch (Exception e) {
            System.err.println("Error en setReferencia: " + e.getMessage());
            mainLogger.error("Error en setReferencia: {}", e.getMessage());
            this.referencia = null; // fallback seguro
        }
    }

    @Override
    public String toString() {
        return "Nodo{valor=" + valor + ", referencia=" + (referencia != null ? referencia.valor : "null") + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Nodo)) return false;
        Nodo<?> other = (Nodo<?>) obj;
        return (valor != null ? valor.equals(other.valor) : other.valor == null);
    }
}
