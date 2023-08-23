/*
 * Classe Test:
 * 
 * Questa classe permette di testare gli algoritmi su Elearning.
 * 
 * Commenta o decommenta le righe fractionalperiod = Algos.period...(s);
 * in base all'algoritmo che vuoi testare.
 * 
 * NB: carica Test come classe principale e Algos.
 */

import java.util.Scanner;
public class Test {

    public static void main(String[] args) {
        int fractionalperiod=0;

        Scanner stringa = new Scanner(System.in);
        String s = stringa.nextLine();

        fractionalperiod = Algos.periodNaive(s);

        //fractionalperiod = Algos.periodSmart(s);

        System.out.println(fractionalperiod);

    }
}
