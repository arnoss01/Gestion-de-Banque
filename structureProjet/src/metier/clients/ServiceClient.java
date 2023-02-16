package metier.clients;

import dao.IDao;
import dao.daoFiles.AccountDao;
import dao.daoFiles.ClientDao;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.TypeLog;

import java.time.LocalDate;
import java.util.Scanner;

public class ServiceClient implements IServiceClient{
    private Client client;
    private Compte c;
    public ServiceClient(Client client){
        this.client=client;
    }
    Scanner clavier = new Scanner(System.in);
    @Override
    public boolean versement() {
        if(c!=null)
        {
            System.out.println("=> veuillez entrer la somme que vous voulez verser");
            String montant = clavier.nextLine();
            int a = client.getComptesClient().indexOf(c);
        client.getComptesClient().get(a).setSolde(c.getSolde()+ Integer.parseInt(montant));
            client.getComptesClient().get(a).setLog(TypeLog.VERSEMENT , "=> Montant "+montant+" DH versé en votre faveur",c);
            System.out.println("| Voulez vous un ticket ? (oui/non)");
            String affirmation = clavier.nextLine();
            if(affirmation.toLowerCase().equals("oui"))
                afficherTicket();
            return true;

        }

        return false;
    }

    @Override
    public boolean retrait() {
        if(c!=null)
        {
            int a = client.getComptesClient().indexOf(c);
            System.out.println("=> veuillez entrer la somme que vous voulez retirer");
            String montant = clavier.nextLine();
            if( client.getComptesClient().get(a).getSolde()>=Double.parseDouble(montant) ){
            client.getComptesClient().get(a).setSolde(c.getSolde()- Integer.parseInt(montant));
            client.getComptesClient().get(a).setLog(TypeLog.RETRAIT , "retrait de: "+montant+" DH",c);
            System.out.println("| Voulez vous un ticket ? (oui/non)");
            String affirmation = clavier.nextLine();

            if( affirmation.toLowerCase().equals("oui"))
                afficherTicket();
            return true;
        }
            System.out.println("\u001B[31m"+"=*=*=*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("\u001B[31m"+"|==    Opération echoué  [SOLDE INSUFFISANT ]    ==|");
            System.out.println("\u001B[31m"+"=*=*=*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*=*=*=*="+"\u001B[0m");
        }

        return false;
    }

    @Override
    public boolean retrait(int choixRetrait) {
        do{
        switch (choixRetrait) {
            case 1: {
               int a = client.getComptesClient().indexOf(c);
               if(client.getComptesClient().get(a).getSolde()>=100){
               client.getComptesClient().get(a).setSolde( client.getComptesClient().get(a).getSolde()-100);
                   System.out.println("Montant 100.0 DH est retiré du compte "+ c.getNumeroCompte());
                   client.getComptesClient().get(a).setLog(TypeLog.RETRAIT , "retrait de: 100 DH",c);
                   System.out.println("| Voulez vous un ticket ? (oui/non)");
                   String affirmation = clavier.nextLine();

                   if( affirmation.toLowerCase().equals("oui"))
                       afficherTicket();

                   return true;
               }
                System.out.println("\u001B[31m"+"=*=*=*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
                System.out.println("\u001B[31m"+"|==    Opération echoué  [SOLDE INSUFFISANT ]    ==|");
                System.out.println("\u001B[31m"+"=*=*=*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*=*=*=*="+"\u001B[0m");
                choixRetrait=6;
            }
            case 2: {
                int a = client.getComptesClient().indexOf(c);
                if(client.getComptesClient().get(a).getSolde()>=200){
                    client.getComptesClient().get(a).setSolde( client.getComptesClient().get(a).getSolde()-200);
                    System.out.println("Montant 200.0 DH est retiré du compte "+ c.getNumeroCompte());
                    client.getComptesClient().get(a).setLog(TypeLog.RETRAIT , "retrait de: 200 DH",c);
                    System.out.println("| Voulez vous un ticket ? (oui/non)");
                    String affirmation = clavier.nextLine();

                    if( affirmation.toLowerCase().equals("oui"))
                        afficherTicket();
                    return true;
                }
                System.out.println("\u001B[31m"+"=*=*=*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
                System.out.println("\u001B[31m"+"|==    Opération echoué  [SOLDE INSUFFISANT ]    ==|");
                System.out.println("\u001B[31m"+"=*=*=*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*=*=*=*="+"\u001B[0m");
                choixRetrait=6;
            }
            case 3: {
                int a = client.getComptesClient().indexOf(c);
                if(client.getComptesClient().get(a).getSolde()>=500){
                    client.getComptesClient().get(a).setSolde( client.getComptesClient().get(a).getSolde()-500);
                    System.out.println("Montant 500.0 DH est retiré du compte "+ c.getNumeroCompte());
                    client.getComptesClient().get(a).setLog(TypeLog.RETRAIT , "retrait de: 500 DH",c);
                    System.out.println("| Voulez vous un ticket ? (oui/non)");
                    String affirmation = clavier.nextLine();

                    if( affirmation.toLowerCase().equals("oui"))
                        afficherTicket();
                    return true;
                }
                System.out.println("\u001B[31m"+"=*=*=*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
                System.out.println("\u001B[31m"+"|==    Opération echoué  [SOLDE INSUFFISANT ]     ==|");
                System.out.println("\u001B[31m"+"=*=*=*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*=*=*=*="+"\u001B[0m");
                choixRetrait=6;
            }
            case 4: {
                int a = client.getComptesClient().indexOf(c);
                if(client.getComptesClient().get(a).getSolde()>=1000){
                    client.getComptesClient().get(a).setSolde( client.getComptesClient().get(a).getSolde()-1000);
                    System.out.println("Montant 1000.0 DH est retiré du compte "+ c.getNumeroCompte());
                    client.getComptesClient().get(a).setLog(TypeLog.RETRAIT , "retrait de: 1000 DH",c);
                    System.out.println("| Voulez vous un ticket ? (oui/non)");
                    String affirmation = clavier.nextLine();

                    if( affirmation.toLowerCase().equals("oui"))
                        afficherTicket();
                    return true;
                }
                System.out.println("\u001B[31m"+"=*=*=*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
                System.out.println("\u001B[31m"+"|==    Opération échouée  [SOLDE INSUFFISANT ]    ==|");
                System.out.println("\u001B[31m"+"=*=*=*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*=*=*=*="+"\u001B[0m");
                choixRetrait=6;
            }
            case 5: {
                int a = client.getComptesClient().indexOf(c);
                if(client.getComptesClient().get(a).getSolde()>=2000){
                    client.getComptesClient().get(a).setSolde( client.getComptesClient().get(a).getSolde()-2000);
                    System.out.println("Montant 2000.0 DH est retiré du compte "+ c.getNumeroCompte());
                    client.getComptesClient().get(a).setLog(TypeLog.RETRAIT , "retrait de: 2000 DH",c);
                    System.out.println("| Voulez vous un ticket ? (oui/non)");
                    String affirmation = clavier.nextLine();

                    if( affirmation.toLowerCase().equals("oui"))
                        afficherTicket();
                    return true;
                }
                System.out.println("\u001B[31m"+"|=*=*=*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*=*=*=*");
                System.out.println("\u001B[31m"+"|==    Opération echoué  [SOLDE INSUFFISANT ]    ==|");
                System.out.println("\u001B[31m"+"|=*=*=*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*=*=*=*"+"\u001B[0m");
                choixRetrait=6;
            }
            case 6: {
              if(retrait())
                  return true;
                choixRetrait=6;
            break;
            }

    }}
            while(choixRetrait!=7);
        return false;
    }

    @Override
    public boolean virement() {
        if(c!=null) {
            int a = client.getComptesClient().indexOf(c);
            String conf ="";
            do {
                System.out.println("|=*=*=*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*=*=*=*=|");
                System.out.print("|==  Entrez le numero de beneficiaire                |>");
                String num = clavier.nextLine();
                System.out.println("\n|=*=*=*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*=*=*=*=|");
                for (Compte compte:client.getComptesClient()) {
                    if(compte.getNumeroCompte().equals(num))
                    {
                        System.out.println("Compte cible        : "+ compte.getNumeroCompte());
                        System.out.println("beneficiaire        : "+ compte.getPropriétaire().getNomComplet());
                        System.out.println("|=*=*=*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*=*=*=*=|");
                        System.out.println("|=* Valider en tapant (oui) s'il s'agit bien du compte cible=|");
                        System.out.println("|=* Annuler en tapant (non) pour modifier le compte=|");
                        conf = clavier.nextLine();
                        if(conf.toLowerCase().equals("oui"))
                        {
                            System.out.println("\n|=*=*=*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*=*=*=*=|");
                            System.out.print("|=*=> Entrez le montant (en DH) du virement :");
                            String prix = clavier.nextLine();
                            System.out.println("\n|=*=*=*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*=*=*=*=|");
                            if(client.getComptesClient().get(a).getSolde()>=Double.parseDouble(prix))
                            {
                                client.getComptesClient().get(a).setSolde(client.getComptesClient().get(a).getSolde()-Double.parseDouble(prix));
                                compte.setSolde(compte.getSolde()+Double.parseDouble(prix));
                                System.out.println("Montant "+Double.parseDouble(prix)+" est retiré du compte"+c.getNumeroCompte());
                                System.out.println("= Montant "+ Double.parseDouble(prix)+ " est versé dans le compte "+num);
                             //   System.out.println("= Virement  effecuté d'un montant de "+Double.parseDouble(prix)+" du compte "+ c.getNumeroCompte() + " vers " + num );
                                client.getComptesClient().get(a).setLog(TypeLog.RETRAIT , "Virement  effecuté d'un montant de "+Double.parseDouble(prix)+" du compte "+ c.getNumeroCompte() + " vers " + num , c );
                                System.out.println("| Voulez vous un ticket ? (oui/non)");
                                String affirmation = clavier.nextLine();

                                if( affirmation.toLowerCase().equals("oui"))
                                    afficherTicket();
                                return true;
                            }
                            else
                                return false;
                        }
                    }
                }
            }while (conf.toLowerCase().equals("non"));
            return true;
        }
        return false;
    }

    @Override
    public boolean modifierProfile(int choixModification) {
        switch (choixModification) {
            case 1: {
                System.out.println("=> Saisir votre nouveau nom\n");
                String nom = clavier.nextLine();
                client.setNom(nom);
                System.out.println("=> Saisir votre nouveau prenom\n");
                String pren = clavier.nextLine();
                client.setPrenom(pren);
                return true;
            }
            case 2: {
                System.out.println("=*=> Votre mot de passe actuel est :"+ client.getMotDePasse());
                System.out.print("=*=> Voulez vous vraiment le modifier ? (oui/non)");
                String affirmation = clavier.nextLine();
                if(affirmation.equals("oui")){
                    String mdp ="";
                    do{
                        System.out.println("=*=> Nouveau mot de passe :");
                         mdp = clavier.nextLine();
                         if(mdp.length()<4){
                             System.out.println("\u001B[31m"+"Le mot de passe doit contenir plus que 4 caractères"+"\u001B[0m");
                         }
                    }while(mdp.length()<4);
                    String mdp2 = "";
                    do{
                        System.out.print("=*=> Confirmez mot de passe:");
                         mdp2 = clavier.nextLine();
                         if(mdp.equals(mdp2)==false){
                             System.out.println("\u001B[31m"+"??? mot de passe et confirmation ne correspondent pas ??? "+"\u001B[0m");
                             System.out.println("\u001B[31m"+"???                  Réessayer                      ???"+"\u001B[0m");
                         }
                    }while(mdp.equals(mdp2)==false);
                    client.setMotDePasse(mdp);
                    System.out.println("=*=> Votre mot de passe actuel devient :"+ mdp); return true;
                }
                return false;

            }
            case 3: {
                System.out.println("=> Saisir votre nouveau email\n");
                String email = clavier.nextLine();
                client.setEmail(email);
                return true;
            }
            case 4: {
                System.out.println("=> Saisir votre nouveau mot de passe\n");
                String mdp = clavier.nextLine();
                client.setMotDePasse(mdp);

                return true;
            }
            case 5: {
                System.out.println("=> Saisir votre nouveau numero de telephone\n");
                String tel = clavier.nextLine();
                client.setTel(tel);
                return true;
            }
            default:return false;
        }


    }

    @Override
    public void dernièresOpérations() {
        int indice = client.getComptesClient().indexOf(c);
        System.out.println(client.getComptesClient().get(indice).getLogs().get(client.getComptesClient().get(indice).getLogs().size()-1).toString());
    }

    @Override
    public Double afficherSolde() {
        if(c!=null)
            return client.getComptesClient().get(client.getComptesClient().indexOf(c)).getSolde();
        return 0.0;
    }

    @Override
    public Compte choisirCompte() {

        client.getComptesClient().forEach(compte -> System.out.println(compte.getNumeroCompte()));

        System.out.println("=> voulez-vous entrer le numero du compte\n");
        String numero = "b-co00";
        System.out.print(numero);
         numero += clavier.nextLine();
        for (Compte compte: client.getComptesClient()) {
            if(compte.getNumeroCompte().equals(numero))
            {
                c=compte;
                return compte;
            }

        }
        return null;

    }

    @Override
    public void afficherTicket() {
        System.out.println("****************************************************");
        System.out.println("=*=*=*=*=*=*=*=*= TICKET =*=*=*=*=**=*=*=*=*=*=*=*=*");
        System.out.println("|=*=> Dernière Opération :");
        dernièresOpérations();
        System.out.println("****************************************************");
        System.out.println("|=*=> Solde actuel du compte :"+afficherSolde()+" DH");

    }


}
