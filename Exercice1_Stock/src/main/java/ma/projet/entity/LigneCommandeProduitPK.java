package ma.projet.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class LigneCommandeProduitPK implements Serializable {

    private long produit;
    private long commande;

    public LigneCommandeProduitPK() {
    }

    public LigneCommandeProduitPK(long produit, long commande) {
        this.produit = produit;
        this.commande = commande;
    }

    public long getProduit() {
        return produit;
    }

    public void setProduit(long produit) {
        this.produit = produit;
    }

    public long getCommande() {
        return commande;
    }

    public void setCommande(long commande) {
        this.commande = commande;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LigneCommandeProduitPK)) return false;
        LigneCommandeProduitPK k = (LigneCommandeProduitPK) o;
        return produit == k.produit && commande == k.commande;
    }

    @Override
    public int hashCode() {
        return Objects.hash(produit, commande);
    }
}
