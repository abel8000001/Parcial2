import model.Encriptador;
import model.Frase;
import model.LinkedList;
import service.ApiManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Frase> frasesOriginales = ApiManager.consumirApi();

        for (Frase frase : frasesOriginales) {
            System.out.println(frase.getQ());
        }

        System.out.println("\n\n\n");

        Encriptador.procesarFrase(frasesOriginales.getFirst());
    }
}
