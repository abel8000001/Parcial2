import model.Encriptador;
import model.Frase;
import model.LinkedList;
import service.ApiManager;
import util.EficienciaEspacial;
import util.PerformanceMonitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Deque;
import java.util.List;

public class Main {
    private static final Logger tiemposLogger = LogManager.getLogger("tiempos");
    private static final Logger performanceLogger = LogManager.getLogger("performance");
    private static final Logger mainLogger = LogManager.getLogger("MainLogger");

    public static void main(String[] args) {
        mainLogger.info("Inicio de la aplicación...");

        long mainStart = System.nanoTime();
        PerformanceMonitor monitor = new PerformanceMonitor("Programa");

        try {
            // Inicia medición de rendimiento
            monitor.inicio();
            mainLogger.info("Se inició el monitor de rendimiento.");

            // --- 1. Consumir API ---
            List<Frase> frasesOriginales = null;
            try {
                frasesOriginales = ApiManager.consumirApi();
                mainLogger.info("Se consumió la API y se recibieron {} frases.", 
                                (frasesOriginales != null ? frasesOriginales.size() : 0));

                if (frasesOriginales == null || frasesOriginales.isEmpty()) {
                    throw new IllegalStateException("La API no devolvió frases válidas.");
                }
            } catch (Exception e) {
                mainLogger.info("Error al consumir la API: {}", e.getMessage());
                performanceLogger.error("Error al consumir API: ", e);
                return; // Se detiene si no hay frases
            }

            // --- 2. Medir eficiencia espacial de frases ---
            try {
                performanceLogger.info("Peso de frasesOriginales: {}", EficienciaEspacial.medirPesoObjeto(frasesOriginales));
                mainLogger.info("Se midió el peso de frasesOriginales.");
            } catch (Exception e) {
                mainLogger.info("Error al medir peso de frasesOriginales: {}", e.getMessage());
                performanceLogger.error("Error al medir peso de frasesOriginales: ", e);
            }

            Deque<LinkedList<Integer>> fraseEncriptada = null;

            // --- 3. Procesar cada frase ---
            for (Frase frase : frasesOriginales) {
                if (frase == null || frase.getQ() == null) {
                    performanceLogger.warn("Se encontró una frase nula, se omite.");
                    continue;
                }

                try {
                    // Mostrar frase original
                    System.out.println("frase original: " + frase.getQ());

                    // Encriptar frase
                    fraseEncriptada = Encriptador.encriptarFrase(frase);

                    // Mostrar frase encriptada
                    System.out.print("frase encriptada: ");
                    if (fraseEncriptada != null) {
                        for (LinkedList<Integer> palabra : fraseEncriptada) {
                            if (palabra == null) continue;
                            for (Integer caracter : palabra) {
                                System.out.print((caracter == null ? "null" : caracter) + "-");
                            }
                            System.out.print(" ");
                        }
                    }

                    // Desencriptar y mostrar resultado
                    System.out.println("\nfrase desencriptada: " +
                            Encriptador.desencriptarFrase(fraseEncriptada).getQ());

                    System.out.println("\nsiguiente frase\n");
                } catch (Exception e) {
                    mainLogger.info("Error procesando frase: {}", e.getMessage());
                    performanceLogger.error("Error procesando frase: " + frase.getQ(), e);
                }
            }

            // --- 4. Medir eficiencia espacial de frase encriptada ---
            try {
                performanceLogger.info("Peso de fraseEncriptada: {}", EficienciaEspacial.medirPesoObjeto(fraseEncriptada));
                mainLogger.info("Se midió el peso de fraseEncriptada.");
            } catch (Exception e) {
                mainLogger.info("Error al medir peso de fraseEncriptada: {}", e.getMessage());
                performanceLogger.error("Error al medir peso de fraseEncriptada: ", e);
            }

            // Finaliza medición de rendimiento
            monitor.finalizado();
            mainLogger.info("El monitor de rendimiento finalizó.");

        } catch (Exception e) {
            mainLogger.info("Error inesperado en Main: {}", e.getMessage());
            tiemposLogger.error("Error inesperado en Main: ", e);
        } finally {
            // Log de duración total del programa
            long mainEnd = System.nanoTime();
            tiemposLogger.info("Duracion del proceso Main: {} ms", (mainEnd - mainStart) / 1_000_000);
            mainLogger.info("Aplicación finalizada.");
        }
    }
}
