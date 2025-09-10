package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Frase {
    private static final Logger mainLogger = LogManager.getLogger("main");

    // Frase original de la API
    private String q;

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

    public String getQ() {
        return q;
    }
}
