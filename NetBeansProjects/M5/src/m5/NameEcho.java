package m5;

/**
 *
 * @author Adrian
 */
public class NameEcho {

    private String nom1, nom2, nomRestant, cognom, noms, nomResultant, nomComplet;

    public String Names(String name) {
        System.out.print("Enter your name:");
        this.nomComplet = name.trim();
        if (nomComplet.indexOf(" ") > -1) {
            nom1 = nomComplet.substring(0, nomComplet.indexOf(" "));
            nomRestant = nomComplet.substring(nomComplet.indexOf(" ") + 1);
            if (nomRestant.indexOf(" ") > -1) {
                nom2 = nomRestant.substring(0, nomRestant.indexOf(" "));
                cognom = nomRestant.substring(nomRestant.indexOf(" ") + 1);
                noms = nom1.concat(" " + nom2);
                nomResultant = noms.concat(" " + cognom.toUpperCase());
                System.out.println(nomResultant);
                return nomResultant;
            } else {
                nomResultant = nom1.concat(" " + nomRestant.toUpperCase());
                System.out.println(nomResultant);
                return nomResultant;
            }
        } else {
            System.out.println(nomComplet);
            return nomComplet;
        }
    }
}
