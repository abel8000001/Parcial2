package model;

import java.util.*;

public class Encriptador {

    public static Deque<LinkedList<Integer>> procesarFrase(Frase fraseOriginal) {
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

        return fraseEncriptada;
    }
}
