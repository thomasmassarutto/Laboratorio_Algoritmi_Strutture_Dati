
// periodo frazionario
import java.util.Scanner;

public class progetto {
    public static void main(String[] args) {

        String s = StringGenerator.generateString();

        int res= Algos.periodNaive(s);

        System.out.println(res);

        System.out.println(Chrono.tMin());
    
    }
}
