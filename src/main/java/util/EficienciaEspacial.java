package util;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

public class EficienciaEspacial {

    public static String medirPesoObjeto(Object o) throws Exception {

        try {

            String mensajeCalculo = "";

            mensajeCalculo += "\n|====== Layout Interno ======|\n" + ClassLayout.parseInstance(o).toPrintable();

            mensajeCalculo += "\n|====== Layout con Referencias ======|\n" + GraphLayout.parseInstance(o).toPrintable();

            mensajeCalculo += "\n|====== Tamanio total en memoria ======|\n" + GraphLayout.parseInstance(o).totalSize()
                    + " bytes";

            return mensajeCalculo;

        } catch (Exception e) {
            throw new Exception(e);
        }

    }

}