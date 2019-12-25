package contest.model;

import contest.ContestException;

import java.util.List;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Nov 24, 2011
 * Time: 2:13:37 AM
 */
public class ContestUtils {
    public static void salveazaClasament(String numefis,List<Participant> parts) throws ContestException {
        PrintWriter pw=null;
        try{
            pw=new PrintWriter(numefis);
            String linie=getLinie('-',60);
            pw.println(linie);
            pw.printf("|%5s | %-30s | %7s | %7s |%n", "ID", "Nume si prenume ", "Probe", "Punctaj");
            pw.println(linie);
            for(Participant p:parts){
                pw.printf("|%5s | %-30s | %7d | %7d |%n", p.getID(), p.getNume(), p.getNrProbe(), p.getPunctaj());
                pw.println(linie);
            }
        } catch (FileNotFoundException e) {
            throw new ContestException("NU se poate salva clasamentul"+e);
        }finally {
            if(pw!=null){
                pw.close();
            }
        }
    }

    private static String getLinie(char c, int len) {
       StringBuilder sb=new StringBuilder();
        for(int i=0;i<=len;i++)
            sb.append('-');
        return sb.toString();

    }
}
