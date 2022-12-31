package provajavalibrary1;

import ElsMeysBeans.Comanda;
import ElsMeysBeans.Producte;
import ElsMeysBeans.Venda;
import ElsMeysBeans.BaseDades;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

/**
 *
 * @author Adrian
 */
public class ProvaJavaLibrary1 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String urldb = "jdbc:mysql://localhost/m06";
        String usuari = "Adri";
        String contrasenya = "Adri";
        String driver = "com.mysql.jdbc.Driver";

        BaseDades bd = new BaseDades(urldb, usuari, contrasenya, driver);
        bd.setCrearConnexio();

        if (bd.getCrearConnexio()) {
            System.out.println("CONECTADO");

            System.out.println("===========================");
            System.out.println("Llista inicial de productes");
            VeureProductes(bd);

            System.out.println("============================");
            System.out.println("Es crea Venda de ID 3 amb Quantitat 2");
            CrearVenda(bd, 3, 2);

            System.out.println("==============================");
            System.out.println("Llista de productes despres de crear la venda");
            VeureProductes(bd);

            System.out.println("===============================");
            System.out.println("Llista de vendes");
            VeureVendes(bd);

            System.out.println("===============================");
            System.out.println("Llista de comandes");
            VeureComandes(bd);

            System.out.println("================================");
            System.out.println("Crear producto");
            CrearProductes(bd, new Producte("Casa", 7, 12, 6, 12064));

            System.out.println("================================");
            VeureProductes(bd);
        } else {
            System.out.println("No Connectat");
        }
        bd.tancarConnexio();

    }

    private static void VeureProductes(BaseDades bd) throws SQLException {
        ArrayList<Producte> llista = new ArrayList<Producte>();
        llista = bd.consultaPro("SELECT * FROM PRODUCTE");
        if (llista != null) {
            for (int i = 0; i < llista.size(); i++) {
                Producte p = (Producte) llista.get(i);
                System.out.println("ID=>" + p.getIdproducte() + ": "
                        + p.getDescripcio() + "*Estoc: " + p.getStockactual()
                        + "*PVP: " + p.getPvp() + "*Estoc minim: "
                        + p.getStockminim());
            }
        }
    }

    private static void CrearProductes(BaseDades bd, Producte prod) throws SQLException {
        if (bd.inserirProducte(prod) > 0) {
            System.out.println("Objeto insertado");
        }
    }

    private static void CrearVenda(BaseDades bd, int idproducte, int quantitat) throws SQLException {
        Producte prod = bd.consultarUnProducte(idproducte);
        java.sql.Date dataActual = getCurrentDate();
        if (prod != null) {
            if (bd.actualitzarStock(prod, quantitat, dataActual) > 0) {
                String taula = "VENDA";
                int idvenda = bd.obtenirUltimID(taula);
                Venda ven = new Venda(idvenda, prod.getIdproducte(), dataActual, quantitat);
                if (bd.inserirVenda(ven) > 0) {
                    System.out.println("Venda inserida...");
                }
            } else {
                System.out.println("No es pot fer la venda, no hi ha estoc...");
            }
        } else {
            System.out.println("No hi ha producte");
        }
    }

    private static void VeureComandes(BaseDades bd) throws SQLException {
        ArrayList<Comanda> llista = new ArrayList<Comanda>();
        llista = bd.consultaCom("SELECT * FROM COMANDES");
        if (llista != null) {
            for (int i = 0; i < llista.size(); i++) {
                Comanda c = (Comanda) llista.get(i);
                Producte prod = bd.consultarUnProducte(c.getIdProducte());
                System.out.println("ID Comanda =>" + c.getNumComanda()
                        + "*Producte: " + prod.getDescripcio() + "*Quantitat: "
                        + c.getQuantitat() + "*Data: " + c.getData());
            }
        }
    }

    private static void VeureVendes(BaseDades bd) throws SQLException {
        ArrayList<Venda> llista = new ArrayList<Venda>();
        llista = bd.consultaVen("SELECT * FROM VENDES");
        if (llista != null) {
            for (int i = 0; i < llista.size(); i++) {
                Venda p = (Venda) llista.get(i);
                Producte prod = bd.consultarUnProducte(p.getIdproducte());
                System.out.println("ID Comanda =>" + p.getNumvenda()
                        + "*Producte: " + prod.getDescripcio() + "*Quantitat: "
                        + p.getQuantitat() + "*Data: " + p.getDatavenda());
            }
        }
    }

    private static Date getCurrentDate() {
        java.util.Date avui = new java.util.Date();
        return new java.sql.Date(avui.getTime());
    }

    /* ------------------ACTIVITAT 1------------------
        
        Producte producte = new Producte("Portable MSI USB 3.0", 1, 10, 3, 50);
        Comanda comanda = new Comanda();

        producte.addPropertyChangeListener(comanda);
        producte.setStockactual(2);
        if (comanda.isDemana) {
            System.out.println("Fer comanda en producte: " + producte.getDescripcio());
        } else {
            System.out.println("No és necessari fer la comanda en producte: " + producte.getDescripcio());
        }
     */
 /*
        
        ODB odb = ODBFactory.open("producte_com.BD");

        Producte producte1 = new Producte("Abrigo", 1, 10, 4, 200);
        Producte producte2 = new Producte("Flotador", 2, 53, 12, 10);
        Producte producte3 = new Producte("Bandera", 3, 12, 6, 30);

        odb.store(producte1);
        odb.store(producte2);
        odb.store(producte3);

        odb.close();

        odb = ODBFactory.open("producte_com.BD");
        Objects<Producte> objects = odb.getObjects(Producte.class);
        System.out.println(objects.size() + " Productes: ");

        int i = 1;
        while (objects.hasNext()) {
            Producte pro = objects.next();
            System.out.println((i++) + "\t: " + pro.getDescripcio()
                    + "*Estoc actual: " + pro.getStockactual() + " *PVP: " + pro.getPvp()
                    + "*Minim:  " + pro.getStockminim());
        }
        odb.close();

        Scanner lector = new Scanner(System.in);

        System.out.print("Introdueix ID producte: ");
        int idproducte = lector.nextInt();

        System.out.print("Introdueix quantitat: ");
        int quantitat = lector.nextInt();

        odb = ODBFactory.open("Producte_com.BD");
        IQuery query = new CriteriaQuery(Producte.class,
                Where.equal("idproducte", idproducte));
        Objects<Producte> objectes = odb.getObjects(query);
        try {
            Producte pro = (Producte) objectes.getFirst();
            System.out.println("ID => " + idproducte + ": " + pro.getDescripcio()
                    + "*Estoc ACT: " + pro.getStockactual() + "*PVP: " + pro.getPvp()
                    + "*Estoc MIN: " + pro.getStockminim());
            if (quantitat > 0) {
                java.sql.Date dataActual = getCurrentDate();
                System.out.println("Quantitat a vendre: " + quantitat);
                if (actualitzaEstoc(pro, odb, quantitat)) {
                    int numVenda = obtenirNumVenda(odb);
                    Venda ven
                            = new Venda(numVenda, idproducte, dataActual, quantitat);
                    odb.store(ven);
                    System.out.println("Venda: " + numVenda + " Inserida...");
                } else {
                    System.out.println("Venda no inserida - sense estoc");
                }
            } else {
                System.out.println("La quantitat ha de ser mayor a 0");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No existeix el producte");
        } finally {
            odb.close();
        }
    }

    private static boolean actualitzaEstoc(Producte pro, ODB odb, int quantitat) {
        Comanda comanda = new Comanda();
        java.sql.Date dataActual = getCurrentDate();
        pro.addPropertyChangeListener(comanda);//Afegir oient
        int nouEstoc = pro.getStockactual() - quantitat;//Calcul estoc
        boolean actualitzar = false;
        pro.setStockactual(nouEstoc);
        if (comanda.isDemana()) {
            System.out.println("Fer comanda en producte: "
                    + pro.getDescripcio() + " Quantitat: " + quantitat);
            int numComanda = obtenirNumComanda(odb);
            comanda.setQuantitat(quantitat);
            comanda.setIdProducte(pro.getIdproducte());
            comanda.setNumComanda(numComanda);
            System.out.println("Comanda " + numComanda + " Generat...");
        } else {
            odb.store(pro);//actualitza l'estoc de la base de dades
            System.out.println("Estoc actualitzat");
            actualitzar = true;
        }
        return actualitzar;
    }

    private static int obtenirNumComanda(ODB odb) {
        Values val4 = odb.getValues(new ValuesCriteriaQuery(Comanda.class).max("numComanda", "com_max"));
        ObjectValues ov4 = val4.nextValues();
        BigDecimal maxim = (BigDecimal) ov4.getByAlias("com_max");
        return maxim.intValue() + 1;
    }

    private static Date getCurrentDate() {
        java.util.Date avui = new java.util.Date();
        return new java.sql.Date(avui.getTime());
    }

    private static int obtenirNumVenda(ODB odb) {
        Values val4 = odb.getValues(new ValuesCriteriaQuery(Venda.class).max("numvenda", "ven_max"));
        ObjectValues ov4 = val4.nextValues();
        BigDecimal maxim = (BigDecimal) ov4.getByAlias("ven_max");
        return maxim.intValue() + 1;
    }
     */
}

