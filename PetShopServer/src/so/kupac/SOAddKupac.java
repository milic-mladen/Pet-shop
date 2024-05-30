/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.kupac;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Kupac;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author User
 */
public class SOAddKupac extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Kupac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Kupac!");
        }
        
        Kupac k = (Kupac) ado;
        
        ArrayList<Kupac> kupci = (ArrayList<Kupac>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        
        for (Kupac kupac : kupci) {
            if(k.getKontaktTelefon().equals(kupac.getKontaktTelefon())){
                throw new Exception("Vec postoji kupac s tim kontaktom!");
            }
        }
        
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
