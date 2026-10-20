package ma.projet;

import ma.projet.config.AppConfig;
import ma.projet.entity.Employe;
import ma.projet.entity.EmployeTache;
import ma.projet.entity.Projet;
import ma.projet.entity.Tache;
import ma.projet.service.EmployeService;
import ma.projet.service.ProjetService;
import ma.projet.service.TacheService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainApp {

    static Date d(String s) throws Exception {
        return new SimpleDateFormat("yyyy-MM-dd").parse(s);
    }

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        EmployeService employeService = context.getBean(EmployeService.class);
        ProjetService projetService = context.getBean(ProjetService.class);
        TacheService tacheService = context.getBean(TacheService.class);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // --- Données de test ---
        Employe e1 = new Employe("Alami", "Ahmed", "0612345678");
        Employe e2 = new Employe("Rami", "Sara", "0698765432");
        employeService.addEmploye(e1);
        employeService.addEmploye(e2);

        Projet p = new Projet("Gestion de stock", d("2013-01-14"), d("2013-06-30"), e1);
        projetService.addProjet(p);

        Tache t1 = new Tache("Analyse", d("2013-02-10"), d("2013-02-20"), 800, p);
        Tache t2 = new Tache("Conception", d("2013-03-10"), d("2013-03-15"), 1500, p);
        Tache t3 = new Tache("Développement", d("2013-04-10"), d("2013-04-25"), 3000, p);
        tacheService.addTache(t1);
        tacheService.addTache(t2);
        tacheService.addTache(t3);

        tacheService.addEmployeTache(new EmployeTache(e2, t1, d("2013-02-10"), d("2013-02-20")));
        tacheService.addEmployeTache(new EmployeTache(e2, t2, d("2013-03-10"), d("2013-03-15")));
        tacheService.addEmployeTache(new EmployeTache(e1, t3, d("2013-04-10"), d("2013-04-25")));

        // --- Affichages ---
        System.out.println("--- Tâches réalisées par un employé ---");
        System.out.println("Tâches réalisées par " + e2.getNom() + " " + e2.getPrenom() + " :");
        for (Tache t : employeService.getTachesRealisees(e2)) {
            System.out.println(t.getId() + "  " + t.getNom());
        }

        System.out.println("--- Projets gérés par un employé ---");
        System.out.println("Projets gérés par " + e1.getNom() + " " + e1.getPrenom() + " :");
        for (Projet pr : employeService.getProjetsGeres(e1)) {
            System.out.println(pr.getId() + "  " + pr.getNom());
        }

        System.out.println("--- Tâches planifiées d'un projet ---");
        System.out.println("Tâches planifiées du projet " + p.getNom() + " :");
        for (Tache t : projetService.getTachesPlanifiees(p)) {
            System.out.println(t.getId() + "  " + t.getNom() + "  "
                    + sdf.format(t.getDateDebut()) + "  " + sdf.format(t.getDateFin()));
        }

        System.out.println("--- Tâches réalisées avec dates réelles ---");
        System.out.println("Projet : " + p.getId() + "   Nom : " + p.getNom()
                + "   Date début : " + sdf.format(p.getDateDebut()));
        System.out.println("Liste des tâches :");
        System.out.println("Num  Nom  Date Début Réelle  Date Fin Réelle");
        for (EmployeTache et : projetService.getTachesRealisees(p)) {
            System.out.println(et.getTache().getId() + "  " + et.getTache().getNom() + "  "
                    + sdf.format(et.getDateDebutReelle()) + "  " + sdf.format(et.getDateFinReelle()));
        }

        System.out.println("--- Tâches dont le prix > 1000 DH ---");
        for (Tache t : tacheService.getTachesPrixSup1000()) {
            System.out.println(t.getId() + "  " + t.getNom() + "  " + t.getPrix() + " DH");
        }

        System.out.println("--- Tâches réalisées entre deux dates ---");
        for (EmployeTache et : tacheService.getTachesRealiseesEntreDates(d("2013-03-01"), d("2013-04-30"))) {
            System.out.println(et.getTache().getNom() + "  " + sdf.format(et.getDateDebutReelle())
                    + "  " + sdf.format(et.getDateFinReelle()));
        }

        context.close();
    }
}
