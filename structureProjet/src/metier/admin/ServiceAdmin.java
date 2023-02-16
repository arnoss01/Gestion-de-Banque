package metier.admin;

import dao.IDao;
import dao.daoFiles.ClientDao;
import presentation.modele.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ServiceAdmin implements IServiceAdmin{
    private Banque banque;
    public ServiceAdmin(Banque banque){this.banque=banque;}
    private Scanner clavier = new Scanner(System.in);
    IDao dao = new ClientDao();

    @Override
    public Client nouveauClient() {
        System.out.println("Saisir nom de client");
        String nom = clavier.nextLine();

        System.out.println("Saisir prenom de client");
        String prenom = clavier.nextLine();

        System.out.println("Saisir email de client");
        String email = clavier.nextLine();

        System.out.println("Saisir numero de telephone de client");
        String tel = clavier.nextLine();

        System.out.println("Saisir cin de client");
        String cin = clavier.nextLine();

        System.out.println("Saisir login de client");
        String log = clavier.nextLine();

        System.out.println("Saisir password de client");
        String pass = clavier.nextLine();

        System.out.println("[H] Homme  | [F] Femme");
        String sexe = clavier.nextLine();
        if(sexe.equals("H"))
        {
            Client c = new Client(log , pass , nom , prenom ,email ,cin , tel, Sexe.HOMME);
            banque.getClientsDeBanque().add(c);

            dao.save(c);
            return c;
        } else if (sexe.equals("F")) {
            Client c = new Client(log , pass , nom , prenom ,email ,cin , tel,Sexe.FEMME);
            banque.getClientsDeBanque().add(c);
            dao.save(c);

            return c;
        }
        return null;}

    @Override
    public Client nouveauCompteClientExistant() {
        System.out.println("=> voulez-vous entrer l'id de client");
        String numero = clavier.nextLine();
        Client client =chercherClientParId(Long.parseLong(numero));
        int indice = banque.getClientsDeBanque().indexOf(client);
        if(client!=null)
        {
            Compte compte = new Compte();
            System.out.println("Solde initial [n - o]\n");
            String i = clavier.nextLine();
            if(i.toLowerCase().equals("o"))
            {
                System.out.println("Saisir solde");
                String solde = clavier.nextLine();
                compte.setSolde(Double.parseDouble(solde));
                compte.setLog(TypeLog.CREATION , "Compte "+ compte.getNumeroCompte()+"nouvellement créer avec un solde initial de "+ Double.parseDouble(solde) + " DH",compte);
            }
            compte.setLog(TypeLog.CREATION , "Compte "+ compte.getNumeroCompte()+"nouvellement créer ",compte);
            compte.setPropriétaire(banque.getClientsDeBanque().get(indice));
            banque.getClientsDeBanque().get(indice).getComptesClient().add(compte);
            return banque.getClientsDeBanque().get(indice);
        }
        return null;
    }

    @Override
    public Client chercherClientParId(Long id) {
        for (Client client: banque.getClientsDeBanque()) {
            if(client.getId().equals(id))
            return client;
        }
        return null;
    }

    @Override
    public List<Client> chercherClientParNom(String nom) {

        return banque.getClientsDeBanque()
                .stream()
                .filter(c -> c.getNom().equals(nom))
                .toList();
    }

    @Override
    public List<Client> chercherClientParPrénom(String prenom) {
        return banque.getClientsDeBanque()
            .stream()
            .filter(c -> c.getPrenom().equals(prenom))
            .toList();
    }

    @Override
    public Client chercherClientParCin(String cin) {
        return banque.getClientsDeBanque()
                .stream()
                .filter(c -> c.getCin().equals(cin))
                .findFirst().orElse(null);
    }

    @Override
    public Client chercherClientParEmail(String email) {
        return banque.getClientsDeBanque()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Compte chercherCompteParId(Long idCompte) {
        for (Client client: banque.getClientsDeBanque()) {
            for (Compte compte:client.getComptesClient()
                 ) {
    if(compte.getNumeroCompte().equals(idCompte))
    {
        return compte;
    }
            }
        }
        return null;
    }

    @Override
    public List<Compte> chercherCompteParSolde(double solde) {
        List<Compte> comptes = new ArrayList<>();
        for (Client client: banque.getClientsDeBanque()) {
            for (Compte compte:client.getComptesClient()
            ) {
                if(compte.getSolde().equals(solde))
                {
                    comptes.add(compte);
                    return comptes;
                }
            }
        }
        return null;
    }

    @Override
    public List<Compte> chercherCompteParDateCreation(LocalDateTime date) {
        List<Compte> comptes = new ArrayList<>();
        for (Client client: banque.getClientsDeBanque()) {
            for (Compte compte:client.getComptesClient()
            ) {
                if(compte.getDateCreation().equals(date))
                {
                    comptes.add(compte);
                    return comptes;
                }
            }
        }
        return null;
    }

    @Override
    public List<Compte> chercherCompteParPropriétaire(Client propriétaire) {
        List<Compte> comptes = new ArrayList<>();
        banque.getClientsDeBanque().forEach(client ->
                client.getComptesClient().forEach(compte ->
                        {
                            if (compte.getPropriétaire().equals(propriétaire))
                                comptes.add(compte);
                        }
                        )
                );
        return comptes;
    }

    @Override
    public Client modifierClient(String filtre) {
        System.out.println("======================================*=");
        System.out.println("|=*=     Saisir l'id du client     =*=|");
        String id = clavier.nextLine();
        System.out.println("======================================*=");
        Client client = chercherClientParId(Long.parseLong(id));
        int a = banque.getClientsDeBanque().indexOf(client);
        do {

        switch (filtre.toLowerCase())
        {
            case "a":
                System.out.println("=> Saisir votre nouveau nom\n");
                String nom = clavier.nextLine();
                client.setNom(nom);
                System.out.println("=> Saisir votre nouveau prenom\n");
                String pren = clavier.nextLine();
                client.setPrenom(pren);
                break;
            case "b":
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
                    System.out.println("=*=> Votre mot de passe actuel devient :"+ mdp); break;
                }
                break;
            case "c":
                String log ="";
                do{
                    System.out.println("=*=> Nouveau login :");
                    log = clavier.nextLine();
                    if(log.length()<4){
                        System.out.println("\u001B[31m"+"Le login doit contenir plus que 4 caractères"+"\u001B[0m");
                    }
                }while(log.length()<4);
                client.setLogin(log);
                break;

            case "d":
                System.out.println("=> Saisir votre nouveau numero de telephone\n");
                String tel = clavier.nextLine();
                client.setTel(tel);
            case "e":
            {
                System.out.print("Le client a "+client.getComptesClient().size()+" compte");
                if(client.getComptesClient().size()>0)
                {
                    System.out.println("veuillez choisir un");
                    client.getComptesClient().forEach(compte -> {
                        System.out.println(compte.getNumeroCompte());
                    });
                    String num = clavier.nextLine();
                    for (Compte compte:client.getComptesClient()) {
                        if(compte.getNumeroCompte().equals(num))
                        {
                            String c="";
                            do {
                                System.out.println("|=*= Tapez [ 1 ] pour modifier solde");
                                System.out.println("|=*= Tapez [ 2 ] pour Sortire");
                                c = clavier.nextLine();
                                System.out.println("[=*= Solde actuel est "+compte.getSolde());
                                System.out.println("Saisir le montant a ajout ou retirer en utilisant [ + / - ]");
                                String solde = clavier.nextLine();
                                compte.setSolde(compte.getSolde()+ Double.parseDouble(solde));
                            }while (Integer.parseInt(c)!=2);
                        }
                    }
                }
                break;
            }
        }
        }while (!filtre.toLowerCase().equals("q"));
        banque.getClientsDeBanque().set(a , client);
        return banque.getClientsDeBanque().get(a);
    }

    @Override
    public boolean supprimerClient(Long id) {
        if(banque.getClientsDeBanque().remove(chercherClientParId(id)))
            return true;
        return false;
    }

    @Override
    public boolean supprimerCompte(Long numCompte) {
        String id = "b-co00" + numCompte;
        int i = -1;
        int i1 =-1;
        for (Client c : banque.getClientsDeBanque())
        {
            for(Compte co : c.getComptesClient())
            {
                    if(co.getNumeroCompte().equals(id))
                    {
                        i = c.getComptesClient().indexOf(co);
                        i1 = banque.getClientsDeBanque().indexOf(c);
                    }
            }
        }
            return banque.getClientsDeBanque().get(i1).getComptesClient().remove(chercherCompteParId(numCompte));
    }

    @Override
    public TableauDeBord calculerEtAfficherStatistiques() {
        TableauDeBord tableauDeBord = new TableauDeBord();
        tableauDeBord.setNombreTotaleClient((long)banque.getClientsDeBanque().size());
        banque.getClientsDeBanque().forEach(client -> {
            if (client.getSexe()==Sexe.FEMME)
                tableauDeBord.setTotalClientsFemme(tableauDeBord.getTotalClientsFemme()+1);
        });
        tableauDeBord.setTotaleClientsHomme(tableauDeBord.getNombreTotaleClient()- tableauDeBord.getTotalClientsFemme());
        tableauDeBord.setMaxSolde(trierComptesParSolde().get(trierComptesParSolde().size()-1).getSolde());
        tableauDeBord.setMinSolde(trierComptesParSolde().get(0).getSolde());
        tableauDeBord.setNomClientLePlusRiche(trierComptesParSolde().get(trierComptesParSolde().size()-1).getPropriétaire().getNomComplet());
        return tableauDeBord;
    }

    @Override
    public List<Client> trierClientParNom() {
        Collections.sort(banque.getClientsDeBanque(), (e1, e2)-> e2.getNom().compareTo(e1.getNom()));
       return banque.getClientsDeBanque();
    }

    @Override
    public List<Client> trierClientParCin() {
        Collections.sort(banque.getClientsDeBanque(), (e1, e2)-> e2.getCin().compareTo(e1.getCin()));
        return banque.getClientsDeBanque();
    }

    @Override
    public List<Client> trierClientParEmail() {
        Collections.sort(banque.getClientsDeBanque(), (e1, e2)-> e2.getEmail().compareTo(e1.getEmail()));
        return banque.getClientsDeBanque();
    }
    @Override
    public List<Client> trierClientParSoldeCompte() {
        //Collections.sort(banque.getClientsDeBanque(), (e1, e2)-> e2.gets().compareTo(e1.getEmail()));
        return banque.getClientsDeBanque();
    }

    @Override
    public List<Compte> trierComptesParSolde() {
        List<Compte> comptes = new ArrayList<>();
        banque.getClientsDeBanque().forEach(client ->
        {
            client.getComptesClient().forEach(compte -> {
                comptes.add(compte);
            });
        });


        Collections.sort(comptes,(c1,c2)->c1.getSolde().compareTo(c2.getSolde()));


        return comptes;
    }

    @Override
    public List<Compte> trierComptesParDateDeCreation() {
        List<Compte> comptes = new ArrayList<>();
        banque.getClientsDeBanque().forEach(client ->
        {
            client.getComptesClient().forEach(compte -> {
                comptes.add(compte);
            });
        });
        Collections.sort(comptes,(c1,c2)->c1.getDateCreation().compareTo(c2.getDateCreation()));


        return comptes;
    }

    @Override
    public List<Compte> trierComptesParNomPropriétaire() {
        List<Compte> comptes = new ArrayList<>();
        banque.getClientsDeBanque().forEach(client ->
        {
            client.getComptesClient().forEach(compte -> {
                comptes.add(compte);
            });
        });
        Collections.sort(comptes,(c1,c2)->c1.getPropriétaire().getNom().compareTo(c2.getPropriétaire().getNom()));
        return comptes;
    }
}
