package metier.authentification;

import dao.daoFiles.ClientDao;
import metier.InteractiveConsole;
import metier.admin.IServiceAdmin;
import metier.admin.IServiceIHMAdmin;
import metier.admin.ServiceAdmin;
import metier.admin.ServiceIHMAdmin;
import metier.clients.IServiceClient;
import metier.clients.IServiceIHMClient;
import metier.clients.ServiceClient;
import metier.clients.ServiceIHMClient;
import presentation.modele.Admin;
import presentation.modele.Banque;
import presentation.modele.Client;
import presentation.modele.Utilisateur;

import java.util.Scanner;

public class ServiceAuth implements IAuth{


    @Override
    public Utilisateur seConnecter(String login, String mdp) {
    if(!IsAdmin(login , mdp))
    {   Client c = new ClientDao().findByLoginAndPassword(login , mdp);
        if(c == null)
            return null;
        return c;
    }
    return Admin.getInstance();
    }

    @Override
    public void SeDéconnecter() {

      //  seConnecter();
    }

    @Override
    public boolean IsAdmin(String log , String password) {
        if(log.equals(Admin.getInstance().getLogin()) && password.equals(Admin.getInstance().getMotDePasse()))
            return true;
        return false;
    }


}
