/* 
 * Classe Logger:
 * 
 * Questa classe serve a loggare i dati relativi al tempo di eseguzione del programma in un file csv denominato "results.csv".
 * 
 * PUBBLICO:
 * void initializeFile()            :crea un file denominato "results.csv" e inizializza l'intestazione delle colonne
 * void logPerformanceString(...)   :aggiunge una nuova riga al file contentete parametri utili per l'analisi delle performance
 *                      String Algorithm: tipo di algoritmo (PeriodNaive / PeriodSmart)
 *                      long stringlength: lunghezza della stringa su cui viene testato l'algoritmo
 *                      long duration: durata della run in millisecondi
 *                      long fractionalperiod: lunghezza del periodo
 *                      int run: numero della run
 * 
 * Colonne CSV: Timestamp, Run, Algorithm.Type, String.Length, Duration, Fractional.Period,Run
 * Algorithm.Type   : tipo di algoritmo (PeriodNaive / PeriodSmart)
 * String.Length    : lunghezza della stringa su cui viene testato l'algoritmo
 * Duration         : durata della run in millisecondi
 * Fractional.Period: periodo frazionario
 * Run              : numero della run
 * 
*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    public static final String filename = "./progetto/results.csv";
    public static final String colnames = "Algorithm.Type,String.Length,Duration,Fractional.Period,Run";

    // inizializza il file dei risultati
    public static void initializeFile() {

        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                System.out.println("File creato: " + myObj.getName());
            } else {
                System.out.println("Il file esiste gia'");
            }
        } catch (IOException e) {
            System.out.println("Errore");
            e.printStackTrace();
        }

        writeColnames();
    }

    // crea stringa di log
    public static void logPerformanceString(String algorithm, long stringlength, long duration, long fractionalperiod, int run) {

        String logline= "";

        logline=algorithm+","+stringlength+","+duration+","+fractionalperiod+","+run;
        writeLine(logline);
    }

    // aggiunge riga al file
    private static void writeLine(String line) {
        try {
            FileWriter myWriter = new FileWriter(filename, true);
            myWriter.write(line + "\n");
            myWriter.close();
            //System.out.println("Linea aggiunta");
        } catch (IOException e) {
            System.out.println("Errore");
            e.printStackTrace();
        }
    }

    // aggiunge nome colonne al file csv
    private static void writeColnames() {
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(colnames + "\n");
            myWriter.close();
            System.out.println("File inizializzato correttamente");
        } catch (IOException e) {
            System.out.println("Errore");
            e.printStackTrace();
        }
    }

}
