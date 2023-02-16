package metier.clients;

import metier.authentification.IServiceIHM;

public interface IServiceIHMClient extends IServiceIHM {

    void menuModification();
    void menuRetrait();
    void menuInformations();
    
}
