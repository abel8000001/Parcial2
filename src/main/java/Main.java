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
    public static void main(String[] args) throws Exception {
        long mainStart = System.nanoTime();

        PerformanceMonitor monitor = new PerformanceMonitor("Programa");
        monitor.inicio();

        List<Frase> frasesOriginales = ApiManager.consumirApi();
        System.out.println(EficienciaEspacial.medirPesoObjeto(frasesOriginales));
        performanceLogger.info("Peso de frasesOriginales: {}", EficienciaEspacial.medirPesoObjeto(frasesOriginales));

        Deque<LinkedList<Integer>> fraseEncriptada = null;

        for (Frase frase : frasesOriginales) {
            System.out.println("frase original: " + frase.getQ());

            fraseEncriptada = Encriptador.encriptarFrase(frase);

            System.out.print("frase encriptada: ");

            for (LinkedList<Integer> palabra : fraseEncriptada) {
                for (Integer caracter : palabra) {
                    System.out.print(caracter + "-");
                }
                System.out.print(" ");
            }

            System.out.println("\nfrase desencriptada: " + Encriptador.desencriptarFrase(fraseEncriptada).getQ());

            System.out.println("\nsiguiente frase\n");
        }

        EficienciaEspacial.medirPesoObjeto(fraseEncriptada);
        performanceLogger.info("Peso de fraseEncriptada: {}", EficienciaEspacial.medirPesoObjeto(fraseEncriptada));

        monitor.finalizado();
        long mainEnd = System.nanoTime();
        tiemposLogger.info("Duracion del proceso Main: {} ms", (mainEnd - mainStart) / 1_000_000);
    }
}
