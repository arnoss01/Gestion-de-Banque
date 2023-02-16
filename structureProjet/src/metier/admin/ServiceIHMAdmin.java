package metier.admin;

import metier.authentification.IAuth;
import metier.authentification.IServiceIHM;
import metier.authentification.ServiceAuth;

import java.util.Scanner;

public class ServiceIHMAdmin implements IServiceIHMAdmin {
    private ServiceAdmin serviceAdmin;
    private Scanner clavier = new Scanner(System.in);

    public ServiceIHMAdmin(ServiceAdmin serviceAdmin) {
        this.serviceAdmin = serviceAdmin;
    }

    @Override
    public int menuModification() {
        String choix = "";
        do {
            System.out.println("|=*= Tapez [ a ] pour modifier le nom et le nom");
            System.out.println("|=*= Tapez [ b ] pour modifier Modifier mot de pass");
            System.out.println("|=*= Tapez [ c ] pour modifier le login");
            System.out.println("|=*= Tapez [ d ] pour modifier téléphone");
            System.out.println("|=*= Tapez [ e ] pour modifier les compte");
            System.out.println("|=*= Tapez [ q ] pour sortir");
            choix = clavier.nextLine();
            serviceAdmin.modifierClient(choix);
        } while (!choix.toLowerCase().equals("q"));

        return 0;

    }

    @Override
    public int menuRecherche() {
        return 0;
    }

    @Override
    public int menuInformations() {
        return 0;
    }

    @Override
    public int menuAjout() {
        String choix = "";
        do {
            System.out.println("===> M E N U  [D'AJOUT]");
            System.out.println("======================================*=");
            System.out.println("|=*= Tapez [ 1 ] pour ajouter un nouveau client");
            System.out.println("|=*= Tapez [ 2 ] pour ajouter un nouveau compte a un client");
            System.out.println("|=*= Tapez [ 3 ] pour retourner au menu principale                   ==");
            choix = clavier.nextLine();
            switch (Integer.parseInt(choix)) {
                case 1:
                    serviceAdmin.nouveauClient();
                    break;
                case 2:
                    serviceAdmin.nouveauCompteClientExistant();
                    break;
            }
        } while (Integer.parseInt(choix) != 3);
        return Integer.parseInt(choix);
    }

    @Override
    public int menuSuppression() {
        String choix = "";
        do {
            System.out.println("===> M E N U  [D'AJOUT]");
            System.out.println("======================================*=");
            System.out.println("|=*= Tapez [ 1 ] pour supprimer un client");
            System.out.println("|=*= Tapez [ 2 ] pour supprimer un compte ");
            System.out.println("|=*= Tapez [ 3 ] pour retourner au menu principale                   ==");
            choix = clavier.nextLine();
            switch (Integer.parseInt(choix)) {
                case 1:
                    System.out.println("Saisir le numero du client");
                    String id = clavier.nextLine();
                    if (serviceAdmin.supprimerClient(Long.parseLong(id))) {
                        System.out.println("Client bien été supprimer");
                    }
                    System.out.println("Clinet n'existe pas");
                    break;
                case 2:
                    System.out.println("Saisir le numero du Compte");
                     id = clavier.nextLine();
                    if (serviceAdmin.supprimerCompte(Long.parseLong(id))) {
                        System.out.println("Compte bien été supprimer");
                    }
                    System.out.println("Compte n'existe pas");
            }
        } while (Integer.parseInt(choix) != 3);
        return Integer.parseInt(choix);
    }

    @Override
    public int tableauDeBord() {
        return 0;
    }

    @Override
    public int menuTrie() {
        String choix = "";
        do {
            System.out.println("===> M E N U  [PRICIPALE]");
            System.out.println("======================================*=");
            System.out.println("==                                                              ==");
            System.out.println("== Tapez 1 pour trier client par nom                              ==");
            System.out.println("== Tapez 2 pour trier client par cin                       ==");
            System.out.println("== Tapez 3 pour trier client par email                             ==");
            System.out.println("== Tapez 4 pour  trier client par solde compte           ==");
            System.out.println("== Tapez 5 pour  trier comptes par solde            ==");
            System.out.println("== Tapez 6 pour  trier comptes par date de creation            ==");
            System.out.println("== Tapez 7 pour trier comptes par nom propriétaire                                 ==");
            System.out.println("== Tapez 8 pour se déconnecter                                  ==");
            System.out.println("==                                                              ==");
            System.out.println("================================================================*=");
            System.out.print("Entrez votre choix :   ");
            choix = clavier.nextLine();
            switch (Integer.parseInt(choix)) {

                case 1:
                    serviceAdmin.trierClientParNom().forEach(
                            client -> System.out.println(client.toString())
                    );
                    break;
                case 2:
                    serviceAdmin.trierClientParCin().forEach(
                            client -> System.out.println(client.toString())
                    );
                    break;
                case 3:
                    serviceAdmin.trierClientParEmail().forEach(
                            client -> System.out.println(client.toString())
                    );
                    break;

                case 4:
                    serviceAdmin.trierClientParSoldeCompte().forEach(
                            client -> System.out.println(client.toString())
                    );
                    break;
                case 5:
                    serviceAdmin.trierComptesParSolde().forEach(
                            compte -> System.out.println(compte.toString())
                    );
                    break;


                case 6:
                    serviceAdmin.trierComptesParDateDeCreation().forEach(
                            compte -> System.out.println(compte.toString())
                    );
                    break;
                case 7:
                    serviceAdmin.trierComptesParNomPropriétaire().forEach(
                            compte -> System.out.println(compte.toString())
                    );
                    break;
            }
        } while (Integer.parseInt(choix) != 8);
        return 0;
    }

    @Override
    public int menuComptabilité() {

        return 0;
    }

    @Override
    public void menuGlobal() {
        String choix = "";
        do {
            System.out.println("===> M E N U  [PRICIPALE]");
            System.out.println("======================================*=");
            System.out.println("==                                                              ==");
            System.out.println("== Tapez 1 pour le [SERVICE CRUD]                               ==");
            System.out.println("== Tapez 2 pour  le [SERVICE INFORMATION]                       ==");
            System.out.println("== Tapez 3 pour  le [SERVICE TRIE]                              ==");
            System.out.println("== Tapez 4 pour  le [TABLEAU DE BORD - STATISTIQUES]            ==");
            System.out.println("== Tapez 5 pour se déconnecter                                  ==");
            System.out.println("==                                                              ==");
            System.out.println("================================================================*=");
            System.out.print("Entrez votre choix :   ");
            choix = clavier.nextLine();
            switch (Integer.parseInt(choix)) {
                case 1:
                    break;
                case 2: menuInformations();
                case 3: menuTrie();
                case 4:menuComptabilité();
            }
        }
        while (Integer.parseInt(choix) != 5) ;

    }
}

