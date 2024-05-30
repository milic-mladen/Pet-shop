/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.porudzbina;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Porudzbina;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author User
 */
public class SOGetAllPorudzbina extends AbstractSO {

    private ArrayList<Porudzbina> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Porudzbina)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Porudzbina!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> porudzbine = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Porudzbina>) (ArrayList<?>) porudzbine;
    }

    public ArrayList<Porudzbina> getLista() {
        return lista;
    }

}
