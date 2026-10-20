package ma.projet;

import ma.projet.config.AppConfig;
import ma.projet.entity.Categorie;
import ma.projet.entity.Commande;
import ma.projet.entity.LigneCommandeProduit;
import ma.projet.entity.Produit;
import ma.projet.service.CategorieService;
import ma.projet.service.CommandeService;
import ma.projet.service.ProduitService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainApp {

    static Date d(String s) throws Exception {
        return new SimpleDateFormat("yyyy-MM-dd").parse(s);
    }

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CategorieService categorieService = context.getBean(CategorieService.class);
        ProduitService produitService = context.getBean(ProduitService.class);
        CommandeService commandeService = context.getBean(CommandeService.class);

        // --- Données de test ---
        Categorie c1 = new Categorie("C1", "Ordinateurs");
        Categorie c2 = new Categorie("C2", "Accessoires");
        categorieService.addCategorie(c1);
        categorieService.addCategorie(c2);

        Produit p1 = new Produit("ES12", 120, c1);
        Produit p2 = new Produit("ZR85", 100, c1);
        Produit p3 = new Produit("EE85", 200, c2);
        Produit p4 = new Produit("AB10", 50, c2);
        produitService.addProduit(p1);
        produitService.addProduit(p2);
        produitService.addProduit(p3);
        produitService.addProduit(p4);

        Commande cmd1 = new Commande(d("2013-03-14"));
        Commande cmd2 = new Commande(d("2013-05-20"));
        commandeService.addCommande(cmd1);
        commandeService.addCommande(cmd2);

        commandeService.addLigne(new LigneCommandeProduit(p1, cmd1, 7));
        commandeService.addLigne(new LigneCommandeProduit(p2, cmd1, 14));
        commandeService.addLigne(new LigneCommandeProduit(p3, cmd1, 5));
        commandeService.addLigne(new LigneCommandeProduit(p4, cmd2, 3));

        // --- Affichages ---
        System.out.println("--- Produits par catégorie ---");
        System.out.println("Catégorie : " + c1.getLibelle());
        for (Produit p : produitService.getProduitsParCategorie(c1)) {
            System.out.println(p.getReference() + "  " + p.getPrix() + " DH");
        }

        System.out.println("--- Produits commandés entre deux dates ---");
        for (Produit p : produitService.getProduitsCommandesEntreDates(d("2013-03-01"), d("2013-04-01"))) {
            System.out.println(p.getReference() + "  " + p.getPrix() + " DH");
        }

        System.out.println("--- Produits d'une commande ---");
        System.out.println("Commande : " + cmd1.getId()
                + "   Date : " + new SimpleDateFormat("dd MMMM yyyy").format(cmd1.getDate()));
        System.out.println("Liste des produits :");
        System.out.println("Référence   Prix   Quantité");
        for (LigneCommandeProduit l : commandeService.getLignesDeLaCommande(cmd1)) {
            System.out.println(l.getProduit().getReference() + "   " + l.getProduit().getPrix()
                    + " DH   " + l.getQuantite());
        }

        System.out.println("--- Produits dont le prix > 100 DH ---");
        for (Produit p : produitService.getProduitsPrixSup100()) {
            System.out.println(p.getReference() + "  " + p.getPrix() + " DH");
        }

        context.close();
    }
}
