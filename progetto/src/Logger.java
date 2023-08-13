/* 
 * Classe Logger:
 * 
 * Questa classe serve a loggare i dati relativi al tempo di eseguzione del programma in un file csv denominato "results.csv".
 * 
 * PUBBLICO:
 * void initializeFile()            :crea un file denominato "results.csv" e inizializza l'intestazione delle colonne
 * void logPerformanceString(...)   :aggiunge una nuova riga al file contentete parametri utili per l'analisi delle performance
 *                      long run: numero della run
 *                      String Algorithm: tipo di algoritmo (PeriodNaive / PeriodSmart)
 *                      long stringlength: lunghezza della stringa su cui viene testato l'algoritmo
 *                      long duration: durata della run in millisecondi
 * 
 * Colonne CSV: Timestamp, Run, Algorithm.Type, String.Length, Duration
 * Timestamp        : timestamp della run
 * Run              : numero della run
 * Algorithm.Type   : tipo di algoritmo (PeriodNaive / PeriodSmart)
 * String.Length    : lunghezza della stringa su cui viene testato l'algoritmo
 * Duration         : durata della run in millisecondi
 * 
*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    public static final String filename = "./progetto/results.csv";
    public static final String colnames = "Timestamp,Run,Algorithm.Type,String.Length,Duration";

    // inizializza il file: creazione + colonne
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
    public static void logPerformanceString(long run, String Algorithm, long stringlength, long duration) {

        String logline= "";
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());

        logline= timeStamp +","+ run+","+Algorithm+","+stringlength+","+duration;
        writeLine(logline);
    }

    // aggiunge riga
    private static void writeLine(String line) {
        try {
            FileWriter myWriter = new FileWriter(filename, true);
            myWriter.write(line + "\n");
            myWriter.close();
            System.out.println("Linea aggiunta");
        } catch (IOException e) {
            System.out.println("Errore");
            e.printStackTrace();
        }
    }

    // aggiunge intestazione alle colonne
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
