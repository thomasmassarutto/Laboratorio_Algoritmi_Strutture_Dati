/*
 * Classe StringGenerator
 * 
 * Questa classe si occupa di generare stringhe casuali secondo le direttive del progetto.
 * La lungehzza n deve essere compresa in un range di valori fra 1000 e 500000,
 * con una distribuzione preferibilmente esponenziale.
 * Ad esempio, n potrebbe essere definito da una funzione esponenziale in i del tipo ⌊A⋅B^i⌋
 * In cui se
 *          i=0  -> n=1000
 *          i=99 -> n=500000
 * 
 * La stringa e' generata casualmente su un alfabeto ternario (a,b,c) in cui le lettere
 * sono generatepseudo casualmente, in maniera indipendente l'una dall'altra.
 */

import java.util.Random;

public class StringGenerator {

    private double A=1.2;
    private double B=1.1;
    private Random random;
    private static final char[] alfabeto = { 'a', 'b', 'c' };

    // Metodo per generare la lunghezza della stringa in base all'esponenziale
    private int generateLength() {
        int i = random.nextInt(100);
        int n = (int) (A * Math.pow(B, i));
        return Math.max(1000,  Math.min(500000, n)); // Assicura che la lunghezza sia nel range specificato
    }

}
