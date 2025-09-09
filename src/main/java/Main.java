import model.Encriptador;
import model.Frase;
import model.LinkedList;
import service.ApiManager;

import java.util.Deque;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Frase> frasesOriginales = ApiManager.consumirApi();
        Deque<LinkedList<Integer>> fraseEncriptada;

//        System.out.println(frasesOriginales.getFirst().getQ());

        for (Frase frase : frasesOriginales) {
            System.out.println(frase.getQ());

            System.out.println();

            fraseEncriptada = Encriptador.procesarFrase(frase);

            for (LinkedList<Integer> palabra : fraseEncriptada) {
                System.out.println(palabra);
                System.out.println("\nsiguiente palabra\n");
            }

            System.out.println("\nsiguiente frase\n");
        }


    }
}
