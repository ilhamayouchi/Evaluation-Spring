package ma.projet;

import ma.projet.config.AppConfig;
import ma.projet.entity.Femme;
import ma.projet.entity.Homme;
import ma.projet.entity.Mariage;
import ma.projet.service.FemmeService;
import ma.projet.service.HommeService;
import ma.projet.service.MariageService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainApp {

    static Date d(String s) throws Exception {
        return new SimpleDateFormat("yyyy-MM-dd").parse(s);
    }

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        HommeService hommeService = context.getBean(HommeService.class);
        FemmeService femmeService = context.getBean(FemmeService.class);
        MariageService mariageService = context.getBean(MariageService.class);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        List<Femme> femmes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Femme f = new Femme("NOMF" + i, "Prenom" + i, "060000000" + (i % 10), "Adresse " + i, d((1960 + i) + "-01-01"));
            femmeService.addFemme(f);
            femmes.add(f);
        }
        List<Homme> hommes = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Homme h = new Homme("NOMH" + i, "Prenom" + i, "070000000" + i, "Adresse " + i, d((1955 + i) + "-01-01"));
            hommeService.addHomme(h);
            hommes.add(h);
        }

        Homme h1 = hommes.get(0);
        mariageService.addMariage(new Mariage(h1, femmes.get(0), d("1990-09-03"), d("1995-09-03"), 4));
        mariageService.addMariage(new Mariage(h1, femmes.get(1), d("1995-09-03"), null, 2));
        mariageService.addMariage(new Mariage(h1, femmes.get(2), d("2000-02-02"), null, 3));
        mariageService.addMariage(new Mariage(h1, femmes.get(3), d("2002-05-10"), null, 0));
        mariageService.addMariage(new Mariage(hommes.get(1), femmes.get(0), d("1997-01-01"), null, 1));
        mariageService.addMariage(new Mariage(hommes.get(2), femmes.get(4), d("1999-06-15"), d("2003-06-15"), 2));

        //Affichages
        System.out.println("-Liste des femmes ");
        for (Femme f : femmeService.getAllFemmes()) {
            System.out.println(f.getNom() + " " + f.getPrenom());
        }

        System.out.println("- Femme la plus âgée ");
        Femme plusAgee = femmeService.getFemmeLaPlusAgee();
        System.out.println(plusAgee.getNom() + " " + plusAgee.getPrenom());

        System.out.println("- Épouses d'un homme entre deux dates ");
        System.out.println("Épouses de " + h1.getNom() + " " + h1.getPrenom() + " :");
        for (Femme f : hommeService.getEpousesEntreDates(h1, d("1990-01-01"), d("2001-01-01"))) {
            System.out.println(f.getNom() + " " + f.getPrenom());
        }

        System.out.println("-Nombre d'enfants d'une femme entre deux dates ");
        System.out.println(femmeService.getNbEnfantsEntreDates(femmes.get(0), d("1990-01-01"), d("2000-01-01")));

        System.out.println("- Femmes mariées deux fois ou plus ");
        for (Femme f : femmeService.getFemmesMarieesDeuxFoisOuPlus()) {
            System.out.println(f.getNom() + " " + f.getPrenom());
        }

        System.out.println("- Hommes mariés à quatre femmes entre deux dates ");
        System.out.println(mariageService.getNbHommesMariesQuatreFemmes(d("1990-01-01"), d("2005-01-01")));

        System.out.println("- Mariages d'un homme ");
        List<Mariage> mariages = mariageService.getMariagesDeHomme(h1);
        System.out.println("Nom : " + h1.getNom().toUpperCase() + " " + h1.getPrenom().toUpperCase());

        System.out.println("Mariages en cours :");
        int i = 1;
        for (Mariage m : mariages) {
            if (m.getDateFin() == null) {
                System.out.println(i++ + ". Femme : " + m.getFemme().getNom() + " " + m.getFemme().getPrenom()
                        + "   Date Début : " + sdf.format(m.getpK().getDateDebut())
                        + "   Nbr Enfants : " + m.getNbrEnfant());
            }
        }

        System.out.println("Mariages échus :");
        i = 1;
        for (Mariage m : mariages) {
            if (m.getDateFin() != null) {
                System.out.println(i++ + ". Femme : " + m.getFemme().getNom() + " " + m.getFemme().getPrenom()
                        + "   Date Début : " + sdf.format(m.getpK().getDateDebut())
                        + "   Date Fin : " + sdf.format(m.getDateFin())
                        + "   Nbr Enfants : " + m.getNbrEnfant());
            }
        }

        context.close();
    }
}
