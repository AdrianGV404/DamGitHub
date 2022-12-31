package cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {

    public static void main(String[] args) throws Exception {

        
        //BUCLE WHILE!
        int i = 0;
        while (i == 0) {

            //FLUX PER A ENTRADA ESTÀNDARD
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            //Socket client
            DatagramSocket clientSocket = new DatagramSocket();
            byte[] enviats = new byte[1024];
            byte[] rebuts = new byte[1024];

            //DADES DEL SERVIDOR al qual s'envia el missatge
            InetAddress IPServidor = InetAddress.getLocalHost();
            int port = 9800;

            //INTRODUIR DADES PEL TECLAT
            System.out.print("Introdueix missatge: ");
            String cadena = in.readLine();
            enviats = cadena.getBytes();

            //ENVIANT DATAGRAMA AL SERVIDOR
            System.out.println("Enviant " + enviats.length + "bytes al servidor.");
            DatagramPacket enviament = new DatagramPacket(enviats, enviats.length, IPServidor, port);
            clientSocket.send(enviament);

            //REBENT DATAGRAMA DEL SERVIDOR
            DatagramPacket rebut = new DatagramPacket(rebuts, rebuts.length);
            
            //SET TO TIME OUT -> CON TIEMPO 5000 (5 SEGUNDOS) ** TIENE QUE ESTAR SIEMPRE DESPUES DE DATAGRAMPACKET **
            clientSocket.setSoTimeout(5000);
            
            System.out.println("Esperant datagrama...");
            clientSocket.receive(rebut);            
            String majuscula = new String(rebut.getData());

            //ACONSEGUINT INFORMACIÓ DEL DATAGRAMA
            InetAddress IPOrigen = rebut.getAddress();
            int portOrigen = rebut.getPort();
            System.out.println("\tProcedent de: " + IPOrigen + ":" + portOrigen);
            System.out.println("\tDades: " + majuscula.trim());
            //Tanca el socket
            clientSocket.close();

        }
    }
}
