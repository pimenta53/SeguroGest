
/**
 * Write a description of class ClaComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Comparator;
import java.io.*;

public class ClaComparator implements Comparator<Clausula>, Serializable
{
    public int compare(Clausula cl1, Clausula cl2) {
        return cl1.getCod().compareTo(cl2.getCod());
    }
}
