package metier;

import presentation.modele.Admin;
import presentation.modele.Banque;
import presentation.modele.Client;

public interface Verifiable {



    default boolean isNumeric(String value){
        try {
            Integer.parseInt(value);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    default boolean isDecimal(String value){
        try {
            Double.parseDouble(value);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    default boolean isAdmin(String login , String pass){return false;}
    default Client isClient(String login , String pass , Banque banque){return null;}

}
