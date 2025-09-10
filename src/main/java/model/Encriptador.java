package model;

import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Encriptador {
    private static final Logger performanceLogger = LogManager.getLogger("tiempos");

    public static Deque<LinkedList<Integer>> encriptarFrase(Frase fraseOriginal) {
        long start = System.nanoTime();
        String[] fraseDividida = fraseOriginal.getQ().split(" ");
        int caracterASCII;
        int imparConsecutivo;
        LinkedList<Integer> listaCaracteres;
        Deque<LinkedList<Integer>> fraseEncriptada = new ArrayDeque<>();

        // Separa cada palabra en caracteres
        // Cada iteracion es una palabra
        for (String s : fraseDividida) {
            listaCaracteres = new LinkedList<>();
            imparConsecutivo = 1;

            for (char caracterPalabra : s.toCharArray()) {
                // Convierte el char a ASCII
                caracterASCII = (int) caracterPalabra;

                // Primer paso de encriptacion (añadir impar consecutivo)
                caracterASCII += imparConsecutivo;
                imparConsecutivo += 2;

                // Añade el caracter al LinkedList
                listaCaracteres.add(caracterASCII);
            }

            listaCaracteres.intercambiarReferencias();

            fraseEncriptada.add(listaCaracteres);
        }
        long end = System.nanoTime();
        performanceLogger.info("Duracion de encriptarFrase: {} ns", (end - start));
        return fraseEncriptada;
    }

    public static Frase desencriptarFrase(Deque<LinkedList<Integer>> fraseEncriptada) {
        long start = System.nanoTime();
        StringBuilder fraseOriginal = new StringBuilder();
        int imparConsecutivo;
        StringBuilder palabra;
        int caracterASCII;

        for (LinkedList<Integer> listaCaracteres : fraseEncriptada) {
            imparConsecutivo = 1;
            palabra = new StringBuilder();
            listaCaracteres.intercambiarReferencias();

            for (int caracterEncriptado : listaCaracteres) {
                caracterASCII = caracterEncriptado - imparConsecutivo;
                palabra.append((char) caracterASCII);
                imparConsecutivo += 2;
            }

            if (!fraseOriginal.isEmpty()) {
                fraseOriginal.append(" ");
            }
            fraseOriginal.append(palabra);
        }
        long end = System.nanoTime();
        performanceLogger.info("Duracion de desencriptarFrase: {} ns", (end - start));
        return new Frase(fraseOriginal.toString());
    }
}
