package obrirurl;

import java.net.*;

import java.io.*;

public class ObrirUrl {

    public static void main(String[] args) {

        URL url = null;

        BufferedReader in;

        String inputLine;

        if (args.length == 2) {

            URL url_completa = new URL(args[0]);

            int port = Integer.valueOf(args[1]);

            url = new URL(url_completa.getProtocol(),
                    url_completa.getHost(), port,
                    url_completa.getFile());

            System.out.println(url.toString());

        } else {

            System.out.println("Java ObrirUrl [url] [port]");

            System.exit(1);

        }

        InputStream inputStream = url.openStream();

        in = new BufferedReader(new InputStreamReader(inputStream));

        while ((inputLine = in.readLine()) != null) {

            System.out.println(inputLine);

        }

        in.close();

    }

}

Tr
