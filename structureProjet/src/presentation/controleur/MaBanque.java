package presentation.controleur;

import dao.daoFiles.AccountDao;
import dao.daoFiles.ClientDao;
import dao.daoFiles.FileBasePaths;
import metier.authentification.IAuth;
import metier.authentification.ServiceAuth;
import metier.clients.IServiceClient;
import metier.clients.ServiceClient;
import metier.clients.ServiceIHMClient;
import presentation.modele.Banque;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.Sexe;

import java.util.*;

public class MaBanque {
        public static IAuth loginService;
        public static void main(String[] args) {
/*                Banque maBanque
                        = new Banque(   "BP",
                                        "Hassan Rabat",
                                        "212535224433",
                                        "bp@banquePop.ma");

                Scanner clavier = new Scanner(System.in);
                //loginService = new ServiceAuth(maBanque, clavier);
                loginService.seConnecter();
                clavier.close();
*/
                Banque maBanque
                        = new Banque(   "BP",
                        "Hassan Rabat",
                        "212535224433",
                        "bp@banquePop.ma");
/*
                Client client = new Client();
                Compte compte = new Compte();
                Compte compte1 = new Compte();
                Compte compte2 = new Compte();
                compte.setPropriétaire(client);
                compte1.setPropriétaire(client);
                compte2.setPropriétaire(client);
                System.out.println(compte1.getNumeroCompte());
                System.out.println(compte.getNumeroCompte());
                List<Compte> comptes = new ArrayList<>();
                comptes.add(compte);
                comptes.add(compte1);
                comptes.add(compte2);
                client.setComptesClient(comptes);
                client.setMotDePasse("azerty");
                        ServiceClient iServiceClient = new ServiceClient(client);
                 iServiceClient.choisirCompte();
                iServiceClient.versement();
                //iServiceClient.retrait(5);
                System.out.println(iServiceClient.afficherSolde());
               // ServiceIHMClient serviceIHMClient = new ServiceIHMClient(iServiceClient);
             //   serviceIHMClient.menuRetrait();
                iServiceClient.virement();
                System.out.println(iServiceClient.afficherSolde());*/
          /*
                Client client = new Client();
                client.setLogin("anas");
                client.setMotDePasse("anas");
                List<Client> clients = new ArrayList<>();
                Compte compte = new Compte();
                Compte compte1 = new Compte();
                Compte compte2 = new Compte();
                compte.setPropriétaire(client);
                compte1.setPropriétaire(client);
                compte2.setPropriétaire(client);
                System.out.println(compte1.getNumeroCompte());
                compte1.setSolde(10000.0);
                List<Compte> comptes = new ArrayList<>();
                comptes.add(compte);
                comptes.add(compte1);
                comptes.add(compte2);
                client.setComptesClient(comptes);
                clients.add(client);
                maBanque.setClientsDeBanque(clients);
                IAuth iAuth = new ServiceAuth(maBanque);
                iAuth.seConnecter();
                List<String> strings = new ArrayList<>();*/
                List<Compte> c = new AccountDao().findAll();
                c.forEach(compte ->
                                System.out.println(compte.getPropriétaire().toString())
                        );

        }



}