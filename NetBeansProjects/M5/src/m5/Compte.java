package m5;

/**
 *
 * @author Adrian
 */
public class Compte {

    private int saldo;
    private String contrasenya;

    // Constructor
    public Compte(int saldo, String contrasenya) {
        this.saldo = saldo;
        this.contrasenya = contrasenya;
    }

    // Rebem els de diners
    public int diposita(int pasta) {
        this.saldo = this.saldo + pasta;

        return saldo;
    }

// Si la contrasenya és correcta i tenim prou diners, els restem del saldo i
// retornem el valor de la pasta que hem retirat.
// Si no tenim prou saldo, retornem un -1.
// Si la contrasenya no és correcte, retornem un -2.
    public int retira(int pasta, String cs) {
        if (this.contrasenya.equals(cs) && pasta <= this.saldo) {
            this.saldo = this.saldo - pasta;
            return this.saldo;
        } else if (pasta > this.saldo) {
            return -1;
        } else {
            return -2;
        }
    }

    // Retornem el saldo
    public int getSaldo() {
        return saldo;
    }

    // Retornem la contrasenya
    public String getContrasenya() {
        return this.contrasenya;
    }
}
