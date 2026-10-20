package ma.projet.entity;

import javax.persistence.*;

@Entity
public class LigneCommandeProduit {

    @EmbeddedId
    private LigneCommandeProduitPK pK;

    private int quantite;

    @ManyToOne
    @JoinColumn(name = "produit", insertable = false, updatable = false)
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "commande", insertable = false, updatable = false)
    private Commande commande;

    public LigneCommandeProduit() {
    }

    public LigneCommandeProduit(Produit produit, Commande commande, int quantite) {
        this.pK = new LigneCommandeProduitPK(produit.getId(), commande.getId());
        this.produit = produit;
        this.commande = commande;
        this.quantite = quantite;
    }

    public LigneCommandeProduitPK getpK() {
        return pK;
    }

    public void setpK(LigneCommandeProduitPK pK) {
        this.pK = pK;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
}
