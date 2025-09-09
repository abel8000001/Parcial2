package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Encriptador {

    public static void procesarFrase(Frase fraseOriginal) {
        String[] fraseDividida = fraseOriginal.getQ().split(" ");
        char caracter;
        int caracterASCII;
        int imparConsecutivo;
        LinkedList<Integer> listaCaracteres;

        // Separa cada palabra en caracteres
        // Cada iteracion es una palabra
        for (int i = 0; i < fraseDividida.length; i++) {
            listaCaracteres = new LinkedList<>();
            imparConsecutivo = 1;

            for (char caracterPalabra : fraseDividida[i].toCharArray()) {
                // Convierte el char a ASCII
                caracterASCII = (int) caracterPalabra;

                // Primer paso de encriptacion (añadir impar consecutivo)
                caracterASCII += imparConsecutivo;
                imparConsecutivo += 2;

                // Añade el caracter al LinkedList
                listaCaracteres.add(caracterASCII);
            }

            for (int j = 0; j < listaCaracteres.getLength(); j++) {

            }
        }
    }
}
