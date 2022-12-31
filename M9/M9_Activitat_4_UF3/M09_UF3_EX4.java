package m09_uf3_ex4;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class M09_UF3_EX4 {
    
    public static void main (String[] args) throws Exception {
		
		//El servidor escolta pel port 9800
		DatagramSocket serverSocket = new DatagramSocket(9800);
		byte[] enviats = new byte[1024];
		byte[] rebuts = new byte[1024];
		String cadena;
		
		while (true) {
			System.out.println("Esperant datagrama... ");
			//REBUT DATAGRAMA
			rebuts = new byte[1024];
			DatagramPacket paqRebuts = new DatagramPacket(rebuts, rebuts.length);
			serverSocket.receive(paqRebuts);
			cadena = new String(paqRebuts.getData());
			
			//ADREÇA ORIGEN
			InetAddress IPOrigen = paqRebuts.getAddress();
			int port = paqRebuts.getPort();
			System.out.println("\tOrigen: "+IPOrigen+":"+port);
			System.out.println("\tMissatge rebut: "+cadena.trim());
			
			//CONVERTIR CADENA A MAJÚSCULA
			String majuscula = cadena.trim().toUpperCase();
			enviats = majuscula.getBytes();
			
			//ENVIAMENT DATAGRAMA AL CLIENT
			DatagramPacket paqEnviat = new DatagramPacket(enviats, enviats.length, IPOrigen, port);
			serverSocket.send(paqEnviat);
			
			//Per acabar
			if (cadena.trim().equals("*")) break;
			
		}
		
		serverSocket.close();
		
	}
//nmap -p 9800 -sU -PO 127.0.0.1		
	
    
}
