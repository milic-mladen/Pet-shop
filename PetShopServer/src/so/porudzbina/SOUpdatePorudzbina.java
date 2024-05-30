/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.porudzbina;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Porudzbina;
import domen.StavkaPorudzbine;
import java.util.Date;
import so.AbstractSO;

/**
 *
 * @author User
 */
public class SOUpdatePorudzbina extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Porudzbina)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Porudzbina!");
        }
        
        Porudzbina p = (Porudzbina) ado;

        if (!p.getDatumDostave().after(new Date())) {
            throw new Exception("Datum dostave mora biti posle danasnjeg datuma!");
        }

        if(p.getStavkePorudzbine().isEmpty()){
            throw new Exception("Porudzbina mora imati barem jednu stavku!");
        }
        
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
        
        Porudzbina p = (Porudzbina) ado;
        DBBroker.getInstance().delete(p.getStavkePorudzbine().get(0));
        
        for (StavkaPorudzbine stavkaPorudzbine : p.getStavkePorudzbine()) {
            DBBroker.getInstance().insert(stavkaPorudzbine);
        }
        
    }

}
