package model;

import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Encriptador {
    private static final Logger performanceLogger = LogManager.getLogger("tiempos");
    private static final Logger mainLogger = LogManager.getLogger("main");

    // Encripta una frase en una estructura de Deque<LinkedList<Integer>>
    public static Deque<LinkedList<Integer>> encriptarFrase(Frase fraseOriginal) {
        Deque<LinkedList<Integer>> fraseEncriptada = new ArrayDeque<>();
        long start = System.nanoTime();

        mainLogger.info("Iniciando encriptación de frase...");

        try {
            if (fraseOriginal == null || fraseOriginal.getQ() == null || fraseOriginal.getQ().trim().isEmpty()) {
                throw new IllegalArgumentException("La frase original no puede ser nula o vacía.");
            }

            String[] fraseDividida = fraseOriginal.getQ().split(" ");
            for (String s : fraseDividida) {
                if (s == null) continue; // Evita palabras nulas
                LinkedList<Integer> listaCaracteres = new LinkedList<>();
                int imparConsecutivo = 1;

                for (char caracterPalabra : s.toCharArray()) {
                    int caracterASCII = (int) caracterPalabra;

                    // Primer paso de encriptación (añadir impar consecutivo)
                    caracterASCII += imparConsecutivo;
                    imparConsecutivo += 2;

                    listaCaracteres.add(caracterASCII);
                }

                try {
                    listaCaracteres.intercambiarReferencias();
                } catch (Exception e) {
                    performanceLogger.error("Error al intercambiar referencias en encriptarFrase", e);
                }

                fraseEncriptada.add(listaCaracteres);
            }

            mainLogger.info("Frase encriptada correctamente.");
        } catch (Exception e) {
            performanceLogger.error("Error en encriptarFrase: ", e);
            mainLogger.error("Error durante la encriptación de frase.", e);
        } finally {
            long end = System.nanoTime();
            performanceLogger.info("Duracion de encriptarFrase: {} ns", (end - start));
        }

        return fraseEncriptada;
    }

    // Desencripta la frase desde una estructura de Deque<LinkedList<Integer>>
    public static Frase desencriptarFrase(Deque<LinkedList<Integer>> fraseEncriptada) {
        long start = System.nanoTime();
        StringBuilder fraseOriginal = new StringBuilder();

        mainLogger.info("Iniciando desencriptación de frase...");

        try {
            if (fraseEncriptada == null || fraseEncriptada.isEmpty()) {
                throw new IllegalArgumentException("La frase encriptada no puede ser nula o vacía.");
            }

            for (LinkedList<Integer> listaCaracteres : fraseEncriptada) {
                if (listaCaracteres == null) continue;
                int imparConsecutivo = 1;
                StringBuilder palabra = new StringBuilder();

                try {
                    listaCaracteres.intercambiarReferencias();
                } catch (Exception e) {
                    performanceLogger.error("Error al intercambiar referencias en desencriptarFrase", e);
                }

                for (int caracterEncriptado : listaCaracteres) {
                    int caracterASCII = caracterEncriptado - imparConsecutivo;
                    palabra.append((char) caracterASCII);
                    imparConsecutivo += 2;
                }

                if (!fraseOriginal.isEmpty()) {
                    fraseOriginal.append(" ");
                }
                fraseOriginal.append(palabra);
            }

            mainLogger.info("Frase desencriptada correctamente.");
        } catch (Exception e) {
            performanceLogger.error("Error en desencriptarFrase: ", e);
            mainLogger.error("Error durante la desencriptación de frase.", e);
        } finally {
            long end = System.nanoTime();
            performanceLogger.info("Duracion de desencriptarFrase: {} ns", (end - start));
        }

        return new Frase(fraseOriginal.toString());
    }
}
