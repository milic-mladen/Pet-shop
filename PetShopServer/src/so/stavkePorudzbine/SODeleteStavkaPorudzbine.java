/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.stavkePorudzbine;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.StavkaPorudzbine;
import so.AbstractSO;

/**
 *
 * @author User
 */
public class SODeleteStavkaPorudzbine extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StavkaPorudzbine)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkaPorudzbine!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
