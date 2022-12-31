package m09_pt3_uf3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
/**
 *
 * @author Adrian
 */
public class M09_PT3_UF3 {
    
    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {
try {
            String cadena;
            URL url = new URL(args[0]);
            URLConnection connexio = url.openConnection();

            System.out.println("===============================================================");
            System.out.println("ADREÇA, DATA I CONTINGUT");
            System.out.println("Adreça [getURL]: " + connexio.getURL());

            Date data = new Date(connexio.getLastModified());
            System.out.println("Data última modificació [getLastModified()]: " + data);
            System.out.println("Tipus de Contingut [getContentType()]" + connexio.getContentType());

            System.out.println("===============================================================");
            System.out.println("TOTS ELS CAMPS DE CAPÇALERA AMB getHeaderFields(): ");

            Map campsCapçalera = connexio.getHeaderFields();
            Iterator it = campsCapçalera.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry map = (Map.Entry) it.next();
                System.out.println(map.getKey() + ":" + map.getValue());
            }
                     

            System.out.println("Contingut de [url.getFile()]: " + url.getFile());
            BufferedReader pagina = new BufferedReader(new InputStreamReader(url.openStream()));
            int i = 0;
            
            while ((cadena = pagina.readLine()) != null && i < Integer.parseInt(args[1])) {
                if(cadena.contains(args[2])){
                    System.out.println(cadena);
                }
                i++;
            }          
           

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}
