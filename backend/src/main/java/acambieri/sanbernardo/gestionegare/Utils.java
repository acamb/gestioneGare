package acambieri.sanbernardo.gestionegare;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author andrea AC
 *         Date: 12/08/2017
 */
public class Utils {
    
    public static String exceptionToString(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
