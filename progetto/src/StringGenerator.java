/*
 * Classe StringGenerator
 * 
 * Questa classe si occupa di generare stringhe casuali secondo le direttive del progetto.
 * La lungehzza n deve essere compresa in un range di valori fra 1000 e 500000,
 * con una distribuzione esponenziale.
 * n (lunghezza della stringa) e' definito da una funzione in i del tipo A⋅B^i
 * In cui: 
 *      i= intero 0-99
 *      A, B= costanti virgola mobile (double) 
 * 
 * inoltre se:
 *          i=0  -> n= 1000
 *          i=99 -> n= 500000
 * 
 * La stringa e' generata casualmente su un alfabeto ternario (a,b,c)
 */

import java.util.Random;

public class StringGenerator {

    private static final double A = 1000;
    private static final double B = 1.0647859778233493;
    private static final char[] alfabeto = { 'a', 'b', 'c' };

    // genera una stringa data una lunghezza
    public static String generateString(int l) {

        String stringa = "";
        Random random = new Random();
        long length = (long) (A * Math.pow(B, l));
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alfabeto.length);
            stringa = stringa + alfabeto[index];
        }

        return stringa;
    }
}
