package metier.forms;

import metier.Verifiable;
import presentation.modele.Admin;
import presentation.modele.Banque;
import presentation.modele.Utilisateur;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

public class LoginFormValidator implements Verifiable {

    private static  final  String  CHAMP_LOGIN = "login" , CHAMP_PASS = "pass";

private Map<String , String > errors;
private Banque banque;
private String ConnectionMsg;


public LoginFormValidator()
{
    errors = new HashMap<>();
}
    public LoginFormValidator(Banque banque)
    {
        errors = new HashMap<>();
        this.banque = banque;
    }
    public Banque getBanque() {
        return banque;
    }
    public void setBanque(Banque banque) {
        this.banque = banque;
    }
    public Map<String, String> getErrors() {
        return errors;
    }
    public void setErrors(String fieldName , String erreurMsg) {
        errors.put(fieldName , erreurMsg);
    }
    public String getConnectionMsg() {
        return ConnectionMsg;
    }
    public void setConnectionMsg(String connectionMsg) {
        ConnectionMsg = connectionMsg;
    }
    // Fonctions de Verifications

    private void verifierLogin(String login) throws FormException {
        if(login != null && login.trim().length() !=0)
        {
            if(login.trim().length() <4)
                throw new FormException("Login must have more than 4 chars !!!!");
            else
            {
                if(login.matches(""))
                {
                    // For email regex
                    throw new FormException("Login format invalide !!!!");
                }
            }

        }
        else
        {
            throw new FormException("Login is required !!!!");
        }


    }

    private void verifierPass(String pass) throws FormException {
        if(pass != null && pass.trim().length() !=0)
        {
            if(pass.trim().length() <4)
                throw new FormException("Pass must have more than 4 chars !!!!");

        }
        else
        {
            throw new FormException("Pass is required !!!!");
        }


    }

    private void validerLogin(String login)
    {
        try
        {
            verifierLogin(login);


        }
        catch (FormException ex)
        {
           setErrors(CHAMP_LOGIN , ex.getMessage());

        }
    }

    private void validerPass(String pass)
    {
        try
        {
            verifierLogin(pass);


        }
        catch (FormException ex)
        {
            setErrors(CHAMP_PASS , ex.getMessage());
        }
    }

    public Utilisateur validerSession(String login , String pass)
    {
        Utilisateur session = null;

        validerLogin(login);
        validerPass(pass);

        if(errors.isEmpty())
        {
            if(isAdmin(login , pass))
            {
                session = Admin.getInstance();
                setConnectionMsg("Secc");
            }
            else
            {
                if(isClient(login , pass , banque) == null)
                {

                }
            }

        }
        else
        {

        }




        return session;
    }

    void afficher()
    {

    }

}
