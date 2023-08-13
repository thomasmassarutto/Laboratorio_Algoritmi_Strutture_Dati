
// periodo frazionario
import java.util.Scanner;

public class progetto {
    public static void main(String[] args) {

        Scanner stringa = new Scanner(System.in);
        String s = stringa.nextLine();

        int res= Algos.periodNaive(s);
        
        System.out.println(res);
    
    }
}
