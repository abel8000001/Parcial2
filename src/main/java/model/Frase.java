package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Frase {
    private static final Logger mainLogger = LogManager.getLogger("main");

    // Representa la frase original proveniente de la API
    private String q;

    // Constructor que valida la frase recibida desde la API
    // Si la frase es nula o vacía, lanza excepción controlada y asigna valor seguro
    public Frase(String q) {
        try {
            if (q == null || q.trim().isEmpty()) {
                throw new IllegalArgumentException("La frase no puede ser nula o vacía.");
            }
            this.q = q;
            mainLogger.info("Objeto Frase creado correctamente con valor: {}", q);
        } catch (Exception e) {
            System.err.println("Error al crear objeto Frase: " + e.getMessage());
            mainLogger.error("Error al crear objeto Frase: {}", e.getMessage());
            this.q = ""; // valor seguro por defecto
        }
    }

    // Devuelve la frase original
    public String getQ() {
        return q;
    }

    @Override
    public String toString() {
        return "Frase{q='" + q + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Frase)) return false;
        Frase other = (Frase) obj;
        return q.equals(other.q);
    }

}
