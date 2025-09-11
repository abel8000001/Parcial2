package util;

import model.Encriptador;
import model.Frase;
import model.LinkedList;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BenchmarkEncriptacion {

    // Genera una frase aleatoria de tamaño n
    private static String generarFrase(int n) {
        StringBuilder sb = new StringBuilder(n);
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        for (int i = 0; i < n; i++) {
            // Genera letras minúsculas aleatorias
            char c = (char) (rand.nextInt(26) + 'a');
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] tamanos = {100, 250, 500, 750, 1000, 2500, 5000, 7500, 10000}; // tamaños de frases
        int repeticiones = 10; // número de frases a procesar en cada prueba

        for (int n : tamanos) {
            // Generar las frases de tamaño n
            List<Frase> frases = new ArrayList<>();
            for (int i = 0; i < repeticiones; i++) {
                frases.add(new Frase(generarFrase(n)));
            }

            // Medir tiempo de encriptar + desencriptar
            long start = System.nanoTime();

            for (Frase f : frases) {
                Deque<LinkedList<Integer>> encriptada = Encriptador.encriptarFrase(f);
                Frase desencriptada = Encriptador.desencriptarFrase(encriptada);
                // opcional: validar que f.equals(desencriptada)
            }

            long end = System.nanoTime();
            long duracion = (end - start) / 1_000_000; // en milisegundos

            System.out.printf("Tamaño: %d, Total caracteres: %d, Tiempo: %d ms%n",
                    n, n * repeticiones, duracion);
        }
    }
}