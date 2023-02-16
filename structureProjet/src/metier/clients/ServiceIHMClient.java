package metier.clients;

import metier.authentification.IAuth;
import metier.authentification.ServiceAuth;

import java.util.Scanner;

public class ServiceIHMClient implements IServiceIHMClient{
    private ServiceClient serviceClient;
    private Scanner clavier = new Scanner(System.in);
    public ServiceIHMClient(ServiceClient serviceClient){this.serviceClient=serviceClient;}
    @Override
    public void menuGlobal() {
        String choix = "";
        do {
            System.out.println("===> M E N U  [PRICIPALE]");
            System.out.println("======================================*=");
            System.out.println("==                                                             ==");
            System.out.println("== Tapez 1 pour faire un versement                             ==");
            System.out.println("== Tapez 2 pour faire un retrait                               ==");
            System.out.println("== Tapez 3 pour faire un virement                              ==");
            System.out.println("== Tapez 4 pour modifier votre profile                         ==");
            System.out.println("== Tapez 5 pour afficher des informations du compte            ==");
            System.out.println("== Tapez 6 pour afficher le solde actuel du compte             ==");
            System.out.println("== Tapez 7 pour changer de compte                              ==");
            System.out.println("== Tapez 8 se deconnecter                   ==");
            System.out.println("==                                                             ==");
            System.out.println("=====================================*=");
            System.out.print("Entrez votre choix :   ");
            choix = clavier.nextLine();
            switch (Integer.parseInt(choix))
            {
                case 1:
                    serviceClient.versement();
                    break;
                case 2:
                    menuRetrait();
                    break;

                case 3:
                    serviceClient.virement();
                    break;

                case 4:
                    menuModification();
                    break;

                case 5:
                    menuInformations();
                    break;

                case 6:
                    System.out.println("=*=>  S O L D E  A C T U E L  : "+serviceClient.afficherSolde());
                    break;

                case 7:
                    serviceClient.choisirCompte();
                    break;
            }
        }while (Integer.parseInt(choix)!=8);



    }
    @Override
    public void menuModification() {
        String choix = "";
        do {
            System.out.println("===> M E N U  [MODIFICATION]");
            System.out.println("======================================*=");
            System.out.println("==                                                             ==");
            System.out.println("== Tapez 1 pour modifier votre nom et prenom                   ==");
            System.out.println("== Tapez 2 pour modifier votre mot de passe                    ==");
            System.out.println("== Tapez 3 pour modifier votre e-mail                          ==");
            System.out.println("== Tapez 4 pour modifier votre n° cin                          ==");
            System.out.println("== Tapez 5 pour modifier votre n° de téléphone                 ==");
            System.out.println("== Tapez 6 pour retourner au menu principale                   ==");
            System.out.println("==                                                             ==");
            System.out.println("=====================================*=");
            System.out.print("Entrez votre choix :   ");
            choix = clavier.nextLine();
            serviceClient.modifierProfile(Integer.parseInt(choix));
        }while (Integer.parseInt(choix)!=6);
    }

    @Override
    public void menuRetrait() {
        String choix ="";
        do{
            System.out.println("===> M E N U  [MODIFICATION]");
            System.out.println("======================================*=");
            System.out.println("==                                                             ==");
            System.out.println("= Tapez 1 pour faire un retrait de 100 DH                      ==");
            System.out.println("= Tapez 2 pour faire un retrait de 200 DH                      ==");
            System.out.println("= Tapez 3 pour faire un retrait de 500 DH                      ==");
            System.out.println("= Tapez 4 pour faire un retrait de 1000 DH                     ==");
            System.out.println("= Tapez 5 pour faire un retrait de 2000 DH                     ==");
            System.out.println("= Tapez 6 pour faire un retrait d'un autre montant             ==");
            System.out.println("= Tapez 7 pour retourner au menu principale                   ==");
            System.out.println("==                                                             ==");
            System.out.println("=====================================*=");
            System.out.print("Entrez votre choix :   ");
            choix = clavier.nextLine();
            serviceClient.retrait(Integer.parseInt(choix));
        }while(Integer.parseInt(choix)!=7);
    }

    @Override
    public void menuInformations() {
        String choix = "";
        do {
            System.out.println("===> M E N U  [INFORMATIONS]");
            System.out.println("======================================*=");
            System.out.println("==                                                             ==");
            System.out.println("== Tapez 1 afficher votre profile                              ==");
            System.out.println("== Tapez 2 afficher le solde de votre compte                   ==");
            System.out.println("== Tapez 3 afficher les dernieres operations du compte         ==");
            System.out.println("== Tapez 4 afficher les operation d'aujourd'hui                ==");
            System.out.println("== Tapez 5 afficher l'archive des virements                    ==");
            System.out.println("== Tapez 6 pour retourner au menu principale                   ==");
            System.out.println("==                                                             ==");
            System.out.println("=====================================*=");
            System.out.print("Entrez votre choix :   ");
            choix = clavier.nextLine();
            switch (Integer.parseInt(choix))
            {


                case 2:
                    System.out.println("=*=>  S O L D E  A C T U E L  : "+serviceClient.afficherSolde());
                    break;

                case 3:
                    serviceClient.dernièresOpérations();
                    break;



            }
        }while (Integer.parseInt(choix)!=6);
    }
}
