package ElsMeysBeans;

import java.beans.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Adrian
 */
public class Comanda implements Serializable, PropertyChangeListener {

    private int numComanda;
    private int idProducte;
    private Date data;
    private int quantitat;
    private boolean demana;
    public boolean isDemana;

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        System.out.println("Valor anterior: "+pce.getOldValue());
        System.out.println("Valor actual: "+pce.getNewValue());
        setDemana(true);
    }

    public Comanda(){}

    public Comanda(int numComanda, int idProducte, java.sql.Date data,
            int quantitat){
        this.numComanda = numComanda;
        this.idProducte = idProducte;
        this.data = data;
        this.quantitat = quantitat;
    }

    public int getNumComanda() {
        return numComanda;
    }

    public void setNumComanda(int numComanda) {
        this.numComanda = numComanda;
    }

    public int getIdProducte() {
        return idProducte;
    }

    public void setIdProducte(int idProducte) {
        this.idProducte = idProducte;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    public boolean isDemana() {
        return demana;
    }

    public void setDemana(boolean demana) {
        this.demana = demana;
    }
}