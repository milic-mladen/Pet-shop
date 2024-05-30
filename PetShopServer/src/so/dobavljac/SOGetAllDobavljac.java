/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.dobavljac;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Dobavljac;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author User
 */
public class SOGetAllDobavljac extends AbstractSO {

    private ArrayList<Dobavljac> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Dobavljac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Dobavljac!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> dobavljaci = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Dobavljac>) (ArrayList<?>) dobavljaci;
    }

    public ArrayList<Dobavljac> getLista() {
        return lista;
    }

}
