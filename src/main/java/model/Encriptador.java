package model;

import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Encriptador {
    private static final Logger performanceLogger = LogManager.getLogger("tiempos");
    private static final Logger mainLogger = LogManager.getLogger("main");

    // Encripta una frase convirtiendo cada palabra en una lista de caracteres ASCII modificados
    // y almacenándolas en un Deque. El "impar consecutivo" se suma progresivamente.
    public static Deque<LinkedList<Integer>> encriptarFrase(Frase fraseOriginal) {
        Deque<LinkedList<Integer>> fraseEncriptada = new ArrayDeque<>();
        long start = System.nanoTime();

        mainLogger.info("Iniciando encriptación de frase...");

        try {
            if (fraseOriginal == null || fraseOriginal.getQ() == null || fraseOriginal.getQ().trim().isEmpty()) {
                throw new IllegalArgumentException("La frase original no puede ser nula o vacía.");
            }

            // Se separa la frase en palabras y se procesa cada una
            String[] fraseDividida = fraseOriginal.getQ().split(" ");
            for (String s : fraseDividida) {
                if (s == null) continue; 
                LinkedList<Integer> listaCaracteres = new LinkedList<>();
                int imparConsecutivo = 1;

                // Se convierte cada caracter a ASCII y se le suma un impar consecutivo
                for (char caracterPalabra : s.toCharArray()) {
                    int caracterASCII = (int) caracterPalabra;
                    caracterASCII += imparConsecutivo;
                    imparConsecutivo += 2;
                    listaCaracteres.add(caracterASCII);
                }

                // Se invierten referencias en pares para aumentar el nivel de encriptación
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

    // Desencripta una frase recorriendo las listas de enteros en el Deque
    // Se resta el mismo "impar consecutivo" para reconstruir los caracteres originales.
    public static Frase desencriptarFrase(Deque<LinkedList<Integer>> fraseEncriptada) {
        long start = System.nanoTime();
        StringBuilder fraseOriginal = new StringBuilder();

        mainLogger.info("Iniciando desencriptación de frase...");

        try {
            if (fraseEncriptada == null || fraseEncriptada.isEmpty()) {
                throw new IllegalArgumentException("La frase encriptada no puede ser nula o vacía.");
            }

            // Se recorren todas las palabras encriptadas
            for (LinkedList<Integer> listaCaracteres : fraseEncriptada) {
                if (listaCaracteres == null) continue;
                int imparConsecutivo = 1;
                StringBuilder palabra = new StringBuilder();

                try {
                    listaCaracteres.intercambiarReferencias(); // Revertir el intercambio de referencias
                } catch (Exception e) {
                    performanceLogger.error("Error al intercambiar referencias en desencriptarFrase", e);
                }

                // Reconstrucción del caracter original
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
