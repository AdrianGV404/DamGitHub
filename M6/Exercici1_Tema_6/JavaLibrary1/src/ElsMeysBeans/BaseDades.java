package ElsMeysBeans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Adrian
 */

public class BaseDades {

    private static Connection connexio;
    private String urldb;
    private String usuari;
    private String contrasenya;
    private String driver;
    private boolean crearConnexio;

    public BaseDades() {
    }

    public BaseDades(String urldb, String usuari, String contrasenya, String driver) {
        this.urldb = urldb;
        this.usuari = usuari;
        this.contrasenya = contrasenya;
        this.driver = driver;
    }

    public void setCrearConnexio() throws ClassNotFoundException {
        crearConnexio = false;
        try {
            Class.forName(driver);
            connexio = DriverManager.getConnection(urldb, usuari, contrasenya);
            crearConnexio = true;
        } catch (Exception e) {
            System.out.println("Problema a la connexió...");
            e.printStackTrace();
        }
    }

    public boolean getCrearConnexio() {
        return crearConnexio;
    }

    public Connection getConnexio() {
        return connexio;
    }
    
    public void tancarConnexio(){
        try{
            connexio.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
 
        
    public ArrayList<Producte> consultaPro(String consulta) throws SQLException{
        ArrayList<Producte> llista = new ArrayList<Producte>();
        try{
            Statement sentencia = getConnexio().createStatement();
            ResultSet resul = sentencia.executeQuery(consulta);
            
            while (resul.next()) {
                Producte p = new Producte(resul.getString(1), resul.getInt(2),
                        resul.getInt(3), resul.getInt(4), resul.getInt(5));
                llista.add(p);
            }
            resul.close();
            sentencia.close();
        }catch (SQLException e){
            System.out.println("Problemes per consultar productes...");
            e.printStackTrace();
        }
        return llista;
    }

    public ArrayList<Comanda> consultaCom(String consulta) throws SQLException{
        ArrayList<Comanda> llista = new ArrayList<Comanda>();
        try{
            Statement sentencia = getConnexio().createStatement();
            ResultSet resul = sentencia.executeQuery(consulta);
            
            while (resul.next()) {
                Comanda p = new Comanda(resul.getInt(1), resul.getInt(2),
                        resul.getDate(3), resul.getInt(4));
                llista.add(p);
            }
            resul.close();
            sentencia.close();
        }catch (SQLException e){
            System.out.println("Problemes per consultar la comanda...");
            e.printStackTrace();
        }
        return llista;
    }
    
    public ArrayList<Venda> consultaVen(String consulta) throws SQLException{
        ArrayList<Venda> llista = new ArrayList<Venda>();
        try{
            Statement sentencia = getConnexio().createStatement();
            ResultSet resul = sentencia.executeQuery(consulta);
            
            while (resul.next()) {
                Venda p = new Venda(resul.getInt(1), resul.getInt(2),
                        resul.getDate(3), resul.getInt(4));
                llista.add(p);
            }
            resul.close();
            sentencia.close();
        }catch (SQLException e){
            System.out.println("Problemes per consultar la comanda...");
            e.printStackTrace();
        }
        return llista;
    }
    
    
    public int obtenirUltimID(String taula) throws SQLException{
        int id = 0;
        String consulta = "SELECT MAX(ID) FROM "+taula;
        try{
            Statement sentencia = getConnexio().createStatement();
            ResultSet resul = sentencia.executeQuery(consulta);
            resul.next();
            id = resul.getInt(1)+1;
            resul.close();
            sentencia.close();
        }catch(SQLException e){
            System.out.println("Problema a l'obtenir maxim id en taula "+taula);
            e.printStackTrace();
        }
        return id;
    }
    
    
    public int inserirVenda(Venda ven) throws SQLException{
        int files = 0;
        String sql = "INSERT INTO VENDA VALUES(?,  ?, ?, ?)";
        try{
            PreparedStatement sentencia = getConnexio().prepareStatement(sql);
            sentencia.setInt(1, ven.getNumvenda());
            sentencia.setInt(2, ven.getIdproducte());
            sentencia.setDate(3, (Date) ven.getDatavenda());
            sentencia.setInt(4, ven.getQuantitat());
            sentencia.close();
        }catch (SQLException e){
            System.out.println("Error al insertar una venda");
            e.printStackTrace();
        }
        return files;
    }
    
    public int inserirProducte(Producte prod) throws SQLException{
        int files = 0;
        String sql = "INSERT INTO PRODUCTE VALUES(?, ?, ?, ?, ?)";
        try{
            PreparedStatement sentencia = getConnexio().prepareStatement(sql);
            sentencia.setString(1, prod.getDescripcio());
            sentencia.setInt(2, prod.getIdproducte());
            sentencia.setInt(3, prod.getStockactual());
            sentencia.setInt(4, prod.getStockminim());
            sentencia.setFloat(5, prod.getPvp());
            sentencia.close();
        }catch (SQLException e){
            System.out.println("Error al insertar una venda");
            e.printStackTrace();
        }
        return files;
    }
    
    
    public int actualitzarStock(Producte producte, int quantitat, java.sql.Date dataActual) throws SQLException{
        Comanda comanda = new Comanda();
        producte.addPropertyChangeListener(comanda);
        int nouStock = producte.getStockactual() - quantitat;
        producte.setStockactual(nouStock);
        
        int files = 0;
        String sql = "";
        PreparedStatement sentencia = null;
        try{
            if (comanda.isDemana()) {
                System.out.println("Fer comanda en producte: ");
                String taula = "COMANDA";
                int numComanda = obtenirUltimID(taula);
                comanda.setQuantitat(quantitat);
                comanda.setIdProducte(producte.getIdproducte());
                comanda.setNumComanda(numComanda);
                comanda.setData(dataActual);
                    sql="INSERT INTO COMANDA VALUES(?, ?, ?, ?)";
                sentencia = getConnexio().prepareStatement(sql);
                sentencia.setInt(1, comanda.getNumComanda());
                sentencia.setInt(2, comanda.getIdProducte());
                sentencia.setDate(3, (Date)comanda.getData());
                sentencia.setInt(4, comanda.getQuantitat());
                files = sentencia.executeUpdate();
                System.out.println("Comanda "+numComanda+" Generat...");
                files = -1;
                sentencia.close();
            } else{
                sql = "UPDATE PRODUCTE SET STOOCKACTUAL = ? WHERE ID = ?";
                sentencia = getConnexio().prepareStatement(sql);
                sentencia.setInt(1, producte.getStockactual());
                sentencia.setInt(1, producte.getIdproducte());
                files = sentencia.executeUpdate();
                System.out.println("Estoc Actualitzat...");
                sentencia.close();
            }
        }catch(SQLException e){
            System.out.println("Problemes al actualitzar l'estoc...");
            files = -1;
        }
        return files;
    }
    
    public Producte consultarUnProducte(int idproducte){
        Producte p = null;
        String consulta = "SELECT * FROM PRODUCTE WHERE ID = ?";
        try{
            PreparedStatement sentencia =
                                getConnexio().prepareStatement(consulta);
            sentencia.setInt(1, idproducte);
            ResultSet resul = sentencia.executeQuery();
            resul.next();
            p=new Producte(resul.getString(1),resul.getInt(2),
                    resul.getInt(3), resul.getInt(4), resul.getFloat(5));
            resul.close();
            sentencia.close();
        }catch(SQLException e){
            System.out.println("Problemes al obtenir el producte amb Id: "
                    +idproducte);
            e.printStackTrace();
        }
        return p;
    }
}
