package br.akd.svc.akadia.utils;

import java.util.Random;

public class ConversorDeDados {
    ConversorDeDados(){}

    public static final Random RANDOM = new Random();

    public static String converteDataUsParaDataBr(String dataUs) {
        String[] dataSplitada = dataUs.split("-");
        if (dataSplitada.length == 3) {
            return dataSplitada[2] + "/" + dataSplitada[1] + "/" + dataSplitada[0];
        }
        return dataUs;
    }
}
