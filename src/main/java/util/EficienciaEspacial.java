package util;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EficienciaEspacial {
    private static final Logger logger = LogManager.getLogger("memoria");
    private static final Logger mainLogger = LogManager.getLogger("main");

    public static String medirPesoObjeto(Object o) {
        if (o == null) {
            mainLogger.warn("Intento de medir objeto null");
            return "Objeto null - no se puede medir";
        }
        try {
            mainLogger.debug("Iniciando medición de peso de objeto tipo={}", o.getClass().getName());
            StringBuilder mensajeCalculo = new StringBuilder();
            mensajeCalculo.append("\n|====== Layout Interno ======|\n").append(ClassLayout.parseInstance(o).toPrintable());
            mensajeCalculo.append("\n|====== Layout con Referencias ======|\n").append(GraphLayout.parseInstance(o).toPrintable());
            mensajeCalculo.append("\n|====== Tamanio total en memoria ======|\n").append(GraphLayout.parseInstance(o).totalSize()).append(" bytes");
            mainLogger.debug("Medición completada exitosamente");
            return mensajeCalculo.toString();
        } catch (Exception e) {
            logger.error("Error midiendo peso de objeto", e);
            mainLogger.error("Fallo en medición de objeto", e);
            return "Error midiendo peso de objeto: " + e.getMessage();
        }
    }
}