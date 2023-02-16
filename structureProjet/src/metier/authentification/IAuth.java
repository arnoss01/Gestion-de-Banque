
package metier.authentification;

import presentation.modele.Utilisateur;

public interface IAuth {

    Utilisateur seConnecter(String Login , String mdp);

    void SeDéconnecter();

    boolean IsAdmin(String log , String password);
}
