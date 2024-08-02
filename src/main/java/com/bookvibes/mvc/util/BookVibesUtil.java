package com.bookvibes.mvc.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BookVibesUtil {

    public static String getSeparator(int[] lengths) {
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        for (int length : lengths) {
            sb.append(StringUtils.repeat('-', length + 2));
            sb.append("+");
        }
        return sb.toString();
    }

    // se ingresan 2 parametros con las longitudes de las columnas y los valores de cada una de estas
    public static void print(int[] lengths, Object[] values) {

        // se crea una lista para saber cuantas lineas tendrá la fila
        List<Object[]> lines = new ArrayList<>();
        boolean again = Boolean.FALSE;
        do {
            // se crea el arreglo para almacenar los valores de la linea
            Object[] line = new Object[values.length];

            for (int i = 0; i < values.length; i++) {

                // en este bucle verificaremos el tipo de dato del valor
                // y tras almacenarlo completamente, asignaremos el valor a null

                // si el valor es de tipo String procesamos el contenido
                if (values[i] instanceof String) {

                    String word = String.valueOf(values[i]);

                    // si el contenido es nulo o en blanco, no almacenar nada
                    if (StringUtils.isBlank(word)) {
                        line[i] = "";
                        values[i] = null;

                    //
                    } else if (word.length() <= lengths[i]) {
                        line[i] = word;
                        values[i] = null;
                    } else {
                        line[i] = StringUtils.substring(word, 0, lengths[i]);
                        values[i] = StringUtils.substring(word, lengths[i], word.length());
                    }

                // si el valor no es de tipo String, simplemente lo almacenamos
                } else {
                    line[i] = values[i] != null ? values[i] : "";
                    values[i] = null;
                }
            }

            lines.add(line);

            for (Object value : values) {
                if (value != null) {
                    again = Boolean.TRUE;
                    break;
                }
                again = Boolean.FALSE;
            }

        } while(again);

        StringBuilder sbFormat = new StringBuilder("|");
        for (int length : lengths) {
            sbFormat.append(" %-");
            sbFormat.append(length);
            sbFormat.append("s |");
        }
        sbFormat.append("%n");

        for (Object[] lineValues : lines) {
            System.out.printf(sbFormat.toString(), lineValues);
        }
    }

}
