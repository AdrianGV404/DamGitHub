package ElsMeysBeans;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author Adrian
 */
public class Producte implements Serializable {

    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";

    private String descripcio;
    private int idproducte;
    private int stockactual;
    private int stockminim;
    private float pvp;
    private PropertyChangeSupport propertySupport;

    public Producte(){
    propertySupport = new PropertyChangeSupport(this);
    }
    
    public Producte(String descripcio, int idproducte, int stockactual, int stockminim, float pvp) {
        propertySupport = new PropertyChangeSupport(this);
        this.descripcio = descripcio;
        this.idproducte = idproducte;
        this.stockactual = stockactual;
        this.stockminim = stockminim;
        this.pvp = pvp;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getIdproducte() {
        return idproducte;
    }

    public void setIdproducte(int idproducte) {
        this.idproducte = idproducte;
    }

    public int getStockactual() {
        return stockactual;
    }
    
    public void setStockactual(int valorNou) {
        int valorAnterior = stockactual;
        stockactual = valorNou;
        if (stockactual < getStockminim()) {
            propertySupport.firePropertyChange("stockactual", valorAnterior, stockactual);
        }
    }

    public int getStockminim() {
        return stockminim;
    }

    public void setStockminim(int stockminim) {
        this.stockminim = stockminim;
    }

    public float getPvp() {
        return pvp;
    }

    public void setPvp(float pvp) {
        this.pvp = pvp;
    }

    public void addPropertyChangeListener(PropertyChangeListener comanda) {
        propertySupport.addPropertyChangeListener(comanda);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

}
